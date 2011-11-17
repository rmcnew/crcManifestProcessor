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

package net.mcnewfamily.rmcnew.model.data;

import java.io.IOException;

public class PreManifest {

    private static PreManifest instance = null;
    private RecordList records;
    private CountryHubCountMap countryHubCountMap = new CountryHubCountMap();

    // class methods
    public static PreManifest getInstance() throws IOException {
        if (instance == null) {
            instance = new PreManifest();
        }
        return instance;
    }

    // instance methods
	private PreManifest()  {
	}

    public RecordList getRecords() {
        return records;
    }

    public void setRecords(RecordList records) {
        this.records = records;
    }

    public CountryHubCountMap getCountryHubCountMap() {
        return countryHubCountMap;
    }

    public void setCountryHubCountMap(CountryHubCountMap countryHubCountMap) {
        this.countryHubCountMap = countryHubCountMap;
    }
}
