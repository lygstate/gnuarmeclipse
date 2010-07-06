package org.eclipse.cdt.cross.arm.gnu.yagarto;

public abstract class IsToolChainSupported extends
		org.eclipse.cdt.cross.arm.gnu.common.IsToolChainSupported {
	
	public String getCompilerName() {
		return "arm-none-eabi-gcc";
	}

}
