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

import com.starrypenguin.rmcnew.model.exception.SheetNotFoundException;
import com.starrypenguin.rmcnew.shared.Util;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * ConfigXlsxReaderTest
 * <p/>
 * Tests for ConfigXlsxReader
 */
public class ConfigXlsxReaderTest {

    private ConfigXlsxReader configXlsxReader;

    @Before
    public void SetupTest() {
        configXlsxReader = new ConfigXlsxReader();
    }

    // Gracefully handle null config
    @Test(expected = IOException.class)
    public void NullConfigTest() throws IOException, SheetNotFoundException {
        configXlsxReader.openXlsxFile((File) null);
        configXlsxReader.read();
    }

    @Test(expected = SheetNotFoundException.class)
    public void EmptyConfigTest() throws IOException, SheetNotFoundException {
        String currentDir = Util.getCurrentDirectory();
        configXlsxReader.openXlsxFile(currentDir + "/test_resource/CRC_Manifest_Processor_Config-empty.xlsx");
        configXlsxReader.read();
    }

    @Test(expected = IllegalArgumentException.class)
    public void InvalidDataConfigTest() throws IOException, SheetNotFoundException {
        String currentDir = Util.getCurrentDirectory();
        configXlsxReader.openXlsxFile(currentDir + "/test_resource/CRC_Manifest_Processor_Config-invalid_data.xlsx");
        configXlsxReader.read();
    }

    @Test(expected = IllegalArgumentException.class)
    public void MissingSheetsConfigTest() throws IOException, SheetNotFoundException {
        String currentDir = Util.getCurrentDirectory();
        configXlsxReader.openXlsxFile(currentDir + "/test_resource/CRC_Manifest_Processor_Config-missing_sheets.xlsx");
        configXlsxReader.read();
    }

    @Test
    public void FullConfigTest() throws IOException, SheetNotFoundException {
        String currentDir = Util.getCurrentDirectory();
        configXlsxReader.openXlsxFile(currentDir + "/test_resource/CRC_Manifest_Processor_Config-full.xlsx");
        configXlsxReader.read();
        assertNotNull(configXlsxReader.getAliasMap());
        assertNotNull(configXlsxReader.getHubMap());
        assertNotNull(configXlsxReader.getHubsWithoutUlnsMap());
        assertNotNull(configXlsxReader.getMosMap());
        assertNotNull(configXlsxReader.getRankComparisonMap());
    }
}
