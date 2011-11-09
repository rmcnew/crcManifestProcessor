/*
 * Copyright (c) 2011 Richard Scott McNew.
 *
 * This file is part of crcManifestProcessor.
 *
 *     crcManifestProcessor is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     crcManifestProcessor is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with crcManifestProcessor.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.mcnewfamily.rmcnew.business_rule;

import net.mcnewfamily.rmcnew.model.Record;
import net.mcnewfamily.rmcnew.shared.Util;

public class MakeAllMilitaryServiceBranchA {
    // If the person is military, make their service branch code "A" regardless of their actual branch
    private static final String ARMY = "A";
    private static final String AIR_FORCE = "AF";
    private static final String MARINE_CORP = "M";
    private static final String NAVY = "N";
    private static final String COAST_GUARD = "CG";

    public static void applyRule(Record record) {
        if (record != null) {
            String serviceBranch = record.getServiceBranch();
            if (Util.notNullAndNotEmpty(serviceBranch)) {
                if (serviceBranch.equalsIgnoreCase(AIR_FORCE) ||
                    serviceBranch.equalsIgnoreCase(MARINE_CORP) ||
                    serviceBranch.equalsIgnoreCase(NAVY) ||
                    serviceBranch.equalsIgnoreCase(COAST_GUARD)
                    ) {
                   record.setServiceBranch(ARMY);
                }
            }
        }
    }
}
