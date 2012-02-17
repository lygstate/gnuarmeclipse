#! /bin/bash

# Script to run on Mac OS X and pack the FRS distribution archive using the
# version published in the Eclipse/updates site

# It creates a temporary folder to download the update site

TMP_FOLDER=/tmp/gnuarmeclipse-publish
echo "Creating temporary folder $TMP_FOLDER"
mkdir -p $TMP_FOLDER
cd $TMP_FOLDER

rm -rf updates
echo "Fetching Eclipse updates site"
rsync -vrCt -e ssh ilg-ul,gnuarmeclipse@frs.sourceforge.net:/home/frs/project/g/gn/gnuarmeclipse/Eclipse/updates .

ARCHIVE_NAME=$(grep "org.eclipse.cdt.cross.arm.gnu" updates/site.xml | sed 's/.*features\/\(org.eclipse.cdt.cross.arm.gnu.*\).jar.*/\1/')
rm -f $ARCHIVE_NAME.zip

echo "Packing archive"
LOCAL_SOURCES="site.xml index.html content.jar artifacts.jar plugins/*.jar features/*.jar"
(cd updates; zip -v ../$ARCHIVE_NAME.zip $LOCAL_SOURCES)

ls -l $ARCHIVE_NAME.zip
