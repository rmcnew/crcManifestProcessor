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

import au.com.bytecode.opencsv.CSVWriter;
import net.mcnewfamily.rmcnew.shared.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public abstract class AbstractCSVWriter {

    protected CSVWriter csvWriter;

    public void openCSVforWriting(String filename) throws IOException {
        if (Util.notNullAndNotEmpty(filename) ) {
            csvWriter = new CSVWriter(new FileWriter(filename));
        }
    }

    public void openCSVforWriting(File file) throws IOException {
        if (file != null) {
            csvWriter = new CSVWriter(new FileWriter(file));
        }
    }

    public void writeAll(List<String[]> allLines) {
        csvWriter.writeAll(allLines);
    }

    public void writeNext(String[] nextLine) {
        csvWriter.writeNext(nextLine);
    }

    public void flush() throws IOException {
        csvWriter.flush();
    }

    public void close() throws IOException {
        csvWriter.close();
    }

    public boolean checkError() {
        return csvWriter.checkError();
    }
}
