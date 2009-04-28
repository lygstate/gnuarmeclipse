package org.eclipse.cdt.cross.arm.gnu.sourcery.windows;

import org.eclipse.cdt.cross.arm.gnu.Tools;

public class IsToolChainSupported extends
		org.eclipse.cdt.cross.arm.gnu.common.IsToolChainSupported {

	public String getCompilerName() {
		return "arm-none-eabi-gcc";
	}
	
	public String getPlatform()
	{
		return Tools.PROPERTY_OS_VALUE_WINDOWS;
	}

}
