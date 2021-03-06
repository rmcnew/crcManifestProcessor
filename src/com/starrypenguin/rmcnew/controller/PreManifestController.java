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

import com.starrypenguin.rmcnew.model.data.Manifest;
import com.starrypenguin.rmcnew.model.exception.SheetNotFoundException;
import com.starrypenguin.rmcnew.shared.Constants;
import com.starrypenguin.rmcnew.writer.PreManifestXlsxWriter;

import java.io.File;
import java.io.IOException;

public class PreManifestController extends AbstractManifestController{

    @Override
	public void runWorkflow(File manifestInputFile, File manifestOutputFile) throws IOException, SheetNotFoundException {
        Manifest preManifest = processManifestFile(manifestInputFile, Constants.PREMANIFEST_SHEET);
        writeResults(preManifest, manifestOutputFile);
	}

    private void writeResults(Manifest preManifest, File preManifestOutputFile) throws IOException {
        PreManifestXlsxWriter xlsxWriter = new PreManifestXlsxWriter();
        xlsxWriter.openXlsxForWriting(preManifestOutputFile);
        xlsxWriter.writePreManifest(preManifest);
        xlsxWriter.close();
    }

}
