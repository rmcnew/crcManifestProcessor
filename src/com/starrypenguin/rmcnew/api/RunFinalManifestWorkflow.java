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

import com.starrypenguin.rmcnew.controller.FinalManifestController;
import com.starrypenguin.rmcnew.model.exception.SheetNotFoundException;

import java.io.File;
import java.io.IOException;

/**
 * RunFinalManifest
 * <p/>
 * Java library API for automated run of Final Manifest workflow
 * <p/>
 * Author: Richard Scott McNew
 */
public class RunFinalManifestWorkflow extends RunAbstractWorkflow {

    public RunFinalManifestWorkflow(File inputManifest,
                                    File processedManifest) throws IOException, SheetNotFoundException {
        super(inputManifest, processedManifest, new FinalManifestController());
    }
}
