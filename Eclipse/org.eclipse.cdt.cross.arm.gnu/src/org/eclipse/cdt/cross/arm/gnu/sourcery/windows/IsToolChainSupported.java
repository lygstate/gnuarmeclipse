package org.eclipse.cdt.cross.arm.gnu.sourcery.windows;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.cdt.managedbuilder.core.IManagedIsToolChainSupported;
import org.eclipse.cdt.managedbuilder.core.ITool;
import org.eclipse.cdt.managedbuilder.core.IToolChain;
import org.eclipse.core.runtime.PluginVersionIdentifier;

@SuppressWarnings("deprecation")
public class IsToolChainSupported implements IManagedIsToolChainSupported {

	static boolean ms_bSuppChecked = false;
	static boolean ms_bToolchainIsSupported = false;

	static Map<String, Boolean> ms_oToolsMap;
	static {
		ms_oToolsMap = new HashMap<String, Boolean>();
	}

	static Map<String, Boolean> ms_oToolChainsMap;
	static {
		ms_oToolChainsMap = new HashMap<String, Boolean>();
	}

	/*
	 * Called for each project and configuration at start-up, for each project
	 * type at New Project, with isDirty() at edit, and !isDirty() after save.
	 */
	public boolean isSupported(IToolChain oToolChain,
			PluginVersionIdentifier sVersion, String sInstance) {

		String sToolChainID;
		sToolChainID = oToolChain.getId();

		if (oToolChain.isDirty()) {
			ms_oToolChainsMap.remove(sToolChainID);
			return true; // will be called again when saved
		}

		// normally one toolchain should have a single value, this map is
		// experimental, to be able to test each project
		if (ms_oToolChainsMap.containsKey(sToolChainID))
			return ms_oToolChainsMap.get(sToolChainID);

		if (false)
			System.out.println(oToolChain.getName() + " " + sInstance + " "
					+ oToolChain.isDirty() + " " + oToolChain.isSystemObject()
					+ " " + oToolChain.getId() + " "
					+ oToolChain.getSuperClass());

		boolean bResult;
		bResult = false;

		if (PathResolver.isWindows()) {

			String sToolPath;
			sToolPath = PathResolver.getBinPath();
			if (sToolPath != null) {

				bResult = true; // if bin directory present assume toolchain present

				if (false) {
					/* 
					 * Experimental support for individual tool test
					 */
					ITool aoTools[];
					aoTools = oToolChain.getTools();
					for (int i = 0; i < aoTools.length; ++i) {
						ITool oTool;
						oTool = aoTools[i];

						boolean bIsEnabled;
						try {
							bIsEnabled = oTool.isEnabled();
						} catch (NullPointerException e) {
							bIsEnabled = true;
						}
						
						if (true)
							System.out.println(oTool.getName() + " "
									+ oTool.getToolCommand() + " "
									+ oTool.getNatureFilter() + " "
									+ bIsEnabled
									+ " " + oTool.getId() + " "
									+ oTool.getToolCommand());

						if (bIsEnabled) {
							if (!checkTool(sToolPath, oTool.getToolCommand())) {
								bResult = false; // no tool
							}
						}
					}
				}

			}
		}

		ms_oToolChainsMap.put(sToolChainID, new Boolean(bResult));
		if (true)
			System.out.println(bResult);

		return bResult;
	}

	private boolean checkTool(String sToolDir, String sToolName) {

		if (ms_oToolsMap.containsKey(sToolName))
			return ms_oToolsMap.get(sToolName);

		boolean bResult;
		bResult = true;

		String sTool;
		// TODO: consider absolute path case 
		// TODO: consider extension case
		sTool = sToolDir + "\\" + sToolName + ".exe";

		if (true)
			System.out.println(sTool);

		File oFile;
		oFile = new File(sTool);
		if (!oFile.exists() || oFile.isDirectory()) {
			bResult = false; // no tool
		}

		ms_oToolsMap.put(sToolName, new Boolean(bResult));

		return bResult;
	}
}
