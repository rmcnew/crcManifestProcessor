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

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * ConfigXlsxReaderTest
 * <p/>
 * Tests for ConfigXlsxReader
 */
public class ConfigXlsxReaderTest {

    // Gracefully handle null config
    @Test(expected = IOException.class)
    public void NullConfigTest() throws IOException {
        ConfigXlsxReader configXlsxReader = new ConfigXlsxReader();
        configXlsxReader.openXlsxFile((File) null);
        configXlsxReader.read();
    }
}
