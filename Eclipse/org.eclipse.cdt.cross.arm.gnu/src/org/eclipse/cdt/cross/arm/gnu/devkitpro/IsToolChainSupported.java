package org.eclipse.cdt.cross.arm.gnu.devkitpro;

public abstract class IsToolChainSupported extends
		org.eclipse.cdt.cross.arm.gnu.common.IsToolChainSupported {
	
	public String getCompilerName() {
		return "arm-eabi-gcc";
	}

}
