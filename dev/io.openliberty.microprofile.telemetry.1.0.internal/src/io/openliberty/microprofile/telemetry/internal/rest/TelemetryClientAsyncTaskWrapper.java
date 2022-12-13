/*******************************************************************************
 * Copyright (c) 2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package io.openliberty.microprofile.telemetry.internal.rest;

import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import io.openliberty.restfulWS.client.ClientAsyncTaskWrapper;
import io.opentelemetry.context.Context;

/**
 * Ensures that the OTel context is used when either JAX-RS client or MP Rest Client makes an async request.
 */
@Component(configurationPolicy = ConfigurationPolicy.IGNORE)
public class TelemetryClientAsyncTaskWrapper implements ClientAsyncTaskWrapper {

    /** {@inheritDoc} */
    @Override
    public Runnable wrap(Runnable r) {
        return Context.current().wrap(r);
    }

    /** {@inheritDoc} */
    @Override
    public <T> Callable<T> wrap(Callable<T> c) {
        return Context.current().wrap(c);
    }

}