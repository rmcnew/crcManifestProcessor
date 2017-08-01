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

import com.starrypenguin.rmcnew.model.config.CrcManifestProcessorConfig;
import com.starrypenguin.rmcnew.model.config.HubCountry;

import java.util.Map;
import java.util.Set;

/**
 * FinalDestinationCountryGenerator
 * <p/>
 * Generate Final Destination and Country for test Record generation
 */
public class FinalDestinationCountryGenerator {

    private DestinationCountry[] destinationCountries;

    public FinalDestinationCountryGenerator(CrcManifestProcessorConfig config) {
        Set<Map.Entry<String, HubCountry>> entries = config.getHubMap().entrySet();
        destinationCountries = new DestinationCountry[entries.size()];
        int index = 0;
        for (Map.Entry<String, HubCountry> entry : entries) {
            destinationCountries[index].destination = entry.getKey();
            destinationCountries[index].country = entry.getValue().getCountry();
        }
    }

    public DestinationCountry getRandomDestinationCountry() {
        return destinationCountries[(int) (Math.random() * (destinationCountries.length - 1))];
    }
}
