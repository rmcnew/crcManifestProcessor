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

package com.starrypenguin.rmcnew.reader;

import com.starrypenguin.rmcnew.model.config.*;
import com.starrypenguin.rmcnew.shared.Constants;
import com.starrypenguin.rmcnew.shared.Util;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ConfigXlsxReader extends AbstractXlsxReader {

    private DestinationHubMap hubMap;
    private LocationAliasMap aliasMap;
    private PriorityMOSMap mosMap;
    private RankComparisonMap rankComparisonMap;
    private HubsWithoutUlnsMap hubsWithoutUlnsMap;

    public void read() {
        readDestinationHubs();
        readLocationAlias();
        readPriorityMos();
        readRankComparison();
        readHubsWithoutUlns();
    }

    private void readDestinationHubs() {
        XSSFSheet destinationHubSheet = workbook.getSheet(Constants.DESTINATION_HUB_MAP_SHEET);
        hubMap = new DestinationHubMap();
        boolean headerSeen = false;
        for (Row row : destinationHubSheet) {
            String cell0 = Util.getCellValueAsStringOrEmptyString(row.getCell(0));
            String cell1 = Util.getCellValueAsStringOrEmptyString(row.getCell(1));
            String cell2 = Util.getCellValueAsStringOrEmptyString(row.getCell(2));
            if (headerSeen) {
                if (Util.notNullAndNotEmpty(cell0) && Util.notNullAndNotEmpty(cell1) && Util.notNullAndNotEmpty(cell2)) {
                    hubMap.put(cell0, cell1, cell2);
                }
            } else if (cell0.equalsIgnoreCase(Constants.FINAL_DESTINATION) &&
					   cell1.equalsIgnoreCase(Constants.HUB) &&
					   cell2.equalsIgnoreCase(Constants.COUNTRY)) {
				headerSeen = true;
			} else {
				throw new IllegalArgumentException("Error in " + Constants.DESTINATION_HUB_MAP_SHEET  + " tab of " + Constants.CONFIGURATION_XLSX  + "!");
			}
        }
    }

    private void readLocationAlias() {
        XSSFSheet locationAliasSheet = workbook.getSheet(Constants.LOCATION_ALIAS_MAP_SHEET);
        aliasMap = new LocationAliasMap();
        boolean headerSeen = false;
        for (Row row : locationAliasSheet) {
            String cell0 = Util.getCellValueAsStringOrEmptyString(row.getCell(0));
            String cell1 = Util.getCellValueAsStringOrEmptyString(row.getCell(1));
            if (headerSeen) {
                if (Util.notNullAndNotEmpty(cell0) && Util.notNullAndNotEmpty(cell1)) {
                    aliasMap.put(cell0, cell1);
                }
            } else if (cell0.equalsIgnoreCase(Constants.ALIAS) &&
					   cell1.equalsIgnoreCase(Constants.FINAL_DESTINATION)) {
				headerSeen = true;
			} else {
				throw new IllegalArgumentException("Error in " + Constants.LOCATION_ALIAS_MAP_SHEET  + " tab of " + Constants.CONFIGURATION_XLSX  + "!");
			}
        }
    }

    private void readPriorityMos() {
        XSSFSheet priorityMosSheet = workbook.getSheet(Constants.PRIORITY_MOS_SHEET);
        mosMap = new PriorityMOSMap();
        boolean headerSeen = false;
        for (Row row : priorityMosSheet) {
            String cell0 = Util.getCellValueAsStringOrEmptyString(row.getCell(0));
            if (headerSeen) {
                if (Util.notNullAndNotEmpty(cell0)) {
                    mosMap.put(cell0, true);
                }
            } else if (cell0.equalsIgnoreCase(Constants.MOS)) {
				headerSeen = true;
			} else {
				throw new IllegalArgumentException("Error in " + Constants.PRIORITY_MOS_SHEET  + " tab of " + Constants.CONFIGURATION_XLSX  + "!");
			}
        }
    }

    private void readRankComparison() {
        XSSFSheet rankComparisonSheet = workbook.getSheet(Constants.RANK_COMPARISON_SHEET);
        rankComparisonMap = new RankComparisonMap();
        boolean headerSeen = false;
        for (Row row : rankComparisonSheet) {
            String cell0 = Util.getCellValueAsStringOrEmptyString(row.getCell(0));
            String cell1 = Util.getCellValueAsStringOrEmptyString(row.getCell(1));
            if (headerSeen)  {
                if (Util.notNullAndNotEmpty(cell0) && Util.notNullAndNotEmpty(cell1)) {
                    rankComparisonMap.put(cell0, Integer.parseInt(cell1));
                }
            } else if (cell0.equalsIgnoreCase(Constants.RANK) &&
					   cell1.equalsIgnoreCase(Constants.LEVEL)) {
				headerSeen = true;
			} else {
				throw new IllegalArgumentException("Error in " + Constants.RANK_COMPARISON_SHEET + " tab of " + Constants.CONFIGURATION_XLSX  + "!");
			}
        }
    }

    private void readHubsWithoutUlns() {
        XSSFSheet hubsWithoutUlnsSheet = workbook.getSheet(Constants.HUBS_WITHOUT_ULNS_SHEET);
        hubsWithoutUlnsMap = new HubsWithoutUlnsMap();
        boolean headerSeen = false;
        for (Row row : hubsWithoutUlnsSheet) {
            String cell0 = Util.getCellValueAsStringOrEmptyString(row.getCell(0));
            if (headerSeen) {
                if (Util.notNullAndNotEmpty(cell0)) {
                    hubsWithoutUlnsMap.put(cell0, true);
                }
            } else if (cell0.equalsIgnoreCase(Constants.HUBS_WITHOUT_ULNS)) {
				headerSeen = true;
			} else {
				throw new IllegalArgumentException("Error in " + Constants.HUBS_WITHOUT_ULNS_SHEET  + " tab of " + Constants.CONFIGURATION_XLSX  + "!");
			}
        }
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

    public RankComparisonMap getRankComparisonMap() {
        return rankComparisonMap;
    }

    public HubsWithoutUlnsMap getHubsWithoutUlnsMap() {
        return hubsWithoutUlnsMap;
    }
}
