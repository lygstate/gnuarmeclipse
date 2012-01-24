#! /bin/bash

ARCHIVE_NAME=$(grep "org.eclipse.cdt.cross.arm.gnu" ../site.xml | sed 's/.*features\/\(org.eclipse.cdt.cross.arm.gnu.*\).jar.*/\1/')

LOCAL_SOURCES="site.xml index.html content.jar artifacts.jar plugins features"
(cd ..; zip -v scripts/$ARCHIVE_NAME.zip $LOCAL_SOURCES)

