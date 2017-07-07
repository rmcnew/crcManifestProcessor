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

package com.starrypenguin.rmcnew.user_interface;

import com.starrypenguin.rmcnew.api.RunFinalManifestWorkflow;
import com.starrypenguin.rmcnew.api.RunPreManifestWorkflow;
import com.starrypenguin.rmcnew.model.exception.SheetNotFoundException;
import com.starrypenguin.rmcnew.shared.Constants;

import java.io.File;
import java.io.IOException;

/**
 * CommandLine
 * <p/>
 * Command line interface to invoke library
 * <p/>
 * Author: Richard Scott McNew
 */
public class CommandLine {

    public static void main(String[] args) throws IOException, SheetNotFoundException {
        File inputFile = new File(args[0]);
        if (!inputFile.canRead()) {
            System.err.println("Input file " + inputFile.getName() + " is not readable!");
        }
        File outputFile = new File(args[1]);
        if (!outputFile.createNewFile()) {
            System.err.println("Output file " + outputFile.getName() + " is not writable!");
        }
        if (args[2].equalsIgnoreCase(Constants.PRE)) {
            new RunPreManifestWorkflow(inputFile, outputFile);
        } else if (args[2].equalsIgnoreCase(Constants.FINAL)) {
            new RunFinalManifestWorkflow(inputFile, outputFile);
        } else {
            System.err.println("Parameter 3 must be PRE or FINAL");
        }
    }
}
