/*******************************************************************************
 * Copyright (c) 2005, 2007 Intel Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Intel Corporation - Initial API and implementation
 *******************************************************************************/
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
	//static final String PROPERTY_OSNAME = "os.name"; //$NON-NLS-1$

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
