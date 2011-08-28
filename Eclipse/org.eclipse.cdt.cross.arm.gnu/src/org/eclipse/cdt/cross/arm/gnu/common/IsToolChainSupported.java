package org.eclipse.cdt.cross.arm.gnu.common;

import org.eclipse.cdt.cross.arm.gnu.Tools;
import org.eclipse.cdt.managedbuilder.core.IManagedIsToolChainSupported;
import org.eclipse.cdt.managedbuilder.core.IToolChain;
import org.osgi.framework.Version;

public abstract class IsToolChainSupported implements
		IManagedIsToolChainSupported {

	static final boolean DEBUG = false;

	public IsToolChainSupported() {
		if (DEBUG)
			System.out.println(this.getClass().getName() + " "
					+ this.getCompilerName());
	}

	public String getCompilerName() {
		return "arm-elf-gcc";
	}

	public String getPlatform() {
		return Tools.PROPERTY_OS_VALUE_LINUX;
	}

	/*
	 * Called for each project and configuration at start-up, for each project
	 * type at New Project, with isDirty() at edit, and !isDirty() after save.
	 */
	public boolean isSupportedImpl(IToolChain oToolChain,
			Version oVersion, String sInstance,
			IsToolchainData oStaticData) {

		/* temporarily disabled */
		if (true)
			return true;

		if (oStaticData.m_bSuppChecked)
			return oStaticData.m_bToolchainIsSupported;

		oStaticData.m_bSuppChecked = true;
		oStaticData.m_bToolchainIsSupported = false;

		if (!Tools.isPlatform(getPlatform()))
			return false;

		if (DEBUG) {
			System.out.println(oToolChain.getName() + " " + sInstance + " "
					+ oToolChain.isDirty() + " " + oToolChain.isSystemObject()
					+ " " + oToolChain.getId() + " "
					+ oToolChain.getSuperClass());
			if (oStaticData.m_sBinPath != null)
				System.out.println(oStaticData.m_sBinPath);
		}

		String sLines[];
		sLines = Tools.exec(getCompilerName(), oToolChain.getParent(),
				oStaticData.m_sBinPath);
		if (sLines != null)
			oStaticData.m_bToolchainIsSupported = false;

		if (DEBUG)
			System.out.println(oStaticData.m_bToolchainIsSupported);

		return oStaticData.m_bToolchainIsSupported;
	}

}
