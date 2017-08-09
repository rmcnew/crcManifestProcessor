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

package com.starrypenguin.rmcnew.model.data;

import com.starrypenguin.rmcnew.shared.Constants;
import com.starrypenguin.rmcnew.shared.Util;
import com.starrypenguin.rmcnew.writer.FinalManifestXlsxWriter;
import com.starrypenguin.rmcnew.writer.PreManifestXlsxWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

/**
 * ManifestBuilder
 * <p/>
 * Creates a manifest file for use in testing using the Manifest_Template.xlsx file
 */
public class ManifestBuilder {

    private static final Path manifestTemplate = new File(Util.getCurrentDirectory() + File.separator + "Manifest_Template.xlsx").toPath();
    private static RecordBuilder recordBuilder = new RecordBuilder();
    private Path tempDirectory;


    public ManifestBuilder() {
        try {
            String tempDirString = System.getProperty("java.io.tmpdir");
            //System.out.println("System temp directory is: " + tempDirString);
            //String tempDirString = Util.getCurrentDirectory();
            tempDirectory = Files.createTempDirectory((new File(tempDirString)).toPath(), "ManifestBuilder_" + Instant.now().toString());
            tempDirectory.toFile().deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Path createGoodPreManifest() throws IOException {
        Path manifestFile = new File(tempDirectory.toString() + File.separator +
                "Good_Pre_Manifest_" + Instant.now().toString() + ".xlsx").toPath();
        // copy manifest template to temp directory with randomly generated name
        copyManifestTemplateToTempDir(manifestFile);
        // open manifest file
        PreManifestXlsxWriter preManifestXlsxWriter = new PreManifestXlsxWriter();
        preManifestXlsxWriter.openXlsxForWriting(manifestFile.toFile());
        // pick a random number between 4 and 16 for number of rows to generate
        int rowCount = generateRandomRecordCount();
        System.out.println(rowCount + " rows will be created for the good pre-manifest.");
        Records records = new Records();
        for (int i = 0; i < rowCount; i++) {
            Record record = recordBuilder.generateGoodRecord();
            records.addRecord(record);
        }
        // generate good rows and put them in the premanifest
        preManifestXlsxWriter.writeRecords(records, Constants.PREMANIFEST_SHEET);
        preManifestXlsxWriter.close();
        return manifestFile;
    }

    public Path createBadPreManifest() throws IOException {
        Path manifestFile = new File(tempDirectory.toString() + File.separator +
                "Bad_Pre_Manifest_" + Instant.now().toString() + ".xlsx").toPath();
        // copy manifest template to temp directory with randomly generated name
        copyManifestTemplateToTempDir(manifestFile);
        // open manifest file
        PreManifestXlsxWriter preManifestXlsxWriter = new PreManifestXlsxWriter();
        preManifestXlsxWriter.openXlsxForWriting(manifestFile.toFile());
        // pick a random number between 4 and 16 for number of rows to generate
        int rowCount = generateRandomRecordCount();
        System.out.println(rowCount + " rows will be created for the bad pre-manifest.");
        Records records = new Records();
        for (int i = 0; i < rowCount; i++) {
            Record record = recordBuilder.generateBadRecord();
            records.addRecord(record);
        }
        // generate good rows and put them in the premanifest
        preManifestXlsxWriter.writeRecords(records, Constants.PREMANIFEST_SHEET);
        preManifestXlsxWriter.close();

        return manifestFile;
    }

    public Path createGoodFinalManifest() throws IOException {
        Path manifestFile = new File(tempDirectory.toString() + File.separator +
                "Good_Final_Manifest_" + Instant.now().toString() + ".xlsx").toPath();
        // copy manifest template to temp directory with randomly generated name
        copyManifestTemplateToTempDir(manifestFile);
        // open manifest file
        FinalManifestXlsxWriter finalManifestXlsxWriter = new FinalManifestXlsxWriter();
        finalManifestXlsxWriter.openXlsxForWriting(manifestFile.toFile());
        int rowCount = generateRandomRecordCount();
        System.out.println(rowCount + " rows will be created for the good pre-manifest.");
        Records preManifestRecords = new Records();
        Records finalManifestRecords = new Records();
        for (int i = 0; i < rowCount; i++) {
            Record record = recordBuilder.generateGoodRecord();
            preManifestRecords.addRecord(record);
            if (i % 5 != 0) {
                finalManifestRecords.addRecord(record);
            }
        }
        // generate good rows and put them in the premanifest
        finalManifestXlsxWriter.writeRecords(preManifestRecords, Constants.PREMANIFEST_SHEET);
        System.out.println(finalManifestRecords.size() + " rows will be created for the good final manifest.");
        // generate good rows and put them in the final manifest
        finalManifestXlsxWriter.writeRecords(finalManifestRecords, Constants.FINAL_MANIFEST_SHEET);
        finalManifestXlsxWriter.close();

        return manifestFile;
    }

    public Path createBadFinalManifest() throws IOException {
        Path manifestFile = new File(tempDirectory.toString() + File.separator +
                "Bad_Final_Manifest_" + Instant.now().toString() + ".xlsx").toPath();
        // copy manifest template to temp directory with randomly generated name
        copyManifestTemplateToTempDir(manifestFile);
        // open manifest file
        FinalManifestXlsxWriter finalManifestXlsxWriter = new FinalManifestXlsxWriter();
        finalManifestXlsxWriter.openXlsxForWriting(manifestFile.toFile());
        int rowCount = generateRandomRecordCount();
        System.out.println(rowCount + " rows will be created for the bad pre-manifest.");
        Records preManifestRecords = new Records();
        Records finalManifestRecords = new Records();
        for (int i = 0; i < rowCount; i++) {
            Record record = recordBuilder.generateBadRecord();
            preManifestRecords.addRecord(record);
            if (i % 5 != 0) {
                finalManifestRecords.addRecord(record);
            }
        }
        // generate good rows and put them in the premanifest
        finalManifestXlsxWriter.writeRecords(preManifestRecords, Constants.PREMANIFEST_SHEET);
        System.out.println(finalManifestRecords.size() + " rows will be created for the bad final manifest.");
        // generate good rows and put them in the final manifest
        finalManifestXlsxWriter.writeRecords(finalManifestRecords, Constants.FINAL_MANIFEST_SHEET);
        finalManifestXlsxWriter.close();
        
        return manifestFile;
    }

    private void copyManifestTemplateToTempDir(Path manifestFilename) throws IOException {
        if (manifestFilename != null) {
            System.out.println("Copying " + manifestTemplate.toString() + " to " + manifestFilename.toString() + " . . .");
            Files.copy(manifestTemplate, manifestFilename);
        }
    }

    private int generateRandomRecordCount() {
        return (int) ((Math.random() * 12) + 8);
    }


    public Path getTempDirectory() {
        return tempDirectory;
    }
}
