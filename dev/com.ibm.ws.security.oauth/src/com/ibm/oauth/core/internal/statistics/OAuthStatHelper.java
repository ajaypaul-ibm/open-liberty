/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation and others.
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
package com.ibm.oauth.core.internal.statistics;

public class OAuthStatHelper {
    String _statName;
    long _startTime;

    public OAuthStatHelper(String statName) {
        _statName = statName;
        _startTime = System.currentTimeMillis();
    }

    public String getName() {
        return _statName;
    }

    public long getStartTime() {
        return _startTime;
    }

    public long getElapsedTime() {
        return System.currentTimeMillis() - _startTime;
    }

}
