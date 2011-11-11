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

import net.mcnewfamily.rmcnew.model.data.Record;
import net.mcnewfamily.rmcnew.shared.Constants;
import net.mcnewfamily.rmcnew.shared.Util;

public class MakeAllMilitaryServiceBranchA {
    // If the person is military, make their service branch code "A" regardless of their actual branch
    public static void applyRule(Record record) {
        if (record != null) {
            String serviceBranch = record.getServiceBranch();
            if (Util.notNullAndNotEmpty(serviceBranch)) {
                if (serviceBranch.equalsIgnoreCase(Constants.AIR_FORCE) ||
                    serviceBranch.equalsIgnoreCase(Constants.MARINE_CORP) ||
                    serviceBranch.equalsIgnoreCase(Constants.NAVY) ||
                    serviceBranch.equalsIgnoreCase(Constants.COAST_GUARD)
                    ) {
                   record.setServiceBranch(Constants.ARMY);
                }
            }
        }
    }
}
