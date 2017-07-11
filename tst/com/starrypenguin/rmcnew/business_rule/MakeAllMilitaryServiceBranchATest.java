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
 * MakeAllMilitaryServiceBranchATest
 * <p/>
 * Tests for MakeAllMilitaryServiceBranchA
 */
public class MakeAllMilitaryServiceBranchATest {

    Record record;

    @Before
    public void SetupTest() {
        // create the test data
        record = new Record();
    }

    @Test
    public void TestBusinessRuleApplied() {
        record.setServiceBranch(Constants.AIR_FORCE);
        MakeAllMilitaryServiceBranchA.applyRule(record);
        assertEquals(record.getServiceBranch(), Constants.ARMY);
    }

    @Test
    public void TestBusinessRuleApplied2() {
        record.setServiceBranch(Constants.AIR_FORCE2);
        MakeAllMilitaryServiceBranchA.applyRule(record);
        assertEquals(record.getServiceBranch(), Constants.ARMY);
    }

    @Test
    public void TestBusinessRuleApplied3() {
        record.setServiceBranch(Constants.MARINE_CORP);
        MakeAllMilitaryServiceBranchA.applyRule(record);
        assertEquals(record.getServiceBranch(), Constants.ARMY);
    }

    @Test
    public void TestBusinessRuleApplied4() {
        record.setServiceBranch(Constants.NAVY);
        MakeAllMilitaryServiceBranchA.applyRule(record);
        assertEquals(record.getServiceBranch(), Constants.ARMY);
    }

    @Test
    public void TestBusinessRuleApplied5() {
        record.setServiceBranch(Constants.COAST_GUARD);
        MakeAllMilitaryServiceBranchA.applyRule(record);
        assertEquals(record.getServiceBranch(), Constants.ARMY);
    }

    @Test
    public void TestBusinessRuleNotApplied() {
        record.setServiceBranch("CIVILIAN");
        MakeAllMilitaryServiceBranchA.applyRule(record);
        assertNotEquals(record.getServiceBranch(), Constants.ARMY);
    }

    @Test
    public void TestBusinessRuleNotApplied2() {
        record.setServiceBranch("CONTRACTOR");
        MakeAllMilitaryServiceBranchA.applyRule(record);
        assertNotEquals(record.getServiceBranch(), Constants.ARMY);
    }
}
