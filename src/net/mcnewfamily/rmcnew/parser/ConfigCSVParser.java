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

package net.mcnewfamily.rmcnew.parser;

import au.com.bytecode.opencsv.CSVReader;
import net.mcnewfamily.rmcnew.shared.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public abstract class ConfigCSVParser {

	protected CSVReader csvReader;

	protected void openCSVFile(String filename) throws FileNotFoundException {
		if (Util.notNullAndNotEmpty(filename) ) {
			csvReader = new CSVReader(new FileReader(filename));
		}
	}

	protected void openCSVFile(File file) throws FileNotFoundException {
		if (file != null) {
			csvReader = new CSVReader(new FileReader(file));
		}
	}

	protected List<String[]> readAll() throws IOException {
		return csvReader.readAll();
	}

	protected String[] readNext() throws IOException {
		return csvReader.readNext();
	}

	protected void close() throws IOException {
		csvReader.close();
	}


}
