package org.eclipse.cdt.cross.arm.gnu.summon;

public abstract class IsToolChainSupported extends
		org.eclipse.cdt.cross.arm.gnu.common.IsToolChainSupported {
	
	public String getCompilerName() {
		return "arm-none-eabi-gcc";
	}

}
