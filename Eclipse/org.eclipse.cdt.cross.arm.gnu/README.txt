
This is a functional beta version of the new 
GNU ARM Eclipse Plug-in (Second Edition).

https://sourceforge.net/projects/gnuarmeclipse/


Installation
------------

The recommended way is to use the Eclipse standard update mechanism:

Help -> Software Updated -> Available Software -> 
Add Site -> http://gnuarmeclipse.sourceforge.net/updates

The alternate solution to the online site is to download the archive 
from SourceForge and to to point the Eclipse update mechanism to it
(Help -> Software Updated -> Available Software -> Add Site -> Archive).

A quick and dirty way is to extract the plug-in .jar file from the 
downloaded archive and to copy it to the .../eclipse/plugins directory.


Requirements
------------

- Eclipse 3.4
- CDT 5.0.2 (!)
- Java JRE 1.5
- Cygwin (note: although CodeSourcery provides cs-make, if the sources 
are organized in subdirectories, like 'src', the generated makefiles
require additional shell support, so having Cygwin in the build path
is a good choice).


Implementation notes
--------------------

Supported toolchains:
- The CodeSourcery G++ Lite on Windows (arm-none-eabi-gcc)
- The CodeSourcery G++ Lite on Linux (arm-none-linux-gnueabi-gcc)

- GNUARM and derivatives on Windows (arm-elf-gcc)
- GNUARM on Linux (arm-elf-gcc)
- GNUARM on Mac OS/X (arm-elf-gcc)

Supported project types are:
- applications (.elf, .hex)
- static libraries (.a)

The processor type can be selected for all tools in the "Target Processor"
section.
 
Creation of .HEX file, listing file and size report can be controlled from 
the "Additional Tools" section.

Debugging options for all tools were moved to a common "Debugging" section.

The assembly step is now done with the more general 'gcc' instead of 'as'.

Similarly, the link step is done by 'gcc/g++', instead of 'ld'.


Bug Fixes & Changes since 0.5.3.200904131820
--------------------------------------------

- patches from Freddie Chopin applied
	- linker --gc-sections added
	- linker -nostartfiles enabled for C, disabled for C++
	- linker -nostdlibs disabled
	- C++ compiler -no-rtti option added
	- C/C++ compiler signed char as option, default is unsigned (compiler default)

- recompiled with Eclipse 3.5 & CDT 6.0


Bug Fixes & Changes since 0.5.3.200904072239
--------------------------------------------

- Discovery Options implemented for all toolchains.
(Note: be sure you update to CDT 5.0.2, on 5.0.0 support
for Discovery is buggy).
 

Bug Fixes & Changes since 0.5.3.200903302247
--------------------------------------------

- empty makefile in C projects bug fixed 

- the ARM icon is now visible in the Help -> About window.
(only if installed via the update mechanism)

- update site URL renamed from 
	http://gnuarmeclipse.sourceforge.net/update
to 
	http://gnuarmeclipse.sourceforge.net/updates


Known Bugs
----------

- toolchain presence is not yet verified, so missing toolchains 
are also offered for selection

- screen update after selecting/deselecting additional tools requires 
leaving that tab and returning


Any comments/suggestions will be highly appreciated.

Regards,

Liviu Ionescu

ilg-ul@users.sourceforge.net
ilg@livius.net

