package org.eclipse.cdt.cross.arm.gnu.windows;

import org.eclipse.cdt.cross.arm.gnu.Tools;
import org.eclipse.cdt.cross.arm.gnu.common.IsToolchainData;
import org.eclipse.cdt.cross.arm.gnu.sourcery.windows.PathResolver;
import org.eclipse.cdt.managedbuilder.core.IToolChain;
import org.eclipse.core.runtime.PluginVersionIdentifier;

@SuppressWarnings("deprecation")
public class IsToolChainSupported extends
		org.eclipse.cdt.cross.arm.gnu.common.IsToolChainSupported {

	static IsToolchainData ms_oData = null;

	public boolean isSupported(IToolChain oToolChain,
			PluginVersionIdentifier sVersion, String sInstance) {
		
		if (ms_oData == null)
			ms_oData = new IsToolchainData();
		
		if (ms_oData.m_sBinPath == null)
			ms_oData.m_sBinPath = PathResolver.getBinPath();

		return isSupportedImpl(oToolChain, sVersion, sInstance,
				ms_oData);
	}

	public String getPlatform()
	{
		return Tools.PROPERTY_OS_VALUE_WINDOWS;
	}
}
