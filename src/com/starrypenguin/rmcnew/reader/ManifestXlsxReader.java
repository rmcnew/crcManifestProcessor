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

import com.starrypenguin.rmcnew.model.data.Manifest;
import com.starrypenguin.rmcnew.model.data.Record;
import com.starrypenguin.rmcnew.model.data.Records;
import com.starrypenguin.rmcnew.model.exception.SheetNotFoundException;
import com.starrypenguin.rmcnew.shared.Constants;
import com.starrypenguin.rmcnew.shared.Util;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.IOException;

public class ManifestXlsxReader extends AbstractXlsxReader {

    public Manifest readPreManifest() throws IOException, SheetNotFoundException {
        return readManifest(Constants.PREMANIFEST_SHEET);
    }

    public Manifest readFinalManifest() throws IOException, SheetNotFoundException {
        return readManifest(Constants.FINAL_MANIFEST_SHEET);
    }

    public Manifest readManifest(String sheetName) throws IOException, SheetNotFoundException {
        if (Util.notNullAndNotEmpty(sheetName)) {
            Records records = new Records();
            boolean headerSeen = false;
            XSSFSheet manifestSheet = workbook.getSheet(sheetName);
            if (manifestSheet == null) {
                throw new SheetNotFoundException("Cannot find Excel worksheet named:  " + sheetName + "\nPlease check the input file and ensure this sheet exists!");
            }
            for (Row row : manifestSheet) {
                // OML, NAME, RANK, AFSC_MOS, SERVICE_BRANCH, GENDER,
                // FINAL_DESTINATION, HUB, COUNTRY, ULN, FTN, WIAS
                String oml = Util.getCellValueAsStringOrEmptyString(row.getCell(0));
                String name = Util.getCellValueAsStringOrEmptyString(row.getCell(1));
                if (name == null || name.isEmpty()) {
                    continue; // name is a required field; do not process empty lines
                }
                String rank = Util.getCellValueAsStringOrEmptyString(row.getCell(2));
                String afscMos = Util.getCellValueAsStringOrEmptyString(row.getCell(3));
                String serviceBranch = Util.getCellValueAsStringOrEmptyString(row.getCell(4));
                String gender = Util.getCellValueAsStringOrEmptyString(row.getCell(5));
                String finalDestination = Util.getCellValueAsStringOrEmptyString(row.getCell(6));
                String hub = Util.getCellValueAsStringOrEmptyString(row.getCell(7));
                String country = Util.getCellValueAsStringOrEmptyString(row.getCell(8));
                String uln = Util.getCellValueAsStringOrEmptyString(row.getCell(9));
                String ftn = Util.getCellValueAsStringOrEmptyString(row.getCell(10));
                String wias = Util.getCellValueAsStringOrEmptyString(row.getCell(11));
//                System.out.println("oml: " + oml);
//                System.out.println("name: " + name);
//                System.out.println("rank: " + rank);
//                System.out.println("afscMos: " + afscMos);
//                System.out.println("serviceBranch: " + serviceBranch);
//                System.out.println("gender: " + gender);
//                System.out.println("finalDestination: " + finalDestination);
//                System.out.println("hub: " + hub);
//                System.out.println("country: " + country);
//                System.out.println("uln: " + uln);
//                System.out.println("ftn: " + ftn);
//                System.out.println("wias: " + wias);
                if (headerSeen) {
                    Record record = new Record(oml, name, rank, afscMos, serviceBranch, gender, finalDestination, hub, country, uln, ftn, wias);
                    records.addRecord(record);
                } else if (oml.equalsIgnoreCase(Constants.OML) &&
                        name.equalsIgnoreCase(Constants.NAME) &&
                        rank.equalsIgnoreCase(Constants.RANK) &&
                        (afscMos.equalsIgnoreCase(Constants.MOS) || (afscMos.equalsIgnoreCase(Constants.AFSC_MOS)) || (afscMos.equalsIgnoreCase(Constants.AFSC_SLASH_MOS))) &&
                        serviceBranch.equalsIgnoreCase(Constants.SERVICE_BRANCH) &&
                        gender.equalsIgnoreCase(Constants.GENDER) &&
                        finalDestination.equalsIgnoreCase(Constants.FINAL_DESTINATION) &&
                        hub.equalsIgnoreCase(Constants.HUB) &&
                        country.equalsIgnoreCase(Constants.COUNTRY) &&
                        uln.equalsIgnoreCase(Constants.ULN) &&
                        ftn.equalsIgnoreCase(Constants.FTN) &&
                        wias.equalsIgnoreCase(Constants.WIAS)) {
                    headerSeen = true;
                } else {
                    throw new IllegalArgumentException("Error in Manifest xlsx file format!");
                }
            }
            Manifest manifest = new Manifest();
            manifest.setRecords(records);
            copyInstructions(manifest);
            return manifest;
        } else {
            throw new IllegalArgumentException("Cannot read manifest using null or empty sheet name!");
        }
    }

    public void copyInstructions(Manifest manifest) {
        if (manifest != null) {
            int instructionsSheetIndex = workbook.getSheetIndex(Constants.INSTRUCTIONS_SHEET);
            if (instructionsSheetIndex >= 0) {
                manifest.setInstructions(workbook.cloneSheet(instructionsSheetIndex));
            }
        } else {
            throw new IllegalArgumentException("Cannot add instructions to a null Manifest!");
        }
    }
}
