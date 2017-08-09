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

package com.starrypenguin.rmcnew.writer;

import com.starrypenguin.rmcnew.model.data.Manifest;
import com.starrypenguin.rmcnew.model.data.Records;
import com.starrypenguin.rmcnew.shared.Constants;
import com.starrypenguin.rmcnew.shared.Util;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class AbstractXlsxWriter {

    XSSFWorkbook workbook = null;
    FileOutputStream fileOutputStream = null;

    public void openXlsxForWriting(File file) throws IOException {
        if (file != null) {
            if ((workbook == null) && (fileOutputStream == null)) {
                workbook = new XSSFWorkbook();
                fileOutputStream = new FileOutputStream(file);
            } else {
                throw new IllegalStateException("File already open for writing!");
            }
        } else {
            throw new IllegalArgumentException("Output file cannot be null!");
        }
    }

    public void close() throws IOException {
        if ((workbook != null) && (fileOutputStream != null)) {
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            workbook = null;
            fileOutputStream = null;
        } else {
            throw new IllegalStateException("Cannot close a file is that is not open!");
        }
    }

    public void writeRecords(Records records, String sheetName) {
        if ((records != null) && (!records.isEmpty())) {
            if ((workbook != null) && (fileOutputStream != null)) {
                XSSFSheet manifestSheet = records.toSheetEssence(sheetName).toXSSFSheet(workbook);
                for (int columnIndex = 0; columnIndex < 13; columnIndex++) {
                    manifestSheet.autoSizeColumn(columnIndex);
                }
            } else {
                throw new IllegalStateException("File is not open!  Cannot write records to unopened file.");
            }
        } else {
            if (records == null) {
                System.err.println("records is null!");
            } else if (records.isEmpty()) {
                System.err.println("records is empty!");
            }
            throw new IllegalArgumentException("Cannot create XLSX sheet from null or empty Records!");
        }
    }

    protected void writeSummaryTable(Manifest manifest, String sheetName) {
        if (manifest != null) {
            if ((workbook != null) && (fileOutputStream != null)) {
                XSSFSheet manifestCountsSheet = manifest.toSheetEssence(sheetName).toXSSFSheet(workbook);
                for (int columnIndex = 0; columnIndex < 4; columnIndex++) {
                    manifestCountsSheet.autoSizeColumn(columnIndex);
                }
            } else {
                throw new IllegalStateException("Cannot write summary table to  a file is that is not open!");
            }
        } else {
            throw new IllegalArgumentException("Cannot create XLSX sheet from null or empty manifest!");
        }
    }

    protected void writeInstructions(Manifest manifest) {
        if (manifest != null) {
            if ((workbook != null) && (fileOutputStream != null)) {
                XSSFSheet destSheet = workbook.createSheet(Constants.INSTRUCTIONS_SHEET);
                XSSFSheet srcSheet = manifest.getInstructions();
                Util.copyXSSFSheet(srcSheet, destSheet);
                for (int columnIndex = 0; columnIndex < 13; columnIndex++) {
                    destSheet.autoSizeColumn(columnIndex);
                }
            } else {
                throw new IllegalStateException("Cannot write instructions to  a file is that is not open!");
            }
        } else {
            throw new IllegalArgumentException("Cannot create instructions sheet from null Manifest!");
        }
    }

    protected void createTextBox(XSSFSheet xssfSheet, XSSFClientAnchor xssfClientAnchor, String text) {
        if (xssfSheet != null) {
            if ((workbook != null) && (fileOutputStream != null)) {
                XSSFDrawing xssfDrawing = xssfSheet.createDrawingPatriarch();
                XSSFTextBox xssfTextBox = xssfDrawing.createTextbox(xssfClientAnchor);
                xssfTextBox.setText(new XSSFRichTextString(text));
            } else {
                throw new IllegalStateException("Cannot create text box in a file is that is not open!");
            }
        } else {
            throw new IllegalArgumentException("Cannot create drawing on  null or empty sheet!");
        }
    }


}
