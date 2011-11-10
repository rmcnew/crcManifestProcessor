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

package net.mcnewfamily.rmcnew.writer;

import net.mcnewfamily.rmcnew.shared.Util;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public abstract class AbstractXlsxWriter {

    XSSFWorkbook workbook;
    FileOutputStream fileOutputStream;

    public void openXlsxForWriting(String filename) throws IOException {
        if (Util.notNullAndNotEmpty(filename) ) {
            workbook = new XSSFWorkbook();
            fileOutputStream = new FileOutputStream(filename);
        }
    }

    public void openXlsxForWriting(File file) throws IOException {
        if (file != null) {
            workbook = new XSSFWorkbook();
            fileOutputStream = new FileOutputStream(file);
        }
    }

    public void close() throws IOException {
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    public void addListToRow(XSSFRow row, List<String> list) {
        if (row != null && list != null) {
            int cellIndex = 0;
            for (String string : list) {
                XSSFCell cell = row.createCell(cellIndex++, XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(string);
            }
        }
    }

    public void addListOfListsToSheet(XSSFSheet sheet, List<List<String>> listOfLists) {
        if (sheet != null && listOfLists != null) {
            int rowIndex = 0;
            for (List<String> list : listOfLists) {
                XSSFRow row = sheet.createRow(rowIndex++);
                addListToRow(row, list);
            }
        }
    }


}
