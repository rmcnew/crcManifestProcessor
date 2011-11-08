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

import net.mcnewfamily.rmcnew.model.Record;
import net.mcnewfamily.rmcnew.model.RecordList;
import net.mcnewfamily.rmcnew.shared.Constants;
import net.mcnewfamily.rmcnew.shared.Util;

import java.io.IOException;
import java.util.List;

public class FromCRCPremanifestReader extends AbstractCSVReader {

    public RecordList read() throws IOException {
        RecordList records = new RecordList();
        boolean headerSeen = false;

		List<String[]> lines = this.readAll();
		for (String[] line : lines) {
            // NAME, RANK, MOS, SERVICE_BRANCH, GENDER, FINAL_DESTINATION, HUB, COUNTRY
			if (headerSeen) {
				Record record = new Record(line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7]);
                if (Util.notNullAndNotEmpty(line[0])) {
                    records.add(record);
                }
			} else if (line[0].equalsIgnoreCase(Constants.NAME) &&
					   line[1].equalsIgnoreCase(Constants.RANK) &&
					   (line[2].equalsIgnoreCase(Constants.MOS) || (line[2].equalsIgnoreCase(Constants.AFSC_MOS)) ) &&
                       line[3].equalsIgnoreCase(Constants.SERVICE_BRANCH) &&
                       line[4].equalsIgnoreCase(Constants.GENDER) &&
                       line[5].equalsIgnoreCase(Constants.FINAL_DESTINATION) &&
                       line[6].equalsIgnoreCase(Constants.HUB) &&
                       line[7].equalsIgnoreCase(Constants.COUNTRY) ) {
				headerSeen = true;
			} else {
				throw new IllegalArgumentException("Error in premanifest CSV file format!");
			}
		}
        System.out.println("Records read:\n");
        for (Record record : records) {
            System.out.println(record);
        }
        return records;
    }
}
