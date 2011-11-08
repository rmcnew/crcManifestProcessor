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

import net.mcnewfamily.rmcnew.business_rule.AfghanUnknownPriorityMOSGoesToBagram;
import net.mcnewfamily.rmcnew.business_rule.KuwaitQatarSingleHub;
import net.mcnewfamily.rmcnew.model.*;
import net.mcnewfamily.rmcnew.reader.FromCRCPremanifestReader;
import net.mcnewfamily.rmcnew.shared.Constants;
import net.mcnewfamily.rmcnew.writer.PremanifestWriter;

import java.io.File;
import java.io.IOException;

public class PremanifestController {

	public static void runWorkflow(File preManifestInputFile, File preManifestOutputFile) throws IOException {

        CrcManifest crcManifest = CrcManifest.getInstance();
        FromCRCPremanifestReader premanifestReader = new FromCRCPremanifestReader();
        premanifestReader.openCSVFile(preManifestInputFile);
        RecordList records = premanifestReader.read();
        crcManifest.setRecords(records);

        LocationAliasMap aliasMap = crcManifest.getAliasMap();
        DestinationHubMap hubMap = crcManifest.getHubMap();
        PriorityMOSMap mosMap = crcManifest.getMosMap();

        for (Record record : records) {
            // location alias substitution\
            String finalDestination = record.getFinalDestination();
            String alias = aliasMap.get(finalDestination);
            if (alias != null) {
                System.out.println("Replaced alias:  " + finalDestination + " => " + alias);
                finalDestination = alias;
                record.setFinalDestination(alias);
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
            AfghanUnknownPriorityMOSGoesToBagram.applyRule(record, mosMap);
        }
        // write out results
        PremanifestWriter premanifestWriter = new PremanifestWriter();
        premanifestWriter.openCSVforWriting(preManifestOutputFile);
        premanifestWriter.writeAll(records.toListOfStringArray());
        premanifestWriter.flush();
        premanifestWriter.close();
	}

}
