package org.eclipse.cdt.cross.arm.gnu.common;

public class IsToolchainData {
	public boolean m_bSuppChecked;
	public boolean m_bToolchainIsSupported;
	public String m_sBinPath;

	public IsToolchainData() {
		m_bSuppChecked = false;
		m_bToolchainIsSupported = false;
		m_sBinPath = null;
	}

}
