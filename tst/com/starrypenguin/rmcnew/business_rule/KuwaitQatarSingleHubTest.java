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

import com.starrypenguin.rmcnew.model.data.Record;
import com.starrypenguin.rmcnew.shared.Constants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * KuwaitQatarSingleHubTest
 * <p/>
 * Tests for KuwaitQatarSingleHub
 */
public class KuwaitQatarSingleHubTest {

    Record record;

    @Before
    public void SetupTest() {
        // create the test data
        record = new Record();
    }

    @Test
    public void TestBusinessRuleApplied() {
        record.setCountry("Kuwait");
        record.setFinalDestination("Unknown");
        KuwaitQatarSingleHub.applyRule(record);
        assertEquals(record.getHub(), Constants.ALI_AL_SALEM);
    }

    @Test
    public void TestBusinessRuleApplied2() {
        record.setCountry("Kuwait");
        record.setFinalDestination("Cross-Fit Gym");
        record.setHub("Not_Found");
        KuwaitQatarSingleHub.applyRule(record);
        assertEquals(record.getHub(), Constants.ALI_AL_SALEM);
    }

    @Test
    public void TestBusinessRuleApplied3() {
        record.setCountry("Qatar");
        record.setFinalDestination("Unknown");
        KuwaitQatarSingleHub.applyRule(record);
        assertEquals(record.getHub(), Constants.AL_UDEID);
    }

    @Test
    public void TestBusinessRuleApplied4() {
        record.setCountry("Qatar");
        record.setFinalDestination("Cross-Fit Gym");
        record.setHub("Not_Found");
        KuwaitQatarSingleHub.applyRule(record);
        assertEquals(record.getHub(), Constants.AL_UDEID);
    }

    @Test
    public void TestBusinessRuleNotApplied() {
        record.setCountry("Afghanistan");
        record.setFinalDestination("Unknown");
        KuwaitQatarSingleHub.applyRule(record);
        assertNotEquals(record.getHub(), Constants.ALI_AL_SALEM);
    }

    @Test
    public void TestBusinessRuleNotApplied2() {
        record.setCountry("Iraq");
        record.setFinalDestination("Unknown");
        KuwaitQatarSingleHub.applyRule(record);
        assertNotEquals(record.getHub(), Constants.AL_UDEID);
    }
}
