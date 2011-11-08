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

import java.io.IOException;
import java.util.List;

public class DestinationHubCSVReader extends AbstractCSVReader {

	public DestinationHubMap parse() throws IOException {
		DestinationHubMap hubMap = new DestinationHubMap();
		boolean headerSeen = false;

		List<String[]> lines = this.readAll();
		for (String[] line : lines) {
			if (headerSeen) {
				hubMap.put(line[0], line[1], line[2]);
			} else if (line[0].equalsIgnoreCase(Constants.FINAL_DESTINATION) &&
					   line[1].equalsIgnoreCase(Constants.HUB) &&
					   line[2].equalsIgnoreCase(Constants.COUNTRY)) {
				headerSeen = true;
			} else {
				throw new IllegalArgumentException("Error in destination Hub Mapping CSV file!");
			}
		}
		return hubMap;
	}
}
