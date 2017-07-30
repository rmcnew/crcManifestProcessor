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

    XSSFWorkbook workbook;
    FileOutputStream fileOutputStream;

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

    public void writeRecords(Records records, String sheetName) {
        if (records != null && !records.isEmpty()) {
            XSSFSheet finalManifestSheet = records.toSheetEssence(sheetName).toXSSFSheet(workbook);
            for (int columnIndex = 0; columnIndex < 13; columnIndex++) {
                finalManifestSheet.autoSizeColumn(columnIndex);
            }
        } else {
            throw new IllegalArgumentException("Cannot create XLSX sheet from null or empty Records!");
        }
    }

    protected void writeSummaryTable(Manifest manifest, String sheetName) {
        if (manifest != null) {
            XSSFSheet manifestCountsSheet = manifest.toSheetEssence(sheetName).toXSSFSheet(workbook);
            for (int columnIndex = 0; columnIndex < 4; columnIndex++) {
                manifestCountsSheet.autoSizeColumn(columnIndex);
            }
        } else {
            throw new IllegalArgumentException("Cannot create XLSX sheet from null or empty manifest!");
        }
    }

    protected void writeInstructions(Manifest manifest) {
        if (manifest != null) {
            XSSFSheet destSheet = workbook.createSheet(Constants.INSTRUCTIONS_SHEET);
            XSSFSheet srcSheet = manifest.getInstructions();
            Util.copyXSSFSheet(srcSheet, destSheet);
            for (int columnIndex = 0; columnIndex < 13; columnIndex++) {
                destSheet.autoSizeColumn(columnIndex);
            }
        } else {
            throw new IllegalArgumentException("Cannot create instructions sheet from null Manifest!");
        }
    }

    protected void createTextBox(XSSFSheet xssfSheet, XSSFClientAnchor xssfClientAnchor, String text) {
        if (xssfSheet != null) {
            XSSFDrawing xssfDrawing = xssfSheet.createDrawingPatriarch();
            XSSFTextBox xssfTextBox = xssfDrawing.createTextbox(xssfClientAnchor);
            xssfTextBox.setText(new XSSFRichTextString(text));

        } else {
            throw new IllegalArgumentException("Cannot create drawing on  null or empty sheet!");
        }
    }

    public void copyInstructionsSheet() {

    }
}
