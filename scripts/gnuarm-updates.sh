PRJNAME="gnuarmeclipse"
DESTDIR_WWW="/home/groups/g/gn/gnuarmeclipse/htdocs"
DESTDIR_UPDATE="$DESTDIR_WWW/updates"

ISTEST=''

if [ $# -ge 1 ]
then
    if [ $1 = 'test' ]
    then
       ISTEST='true'
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

function myCopy()
{
  rm -rf $1/*
  cp -a $TDIR_UPDATE/* $1

  rm -rf $1/.project
  find $1/* -type f -exec chmod 0664 {} \;
  find $1/* -type d -exec chmod 2775 {} \;
  chgrp -R $PRJNAME $1/*
  rm -rf $TDIR
  echo "Copied to $1"
}


if [ $ISTEST = 'true' ]
then
  myCopy $DESTDIR_UPDATE-test
else
  myCopy $DESTDIR_UPDATE 
  myCopy $DESTDIR_UPDATE-test 
fi

