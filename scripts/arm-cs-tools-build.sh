#! /bin/bash

# This script generates the Code Sourcery ARM EABI Toolchain on MAC OS X 10.7
# The new toolchain is installed at
# "$HOME/Developer/Cross/arm-cs-tools-$MENTOR_RELEASE-$TODAY"

# The implementation uses James Sneyder's Mafefile, available from
# https://github.com/jsnyder/arm-eabi-toolchain

# The build process also requires some special packages, that will be
# installed in a non-system folder, to avoid poluting the system

# There are many releases for Code Sourcery, and many releases of jsnyder's
# Makefile, so we need to identify them propely.

# The MENTOR_RELEASE can be obtained from the Mentor URLs
# https://sourcery.mentor.com/sgpp/lite/arm/portal/release2032

# The Sourcery CodeBench Lite 2011.09-69, released 2011-12-19
#MENTOR_RELEASE=2032

# The Sourcery G++ Lite 2011.03-42, released 2011-05-02
MENTOR_RELEASE=1802

# Identify the James Sneyder Makefile Git version
JSNYDER_GIT_LONG_ID=706e73495baecc7959d1a6768dd74178788dcdc5
JSNYDER_GIT_SHORT_ID=706e734

# Useful when testing
#TEST="-test"

# ----------------------------------------------------------------------------- 

patch_makefile () {

# The quotes are used to avoid substitutions inside the string

cat << "END_OF_FILE" | patch
--- Makefile	2011-12-13 18:10:22.000000000 +0200
+++ ../../arm-cs-tools-Makefile-1802-706e734.mk	2012-01-15 18:21:31.000000000 +0200
@@ -1,23 +1,25 @@
 SHELL = /bin/bash
 TARGET = arm-none-eabi
 PREFIX ?= $(HOME)/arm-cs-tools/
-PROCS = 4
+PROCS ?= 4
+
+CS_BASE		?= 2011.03
+CS_REV 		?= 42
+GCC_VERSION 	?= 4.5
+MPC_VERSION 	?= 0.8.1
+SOURCE_PACKAGE	?= 8733
+BIN_PACKAGE	?= 8734
 
-CS_BASE		= 2011.03
-CS_REV 		= 42
-GCC_VERSION 	= 4.5
-MPC_VERSION 	= 0.8.1
 CS_VERSION 	= $(CS_BASE)-$(CS_REV)
 
 LOCAL_BASE 	= arm-$(CS_VERSION)-arm-none-eabi
 LOCAL_SOURCE 	= $(LOCAL_BASE).src.tar.bz2
 LOCAL_BIN 	= $(LOCAL_BASE)-i686-pc-linux-gnu.tar.bz2
-SOURCE_URL 	= http://sourcery.mentor.com/sgpp/lite/arm/portal/package8733/public/arm-none-eabi/$(LOCAL_SOURCE)
-BIN_URL 	= http://sourcery.mentor.com/sgpp/lite/arm/portal/package8734/public/arm-none-eabi/$(LOCAL_BIN)
-
+SOURCE_URL 	= http://sourcery.mentor.com/sgpp/lite/arm/portal/package$(SOURCE_PACKAGE)/public/arm-none-eabi/$(LOCAL_SOURCE)
+BIN_URL 	= http://sourcery.mentor.com/sgpp/lite/arm/portal/package$(BIN_PACKAGE)/public/arm-none-eabi/$(LOCAL_BIN)
 
-SOURCE_MD5_CHCKSUM = 7c302162ec813d039b8388bd7d2b4176
-BIN_MD5_CHECKSUM = b1bd1dcb1f922d815ba7fa8d0e6fcd37
+SOURCE_MD5_CHCKSUM ?= 7c302162ec813d039b8388bd7d2b4176
+BIN_MD5_CHECKSUM ?= b1bd1dcb1f922d815ba7fa8d0e6fcd37
 
 install-cross: cross-binutils cross-gcc cross-g++ cross-newlib cross-gdb
 install-deps: gmp mpfr mpc
@@ -150,14 +152,14 @@
 	pushd ../../gcc-$(GCC_VERSION)-$(CS_BASE) ; \
 	make clean ; \
 	popd ; \
-	../../gcc-$(GCC_VERSION)-$(CS_BASE)/configure --prefix=$(PREFIX) --target=$(TARGET) --enable-languages="c" --with-gnu-ld --with-gnu-as --with-newlib --disable-nls --disable-libssp --with-newlib --without-headers --disable-shared --disable-threads --disable-libmudflap --disable-libgomp --disable-libstdcxx-pch --disable-libunwind-exceptions --disable-libffi --enable-extra-sgxxlite-multilibs && \
+	../../gcc-$(GCC_VERSION)-$(CS_BASE)/configure --prefix=$(PREFIX) --target=$(TARGET) $(DEPENDENCIES) --enable-languages="c" --with-gnu-ld --with-gnu-as --with-newlib --disable-nls --disable-libssp --with-newlib --without-headers --disable-shared --disable-threads --disable-libmudflap --disable-libgomp --disable-libstdcxx-pch --disable-libunwind-exceptions --disable-libffi --enable-extra-sgxxlite-multilibs && \
 	$(MAKE) -j$(PROCS) && \
 	$(MAKE) installdirs install-target && \
 	$(MAKE) -C gcc install-common install-cpp install- install-driver install-headers
 
 cross-g++: cross-binutils cross-gcc cross-newlib gcc-$(GCC_VERSION)-$(CS_BASE) multilibbash
 	mkdir -p build/g++ && cd build/g++ && \
-	../../gcc-$(GCC_VERSION)-$(CS_BASE)/configure --prefix=$(PREFIX) --target=$(TARGET) --enable-languages="c++" --with-gnu-ld --with-gnu-as --with-newlib --disable-nls --disable-libssp --with-newlib --without-headers --disable-shared --disable-threads --disable-libmudflap --disable-libgomp --disable-libstdcxx-pch --disable-libunwind-exceptions --disable-libffi --enable-extra-sgxxlite-multilibs && \
+	../../gcc-$(GCC_VERSION)-$(CS_BASE)/configure --prefix=$(PREFIX) --target=$(TARGET) $(DEPENDENCIES) --enable-languages="c++" --with-gnu-ld --with-gnu-as --with-newlib --disable-nls --disable-libssp --with-newlib --without-headers --disable-shared --disable-threads --disable-libmudflap --disable-libgomp --disable-libstdcxx-pch --disable-libunwind-exceptions --disable-libffi --enable-extra-sgxxlite-multilibs && \
 	$(MAKE) -j$(PROCS) && \
 	$(MAKE) installdirs install-target && \
 	$(MAKE) -C gcc install-common install-cpp install- install-driver install-headers
END_OF_FILE
}

# ----------------------------------------------------------------------------- 

# Numeric representation of today, like 20110704
TODAY=`date "+%Y%m%d"`

BUILD_FOLDER="arm-cs-tools-$MENTOR_RELEASE-$TODAY"

mkdir -p "$BUILD_FOLDER"

cd $BUILD_FOLDER

# ----------------------------------------------------------------------------- 
# Get Homebrew, if not already present
# DO NOT USE the default folder and do not link to /usr/local, to avoid
# interferences with other libraries

BREW_FOLDER=/brew

if [ ! -d "$BREW_FOLDER/local" ]
then
  echo "Enter your sudo password to create the $BREW_FOLDER/local folder"
  sudo mkdir -p "$BREW_FOLDER/local"
  sudo chown -R $USER "$BREW_FOLDER/local"
  curl -Lsf http://github.com/mxcl/homebrew/tarball/master | tar xz --strip 1 -C$BREW_FOLDER/local
fi

BREW_HAS_MPFR=`$BREW_FOLDER/local/bin/brew list | grep mpfr`
if [ -z "BREW_HAS_MPFR" ]
then
  $BREW_FOLDER/local/bin/brew install mpfr gmp libmpc texinfo
else
  $BREW_FOLDER/local/bin/brew upgrade mpfr gmp libmpc texinfo
fi

echo "Homebrew installed packages"
$BREW_FOLDER/local/bin/brew list

if [ -z `$BREW_FOLDER/local/bin/brew list | grep mpfr` ]
then
  echo "Missing Homebrew mpfr, build not possible"
  exit 1
fi
# Homebrew should be fine now


# ----------------------------------------------------------------------------- 
# Get jsnyder's Makefile

JSNYDER_URL="https://github.com/jsnyder/arm-eabi-toolchain/zipball/$JSNYDER_GIT_LONG_ID"

JSNYDER_ZIP_FOLDER="jsnyder-arm-eabi-toolchain-$JSNYDER_GIT_SHORT_ID"
JSNYDER_ZIP_FILE="$JSNYDER_ZIP_FOLDER.zip"

if [ ! -d "$JSNYDER_ZIP_FOLDER" ]
then
  # Fetch James Sneyder archive
  if [ ! -f "$JSNYDER_ZIP_FILE" ]
  then
    # Fetch $JSNYDER_ZIP_FILE
    curl -L $JSNYDER_URL -o "$JSNYDER_ZIP_FILE"
  fi

  # Unpack $JSNYDER_ZIP_FILE
  unzip "$JSNYDER_ZIP_FILE"
  
  pushd "$JSNYDER_ZIP_FOLDER"
  cp -a Makefile Makefile.orig  

  if true
  then
    patch_makefile
    #diff "../../arm-cs-tools-Makefile-$MENTOR_RELEASE-$JSNYDER_GIT_SHORT_ID.mk" Makefile || exit 1
  else
    diff -u Makefile ../../arm-cs-tools-Makefile-$MENTOR_RELEASE-$JSNYDER_GIT_SHORT_ID.mk >../../arm-cs-tools-Makefile-$MENTOR_RELEASE-$JSNYDER_GIT_SHORT_ID.mk.patch
    exit 1
  fi
  popd
fi

# ----------------------------------------------------------------------------- 

# Keep different versions in different folders
TODAY_DESTINATION_FOLDER="$HOME/Developer/Cross/arm-cs-tools-$MENTOR_RELEASE-$TODAY"

# Append test
TODAY_DESTINATION_FOLDER+=$TEST

# Remove the destination folder, in case it exists
if [ -z "$TEST" ] && [ -d "$TODAY_DESTINATION_FOLDER" ]
then
  echo "Remove $TODAY_DESTINATION_FOLDER"
  rm -rf "$TODAY_DESTINATION_FOLDER"
fi

# ----------------------------------------------------------------------------- 
# Prepare make environment

# Define extra dependencies, in case they are not in the system path 
DEPENDENCY_DIR=$BREW_FOLDER/local
export DEPENDENCIES="--with-mpc=$DEPENDENCY_DIR --with-mpfr=$DEPENDENCY_DIR --with-gmp=$DEPENDENCY_DIR"

mkdir -p "$TODAY_DESTINATION_FOLDER/bin"

# Allow the build to use the newly generated tools to compile the libraries
export PATH=$TODAY_DESTINATION_FOLDER/bin:$PATH

# Pass the custom destination folder to the make process
export PREFIX=$TODAY_DESTINATION_FOLDER/
echo "PREFIX=$TODAY_DESTINATION_FOLDER/"

# ----------------------------------------------------------------------------- 
# And finally start the build

cd "$JSNYDER_ZIP_FOLDER"


# Build the tools, using the new Xcode 4.1 compiler
# Leave the newlib at the end, since it might fail :-(

( \
(CC=clang make cross-binutils cross-gcc cross-g++ cross-newlib) \
&& (make cross-gdb) \
&& (make install-bin-extras) \
)

echo
echo "If you need to manually run various build steps..."
echo
echo cd `pwd`
echo export DEPENDENCIES=\"$DEPENDENCIES\"
echo export PATH=$PATH
echo export PREFIX=$PREFIX
echo

exit 0
