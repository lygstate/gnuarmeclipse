package org.eclipse.cdt.cross.arm.gnu.windows;

import org.eclipse.cdt.cross.arm.gnu.Tools;
import org.eclipse.cdt.managedbuilder.core.IManagedIsToolChainSupported;
import org.eclipse.cdt.managedbuilder.core.IToolChain;
import org.eclipse.core.runtime.PluginVersionIdentifier;

@SuppressWarnings("deprecation")
public class IsToolChainSupported implements IManagedIsToolChainSupported {

	static boolean ms_bSuppChecked = false;
	static boolean ms_bToolchainIsSupported = false;

	private static final String GCC_ARM = "arm-elf-gcc"; //$NON-NLS-1$

	/*
	 * Called for each project and configuration at start-up, for each project
	 * type at New Project, with isDirty() at edit, and !isDirty() after save.
	 */
	public boolean isSupported(IToolChain oToolChain,
			PluginVersionIdentifier sVersion, String sInstance) {

		if (ms_bSuppChecked)
			return ms_bToolchainIsSupported;

		if (false)
			System.out.println(oToolChain.getName() + " " + sInstance);

		ms_bSuppChecked = true;
		ms_bToolchainIsSupported = false;

		if (!Tools.isWindows())
			return false;

		String sLines[];
		sLines = Tools.exec(GCC_ARM, oToolChain.getParent());
		if (sLines != null)
			ms_bToolchainIsSupported = true;

		return ms_bToolchainIsSupported;
	}

}
