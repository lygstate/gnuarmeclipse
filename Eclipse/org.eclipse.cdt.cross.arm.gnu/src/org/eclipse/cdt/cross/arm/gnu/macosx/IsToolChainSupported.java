package org.eclipse.cdt.cross.arm.gnu.macosx;

import org.eclipse.cdt.cross.arm.gnu.Tools;

public class IsToolChainSupported extends
		org.eclipse.cdt.cross.arm.gnu.common.IsToolChainSupported {

	public String getPlatform()
	{
		return Tools.PROPERTY_OS_VALUE_MACOSX;
	}

}
