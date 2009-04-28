package org.eclipse.cdt.cross.arm.gnu.common;

import org.eclipse.cdt.cross.arm.gnu.Tools;
import org.eclipse.cdt.managedbuilder.core.IManagedIsToolChainSupported;
import org.eclipse.cdt.managedbuilder.core.IToolChain;
import org.eclipse.core.runtime.PluginVersionIdentifier;

@SuppressWarnings("deprecation")
public class IsToolChainSupported implements IManagedIsToolChainSupported {

	static final boolean DEBUG = true;

	private boolean m_bSuppChecked;
	private boolean m_bToolchainIsSupported;

	public IsToolChainSupported() {
		m_bSuppChecked = false;
		m_bToolchainIsSupported = false;
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
	public boolean isSupported(IToolChain oToolChain,
			PluginVersionIdentifier sVersion, String sInstance) {

		if (m_bSuppChecked)
			return m_bToolchainIsSupported;

		m_bSuppChecked = true;
		m_bToolchainIsSupported = false;

		if (!Tools.isPlatform(getPlatform()))
			return false;

		if (DEBUG)
			System.out.println(oToolChain.getName() + " " + sInstance + " "
					+ oToolChain.isDirty() + " " + oToolChain.isSystemObject()
					+ " " + oToolChain.getId() + " "
					+ oToolChain.getSuperClass());

		String sLines[];
		sLines = Tools.exec(getCompilerName(), oToolChain.getParent());
		if (sLines != null)
			m_bToolchainIsSupported = true;

		if (DEBUG)
			System.out.println(m_bToolchainIsSupported);

		return m_bToolchainIsSupported;
	}
}
