package org.eclipse.cdt.cross.arm.gnu.sourcery.windows;

import java.io.File;

import org.eclipse.cdt.managedbuilder.core.IBuildPathResolver;
import org.eclipse.cdt.managedbuilder.core.IConfiguration;
import org.eclipse.cdt.utils.WindowsRegistry;

public class PathResolver implements IBuildPathResolver {

	private static boolean ms_bChecked = false;
	private static String ms_sBinCygwin = null;

	private static final String PROPERTY_OS_NAME = "os.name"; //$NON-NLS-1$
	private static final String PROPERTY_OS_VALUE = "windows";//$NON-NLS-1$

	private static final String REGISTRY_KEY = "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\Sourcery G++ Lite for ARM EABI"; //$NON-NLS-1$ 
	private static final String PATH_NAME = "InstallLocation"; //$NON-NLS-1$

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
		return ms_sBinCygwin;
	}

	public static boolean isWindows() {
		return (System.getProperty(PROPERTY_OS_NAME).toLowerCase()
				.startsWith(PROPERTY_OS_VALUE));
	}

	/**
	 * reads once data from registry (for Win32 only) and sets corresponding
	 * properties;
	 */
	private static synchronized void checkRegistry() {
		if (ms_bChecked)
			return;

		ms_sBinCygwin = null;
		if (!isWindows())
			return;

		String sInstallDir;
		sInstallDir = getLocalMachineValue(REGISTRY_KEY, PATH_NAME);
		if (sInstallDir != null) {

			String sToolPath;
			sToolPath = sInstallDir + "\\bin";
			File oDir = new File(sToolPath);
			if (!oDir.exists() || !oDir.isDirectory()) {
				; // no "\bin" directory
			} else {
				ms_sBinCygwin = sToolPath;
			}
		}
		ms_bChecked = true;
	}

	private static String getLocalMachineValue(String sKey, String sName) {
		WindowsRegistry registry = WindowsRegistry.getRegistry();
		if (null != registry) {
			String s;
			s = registry.getLocalMachineValue(sKey, sName);

			if (s != null) {
				return s;
			}
		}
		return null;
	}
}
