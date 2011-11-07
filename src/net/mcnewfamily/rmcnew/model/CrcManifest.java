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

import net.mcnewfamily.rmcnew.parser.DestinationHubMappingParser;
import net.mcnewfamily.rmcnew.parser.LocationAliasMappingParser;
import net.mcnewfamily.rmcnew.parser.PriorityMOSParser;

import java.io.IOException;
import java.util.List;

public class CrcManifest {
	private final String DESTINATION_HUB_MAP_CSV = "destinationHubMapping.csv";
    private final String LOCATION_ALIAS_MAP_CSV = "locationAlias.csv";
    private final String PRIORITY_MOS_CSV = "priorityMOS.csv";

    private static CrcManifest instance = null;
	private DestinationHubMap hubMap;
    private LocationAliasMap aliasMap;
    private PriorityMOSMap mosMap;
    private List<Record> records;

    // class methods
    public static CrcManifest getInstance() throws IOException {
        if (instance == null) {
            instance = new CrcManifest();
        }
        return instance;
    }

    // instance methods
	private CrcManifest() throws IOException {
        // parse the configuration files
		DestinationHubMappingParser hubMappingParser = new DestinationHubMappingParser();
		hubMappingParser.openCSVFile(DESTINATION_HUB_MAP_CSV);
		hubMap = hubMappingParser.parse();

        LocationAliasMappingParser aliasMappingParser = new LocationAliasMappingParser();
        aliasMappingParser.openCSVFile(LOCATION_ALIAS_MAP_CSV);
        aliasMap = aliasMappingParser.parse();
        System.out.println(aliasMap);

        PriorityMOSParser mosParser = new PriorityMOSParser();
        mosParser.openCSVFile(PRIORITY_MOS_CSV);
        mosMap = mosParser.parse();
        System.out.println(mosMap);
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

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
