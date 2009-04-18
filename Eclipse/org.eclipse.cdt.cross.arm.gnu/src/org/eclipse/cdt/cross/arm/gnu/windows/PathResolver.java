package org.eclipse.cdt.cross.arm.gnu.windows;

import java.io.File;

import org.eclipse.cdt.cross.arm.gnu.Tools;
import org.eclipse.cdt.managedbuilder.core.IBuildPathResolver;
import org.eclipse.cdt.managedbuilder.core.IConfiguration;

public class PathResolver implements IBuildPathResolver {

	private static boolean ms_bChecked = false;
	private static String ms_sBinGNUARM = null;

	private static final String REGISTRY_KEY = "SOFTWARE\\GNUARM\\4.1.1"; //$NON-NLS-1$ 
	private static final String PATH_NAME = "InstallPath"; //$NON-NLS-1$

	private static final String DELIMITER_WIN = ";"; //$NON-NLS-1$

	public String[] resolveBuildPaths(int pathType, String variableName,
			String variableValue, IConfiguration configuration) {

		// don't think this is used somewhere
		System.out.println(PathResolver.class.getName()
				+ " resolveBuildPaths()");
		return variableValue.split(DELIMITER_WIN);
	}

	public static String getBinPath() {
		if (!ms_bChecked)
			checkRegistry();
		return ms_sBinGNUARM;
	}


	/**
	 * reads once data from registry (for Win32 only) and sets corresponding
	 * properties;
	 */
	private static synchronized void checkRegistry() {
		if (ms_bChecked)
			return;

		ms_sBinGNUARM = null;
		if (!Tools.isWindows())
			return;

		String sInstallDir;
		sInstallDir = Tools.getLocalMachineValue(REGISTRY_KEY, PATH_NAME);
		if (sInstallDir != null) {

			String sToolPath;
			sToolPath = sInstallDir + "\\bin";
			File oDir = new File(sToolPath);
			if (!oDir.exists() || !oDir.isDirectory()) {
				; // no "\bin" directory
			} else {
				ms_sBinGNUARM = sToolPath;
			}
		}
		ms_bChecked = true;
	}

}
