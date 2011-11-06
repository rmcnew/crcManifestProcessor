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

package net.mcnewfamily.rmcnew.model;

import net.mcnewfamily.rmcnew.parser.DestinationHubMappingParser;

import java.io.IOException;

public class CrcManifest {
	private final String DESTINATION_HUB_MAP_CSV = "destinationHubMapping.csv";

	private DestinationHubMap hubMap;

	public CrcManifest() throws IOException {
		DestinationHubMappingParser hubMappingParser = new DestinationHubMappingParser();
		hubMappingParser.openCSVFile(DESTINATION_HUB_MAP_CSV);
		hubMap = hubMappingParser.parse();
		System.out.println(hubMap.toString());
	}


}
