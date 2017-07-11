/*
 * Copyright (c) 2011 Richard Scott McNew.
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

import java.io.IOException;

public class AfghanUnknownPriorityMosGoesToBagram {

    /**
    *  if COUNTRY is Afghanistan and destination is UNKNOWN and MOS
    *  is from the priority list, make the HUB Bagram
    */
    public static void applyRule(Record record, PriorityMOSMap priorityMOSMap) throws IOException {
        if ((record != null) && (priorityMOSMap != null)) {
            if (record.getCountry().equalsIgnoreCase(Constants.AFGHANISTAN) &&
                    record.getFinalDestination().equalsIgnoreCase(Constants.UNKNOWN) &&
                    priorityMOSMap.get(record.getMOS())) {
                record.setHub(Constants.BAGRAM);
            }
        }
    }
}
