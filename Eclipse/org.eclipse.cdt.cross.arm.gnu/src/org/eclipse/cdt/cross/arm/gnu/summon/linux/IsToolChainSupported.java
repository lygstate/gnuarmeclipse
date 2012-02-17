package org.eclipse.cdt.cross.arm.gnu.summon.linux;

import org.eclipse.cdt.cross.arm.gnu.common.IsToolchainData;
import org.eclipse.cdt.managedbuilder.core.IToolChain;
import org.osgi.framework.Version;

public class IsToolChainSupported extends
		org.eclipse.cdt.cross.arm.gnu.summon.IsToolChainSupported {

	static IsToolchainData ms_oData = null;

	public boolean isSupported(IToolChain toolChain, Version version,
			String instance) {
		
		if (ms_oData == null)
			ms_oData = new IsToolchainData();
		
		return isSupportedImpl(toolChain, version, instance,
				ms_oData);
	}

}
