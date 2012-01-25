/**
 * Distribution License:
 * JSword is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License, version 2.1 as published by
 * the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * The License is available on the internet at:
 *       http://www.gnu.org/copyleft/lgpl.html
 * or by writing to:
 *      Free Software Foundation, Inc.
 *      59 Temple Place - Suite 330
 *      Boston, MA 02111-1307, USA
 *
 * Copyright: 2012
 *     The copyright to this program is held by it's authors.
 *
 * ID: $Id$
 */
package org.crosswire.jsword.versification.system;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.crosswire.jsword.versification.Versification;

/**
 * The Versifications class manages the creation of Versifications as needed.
 * It delays the construction of the Versification until getVersification(String name) is called.
 *
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public class Versifications {

    /**
     * Get the singleton instance of Versifications.
     * 
     * @return the singleton
     */
    public static Versifications instance() {
        return instance;
    }

    /**
     * Get the Versification by its name. If name is null then return the default Versification.
     * 
     * @param name the name of the Versification
     * @return the Versification or null if it is not known.
     */
    public synchronized Versification getVersification(String name) {
        String actual = name;
        if (actual == null) {
            actual = DEFAULT_REFERENCE_SYSTEM;
        }

        // This class delays the building of a Versification to when it is
        // actually needed.
        Versification rs = fluffed.get(actual);
        if (rs == null) {
            rs = fluff(actual);
            if (rs != null) {
                fluffed.put(actual, rs);
            }
        }

        return rs;
    }

    /**
     * Determine whether the named Versification is known.
     * 
     * @param name the name of the Versification
     * @return true when the Versification is available for use
     */
    public synchronized boolean isDefined(String name) {
        return name == null || known.contains(name);
    }

    private Versification fluff(String name) {
        if (SystemKJV.name.equals(name)) {
            return new SystemKJV();
        }
        if (SystemCatholic.name.equals(name)) {
            return new SystemCatholic();
        }
        if (SystemCatholic2.name.equals(name)) {
            return new SystemCatholic2();
        }
        if (SystemKJVA.name.equals(name)) {
            return new SystemKJVA();
        }
        if (SystemGerman.name.equals(name)) {
            return new SystemGerman();
        }
        if (SystemLeningrad.name.equals(name)) {
            return new SystemLeningrad();
        }
        if (SystemLuther.name.equals(name)) {
            return new SystemLuther();
        }
        if (SystemMT.name.equals(name)) {
            return new SystemMT();
        }
        if (SystemNRSV.name.equals(name)) {
            return new SystemNRSV();
        }
        if (SystemNRSVA.name.equals(name)) {
            return new SystemNRSVA();
        }
        if (SystemSynodal.name.equals(name)) {
            return new SystemSynodal();
        }
        if (SystemSynodalP.name.equals(name)) {
            return new SystemSynodalP();
        }
        return null;
    }

    /**
     * Add a Versification that is not predefined by JSword.
     * 
     * @param rs the Versification to register
     */
    public synchronized void register(Versification rs) {
        fluffed.put(rs.getName(), rs);
        known.add(rs.getName());
    }

    /**
     * This class is a singleton, enforced by a private constructor.
     */
    private Versifications() {
        known = new HashSet<String>();
        known.add(SystemCatholic.name);
        known.add(SystemCatholic2.name);
        known.add(SystemKJV.name);
        known.add(SystemGerman.name);
        known.add(SystemKJVA.name);
        known.add(SystemLeningrad.name);
        known.add(SystemLuther.name);
        known.add(SystemMT.name);
        known.add(SystemNRSV.name);
        known.add(SystemNRSVA.name);
        known.add(SystemSynodal.name);
        known.add(SystemSynodalP.name);
        known.add(SystemVulg.name);
        fluffed = new HashMap<String, Versification>();
    }

    /**
     * The default Versification for JSword is the KJV.
     * This is subject to change at any time.
     */
    private final String DEFAULT_REFERENCE_SYSTEM = "KJV";

    /**
     * The set of v11n names.
     */
    private Set<String> known;

    /**
     * The map of instantiated Versifications, given by their names.
     */
    private Map<String, Versification> fluffed;

    private static final Versifications instance = new Versifications();
}
