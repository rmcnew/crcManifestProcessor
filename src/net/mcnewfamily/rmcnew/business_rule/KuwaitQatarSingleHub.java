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

package net.mcnewfamily.rmcnew.business_rule;

import net.mcnewfamily.rmcnew.model.data.Record;
import net.mcnewfamily.rmcnew.shared.Constants;

public class KuwaitQatarSingleHub {
    /**
     * Kuwait and Qatar both have single hubs.  If the destination is UNKNOWN or the
     * hub is NOT_FOUND and the country is Kuwait, make the hub ALI AL SALEM.
     * If the destination is UNKNOWN or the hub is NOT_FOUND and the country is
     * Qatar, make the hub AL UDEID.
     * @param record the record to which the business rule is applied
     */
    public static void applyRule(Record record) {
        if ( (record.getFinalDestination().equalsIgnoreCase(Constants.UNKNOWN)) || record.getHub().equals(Constants.NOT_FOUND)) {
            if (record.getCountry().equals(Constants.KUWAIT)) {
                record.setHub(Constants.ALI_AL_SALEM);
            } else if (record.getCountry().equals(Constants.QATAR)) {
                record.setHub(Constants.AL_UDEID);
            }
        }
    }
}
