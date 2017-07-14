/*
 * Copyright (c) 2017 Richard Scott McNew.
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

package com.starrypenguin.rmcnew.reader;

import com.starrypenguin.rmcnew.model.data.Manifest;
import com.starrypenguin.rmcnew.model.data.Records;
import com.starrypenguin.rmcnew.model.exception.SheetNotFoundException;
import com.starrypenguin.rmcnew.shared.Util;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * ManifestXlsxReaderTest
 * <p/>
 * Tests for ManifestXlsxReader
 */
public class ManifestXlsxReaderTest {

    private ManifestXlsxReader manifestXlsxReader;

    @Before
    public void SetupTest() {
        manifestXlsxReader = new ManifestXlsxReader();
    }

    // Gracefully handle null manifest
    @Test(expected = IOException.class)
    public void NullManifestTest() throws IOException, SheetNotFoundException {
        manifestXlsxReader.openXlsxFile((File) null);
        manifestXlsxReader.readPreManifest();
    }

    @Test(expected = SheetNotFoundException.class)
    public void EmptyManifestTest() throws IOException, SheetNotFoundException {
        String currentDir = Util.getCurrentDirectory();
        manifestXlsxReader.openXlsxFile(currentDir + "/test_resource/Manifest-empty.xlsx");
        manifestXlsxReader.readPreManifest();
    }

    @Test(expected = IllegalArgumentException.class)
    public void InvalidDataManifestTest() throws IOException, SheetNotFoundException {
        String currentDir = Util.getCurrentDirectory();
        manifestXlsxReader.openXlsxFile(currentDir + "/test_resource/Manifest-invalid_data.xlsx");
        manifestXlsxReader.readPreManifest();
    }

    @Test(expected = SheetNotFoundException.class)
    public void MissingSheetsManifestTest() throws IOException, SheetNotFoundException {
        String currentDir = Util.getCurrentDirectory();
        manifestXlsxReader.openXlsxFile(currentDir + "/test_resource/Manifest-missing_sheets.xlsx");
        manifestXlsxReader.readPreManifest();
    }

    @Test
    public void PreManifestTest() throws IOException, SheetNotFoundException {
        String currentDir = Util.getCurrentDirectory();
        manifestXlsxReader.openXlsxFile(currentDir + "/test_resource/PreManifest-valid.xlsx");
        Manifest preManifest = manifestXlsxReader.readPreManifest();
        assertNotNull(preManifest);
        Records records = preManifest.getRecords();
        assertNotNull(records);
    }

    @Test
    public void FinalManifestTest() throws IOException, SheetNotFoundException {
        String currentDir = Util.getCurrentDirectory();
        manifestXlsxReader.openXlsxFile(currentDir + "/test_resource/FinalManifest-valid.xlsx");
        Manifest preManifest = manifestXlsxReader.readPreManifest();
        assertNotNull(preManifest);
        Records preManifestRecords = preManifest.getRecords();
        assertNotNull(preManifestRecords);
        Manifest finalManifest = manifestXlsxReader.readFinalManifest();
        assertNotNull(finalManifest);
        Records finalManifestRecords = finalManifest.getRecords();
        assertNotNull(finalManifestRecords);
    }

}
