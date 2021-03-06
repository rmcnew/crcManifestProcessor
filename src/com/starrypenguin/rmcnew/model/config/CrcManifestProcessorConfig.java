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

package com.starrypenguin.rmcnew.model.config;

import com.starrypenguin.rmcnew.model.exception.SheetNotFoundException;
import com.starrypenguin.rmcnew.reader.ConfigXlsxReader;
import com.starrypenguin.rmcnew.shared.Constants;

import java.io.IOException;

public class CrcManifestProcessorConfig {

    private static CrcManifestProcessorConfig instance;
    private static DestinationHubMap destinationHubMap;
    private static LocationAliasMap locationAliasMap;
    private static PriorityMOSMap priorityMosMap;
    private static RankComparisonMap rankComparisonMap;
    private static HubsWithoutUlnsMap hubsWithoutUlnsMap;

    private CrcManifestProcessorConfig(){
        // do nothing;  init() does the configuration readManifest
    }

    public static CrcManifestProcessorConfig getInstance() {
        if (instance == null) {
            try {
                CrcManifestProcessorConfig.init();
            } catch (IOException | SheetNotFoundException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public static void init() throws IOException, SheetNotFoundException {
        instance = new CrcManifestProcessorConfig();
        ConfigXlsxReader configXlsxReader = new ConfigXlsxReader();
        configXlsxReader.openXlsxFile(Constants.CONFIGURATION_XLSX);
        configXlsxReader.read();
        destinationHubMap = configXlsxReader.getHubMap();
        locationAliasMap = configXlsxReader.getAliasMap();
        priorityMosMap = configXlsxReader.getMosMap();
        rankComparisonMap = configXlsxReader.getRankComparisonMap();
        hubsWithoutUlnsMap = configXlsxReader.getHubsWithoutUlnsMap();
    }

    public DestinationHubMap getHubMap() {
        return destinationHubMap;
    }

    public LocationAliasMap getAliasMap() {
        return locationAliasMap;
    }

    public PriorityMOSMap getMosMap() {
        return priorityMosMap;
    }

    public RankComparisonMap getRankComparisonMap() {
        return rankComparisonMap;
    }

    public HubsWithoutUlnsMap getHubsWithoutUlnsMap() {
        return hubsWithoutUlnsMap;
    }
}
