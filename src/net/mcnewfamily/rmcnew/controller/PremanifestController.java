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
import net.mcnewfamily.rmcnew.shared.Constants;
import net.mcnewfamily.rmcnew.writer.PreManifestXlsxWriter;

import java.io.File;
import java.io.IOException;

public class PreManifestController extends AbstractManifestController{

	public static void runWorkflow(File preManifestInputFile, File preManifestOutputFile) throws IOException {
        Manifest preManifest = processManifestFile(preManifestInputFile, Constants.PREMANIFEST_SHEET);
        writeResults(preManifest, preManifestOutputFile);
	}

    private static void writeResults(Manifest preManifest, File preManifestOutputFile) throws IOException {
        PreManifestXlsxWriter xlsxWriter = new PreManifestXlsxWriter();
        xlsxWriter.openXlsxForWriting(preManifestOutputFile);
        xlsxWriter.writePreManifest(preManifest);
        xlsxWriter.close();
    }

}
