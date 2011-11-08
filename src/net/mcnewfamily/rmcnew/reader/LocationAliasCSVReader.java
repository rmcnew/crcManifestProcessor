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

import net.mcnewfamily.rmcnew.model.LocationAliasMap;
import net.mcnewfamily.rmcnew.shared.Constants;

import java.io.IOException;
import java.util.List;

public class LocationAliasCsvReader extends AbstractCsvReader {

    public LocationAliasMap read() throws IOException {
        LocationAliasMap aliasMap = new LocationAliasMap();
        boolean headerSeen = false;

		List<String[]> lines = this.readAll();
		for (String[] line : lines) {
			if (headerSeen) {
				aliasMap.put(line[0], line[1]);
			} else if (line[0].equalsIgnoreCase(Constants.ALIAS) &&
					   line[1].equalsIgnoreCase(Constants.FINAL_DESTINATION)) {
				headerSeen = true;
			} else {
				throw new IllegalArgumentException("Error in location alias CSV file!");
			}
		}
        return aliasMap;
    }
}
