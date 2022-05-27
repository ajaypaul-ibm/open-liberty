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

package io.openliberty.jpa.tests.jpa31.models;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 */
public class XMLUUID_IDClass implements Serializable {

    private UUID uuid_id;
    private long l_id;

    public XMLUUID_IDClass() {

    }

    /**
     * @return the uuid_id
     */
    public UUID getUuid_id() {
        return uuid_id;
    }

    /**
     * @param uuid_id the uuid_id to set
     */
    public void setUuid_id(UUID uuid_id) {
        this.uuid_id = uuid_id;
    }

    /**
     * @return the l_id
     */
    public long getL_id() {
        return l_id;
    }

    /**
     * @param l_id the l_id to set
     */
    public void setL_id(long l_id) {
        this.l_id = l_id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (l_id ^ (l_id >>> 32));
        result = prime * result + ((uuid_id == null) ? 0 : uuid_id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        XMLUUID_IDClass other = (XMLUUID_IDClass) obj;
        if (l_id != other.l_id)
            return false;
        if (uuid_id == null) {
            if (other.uuid_id != null)
                return false;
        } else if (!uuid_id.equals(other.uuid_id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "XMLUUID_IDClass [uuid_id=" + uuid_id + ", l_id=" + l_id + "]";
    }

}
