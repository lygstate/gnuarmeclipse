package org.eclipse.cdt.cross.arm.gnu;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.cdt.managedbuilder.core.BuildException;
import org.eclipse.cdt.managedbuilder.core.IManagedCommandLineInfo;
import org.eclipse.cdt.managedbuilder.core.IOption;
import org.eclipse.cdt.managedbuilder.core.ITool;
import org.eclipse.cdt.managedbuilder.core.IToolChain;
import org.eclipse.cdt.managedbuilder.internal.core.ManagedCommandLineGenerator;

@SuppressWarnings("restriction")
public class ARMManagedCommandLineGenerator extends ManagedCommandLineGenerator {

	private static final String OPTION_SUFIX_PROCESSOR = ".option.target.processor";
	private static final String OPTION_SUFIX_THUMB = ".option.target.thumb";
	private static final String OPTION_SUFIX_THUMB_INTERWORK = ".option.target.thumbinterwork";
	private static final String OPTION_SUFFIX_ENDIANNES = ".option.target.endiannes";
	private static final String OPTION_SUFFIX_FLOAT_ABI = ".option.target.fpu.abi";
	private static final String OPTION_SUFFIX_FLOAT_UNIT = ".option.target.fpu.unit";
	private static final String OPTION_SUFIX_DEBUGGING_LEVEL = ".option.debugging.level";
	private static final String OPTION_SUFIX_DEBUGGING_FORMAT = ".option.debugging.format";
	private static final String OPTION_SUFIX_DEBUGGING_OTHER = ".option.debugging.other";
	private static final String OPTION_SUFIX_DEBUGGING_PROF = ".option.debugging.prof";
	private static final String OPTION_SUFIX_DEBUGGING_GPROF = ".option.debugging.gprof";

	private static final boolean DEBUG_LOCAL = false;

	public ARMManagedCommandLineGenerator() {
		;
	}

	public IManagedCommandLineInfo generateCommandLineInfo(ITool oTool,
			String sCommandName, String asFlags[], String sOutputFlag,
			String sOutputPrefix, String sOutputName,
			String asInputResources[], String sCommandLinePattern) {

		return generateCommandLineInfo(oTool, sCommandName, asFlags,
				sOutputFlag, sOutputPrefix, sOutputName, asInputResources,
				sCommandLinePattern, false);
	}

	public IManagedCommandLineInfo generateCommandLineInfo(ITool oTool,
			String sCommandName, String asFlags[], String sOutputFlag,
			String sOutputPrefix, String sOutputName,
			String asInputResources[], String sCommandLinePattern, boolean bFlag) {

		ArrayList<String> oList = new ArrayList<String>();
		oList.addAll(((java.util.Collection<String>) (Arrays
				.asList(((asFlags))))));

		Object oParent = oTool.getParent();
		while (oParent != null && !(oParent instanceof IToolChain)) {
			// climb up the hierarchy and search for IToolChain
			Object oSuper;
			oSuper = oTool.getSuperClass();
			if (oSuper != null && (oSuper instanceof ITool)) {
				oParent = ((ITool)oSuper).getParent();
			} else {
				oParent = null;
			}
		}
		
		if (oParent != null && (oParent instanceof IToolChain)) {
			IToolChain oToolChain = (IToolChain) oParent;
			// IConfiguration iconfiguration = itoolchain.getParent();
			IOption aoOptions[] = oToolChain.getOptions();

			String sProcessor;
			sProcessor = null;

			String sThumb;
			sThumb = null;

			String sThumbInterwork;
			sThumbInterwork = null;
			
			String sProcessorEndiannes;
			sProcessorEndiannes = null;
			
			String sFloatAbi;
			sFloatAbi = null;
			
			String sFloatUnit;
			sFloatUnit = null;

			String sDebugLevel;
			sDebugLevel = null;

			String sDebugFormat;
			sDebugFormat = null;

			String sDebugOther;
			sDebugOther = null;

			String sDebugProf;
			sDebugProf = null;

			String sDebugGProf;
			sDebugGProf = null;

			for (int i = 0; i < aoOptions.length; ++i) {
				IOption oOption;
				oOption = aoOptions[i];

				String sID;
				sID = oOption.getId();

				Object oValue;
				oValue = oOption.getValue();

				String sCommand;
				sCommand = oOption.getCommand();

				if (oValue instanceof String) {
					String sVal;
					try {
						sVal = oOption.getStringValue();
					} catch (BuildException e) {
						sVal = null;
					}

					String sEnumCommand;
					try {
						sEnumCommand = oOption.getEnumCommand(sVal);
					} catch (BuildException e1) {
						sEnumCommand = null;
					}

					if (DEBUG_LOCAL)
						System.out.println(oOption.getName() + " " + sID + " "
								+ sVal + " " + sCommand + " " + sEnumCommand);

					if (sID.endsWith(OPTION_SUFIX_PROCESSOR)
							|| sID.indexOf(OPTION_SUFIX_PROCESSOR + ".") > 0) {
						sProcessor = sEnumCommand;
					} else if (sID.endsWith(OPTION_SUFFIX_ENDIANNES)
							|| sID.indexOf(OPTION_SUFFIX_ENDIANNES + ".") > 0) {
						sProcessorEndiannes = sCommand;
					} else if (sID.endsWith(OPTION_SUFFIX_FLOAT_ABI)
							|| sID.indexOf(OPTION_SUFFIX_FLOAT_ABI + ".") > 0) {
						sFloatAbi = sEnumCommand;
					} else if (sID.endsWith(OPTION_SUFFIX_FLOAT_UNIT)
							|| sID.indexOf(OPTION_SUFFIX_FLOAT_UNIT + ".") > 0) {
						sFloatUnit = sEnumCommand;
					} else if (sID.endsWith(OPTION_SUFIX_DEBUGGING_LEVEL)
							|| sID.indexOf(OPTION_SUFIX_DEBUGGING_LEVEL + ".") > 0) {
						sDebugLevel = sEnumCommand;
					} else if (sID.endsWith(OPTION_SUFIX_DEBUGGING_FORMAT)
							|| sID.indexOf(OPTION_SUFIX_DEBUGGING_FORMAT + ".") > 0) {
						sDebugFormat = sEnumCommand;
					} else if (sID.endsWith(OPTION_SUFIX_DEBUGGING_OTHER)
							|| sID.indexOf(OPTION_SUFIX_DEBUGGING_OTHER + ".") > 0) {
						sDebugOther = sVal;
					}
				} else if (oValue instanceof Boolean) {
					boolean bVal;
					try {
						bVal = oOption.getBooleanValue();
					} catch (BuildException e) {
						bVal = false;
					}

					if (DEBUG_LOCAL)
						System.out.println(oOption.getName() + " " + sID + " "
								+ bVal + " " + sCommand);

					if (sID.endsWith(OPTION_SUFIX_THUMB)
							|| sID.indexOf(OPTION_SUFIX_THUMB + ".") > 0) {
						if (bVal)
							sThumb = sCommand;
					} else if (sID.endsWith(OPTION_SUFIX_THUMB_INTERWORK)
							|| sID.indexOf(OPTION_SUFIX_THUMB_INTERWORK + ".") > 0) {
						if (bVal)
							sThumbInterwork = sCommand;
					} else if (sID.endsWith(OPTION_SUFIX_DEBUGGING_PROF)
							|| sID.indexOf(OPTION_SUFIX_DEBUGGING_PROF + ".") > 0) {
						if (bVal)
							sDebugProf = sCommand;
					} else if (sID.endsWith(OPTION_SUFIX_DEBUGGING_GPROF)
							|| sID.indexOf(OPTION_SUFIX_DEBUGGING_GPROF + ".") > 0) {
						if (bVal)
							sDebugGProf = sCommand;
					}
				} else {
					if (DEBUG_LOCAL)
						System.out.println(oOption.getName() + " " + sID + " "
								+ oValue + " " + sCommand);
				}
			}

			if (DEBUG_LOCAL)
				System.out.println(sProcessor + " " + sThumb + " "
						+ sThumbInterwork + " " + sDebugLevel + " "
						+ sDebugFormat + " " + sDebugOther + " " + sDebugProf
						+ " " + sDebugGProf);

			if (sProcessor != null && sProcessor.length() > 0)
				oList.add(sProcessor);
			if (sThumb != null && sThumb.length() > 0)
				oList.add(sThumb);
			if (sThumbInterwork != null && sThumbInterwork.length() > 0)
				oList.add(sThumbInterwork);
			if (sProcessorEndiannes != null && sProcessorEndiannes.length() > 0)
				oList.add(sProcessorEndiannes);
			if (sFloatAbi != null && sFloatAbi.length() > 0) {
				oList.add(sFloatAbi);
				
				if (sFloatUnit != null && sFloatUnit.length() > 0)
					oList.add(sFloatUnit);
			}
			if (sDebugLevel != null && sDebugLevel.length() > 0) {
				oList.add(sDebugLevel);

				if (sDebugFormat != null && sDebugFormat.length() > 0)
					oList.add(sDebugFormat);
			}
			if (sDebugOther != null && sDebugOther.length() > 0)
				oList.add(sDebugOther);
			if (sDebugProf != null && sDebugProf.length() > 0)
				oList.add(sDebugProf);
			if (sDebugGProf != null && sDebugGProf.length() > 0)
				oList.add(sDebugGProf);

		}

		return super.generateCommandLineInfo(oTool, sCommandName, oList
				.toArray(new String[0]), sOutputFlag, sOutputPrefix,
				sOutputName, asInputResources, sCommandLinePattern);
	}
}
