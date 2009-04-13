package org.eclipse.cdt.cross.arm.gnu.scannerconfig;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.cdt.make.core.scannerconfig.IScannerInfoCollector3;
import org.eclipse.cdt.make.core.scannerconfig.InfoContext;
import org.eclipse.cdt.make.core.scannerconfig.ScannerInfoTypes;
import org.eclipse.cdt.make.internal.core.scannerconfig.util.CygpathTranslator;
import org.eclipse.cdt.make.internal.core.scannerconfig2.PerProjectSICollector;
import org.eclipse.cdt.managedbuilder.scannerconfig.IManagedScannerInfoCollector;
import org.eclipse.core.resources.IProject;


public class ARMGnuWinScannerInfoCollector extends PerProjectSICollector implements IScannerInfoCollector3, IManagedScannerInfoCollector  {
	
	private IProject m_oProject; 
	
    /* (non-Javadoc)
     * @see org.eclipse.cdt.make.core.scannerconfig.IScannerInfoCollector#contributeToScannerConfig(java.lang.Object, java.util.Map)
     */
    @SuppressWarnings("unchecked")
	public void contributeToScannerConfig(Object oResource, Map oScannerInfo) {
        // check the resource
//        if (resource != null && resource instanceof IResource &&
//                ((IResource) resource).getProject().equals(getProject())) {
            List oIncludes = (List) oScannerInfo.get(ScannerInfoTypes.INCLUDE_PATHS);
//            List symbols = (List) scannerInfo.get(ScannerInfoTypes.SYMBOL_DEFINITIONS);
            
    		// This method will be called by the parser each time there is a new value
            List oTranslatedIncludes = CygpathTranslator.translateIncludePaths(m_oProject, oIncludes);
    		Iterator oPathIter = oTranslatedIncludes.listIterator();
    		while (oPathIter.hasNext()) {
    			String sConvertedPath = (String) oPathIter.next();
    			// On MinGW, there is no facility for converting paths
    			if (sConvertedPath.startsWith("/")) //$NON-NLS-1$
    				oPathIter.remove();
//    			// Add it if it is not a duplicate
//    			if (!getIncludePaths().contains(convertedPath)){
//    					getIncludePaths().add(convertedPath);
//    			}
    		}
    		oScannerInfo.put(ScannerInfoTypes.INCLUDE_PATHS, oTranslatedIncludes);
    		
//    		// Now add the macros
//    		Iterator symbolIter = symbols.listIterator();
//    		while (symbolIter.hasNext()) {
//    			// See if it has an equals
//    			String[] macroTokens = ((String)symbolIter.next()).split(EQUALS);
//    			String macro = macroTokens[0].trim();
//    			String value = (macroTokens.length > 1) ? macroTokens[1].trim() : new String();
//    			getDefinedSymbols().put(macro, value);
//    		}
    		super.contributeToScannerConfig(oResource, oScannerInfo);
        }
//	}
	public void setProject(IProject oProject) {
		m_oProject = oProject;
		super.setProject(oProject);
	}

	public void setInfoContext(InfoContext oContext) {
		m_oProject = oContext.getProject();
		super.setInfoContext(oContext);
	}

}
