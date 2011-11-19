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

package net.mcnewfamily.rmcnew.writer;

import net.mcnewfamily.rmcnew.model.data.CountryHubCountMap;
import net.mcnewfamily.rmcnew.model.data.Manifest;
import net.mcnewfamily.rmcnew.model.data.Records;
import net.mcnewfamily.rmcnew.shared.Constants;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class FinalManifestXlsxWriter extends AbstractXlsxWriter {

    public void writeFinalManifest(Manifest finalManifest) {
        if (finalManifest != null) {
            writeRecords(finalManifest.getRecords());
            writeSummaryTable(finalManifest.getCountryHubCountMap());
        } else {
            throw new IllegalArgumentException("Cannot write Final Manifest for null FinalManifest model!");
        }
    }

    private void writeRecords(Records records) {
        if (records != null && !records.isEmpty()) {
            XSSFSheet premanifestSheet = records.toSheetEssence(Constants.FINAL_MANIFEST_SHEET).toXSSFSheet(workbook);
            for (int columnIndex = 0; columnIndex < 8; columnIndex++) {
                premanifestSheet.autoSizeColumn(columnIndex);
            }
        } else {
            throw new IllegalArgumentException("Cannot create XLSX sheet from null or empty Records!");
        }
    }

    private void writeSummaryTable(CountryHubCountMap countryHubCountMap) {
        if (countryHubCountMap != null && !countryHubCountMap.isEmpty()) {
            XSSFSheet finalManifestCountsSheet = countryHubCountMap.toSheetEssence(Constants.FINAL_MANIFEST_COUNTS_SHEET).toXSSFSheet(workbook);
            for (int columnIndex = 0; columnIndex < 4; columnIndex++) {
                finalManifestCountsSheet.autoSizeColumn(columnIndex);
            }
        } else {
            throw new IllegalArgumentException("Cannot create XLSX sheet from null or empty CountryHubCountMap!");
        }
    }
}
