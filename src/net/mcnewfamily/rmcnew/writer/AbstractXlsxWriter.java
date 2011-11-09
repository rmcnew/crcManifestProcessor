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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

    public void closeXlsx() throws IOException {
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }
}
