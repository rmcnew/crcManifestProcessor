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

package com.starrypenguin.rmcnew.api;

import com.starrypenguin.rmcnew.model.data.ManifestBuilder;
import com.starrypenguin.rmcnew.model.exception.SheetNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * RunFinalManifestWorkflowTest
 * <p/>
 * System test for FinalManifestWorkflow API
 */
public class RunFinalManifestWorkflowTest {

    private ManifestBuilder manifestBuilder;

    @Before
    public void SetupTests() {
        manifestBuilder = new ManifestBuilder();
    }

    @Test
    public void GoodFinalManifestTest() throws IOException, SheetNotFoundException {
        Path finalManifestPath = manifestBuilder.createGoodFinalManifest();
        File finalManifestOutputFile = new File(OutputManifestFilename.convert(finalManifestPath.toString()));
        System.out.println("Output manifest filename is: " + finalManifestOutputFile.getAbsolutePath());
        RunFinalManifestWorkflow runFinalManifestWorkflow = new RunFinalManifestWorkflow(finalManifestPath.toFile(), finalManifestOutputFile);
        Assert.assertNotNull(runFinalManifestWorkflow);
        finalManifestPath.toFile().deleteOnExit();
        //finalManifestOutputFile.deleteOnExit();
    }

    @Test
    public void BadFinalManifestTest() throws IOException, SheetNotFoundException {
        Path finalManifestPath = manifestBuilder.createBadFinalManifest();
        File finalManifestOutputFile = new File(OutputManifestFilename.convert(finalManifestPath.toString()));
        System.out.println("Output manifest filename is: " + finalManifestOutputFile.getAbsolutePath());
        RunFinalManifestWorkflow runFinalManifestWorkflow = new RunFinalManifestWorkflow(finalManifestPath.toFile(), finalManifestOutputFile);
        Assert.assertNotNull(runFinalManifestWorkflow);
        finalManifestPath.toFile().deleteOnExit();
        //finalManifestOutputFile.deleteOnExit();
    }
}
