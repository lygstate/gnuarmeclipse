package org.eclipse.cdt.cross.arm.gnu.windows;

import org.eclipse.cdt.cross.arm.gnu.Tools;

public class IsToolChainSupported extends
		org.eclipse.cdt.cross.arm.gnu.common.IsToolChainSupported {

	public String getPlatform()
	{
		return Tools.PROPERTY_OS_VALUE_WINDOWS;
	}

}
