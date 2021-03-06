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

package com.starrypenguin.rmcnew.model.data.record_fields;

import com.starrypenguin.rmcnew.shared.Constants;

/**
 * ServiceBranchGenerator
 * <p/>
 * Generate Service Branch for test Record generation
 */
public class ServiceBranchGenerator {

    private static String[] serviceBranches = new String[6];

    static {
        serviceBranches[0] = Constants.ARMY;
        serviceBranches[1] = Constants.AIR_FORCE;
        serviceBranches[2] = Constants.AIR_FORCE2;
        serviceBranches[3] = Constants.NAVY;
        serviceBranches[4] = Constants.MARINE_CORP;
        serviceBranches[5] = Constants.COAST_GUARD;
    }

    public ServiceBranchGenerator() {
    }

    public String getRandomServiceBranch() {
        return serviceBranches[(int) (Math.random() * (serviceBranches.length - 1))];
    }

    public String getPossiblyBadServiceBranch() {
        if (Coin.flip()) {
            return getRandomServiceBranch();
        } else {
            return null;
        }
    }
}
