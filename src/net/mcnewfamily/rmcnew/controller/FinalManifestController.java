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

package net.mcnewfamily.rmcnew.controller;

import net.mcnewfamily.rmcnew.model.config.CrcManifestProcessorConfig;
import net.mcnewfamily.rmcnew.model.config.DestinationHubMap;
import net.mcnewfamily.rmcnew.model.config.LocationAliasMap;
import net.mcnewfamily.rmcnew.model.config.PriorityMOSMap;

import java.io.File;
import java.io.IOException;

public class FinalManifestController {

    public static void runWorkflow(File preManifestInputFile, File finalManifestInputFile, File preManifestOutputFile) throws IOException {
        CrcManifestProcessorConfig config = CrcManifestProcessorConfig.getInstance();
        LocationAliasMap aliasMap = config.getAliasMap();
        DestinationHubMap hubMap = config.getHubMap();
        PriorityMOSMap mosMap = config.getMosMap();


    }
}
