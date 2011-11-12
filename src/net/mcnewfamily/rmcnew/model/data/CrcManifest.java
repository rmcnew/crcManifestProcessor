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

import net.mcnewfamily.rmcnew.model.config.DestinationHubMap;
import net.mcnewfamily.rmcnew.model.config.LocationAliasMap;
import net.mcnewfamily.rmcnew.model.config.PriorityMOSMap;
import net.mcnewfamily.rmcnew.reader.ConfigXlsxReader;
import net.mcnewfamily.rmcnew.shared.Constants;

import java.io.IOException;

public class CrcManifest {

    private static CrcManifest instance = null;
	private DestinationHubMap hubMap;
    private LocationAliasMap aliasMap;
    private PriorityMOSMap mosMap;
    private RecordList records;
    private CountryHubCountMap countryHubCountMap = new CountryHubCountMap();

    // class methods
    public static CrcManifest getInstance() throws IOException {
        if (instance == null) {
            instance = new CrcManifest();
        }
        return instance;
    }

    // instance methods
	private CrcManifest() throws IOException {
        ConfigXlsxReader configXlsxReader = new ConfigXlsxReader();
        configXlsxReader.openXlsxFile(Constants.CONFIGURATION_XLSX);
        configXlsxReader.read();
        hubMap = configXlsxReader.getHubMap();
        aliasMap = configXlsxReader.getAliasMap();
        mosMap = configXlsxReader.getMosMap();
	}

    public DestinationHubMap getHubMap() {
        return hubMap;
    }

    public LocationAliasMap getAliasMap() {
        return aliasMap;
    }

    public PriorityMOSMap getMosMap() {
        return mosMap;
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
