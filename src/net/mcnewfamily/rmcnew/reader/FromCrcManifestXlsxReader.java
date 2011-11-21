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

package net.mcnewfamily.rmcnew.reader;

import net.mcnewfamily.rmcnew.model.data.Record;
import net.mcnewfamily.rmcnew.model.data.Records;
import net.mcnewfamily.rmcnew.model.exception.SheetNotFoundException;
import net.mcnewfamily.rmcnew.shared.Constants;
import net.mcnewfamily.rmcnew.shared.Util;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.IOException;

public class FromCrcManifestXlsxReader extends AbstractXlsxReader {

    public Records read(String sheetName) throws IOException, SheetNotFoundException {
        if (Util.notNullAndNotEmpty(sheetName)) {
            Records records = new Records();
            boolean headerSeen = false;
            XSSFSheet manifestSheet = workbook.getSheet(sheetName);
            if (manifestSheet == null) {
                throw new SheetNotFoundException("Cannot find Excel worksheet named:  " + sheetName +"\nPlease check the input file and ensure this sheet exists!");
            }
            for (Row row : manifestSheet) {
                // OML, NAME, RANK, INTRA_THEATER_ULN, AFSC_MOS, SERVICE_BRANCH, GENDER,
                // FINAL_DESTINATION, HUB, COUNTRY, TO_THEATER_ULN, FTN, WIAS
                String oml = Util.getCellValueAsStringOrEmptyString(row.getCell(0));

                String name = Util.getCellValueAsStringOrEmptyString(row.getCell(1));
                if (name == null || name.isEmpty()) {
                    continue; // name is a required field; do not process empty lines
                }
                String rank = Util.getCellValueAsStringOrEmptyString(row.getCell(2));
                String intraTheaterUln = Util.getCellValueAsStringOrEmptyString(row.getCell(3));
                String afscMos = Util.getCellValueAsStringOrEmptyString(row.getCell(4));
                String serviceBranch = Util.getCellValueAsStringOrEmptyString(row.getCell(5));
                String gender = Util.getCellValueAsStringOrEmptyString(row.getCell(6));
                String finalDestination = Util.getCellValueAsStringOrEmptyString(row.getCell(7));
                String hub = Util.getCellValueAsStringOrEmptyString(row.getCell(8));
                String country = Util.getCellValueAsStringOrEmptyString(row.getCell(9));
                String toTheaterUln = Util.getCellValueAsStringOrEmptyString(row.getCell(10));
                String ftn = Util.getCellValueAsStringOrEmptyString(row.getCell(11));
                String wias = Util.getCellValueAsStringOrEmptyString(row.getCell(12));
                if (headerSeen) {
                    Record record = new Record(oml, name, rank, intraTheaterUln, afscMos, serviceBranch, gender, finalDestination, hub, country, toTheaterUln, ftn, wias);
                    records.addRecord(record);
                } else if (oml.equalsIgnoreCase(Constants.OML)&&
                        name.equalsIgnoreCase(Constants.NAME) &&
                        rank.equalsIgnoreCase(Constants.RANK) &&
                        intraTheaterUln.equalsIgnoreCase(Constants.INTRA_THEATER_ULN) &&
                        (afscMos.equalsIgnoreCase(Constants.MOS) || (afscMos.equalsIgnoreCase(Constants.AFSC_MOS)) ||(afscMos.equalsIgnoreCase(Constants.AFSC_SLASH_MOS)) ) &&
                        serviceBranch.equalsIgnoreCase(Constants.SERVICE_BRANCH) &&
                        gender.equalsIgnoreCase(Constants.GENDER) &&
                        finalDestination.equalsIgnoreCase(Constants.FINAL_DESTINATION) &&
                        hub.equalsIgnoreCase(Constants.HUB) &&
                        country.equalsIgnoreCase(Constants.COUNTRY) &&
                        toTheaterUln.equalsIgnoreCase(Constants.TO_THEATER_ULN) &&
                        ftn.equalsIgnoreCase(Constants.FTN) &&
                        wias.equalsIgnoreCase(Constants.WIAS) ) {
                    headerSeen = true;
                } else {
                    throw new IllegalArgumentException("Error in PreManifest xlsx file format!");
                }
            }
            return records;
        } else {
            throw new IllegalArgumentException("Cannot read manifest using null or empty sheet name!");
        }
    }
}
