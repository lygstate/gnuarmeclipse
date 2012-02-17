package org.eclipse.cdt.cross.arm.gnu.summon.windows;

import org.eclipse.cdt.cross.arm.gnu.Tools;
import org.eclipse.cdt.cross.arm.gnu.common.IsToolchainData;
import org.eclipse.cdt.cross.arm.gnu.sourcery.windows.PathResolver;
import org.eclipse.cdt.managedbuilder.core.IToolChain;
import org.osgi.framework.Version;

public class IsToolChainSupported extends
		org.eclipse.cdt.cross.arm.gnu.summon.IsToolChainSupported {

	static IsToolchainData ms_oData = null;

	public boolean isSupported(IToolChain toolChain, Version version,
			String instance) {
		
		if (ms_oData == null)
			ms_oData = new IsToolchainData();
		
		if (ms_oData.m_sBinPath == null)
			ms_oData.m_sBinPath = PathResolver.getBinPath();
		
		return isSupportedImpl(toolChain, version, instance,
				ms_oData);
	}
	
	public String getPlatform() {
		return Tools.PROPERTY_OS_VALUE_WINDOWS;
	}
}
