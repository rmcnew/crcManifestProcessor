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

package net.mcnewfamily.rmcnew.model;

import net.mcnewfamily.rmcnew.reader.DestinationHubCsvReader;
import net.mcnewfamily.rmcnew.reader.LocationAliasCsvReader;
import net.mcnewfamily.rmcnew.reader.PriorityMosCsvReader;
import net.mcnewfamily.rmcnew.shared.Constants;

import java.io.IOException;

public class CrcManifest {

    private static CrcManifest instance = null;
	private DestinationHubMap hubMap;
    private LocationAliasMap aliasMap;
    private PriorityMOSMap mosMap;
    private RecordList records;

    // class methods
    public static CrcManifest getInstance() throws IOException {
        if (instance == null) {
            instance = new CrcManifest();
        }
        return instance;
    }

    // instance methods
	private CrcManifest() throws IOException {
        // read the configuration files
		DestinationHubCsvReader hubCSVParser = new DestinationHubCsvReader();
		hubCSVParser.openCsvFile(Constants.DESTINATION_HUB_MAP_CSV);
		hubMap = hubCSVParser.read();

        LocationAliasCsvReader aliasCSVParser = new LocationAliasCsvReader();
        aliasCSVParser.openCsvFile(Constants.LOCATION_ALIAS_MAP_CSV);
        aliasMap = aliasCSVParser.read();

        PriorityMosCsvReader MosCsvParser = new PriorityMosCsvReader();
        MosCsvParser.openCsvFile(Constants.PRIORITY_MOS_CSV);
        mosMap = MosCsvParser.read();
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
}
