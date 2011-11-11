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

import net.mcnewfamily.rmcnew.model.data.Record;
import net.mcnewfamily.rmcnew.model.data.RecordList;
import net.mcnewfamily.rmcnew.shared.Constants;
import net.mcnewfamily.rmcnew.shared.Util;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.IOException;

public class FromCrcPremanifestXlsxReader extends AbstractXlsxReader {

    public RecordList read() throws IOException {
        RecordList records = new RecordList();
        boolean headerSeen = false;
        XSSFSheet premanifestSheet = workbook.getSheet(Constants.PREMANIFEST_SHEET);
        for (Row row : premanifestSheet) {
            // NAME, RANK, MOS, SERVICE_BRANCH, GENDER, FINAL_DESTINATION, HUB, COUNTRY
            String cell0 = Util.getCellValueAsStringOrEmptyString(row.getCell(0)); // name is a required field
            if (cell0 == null || cell0.isEmpty()) {
                continue; // do not process empty lines
            }
            String cell1 = Util.getCellValueAsStringOrEmptyString(row.getCell(1)); // rank is a required field
            String cell2 = Util.getCellValueAsStringOrEmptyString(row.getCell(2));
            String cell3 = Util.getCellValueAsStringOrEmptyString(row.getCell(3)); // service branch is a required field
            String cell4 = Util.getCellValueAsStringOrEmptyString(row.getCell(4)); // gender is a required field
            String cell5 = Util.getCellValueAsStringOrEmptyString(row.getCell(5));
            String cell6 = Util.getCellValueAsStringOrEmptyString(row.getCell(6));
            String cell7 = Util.getCellValueAsStringOrEmptyString(row.getCell(7));
            if (headerSeen) {
                Record record = new Record(cell0, cell1, cell2, cell3, cell4, cell5, cell6, cell7);
                records.add(record);
            } else if (cell0.equalsIgnoreCase(Constants.NAME) &&
					   cell1.equalsIgnoreCase(Constants.RANK) &&
					   (cell2.equalsIgnoreCase(Constants.MOS) || (cell2.equalsIgnoreCase(Constants.AFSC_MOS)) ) &&
                       cell3.equalsIgnoreCase(Constants.SERVICE_BRANCH) &&
                       cell4.equalsIgnoreCase(Constants.GENDER) &&
                       cell5.equalsIgnoreCase(Constants.FINAL_DESTINATION) &&
                       cell6.equalsIgnoreCase(Constants.HUB) &&
                       cell7.equalsIgnoreCase(Constants.COUNTRY) ) { 
				headerSeen = true;
			} else {
				throw new IllegalArgumentException("Error in premanifest xlsx file format!");
			}     
        }
	    return records;	
    }
}
