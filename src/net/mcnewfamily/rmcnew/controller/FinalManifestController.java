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

import net.mcnewfamily.rmcnew.model.data.Manifest;
import net.mcnewfamily.rmcnew.model.data.Record;
import net.mcnewfamily.rmcnew.model.data.Records;
import net.mcnewfamily.rmcnew.model.exception.SheetNotFoundException;
import net.mcnewfamily.rmcnew.shared.Constants;
import net.mcnewfamily.rmcnew.user_interface.MainWindow;
import net.mcnewfamily.rmcnew.user_interface.UlnQuestionPane;
import net.mcnewfamily.rmcnew.writer.FinalManifestXlsxWriter;

import java.io.File;
import java.io.IOException;

public class FinalManifestController extends AbstractManifestController {

    public static void runWorkflow(File preManifestInputFile, File finalManifestInputFile, File preManifestOutputFile) throws IOException, SheetNotFoundException {
        Manifest preManifest = processManifestFile(preManifestInputFile, Constants.PREMANIFEST_SHEET);
        Manifest finalManifest = processManifestFile(finalManifestInputFile, Constants.FINAL_MANIFEST_SHEET);
        Records onPreManifestButDidNotFly = findOnPreManifestButDidNotFly(preManifest, finalManifest);

        // get set of hubs
        // filter out hubs that do not get ULNs
        // ask user for ULN names and seats
        // prioritize PAX and assign seats
        // write out by-hub / ULN sheets
        UlnQuestionPane.askUserForUlnInfo(MainWindow.getFinalManifestTab(), "TEST HUB");
        writeResults(finalManifest, preManifest, onPreManifestButDidNotFly, preManifestOutputFile);

    }

    private static void writeResults(Manifest finalManifest, Manifest preManifest, Records onPreManifestButDidNotFly, File finalManifestOutputFile) throws IOException {
        FinalManifestXlsxWriter xlsxWriter = new FinalManifestXlsxWriter();
        xlsxWriter.openXlsxForWriting(finalManifestOutputFile);
        xlsxWriter.writeFinalManifest(finalManifest, preManifest, onPreManifestButDidNotFly);
        xlsxWriter.close();
    }

    private static Records findOnPreManifestButDidNotFly(Manifest preManifest, Manifest finalManifest) {
        if (preManifest != null && finalManifest != null) {
            Records onPreManifestButDidNotFly = new Records();
            Records preManifestRecords = preManifest.getRecords();
            Records finalManifestRecords = finalManifest.getRecords();
            for (Record record : preManifestRecords) {
                if (!finalManifestRecords.containsRecord(record)) {
                    onPreManifestButDidNotFly.addRecord(record);
                }
            }
            return onPreManifestButDidNotFly;
        } else {
            throw new IllegalArgumentException("Cannot compare a null manifest!");
        }
    }


}
