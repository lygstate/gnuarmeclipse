package org.eclipse.cdt.cross.arm.gnu.sourcery.windows;

import org.eclipse.cdt.managedbuilder.core.IManagedIsToolChainSupported;
import org.eclipse.cdt.managedbuilder.core.IToolChain;
import org.eclipse.cdt.utils.WindowsRegistry;
import org.eclipse.core.runtime.PluginVersionIdentifier;

@SuppressWarnings("deprecation")
public class IsToolChainSupported implements IManagedIsToolChainSupported {

	static boolean bSuppChecked = false;
	static boolean bToolchainIsSupported = false;

	private static final String PROPERTY_OS_NAME = "os.name"; //$NON-NLS-1$
	private static final String PROPERTY_OS_VALUE = "windows";//$NON-NLS-1$

	private static final String REGISTRY_KEY = "\\HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\Sourcery G++ Lite for ARM EABI\\InstallLocation\\"; //$NON-NLS-1$ 
	private static final String PATH_NAME = "native"; //$NON-NLS-1$

	public boolean isSupported(IToolChain oToolChain,
			PluginVersionIdentifier sVersion, String sInstance) {

		if (bSuppChecked)
			return bToolchainIsSupported;

		bSuppChecked = true;
		bToolchainIsSupported = false;
		
		if (!isWindows())
			return false;

		WindowsRegistry registry = WindowsRegistry.getRegistry();
		if (null != registry) {
			String s;
			s = registry.getLocalMachineValue(REGISTRY_KEY, PATH_NAME);

			if (s != null) {
				bToolchainIsSupported = true;
			}
		}

		return bToolchainIsSupported;
	}

	public static boolean isWindows() {
		return (System.getProperty(PROPERTY_OS_NAME).toLowerCase()
				.startsWith(PROPERTY_OS_VALUE));
	}

}
