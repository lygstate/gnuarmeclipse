package org.eclipse.cdt.cross.arm.gnu.scannerconfig;

import org.eclipse.cdt.build.core.scannerconfig.CfgInfoContext;
import org.eclipse.cdt.core.model.CoreModel;
import org.eclipse.cdt.core.settings.model.ICProjectDescription;
import org.eclipse.cdt.make.core.scannerconfig.IScannerInfoCollector;
import org.eclipse.cdt.make.core.scannerconfig.InfoContext;
import org.eclipse.cdt.make.internal.core.scannerconfig.gnu.GCCScannerInfoConsoleParser;
import org.eclipse.cdt.make.internal.core.scannerconfig2.PerProjectSICollector;
import org.eclipse.cdt.managedbuilder.core.IConfiguration;
import org.eclipse.core.resources.IProject;

public class ARMManagedGCCScannerInfoConsoleParser extends
		GCCScannerInfoConsoleParser {

	Boolean m_bManagedBuildOnState;

	public boolean processLine(String sLine) {
		if (isManagedBuildOn())
			return false;
		return super.processLine(sLine);
	}

	public void shutdown() {
		if (!isManagedBuildOn()) {
			super.shutdown();
		}
		m_bManagedBuildOnState = null;
	}

	public void startup(IProject oProject, IScannerInfoCollector oCollector) {
		if (isManagedBuildOn())
			return;
		super.startup(oProject, oCollector);
	}

	protected boolean isManagedBuildOn() {
		if (m_bManagedBuildOnState == null)
			m_bManagedBuildOnState = Boolean
					.valueOf(doCalcManagedBuildOnState());
		return m_bManagedBuildOnState.booleanValue();
	}

	protected boolean doCalcManagedBuildOnState() {
		IScannerInfoCollector oCr = getCollector();
		InfoContext oC;
		if (oCr instanceof PerProjectSICollector) {
			oC = ((PerProjectSICollector) oCr).getContext();
		} else {
			return false;
		}

		IProject oProject = oC.getProject();
		ICProjectDescription oDes = CoreModel.getDefault()
				.getProjectDescription(oProject, false);
		CfgInfoContext oCc = CfgInfoContext.fromInfoContext(oDes, oC);
		if (oCc != null) {
			IConfiguration cfg = oCc.getConfiguration();
			return cfg.isManagedBuildOn();
		}
		return false;
	}
}
