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

import net.mcnewfamily.rmcnew.model.RecordList;
import net.mcnewfamily.rmcnew.shared.Constants;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class PremanifestXlsxWriter extends AbstractXlsxWriter {

    public void writeRecords(RecordList records) {
        if (records != null && !records.isEmpty()) {
            XSSFSheet premanifestSheet = workbook.createSheet(Constants.PREMANIFEST_SHEET);
            addListOfListsToSheet(premanifestSheet, records.toListOfListOfString());
        }
    }

    public void writeSummaryTable(RecordList records) {
        XSSFSheet premanifestSheet = workbook.createSheet(Constants.PREMANFIEST_COUNTS_SHEET);
    }
}
