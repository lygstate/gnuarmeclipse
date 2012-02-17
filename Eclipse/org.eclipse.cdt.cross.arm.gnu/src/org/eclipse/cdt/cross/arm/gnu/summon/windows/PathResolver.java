package org.eclipse.cdt.cross.arm.gnu.summon.windows;

import java.io.File;

import org.eclipse.cdt.cross.arm.gnu.ARMPlugin;
import org.eclipse.cdt.cross.arm.gnu.Tools;
import org.eclipse.cdt.managedbuilder.core.IBuildPathResolver;
import org.eclipse.cdt.managedbuilder.core.IConfiguration;
import org.eclipse.cdt.managedbuilder.gnu.ui.GnuUIPlugin;
import org.eclipse.core.runtime.Status;

public class PathResolver implements IBuildPathResolver {

	private static boolean ms_bChecked = false;
	private static String ms_sBinSummon = null;

	private static final String DELIMITER_WIN = ";"; //$NON-NLS-1$
	private static final String ARM_TOOLCHAIN_BASEDIR = "arm-toolchain"; //$NON-NLS-1$


	public String[] resolveBuildPaths(int pathType, String variableName,
			String variableValue, IConfiguration configuration) {

		// don't think this is used somewhere
		System.out.println(PathResolver.class.getName()
				+ " resolveBuildPaths()");
		return variableValue.split(DELIMITER_WIN);
	}

	public static String getBinPath() {
		if (!ms_bChecked) {
			
			String sInstallDir;
			sInstallDir = checkSysPath();
			
			if (sInstallDir != null) {

				String sToolPath;
				sToolPath = sInstallDir + "\\bin";
				File oDir = new File(sToolPath);
				if (!oDir.exists() || !oDir.isDirectory()) {
					GnuUIPlugin.getDefault().log(new Status(0, ARMPlugin.PLUGIN_ID,
							"The existing \"arm-toolchain\" folder on the system path does not contain a bin subfolder")); // no "\bin" directory
				} else {
					ms_sBinSummon = sToolPath;
				}
			} else {
				GnuUIPlugin.getDefault().log(new Status(0, ARMPlugin.PLUGIN_ID,
						"No ARM toolchain installation with basedir named \"arm-toolchain\" found in the system path"));
			}
			ms_bChecked = true;
		}
		return ms_sBinSummon;
	}
	
	private static synchronized String checkSysPath() {
		return Tools.getManualInstallPath(ARM_TOOLCHAIN_BASEDIR);
	}
}
