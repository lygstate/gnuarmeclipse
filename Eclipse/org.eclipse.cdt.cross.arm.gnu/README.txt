This is a functional beta version of the new 
GNU ARM Plug-in (Second Edition).

https://sourceforge.net/projects/gnuarmeclipse/


Installation
------------

The recommended way is to use the Eclipse standard update mechanism:

http://gnuarmeclipse.sourceforge.net/updates

A quick and dirty way is to copy the plug-in .jar file to the 
.../eclipse/plugins directory.


Requirements
------------

- Eclipse 3.4
- CDT 5.0
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
 

Bug Fixes & Changes since 0.5.3.200903302247
--------------------------------------------

- empty makefile in C projects bug fixed 

- the ARM icon is now visible in the Help -> About window.

- update site URL renamed from http://gnuarmeclipse.sourceforge.net/update
to http://gnuarmeclipse.sourceforge.net/updates


Known Bugs
----------

- Discovery Options are not yet properly implemented, the Per Language 
scope should point to the 'per project scanner info profile 
(GCCWinManagedMakePerProjectProfileC)' and the compiler command 
should point to the proper tool like 'arm-none-eabi-gcc'. 

- screen update after selecting/deselecting additional tools requires 
leaving that tab and returning

- 'Empty Project' template not shown for ARM applications


Any comments/suggestions will be highly appreciated.

Regards,

Liviu
ilg-ul@users.sourceforge.net
ilg@livius.net

