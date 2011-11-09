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

package net.mcnewfamily.rmcnew.reader;

import net.mcnewfamily.rmcnew.model.DestinationHubMap;
import net.mcnewfamily.rmcnew.model.LocationAliasMap;
import net.mcnewfamily.rmcnew.model.PriorityMOSMap;
import net.mcnewfamily.rmcnew.shared.Constants;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ConfigXlsxReader extends AbstractXlsxReader {

    private DestinationHubMap hubMap;
    private LocationAliasMap aliasMap;
    private PriorityMOSMap mosMap;

    public void read() {
        readDestinationHubs();
        readLocationAlias();
        readPriorityMos();
    }

    private void readDestinationHubs() {
        XSSFSheet destinationHubSheet = workbook.getSheet(Constants.DESTINATION_HUB_MAP_SHEET);
        hubMap = new DestinationHubMap();
        boolean headerSeen = false;
        for (Row row : destinationHubSheet) {
            String cell0 = row.getCell(0).getStringCellValue();
            String cell1 = row.getCell(1).getStringCellValue();
            String cell2 = row.getCell(2).getStringCellValue();
            if (headerSeen) {
                hubMap.put(cell0, cell1, cell2);
            } else if (cell0.equalsIgnoreCase(Constants.FINAL_DESTINATION) &&
					   cell1.equalsIgnoreCase(Constants.HUB) &&
					   cell2.equalsIgnoreCase(Constants.COUNTRY)) {
				headerSeen = true;
			} else {
				throw new IllegalArgumentException("Error in destinationHubMapping tab of " + Constants.CONFIGURATION_XLSX  + "!");
			}
        }
    }

    private void readLocationAlias() {
        XSSFSheet locationAliasSheet = workbook.getSheet(Constants.LOCATION_ALIAS_MAP_SHEET);
        aliasMap = new LocationAliasMap();
        boolean headerSeen = false;
        for (Row row : locationAliasSheet) {
            String cell0 = row.getCell(0).getStringCellValue();
            String cell1 = row.getCell(1).getStringCellValue();
            if (headerSeen) {
                aliasMap.put(cell0, cell1);
            } else if (cell0.equalsIgnoreCase(Constants.ALIAS) &&
					   cell1.equalsIgnoreCase(Constants.FINAL_DESTINATION)) {
				headerSeen = true;
			} else {
				throw new IllegalArgumentException("Error in locationAlias tab of " + Constants.CONFIGURATION_XLSX  + "!");
			}
        }
    }

    private void readPriorityMos() {
        XSSFSheet priorityMosSheet = workbook.getSheet(Constants.PRIORITY_MOS_SHEET);
        mosMap = new PriorityMOSMap();
        boolean headerSeen = false;
        for (Row row : priorityMosSheet) {
            String cell0 = row.getCell(0).getStringCellValue();
            if (headerSeen) {
                mosMap.put(cell0, true);
            } else if (cell0.equalsIgnoreCase(Constants.MOS)) {
				headerSeen = true;
			} else {
				throw new IllegalArgumentException("Error in priorityMOS tab of " + Constants.CONFIGURATION_XLSX  + "!");
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
}
