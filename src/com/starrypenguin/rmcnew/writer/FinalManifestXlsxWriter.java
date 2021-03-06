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

package com.starrypenguin.rmcnew.writer;

import com.starrypenguin.rmcnew.model.config.CrcManifestProcessorConfig;
import com.starrypenguin.rmcnew.model.config.HubsWithoutUlnsMap;
import com.starrypenguin.rmcnew.model.data.*;
import com.starrypenguin.rmcnew.shared.Constants;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class FinalManifestXlsxWriter extends AbstractXlsxWriter {

    public void writeFinalManifest(Manifest finalManifest, Manifest preManifest, Records onPreManifestButDidNotFly) {
        if (finalManifest != null) {
            if (preManifest != null) {
                if (onPreManifestButDidNotFly != null) {
                    writeRecords(finalManifest.getRecords(), Constants.FINAL_MANIFEST_SHEET);
                    writeSummaryTable(finalManifest, Constants.FINAL_MANIFEST_COUNTS_SHEET);
                    writeRecords(preManifest.getRecords(), Constants.PREMANIFEST_SHEET);
                    writeSummaryTable(preManifest, Constants.PREMANFIEST_COUNTS_SHEET);
                    writeRecords(onPreManifestButDidNotFly, Constants.ON_PREMANIFEST_BUT_DID_NOT_FLY);
                    writeHubs(finalManifest);
                    // there are bugs in the current version of Apache POI that do not
                    // copy cell styles correctly and corrupt the spreadsheet.  Copying
                    // the instruction sheet is not essential, so we will defer doing this for now.
                    //writeInstructions(finalManifest);
                } else {
                    throw new IllegalArgumentException("Cannot write null onPreManifestButDidNotFly records!");
                }
            } else {
                throw new IllegalArgumentException("Cannot write Pre Manifest for null PreManifest model!");
            }
        } else {
            throw new IllegalArgumentException("Cannot write Final Manifest for null FinalManifest model!");
        }
    }

    private void writeHubs(Manifest finalManifest) {
        if (finalManifest != null) {
            HubsWithoutUlnsMap hubsWithoutUlnsMap = CrcManifestProcessorConfig.getInstance().getHubsWithoutUlnsMap();
            for (Country country : finalManifest) {
                for (Hub hub : country) {
                    if (hubsWithoutUlnsMap.get(hub.getName())) {
                        continue;
                    }
                    writePrioritizedRecords(hub);
                }
            }
        } else {
            throw new IllegalArgumentException("finalManifest cannot be null!");
        }
    }

    private void writePrioritizedRecords(Hub hub) {
        if (hub != null) {
            PrioritizedRecords prioritizedRecords = hub.getPrioritizedRecords();
            String sheetName = hub.getName();
            if (prioritizedRecords != null && !prioritizedRecords.isEmpty()) {
                XSSFSheet finalManifestSheet = prioritizedRecords.toSheetEssence(sheetName).toXSSFSheet(workbook);
                for (int columnIndex = 0; columnIndex < 13; columnIndex++) {
                    finalManifestSheet.autoSizeColumn(columnIndex);
                }
                //String text = hub.generateUlnUsageString() + hub.generateOnwardMovementString();
                //createTextBox(finalManifestSheet, hub.getClientAnchor(), text);
            } else {
                throw new IllegalArgumentException("Cannot create XLSX sheet from null or empty Records!");
            }
        } else {
            throw new IllegalArgumentException("hub cannot be null!");
        }
    }

}
