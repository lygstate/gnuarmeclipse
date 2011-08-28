package org.eclipse.cdt.cross.arm.gnu.devkitpro.linux;

import org.eclipse.cdt.cross.arm.gnu.common.IsToolchainData;
import org.eclipse.cdt.managedbuilder.core.IToolChain;
import org.osgi.framework.Version;

public class IsToolChainSupported extends
		org.eclipse.cdt.cross.arm.gnu.devkitpro.IsToolChainSupported {

	static IsToolchainData ms_oData = null;

	public boolean isSupported(IToolChain oToolChain,
			Version oVersion, String sInstance) {
		
		if (ms_oData == null)
			ms_oData = new IsToolchainData();
		
		return isSupportedImpl(oToolChain, oVersion, sInstance,
				ms_oData);
	}

}
