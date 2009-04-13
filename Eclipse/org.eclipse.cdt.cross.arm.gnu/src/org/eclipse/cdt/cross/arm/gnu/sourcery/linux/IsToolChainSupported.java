package org.eclipse.cdt.cross.arm.gnu.sourcery.linux;

import org.eclipse.cdt.managedbuilder.core.IManagedIsToolChainSupported;
import org.eclipse.cdt.managedbuilder.core.IToolChain;
import org.eclipse.core.runtime.PluginVersionIdentifier;

@SuppressWarnings("deprecation")
public class IsToolChainSupported implements IManagedIsToolChainSupported {

	static boolean bSuppChecked = false;
	static boolean bToolchainIsSupported = false;

	private static final String PROPERTY_OS_NAME = "os.name"; //$NON-NLS-1$
	private static final String PROPERTY_OS_VALUE = "linux";//$NON-NLS-1$


	public boolean isSupported(IToolChain oToolChain,
			PluginVersionIdentifier sVersion, String sInstance) {

		if (bSuppChecked)
			return bToolchainIsSupported;

		bSuppChecked = true;
		bToolchainIsSupported = false;
		
		if (!isLinux())
			return false;

		// TODO: compute bToolchainIsSupported properly
		bToolchainIsSupported = true;
		
		return bToolchainIsSupported;
	}

	public static boolean isLinux() {
		return (System.getProperty(PROPERTY_OS_NAME).toLowerCase()
				.startsWith(PROPERTY_OS_VALUE));
	}

}
