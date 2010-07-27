PRJNAME="gnuarmeclipse"
DESTDIR_WWW="/home/groups/g/gn/gnuarmeclipse/htdocs"
DESTDIR_UPDATE="$DESTDIR_WWW/updates"

if [ $# -ge 1 ]
then
    if [ $1 = 'test' ]
    then
       DESTDIR_UPDATE+="-test"
    fi
fi

URL_TRUNK="https://$PRJNAME.svn.sourceforge.net/svnroot/$PRJNAME/trunk"
URL_UPDATE="$URL_TRUNK/Eclipse/current/"
URL_UPDATE+="org.eclipse.cdt.cross.arm.gnu-updatesite"
URL_WWW="$URL_TRUNK/www/"
TDIR="/tmp/ilg-$$"
TDIR_UPDATE="/tmp/ilg-$$/updates"

echo $PRJNAME $URL_UPDATE $TDIR $DESTDIR_UPDATE

#exit

svn export $URL_WWW $TDIR
echo cp $TDIR/* $DESTDIR_WWW
cp -a $TDIR/* $DESTDIR_WWW
ls -l $DESTDIR_WWW

#exit

svn export $URL_UPDATE $TDIR_UPDATE
rm -rf $DESTDIR_UPDATE/*
mv $TDIR_UPDATE/* $DESTDIR_UPDATE

rm -rf $DESTDIR_UPDATE/.project
find $DESTDIR_UPDATE/* -type f -exec chmod 0664 {} \;
find $DESTDIR_UPDATE/* -type d -exec chmod 2775 {} \;
chgrp -R $PRJNAME $DESTDIR_UPDATE/*
rm -rf $TDIR
echo "Copied to $DESTDIR_UPDATE"

