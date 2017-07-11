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

package com.starrypenguin.rmcnew.business_rule;

import com.starrypenguin.rmcnew.model.config.PriorityMOSMap;
import com.starrypenguin.rmcnew.model.data.Record;
import com.starrypenguin.rmcnew.shared.Constants;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * AfghanUnknownPriorityMosGoesToBagramTest
 * <p/>
 * Tests for AfghanUnknownPriorityMosGoesToBagram
 */
public class AfghanUnknownPriorityMosGoesToBagramTest {

    Record record;
    PriorityMOSMap mosMap;

    @Before
    public void SetupTest() {
        // create the test data
        record = new Record();
        mosMap = new PriorityMOSMap();
        mosMap.put("68W", true); // combat medic
    }

    @Test
    public void TestBusinessRuleApplied() {
        record.setCountry("Afghanistan");
        record.setFinalDestination("Unknown");
        record.setMOS("68W");
        try {
            AfghanUnknownPriorityMosGoesToBagram.applyRule(record, mosMap);
        } catch (IOException e) {
            fail("An exception should not be thrown!");
        }
        assertEquals(record.getHub(), Constants.BAGRAM);
    }

    @Test
    public void TestBusinessRuleNotApplied() {
        record.setCountry("Afghanistan");
        record.setFinalDestination("Salerno"); // Destination is not "UNKNOWN"
        record.setMOS("68W");
        try {
            AfghanUnknownPriorityMosGoesToBagram.applyRule(record, mosMap);
        } catch (IOException e) {
            fail("An exception should not be thrown!");
        }
        assertNotEquals(record.getHub(), Constants.BAGRAM);
    }

    @Test
    public void TestBusinessRuleNotApplied2() {
        record.setCountry("Afghanistan");
        record.setFinalDestination("Unknown");
        record.setMOS("88M"); // MOS is not in priority MOS map
        try {
            AfghanUnknownPriorityMosGoesToBagram.applyRule(record, mosMap);
        } catch (IOException e) {
            fail("An exception should not be thrown!");
        }
        assertNotEquals(record.getHub(), Constants.BAGRAM);
    }
}
