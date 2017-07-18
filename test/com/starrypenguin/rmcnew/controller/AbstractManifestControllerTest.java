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

package com.starrypenguin.rmcnew.controller;

import com.starrypenguin.rmcnew.model.data.Manifest;
import com.starrypenguin.rmcnew.model.exception.SheetNotFoundException;
import com.starrypenguin.rmcnew.shared.Constants;
import com.starrypenguin.rmcnew.shared.Util;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * AbstractManifestControllerTest
 * <p/>
 * Tests for AbstractManifestController
 */
public class AbstractManifestControllerTest {

    @Test
    public void processManifestFileTest() throws IOException, SheetNotFoundException {
        String manifestFileStr = Util.getCurrentDirectory() + "/test_resource/FinalManifest-valid.xlsx";
        System.out.println("processing file:  " + manifestFileStr);
        File manifestFile = new File(manifestFileStr);
        // test pre-manifest processing
        Manifest preManifest = AbstractManifestController.processManifestFile(manifestFile, Constants.PREMANIFEST_SHEET);
        Assert.assertTrue(preManifest.getGrandTotal() > 0);
        // test final manifest processing
        Manifest finalManifest = AbstractManifestController.processManifestFile(manifestFile, Constants.FINAL_MANIFEST_SHEET);
        Assert.assertTrue(finalManifest.getGrandTotal() > 0);
    }

    @Test
    public void processFinalDestinationTest() {

    }

    @Test
    public void processHubLookupTest() {

    }

    @Test
    public void applyBusinessRulesTest() {

    }
}
