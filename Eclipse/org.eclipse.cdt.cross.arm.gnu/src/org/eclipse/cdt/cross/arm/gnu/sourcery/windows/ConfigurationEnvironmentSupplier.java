package org.eclipse.cdt.cross.arm.gnu.sourcery.windows;

import org.eclipse.cdt.managedbuilder.core.IConfiguration;
import org.eclipse.cdt.managedbuilder.envvar.IBuildEnvironmentVariable;
import org.eclipse.cdt.managedbuilder.envvar.IConfigurationEnvironmentVariableSupplier;
import org.eclipse.cdt.managedbuilder.envvar.IEnvironmentVariableProvider;
import org.eclipse.cdt.managedbuilder.internal.envvar.BuildEnvVar;

public class ConfigurationEnvironmentSupplier implements
		IConfigurationEnvironmentVariableSupplier {

	static final String VARNAME_PATH = "PATH"; //$NON-NLS-1$
	static final String DELIMITER_UNIX = ":"; //$NON-NLS-1$
	static final String PROPERTY_DELIMITER = "path.separator"; //$NON-NLS-1$

	public IBuildEnvironmentVariable getVariable(String variableName,
			IConfiguration configuration, IEnvironmentVariableProvider provider) {

		if (!PathResolver.isWindows()) //$NON-NLS-1$ 
			return null;

		if (variableName == null)
			return null;
		
		if (!VARNAME_PATH.equalsIgnoreCase(variableName))
			return null;

		String p = PathResolver.getBinPath();
		if (p != null) {

			String sDelimiter;
			sDelimiter = System.getProperty(PROPERTY_DELIMITER, DELIMITER_UNIX);

			String sPath;
			sPath = p.replace('/', '\\');

			return new BuildEnvVar(VARNAME_PATH, sPath,
					IBuildEnvironmentVariable.ENVVAR_PREPEND, sDelimiter); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return null;
	}

	public IBuildEnvironmentVariable[] getVariables(
			IConfiguration configuration, IEnvironmentVariableProvider provider) {

		IBuildEnvironmentVariable[] tmp = new IBuildEnvironmentVariable[1];
		tmp[0] = getVariable(VARNAME_PATH, configuration, provider);
		if (tmp[0] != null)
			return tmp;
		return null;
	}
}
