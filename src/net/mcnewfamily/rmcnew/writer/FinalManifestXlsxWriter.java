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

package net.mcnewfamily.rmcnew.writer;

import net.mcnewfamily.rmcnew.model.data.Manifest;
import net.mcnewfamily.rmcnew.model.data.Records;
import net.mcnewfamily.rmcnew.shared.Constants;

public class FinalManifestXlsxWriter extends AbstractXlsxWriter {

    public void writeFinalManifest(Manifest finalManifest, Manifest preManifest, Records onPreManifestButDidNotFly) {
        if (finalManifest != null) {
            writeRecords(finalManifest.getRecords(), Constants.FINAL_MANIFEST_SHEET);
            writeSummaryTable(finalManifest, Constants.FINAL_MANIFEST_COUNTS_SHEET);
            writeRecords(preManifest.getRecords(), Constants.PREMANIFEST_SHEET);
            writeSummaryTable(preManifest, Constants.PREMANFIEST_COUNTS_SHEET);
            writeRecords(onPreManifestButDidNotFly, Constants.ON_PREMANIFEST_BUT_DID_NOT_FLY);
        } else {
            throw new IllegalArgumentException("Cannot write Final Manifest for null FinalManifest model!");
        }
    }



}
