PRJNAME="gnuarmeclipse"
DESTDIR="/home/groups/g/gn/gnuarmeclipse/htdocs/updates"

if [ $# -ge 1 ]
then
    if [ $1 = 'test' ]
    then
       DESTDIR+="-test"
    fi
fi

URL="https://$PRJNAME.svn.sourceforge.net/svnroot/$PRJNAME/trunk/Eclipse/current/"
URL+="org.eclipse.cdt.cross.arm.gnu-updatesite"
TDIR="/tmp/ilg-$$"

echo $PRJNAME $URL $TDIR $DESTDIR

#exit

svn export $URL $TDIR
rm -rf $DESTDIR/*
mv $TDIR/* $DESTDIR

rm -rf $DESTDIR/.project
find $DESTDIR/* -type f -exec chmod 0664 {} \;
find $DESTDIR/* -type d -exec chmod 2775 {} \;
chgrp -R $PRJNAME $DESTDIR/*
rm -rf $TDIR
echo "Copied to $DESTDIR"

