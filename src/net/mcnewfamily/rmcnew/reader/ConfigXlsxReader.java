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
import net.mcnewfamily.rmcnew.shared.Constants;
import net.mcnewfamily.rmcnew.shared.Util;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ConfigXlsxReader {

    private XSSFWorkbook workbook;

    public void openXlsxFile(String filename) throws IOException {
        if (Util.notNullAndNotEmpty(filename) ) {
            workbook = new XSSFWorkbook(new FileInputStream(filename));
        }
    }

    public void openXlsxFile(File file) throws IOException {
        if (file != null) {
            workbook = new XSSFWorkbook(new FileInputStream(file));
        }
    }

    public void read() {
        // get the hub mapping and read it
        XSSFSheet destinationHubSheet = workbook.getSheet(Constants.DESTINATION_HUB_MAP_SHEET);
        DestinationHubMap hubMap = new DestinationHubMap();
        for (Row row : destinationHubSheet) {
            for (Cell cell : row) {
              // do stuff here
            }
        }
    }
}
