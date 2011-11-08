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

package net.mcnewfamily.rmcnew.controller;

import net.mcnewfamily.rmcnew.business_rule.AfghanUnknownPriorityMosGoesToBagram;
import net.mcnewfamily.rmcnew.business_rule.KuwaitQatarSingleHub;
import net.mcnewfamily.rmcnew.model.*;
import net.mcnewfamily.rmcnew.reader.FromCrcPremanifestCsvReader;
import net.mcnewfamily.rmcnew.shared.Constants;
import net.mcnewfamily.rmcnew.shared.Util;
import net.mcnewfamily.rmcnew.writer.PremanifestCsvWriter;

import java.io.File;
import java.io.IOException;

public class PremanifestController {

	public static void runWorkflow(File preManifestInputFile, File preManifestOutputFile) throws IOException {

        CrcManifest crcManifest = CrcManifest.getInstance();
        FromCrcPremanifestCsvReader premanifestCSVReader = new FromCrcPremanifestCsvReader();
        premanifestCSVReader.openCsvFile(preManifestInputFile);
        RecordList records = premanifestCSVReader.read();
        crcManifest.setRecords(records);

        LocationAliasMap aliasMap = crcManifest.getAliasMap();
        DestinationHubMap hubMap = crcManifest.getHubMap();
        PriorityMOSMap mosMap = crcManifest.getMosMap();

        for (Record record : records) {
            String finalDestination = record.getFinalDestination();
            // strip prefixes and suffixes
            finalDestination = Util.stripLocationPrefixesAndSuffixes(finalDestination);
            // location alias substitution
            String alias = aliasMap.get(finalDestination);
            if (alias != null) {
                System.out.println("Replaced alias:  " + finalDestination + " => " + alias);
                finalDestination = alias;
                record.setFinalDestination(alias);
            } else {
                record.setFinalDestination(finalDestination);
            }
            // hub look-up
            HubCountry hubCountry = hubMap.get(finalDestination);
            if (hubCountry != null) {
                System.out.println("Found hub: " + finalDestination + " => " + hubCountry);
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
            // apply business rules
            KuwaitQatarSingleHub.applyRule(record);
            AfghanUnknownPriorityMosGoesToBagram.applyRule(record, mosMap);
        }
        // write out results
        PremanifestCsvWriter premanifestCSVWriter = new PremanifestCsvWriter();
        premanifestCSVWriter.openCsvForWriting(preManifestOutputFile);
        premanifestCSVWriter.writeAll(records.toListOfStringArray());
        premanifestCSVWriter.flush();
        premanifestCSVWriter.close();
	}

}
