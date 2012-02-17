#! /bin/bash

# This script runs on Mac OS X ans is intended to 
# publishes the org.eclipse.cdt.cross.arm.gnu-updatesite site
# to the File Release System (FRS) folder on SourceForge

if [ $# -gt 0 ] && [ "$1" = "test" ]
then
  SF_FOLDER="Eclipse/updates-test"
  shift
else
  SF_FOLDER="Eclipse/updates"
fi

echo "Updating $SF_FOLDER"

if [ $# -gt 0 ] && [ "$1" = "dry" ]
then
  DRY="dry"
  shift
  echo "Dry run"
fi


SF_USER=ilg-ul
SF_DESTINATION="$SF_USER,gnuarmeclipse@frs.sourceforge.net:/home/frs/project/g/gn/gnuarmeclipse/$SF_FOLDER"
SOURCE_LIST="."

RSYNC_OPTS="-vrCt --exclude=scripts --exclude=.*"
RSYNC_OPTS+=" --delete"

if [ "$DRY" = "dry" ]
then
  RSYNC_OPTS+=" -n"
fi

(cd ..; rsync -e ssh $RSYNC_OPTS $SOURCE_LIST $SF_DESTINATION)