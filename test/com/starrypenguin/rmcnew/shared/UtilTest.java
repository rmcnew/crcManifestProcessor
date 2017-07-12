/*
 * Copyright (c) 2017 Richard Scott McNew.
 *
 * This file is part of CRC Manifest Processor.
 *
 *     CRC Manifest Processor is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     CRC Manifest Processor is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with CRC Manifest Processor.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.starrypenguin.rmcnew.shared;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * UtilTest
 * <p/>
 * Tests for Util class
 */
public class UtilTest {

    @Test
    public void stripLocationPrefixesAndSuffixesTest() {
        // remove destination prefixes: CAMP, FOB, COP, FORWARD OPERATING BASE, FIRE BASE, COMBAT OUTPOST, ANP, etc.
        // "^CAMP|^COB|^FOB|^COP|^FB|^ABP|^PB|^FORWARD OPERATING BASE|^FIRE BASE|^COMBAT OUTPOST|^ANP|^OP|^VSO|^VSP|^RCC
        assertEquals(Util.stripLocationPrefixesAndSuffixes("CAMP VICTORY"), "VICTORY");
        assertEquals(Util.stripLocationPrefixesAndSuffixes("COB VICTORY"), "VICTORY");
        assertEquals(Util.stripLocationPrefixesAndSuffixes("FOB VICTORY"), "VICTORY");
        assertEquals(Util.stripLocationPrefixesAndSuffixes("FB VICTORY"), "VICTORY");
        assertEquals(Util.stripLocationPrefixesAndSuffixes("ABP VICTORY"), "VICTORY");
        assertEquals(Util.stripLocationPrefixesAndSuffixes("PB VICTORY"), "VICTORY");
        assertEquals(Util.stripLocationPrefixesAndSuffixes("FORWARD OPERATING BASE VICTORY"), "VICTORY");
        assertEquals(Util.stripLocationPrefixesAndSuffixes("FIRE BASE VICTORY"), "VICTORY");
        assertEquals(Util.stripLocationPrefixesAndSuffixes("COMBAT OUTPOST VICTORY"), "VICTORY");
        assertEquals(Util.stripLocationPrefixesAndSuffixes("ANP VICTORY"), "VICTORY");
        assertEquals(Util.stripLocationPrefixesAndSuffixes("OP VICTORY"), "VICTORY");
        assertEquals(Util.stripLocationPrefixesAndSuffixes("VSO VICTORY"), "VICTORY");
        assertEquals(Util.stripLocationPrefixesAndSuffixes("VSP VICTORY"), "VICTORY");
        assertEquals(Util.stripLocationPrefixesAndSuffixes("RCC VICTORY"), "VICTORY");

        // remove destination suffixes: AIRFIELD, AIRBASE, ANP, OP, etc.
        // |ANP$|AFLD$|AFB$|DC$|OP$|PRT$|AIRBASE$|AIR BASE$|AIRFIELD$|AIR FIELD$";
        assertEquals(Util.stripLocationPrefixesAndSuffixes("VICTORY ANP"), "VICTORY");
        assertEquals(Util.stripLocationPrefixesAndSuffixes("VICTORY AFLD"), "VICTORY");
        assertEquals(Util.stripLocationPrefixesAndSuffixes("VICTORY AFB"), "VICTORY");
        assertEquals(Util.stripLocationPrefixesAndSuffixes("VICTORY DC"), "VICTORY");
        assertEquals(Util.stripLocationPrefixesAndSuffixes("VICTORY OP"), "VICTORY");
        assertEquals(Util.stripLocationPrefixesAndSuffixes("VICTORY PRT"), "VICTORY");
        assertEquals(Util.stripLocationPrefixesAndSuffixes("VICTORY AIRBASE"), "VICTORY");
        assertEquals(Util.stripLocationPrefixesAndSuffixes("VICTORY AIR BASE"), "VICTORY");
        assertEquals(Util.stripLocationPrefixesAndSuffixes("VICTORY AIRFIELD"), "VICTORY");
        assertEquals(Util.stripLocationPrefixesAndSuffixes("VICTORY AIR FIELD"), "VICTORY");
    }

    @Test
    public void getCellValueAsStringOrEmptyStringTest() {

        // Prepare test data

    }
}
