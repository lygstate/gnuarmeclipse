package org.eclipse.cdt.cross.arm.gnu.sourcery.linux;

import org.eclipse.cdt.cross.arm.gnu.common.IsToolchainData;
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
		
		return isSupportedImpl(oToolChain, sVersion, sInstance,
				ms_oData);
	}

	public String getCompilerName() {
		return "arm-none-eabi-gcc";
	}

}
