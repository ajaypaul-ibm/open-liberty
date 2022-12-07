/*******************************************************************************
 * Copyright (c) 2021 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package fats.cxf.basic.wssec;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2015-09-20T12:23:38.043-05:00
 * Generated source version: 2.6.2
 * 
 */
@WebServiceClient(name = "SAMLAsyncX509Service", 
                  wsdlLocation = "SamlTokenWebSvc.wsdl",
                  targetNamespace = "http://wssec.basic.cxf.fats") 
public class SAMLAsyncX509Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://wssec.basic.cxf.fats", "SAMLAsyncX509Service");
    public final static QName SAMLAsyncX509Port = new QName("http://wssec.basic.cxf.fats", "SAMLAsyncX509Port");
    static {
        URL url = SAMLAsyncX509Service.class.getResource("SamlTokenWebSvc.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(SAMLAsyncX509Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "SamlTokenWebSvc.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public SAMLAsyncX509Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SAMLAsyncX509Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SAMLAsyncX509Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    /**
     *
     * @return
     *     returns SamlTokenType
     */
    @WebEndpoint(name = "SAMLAsyncX509Port")
    public SamlTokenType getSAMLAsyncX509Port() {
        return super.getPort(SAMLAsyncX509Port, SamlTokenType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SamlTokenType
     */
    @WebEndpoint(name = "SAMLAsyncX509Port")
    public SamlTokenType getSAMLAsyncX509Port(WebServiceFeature... features) {
        return super.getPort(SAMLAsyncX509Port, SamlTokenType.class, features);
    }

}