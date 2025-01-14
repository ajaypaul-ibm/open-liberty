/*******************************************************************************
 * Copyright (c) 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.jaxws.clientcontainer.security;

import java.util.ResourceBundle;

import com.ibm.websphere.ssl.Constants;

public class JaxWsSecurityConstants {
    public static final String TR_GROUP = "JaxWsSecurity";

    public static final String TR_RESOURCE_BUNDLE = "com.ibm.ws.jaxws.security.internal.resources.JaxWsSecurityMessages";

    public static final ResourceBundle messages = ResourceBundle.getBundle(TR_RESOURCE_BUNDLE);

    public static final String SERVER_DEFAULT_SSL_CONFIG_ALIAS = "defaultSSLConfig";

    public static final String CLIENT_KEY_STORE_ALIAS = Constants.SSLPROP_KEY_STORE_CLIENT_ALIAS;
}
