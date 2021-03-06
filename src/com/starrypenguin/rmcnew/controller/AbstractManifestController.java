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

package com.starrypenguin.rmcnew.controller;

import com.starrypenguin.rmcnew.business_rule.AfghanUnknownPriorityMosGoesToBagram;
import com.starrypenguin.rmcnew.business_rule.KuwaitQatarSingleHub;
import com.starrypenguin.rmcnew.business_rule.MakeAllMilitaryServiceBranchA;
import com.starrypenguin.rmcnew.model.config.*;
import com.starrypenguin.rmcnew.model.data.Manifest;
import com.starrypenguin.rmcnew.model.data.Record;
import com.starrypenguin.rmcnew.model.exception.SheetNotFoundException;
import com.starrypenguin.rmcnew.reader.ManifestXlsxReader;
import com.starrypenguin.rmcnew.shared.Constants;
import com.starrypenguin.rmcnew.shared.Util;

import java.io.File;
import java.io.IOException;

public abstract class AbstractManifestController {

    public abstract void runWorkflow(File inputFile, File outputFile) throws IOException, SheetNotFoundException;

    protected static Manifest processManifestFile(File manifestInputFile, String sheetName) throws IOException, SheetNotFoundException {
        if (manifestInputFile != null && Util.notNullAndNotEmpty(sheetName)) {
            CrcManifestProcessorConfig config = CrcManifestProcessorConfig.getInstance();
            LocationAliasMap aliasMap = config.getAliasMap();
            DestinationHubMap hubMap = config.getHubMap();
            PriorityMOSMap mosMap = config.getMosMap();

            ManifestXlsxReader manifestXlsxReader = new ManifestXlsxReader();
            manifestXlsxReader.openXlsxFile(manifestInputFile);
            Manifest manifest = manifestXlsxReader.readManifest(sheetName);

            for (Record record : manifest.getRecords()) {
                processFinalDestination(record, aliasMap);
                processHubLookup(record, hubMap);
                applyBusinessRules(record, mosMap);
            }
            manifest.doSummaryCounts();
            return manifest;
        } else {
            throw new IllegalArgumentException("Cannot create manifest from null or empty file or sheetName!");
        }
    }

    protected static void processFinalDestination(Record record, LocationAliasMap aliasMap) {
        if (record != null && aliasMap != null) {
            String finalDestination = record.getFinalDestination();
            // strip prefixes and suffixes
            finalDestination = Util.stripLocationPrefixesAndSuffixes(finalDestination);
            // location alias substitution
            if (Util.nullOrEmpty(finalDestination)) {
                finalDestination = Constants.UNKNOWN;
            }
            String alias = aliasMap.get(finalDestination);
            if (alias != null) {
                //System.out.println("Replaced alias:  " + finalDestination + " => " + alias);
                record.setFinalDestination(alias);
            } else {
                record.setFinalDestination(finalDestination);
            }
        }
    }

    protected static void processHubLookup(Record record, DestinationHubMap hubMap) {
        if (record != null && hubMap != null) {
            String finalDestination = record.getFinalDestination();
            // hub look-up
            if (Util.notNullAndNotEmpty(finalDestination)) {
                HubCountry hubCountry = hubMap.get(finalDestination);
                if (hubCountry != null) {
                    //System.out.println("Found hub: " + finalDestination + " => " + hubCountry);
                    record.setHub(hubCountry.getHub());
                    if (!record.getCountry().equalsIgnoreCase(hubCountry.getCountry())) {
                        System.out.println("Countries do not match!  " + record.getCountry() + " != " + hubCountry.getCountry());
                    }
                    record.setCountry(hubCountry.getCountry());
                } else {
                    if (finalDestination.equalsIgnoreCase(Constants.UNKNOWN)) {
                        record.setHub(Constants.UNKNOWN);
                    } else {
                        record.setHub(Constants.NOT_FOUND);
                    }
                }
            } else {
                record.setFinalDestination(Constants.UNKNOWN);
                record.setHub(Constants.UNKNOWN);
            }
        }
    }

    protected static void applyBusinessRules(Record record, PriorityMOSMap mosMap) throws IOException {
        if (record != null && mosMap != null) {
            KuwaitQatarSingleHub.applyRule(record);
            AfghanUnknownPriorityMosGoesToBagram.applyRule(record, mosMap);
            MakeAllMilitaryServiceBranchA.applyRule(record);
        }
    }
}
