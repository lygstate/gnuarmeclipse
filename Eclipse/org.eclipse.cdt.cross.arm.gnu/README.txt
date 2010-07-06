
This is a functional beta version of the new 
GNU ARM Eclipse Plug-in (Second Edition).

https://sourceforge.net/projects/gnuarmeclipse/


Installation
------------

The recommended way is to use the Eclipse standard update mechanism:

Help -> Software Updated -> Available Software -> Add Site 

	http://gnuarmeclipse.sourceforge.net/updates

(be sure you uncheck 'Group items by category' and later accept
the unsigned plug-in)

The alternate solution to the online site is to download the archive 
from SourceForge and to to point the Eclipse update mechanism to it
(Help -> Software Updated -> Available Software -> Add Site -> Archive).

A quick and dirty way is to extract the plug-in .jar file from the 
downloaded archive and to copy it to the .../eclipse/plugins directory,
but this is not recommended. 


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

- The CodeSourcery G++ Lite for ARM EABI (arm-none-eabi-gcc) on
	Windows
	GNU/Linux
	Mac OS X

- GNUARM and derivatives (arm-elf-gcc) on
	Windows
	GNU/Linux
	Mac OS X

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


Current version: 0.5.3.201007061210
-----------------------------------

Bug Fixes & Changes since 0.5.3.201006282150
--------------------------------------------

- CodeSourcery added to Mac OS X
- Mac OS X missing GNUARM configuration reference fixed
- (#3024374) individual properties processed


Bug Fixes & Changes since 0.5.3.201006091313
--------------------------------------------

- Properties on a source file triggered an error when selecting Tool Settings.
This was due to a bug in ARMManagedCommandLineGenerator, where the parent
of Tool may be ResourceConfiguration instead of ToolChain.


Bug Fixes & Changes since 0.5.3.201004202202
--------------------------------------------

- the confusion between Host OS and Guest OS when selecting the 
CodeSourcery G++ Lite toolchain was finally sorted out. 
The basic toolchain when targeting for embedded systems is ARM EABI. 
The arm-none-eabi toolchain is intended to generate programs 
for running on ARM ported Linux, not embedded systems.


Bug Fixes & Changes since 0.5.3.201001261103
--------------------------------------------

- license changed from GPL to EPL


Bug Fixes & Changes since 0.5.3.201001151708
--------------------------------------------

- no functional changes, just a clean repack of the update site and
of the published archive.


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


Reporting problems
------------------

If you notice any problem while using the plug-in, please check the log at:
	workspace/.metadata/.log

If there are any exceptions related to org.eclipse.cdt.cross.arm.* classes, 
please post the bugs to Sourceforge support forums.


Any comments/suggestions will be highly appreciated.

Regards,

Liviu Ionescu

ilg-ul@users.sourceforge.net
ilg@livius.net

