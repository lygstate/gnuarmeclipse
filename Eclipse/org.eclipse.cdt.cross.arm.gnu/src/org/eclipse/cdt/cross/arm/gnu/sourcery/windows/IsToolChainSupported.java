package org.eclipse.cdt.cross.arm.gnu.sourcery.windows;

import java.io.File;

import org.eclipse.cdt.managedbuilder.core.IManagedIsToolChainSupported;
import org.eclipse.cdt.managedbuilder.core.ITool;
import org.eclipse.cdt.managedbuilder.core.IToolChain;
import org.eclipse.core.runtime.PluginVersionIdentifier;

@SuppressWarnings("deprecation")
public class IsToolChainSupported implements IManagedIsToolChainSupported {

	static boolean ms_bSuppChecked = false;
	static boolean ms_bToolchainIsSupported = false;

	public boolean isSupported(IToolChain oToolChain,
			PluginVersionIdentifier sVersion, String sInstance) {

		if (ms_bSuppChecked)
			return ms_bToolchainIsSupported;

		ms_bSuppChecked = true;
		ms_bToolchainIsSupported = false;

		if (!PathResolver.isWindows())
			return false;

		String sToolPath;
		sToolPath = PathResolver.getBinPath();
		if (sToolPath != null) {

			if (false) {
				// experimental support to test each tool, but it is not appropriate
				ITool aoTools[];
				aoTools = oToolChain.getTools();
				for (int i = 0; i < aoTools.length; ++i) {
					ITool oTool;
					oTool = aoTools[i];

					if (true)
						System.out.println(oTool.getName() + " "
								+ oTool.getToolCommand() + " "
								+ oTool.getNatureFilter() + " "
								+ oTool.isEnabled() + " " + oTool.getId());

					if (oTool.isEnabled()) {

						String sTool;
						sTool = sToolPath + "\\" + oTool.getToolCommand()
								+ ".exe";
						File oFile;
						oFile = new File(sTool);
						if (!oFile.exists() || oFile.isDirectory()) {
							return false; // no tool
						}
					}
				}
			}

			ms_bToolchainIsSupported = true;
		}

		return ms_bToolchainIsSupported;
	}
}
