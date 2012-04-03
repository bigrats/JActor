/*
 * Copyright 2011 Bill La Forge
 *
 * This file is part of AgileWiki and is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License (LGPL) as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 * or navigate to the following url http://www.gnu.org/licenses/lgpl-2.1.txt
 *
 * Note however that only Scala, Java and JavaScript files are being covered by LGPL.
 * All other files are covered by the Common Public License (CPL).
 * A copy of this license is also included and can be
 * found as well at http://www.opensource.org/licenses/cpl1.0.txt
 */
package org.agilewiki.jactor.properties;

import org.agilewiki.jactor.Actor;
import org.agilewiki.jactor.lpc.ConcurrentRequest;

/**
 * This request assigns a value to a property.
 */
public class SetProperty<RESPONSE_TYPE>
        extends ConcurrentRequest<Object, Properties> {
    /**
     * The name of the property.
     */
    private String propertyName;

    /**
     * The value of the property.
     */
    private RESPONSE_TYPE propertyValue;

    /**
     * Create a SetProperty request.
     *
     * @param propertyName  The name of the property.
     * @param propertyValue The value of the property.
     */
    public SetProperty(String propertyName, RESPONSE_TYPE propertyValue) {
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

    /**
     * Returns the property name/
     *
     * @return The property name.
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Returns the property value.
     *
     * @return The property value, or null.
     */
    public RESPONSE_TYPE getPropertyValue() {
        return propertyValue;
    }

    /**
     * Send a synchronous request.
     *
     * @param targetActor The target actor.
     * @return The response.
     * @throws Exception Any uncaught exceptions raised while processing the request.
     */
    @Override
    public Object call(Properties targetActor)
            throws Exception {
        targetActor.setProperty(propertyName, propertyValue);
        return null;
    }

    /**
     * Returns true when targetActor is an instanceof TARGET_TYPE
     *
     * @param targetActor The actor to be called.
     * @return True when targetActor is an instanceof TARGET_TYPE.
     */
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof Properties;
    }
}
