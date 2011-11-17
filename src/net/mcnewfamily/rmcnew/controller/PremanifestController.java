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

import net.mcnewfamily.rmcnew.business_rule.AfghanUnknownPriorityMosGoesToBagram;
import net.mcnewfamily.rmcnew.business_rule.KuwaitQatarSingleHub;
import net.mcnewfamily.rmcnew.business_rule.MakeAllMilitaryServiceBranchA;
import net.mcnewfamily.rmcnew.model.config.CrcManifestProcessorConfig;
import net.mcnewfamily.rmcnew.model.config.DestinationHubMap;
import net.mcnewfamily.rmcnew.model.config.LocationAliasMap;
import net.mcnewfamily.rmcnew.model.config.PriorityMOSMap;
import net.mcnewfamily.rmcnew.model.data.*;
import net.mcnewfamily.rmcnew.reader.FromCrcManifestXlsxReader;
import net.mcnewfamily.rmcnew.shared.Constants;
import net.mcnewfamily.rmcnew.shared.Util;
import net.mcnewfamily.rmcnew.writer.PreManifestXlsxWriter;

import java.io.File;
import java.io.IOException;

public class PreManifestController {

	public static void runWorkflow(File preManifestInputFile, File preManifestOutputFile) throws IOException {

        CrcManifestProcessorConfig config = CrcManifestProcessorConfig.getInstance();
        PreManifest preManifest = PreManifest.getInstance();
        FromCrcManifestXlsxReader manifestXlsxReader = new FromCrcManifestXlsxReader();
        manifestXlsxReader.openXlsxFile(preManifestInputFile);
        RecordList records = manifestXlsxReader.read();
        preManifest.setRecords(records);
        //System.out.println(records);

        LocationAliasMap aliasMap = config.getAliasMap();
        DestinationHubMap hubMap = config.getHubMap();
        PriorityMOSMap mosMap = config.getMosMap();
        CountryHubCountMap countryHubCountMap = preManifest.getCountryHubCountMap();

        for (Record record : records) {
            processFinalDestination(record, aliasMap);
            processHubLookup(record, hubMap);
            applyBusinessRules(record, mosMap);
            if (record.isMilitary()) {
                countryHubCountMap.plusOneToMilCount(record);
            } else {
                countryHubCountMap.plusOneToCivCount(record);
            }
        }
        // write out results
        PreManifestXlsxWriter xlsxWriter = new PreManifestXlsxWriter();
        xlsxWriter.openXlsxForWriting(preManifestOutputFile);
        xlsxWriter.writePremanifest(preManifest);
        xlsxWriter.close();
	}

    private static void processFinalDestination(Record record, LocationAliasMap aliasMap) {
        String finalDestination = record.getFinalDestination();
        // strip prefixes and suffixes
        finalDestination = Util.stripLocationPrefixesAndSuffixes(finalDestination);
        // location alias substitution
        if (finalDestination.isEmpty()) {
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

    private static void processHubLookup(Record record, DestinationHubMap hubMap) {
        String finalDestination = record.getFinalDestination();
        // hub look-up
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
    }

    private static void applyBusinessRules(Record record, PriorityMOSMap mosMap) throws IOException {
        KuwaitQatarSingleHub.applyRule(record);
        AfghanUnknownPriorityMosGoesToBagram.applyRule(record, mosMap);
        MakeAllMilitaryServiceBranchA.applyRule(record);
    }

}
