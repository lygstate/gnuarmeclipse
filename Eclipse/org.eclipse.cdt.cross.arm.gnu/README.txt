
This is the 'GNU ARM Eclipse Plug-in (Second Edition)'.

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

- Eclipse 3.4 (preferred 3.5.2)
- CDT 5.0.2 (preferred 6.x)
- Java JRE 1.5 (preferred 1.6)

- Cygwin, if make/rm not provided 
Note: although CodeSourcery provides cs-make, if the sources are 
organized in subdirectories, like 'src', the generated makefiles
might require additional shell support, so if you notice make 
errors, having Cygwin in the build path is a good choice).


Implementation notes
--------------------

Supported toolchains:

- The CodeSourcery G++ Lite for ARM EABI (arm-none-eabi-gcc) on
	Windows (with provided cs-make/cs-rm)
	GNU/Linux
	Mac OS X

- GNUARM and derivatives (arm-elf-gcc) on
	Windows
	GNU/Linux
	Mac OS X
	
- Yagarto (arm-none-eabi-gcc) on
	Windows
	
- devkitPro ARM (arm-eabi-gcc) on
	Windows (no make/rm provided)
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


Current version: 0.5.3.201007132006
-----------------------------------


Bug Fixes & Changes since 0.5.3.201007072245
--------------------------------------------

- assembler listing support added as -Wa,-adhlns="$@.lst" to source files


Bug Fixes & Changes since 0.5.3.201007062110
--------------------------------------------

- support for devkitPro was added for all platforms (Windows/Linux/Mac OS X). 
On Windows, the usual make/rm utilities are required; Cygwin provides very good 
implementations of them. 
- some Yagarto discovery bugs were fixed


Bug Fixes & Changes since 0.5.3.201006282150
--------------------------------------------

- CodeSourcery added to Mac OS X
- Mac OS X missing GNUARM configuration reference fixed
- (#3024374) individual properties processed
- (#3024483) New Yagarto Toolchain uses EABI


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
(Note: be sure you update to CDT 5.0.2 or higher, on 5.0.0 support
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

As with any software, this plug-in has its share of problems:

Major bugs:

- do not set the build properties for a folder; by doing this, 
global project properties like unchecking the -mthumb, or changing 
debugging setting will be ignored for source files below the folder

- do not set the build properties for a source file; by doing this, 
the build process will terminate prematurely, and no HEX file will 
be created.
 
- do not change the toolchain for a project; by doing this you will 
permanently damage the project. There is not known way to repair 
such a broken project; you have to create a new project and copy 
content.

- do not use the plug-in on Eclipse 3.6 Helios, since the Discovery 
Options are not working.

Other problems:

- toolchain presence is not yet verified, so missing toolchains 
are also offered for selection

- screen update after selecting/deselecting additional tools 
requires leaving that tab and returning


Reporting problems
------------------

If you notice any problem while using the plug-in, please check the log at:
	workspace/.metadata/.log

If there are any exceptions related to org.eclipse.cdt.cross.arm.* classes, 
please post the bugs to SourceForge support forums.


Any comments/suggestions will be highly appreciated.

Regards,

Liviu Ionescu

ilg-ul@users.sourceforge.net
ilg@livius.net

