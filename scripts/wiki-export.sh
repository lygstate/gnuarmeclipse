#!/bin/bash

# Mac OS X script to export the project wiki.
# https://sourceforge.net/apps/mediawiki/gnuarmeclipse/index.php?title=Special:AllPages
# https://sourceforge.net/apps/mediawiki/gnuarmeclipse/index.php?title=Special:NewFiles

CURL="curl --get --silent --show-error"
URL_BASE="http://sourceforge.net/apps/mediawiki/gnuarmeclipse"
URL_PAGES="$URL_BASE/index.php?title=Special:Export"
URL_FILES="$URL_BASE/nfs/project/g/gn/gnuarmeclipse"
WIKI_BASE="../MediaWiki"
#ECHOP="echo"
#ECHOF="echo"

PAGES="\
Features \
Change_log \
GNU_ARM_Eclipse_Plug-in \
Known_problems \
Plug-in_Installation \
How_to_build \
Main_Page \
Reporting_problems \
Toolchain_installation \
"

FILES="\
7/70/MediaWikiSidebarLogo.png \
c/ca/Extra-steps.png \
9/92/Project.png \
"

for s in $PAGES
do
  echo $s
  $ECHOP $CURL --output "$WIKI_BASE/$s.xml" "$URL_PAGES/$s"
done

for s in $FILES
do
  FNAME=`basename $s`
  echo $s
  $ECHOF $CURL --output "$WIKI_BASE/Files/$FNAME" "$URL_FILES/$s"
done

svn --verbose status $WIKI_BASE/*
