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

import com.starrypenguin.rmcnew.model.config.CrcManifestProcessorConfig;
import com.starrypenguin.rmcnew.model.data.record_fields.*;
import com.starrypenguin.rmcnew.model.exception.SheetNotFoundException;

import java.io.IOException;

/**
 * RecordBuilder
 * <p/>
 * Build a Record for testing
 */
public class RecordBuilder {

    private static final CrcManifestProcessorConfig config;

    static {
        try {
            CrcManifestProcessorConfig.init();
        } catch (IOException | SheetNotFoundException e) {
            e.printStackTrace();
        }
        config = CrcManifestProcessorConfig.getInstance();

    }

    private final NameGenerator nameGenerator;
    private final RankGenerator rankGenerator;
    private final MosGenerator mosGenerator;
    private final ServiceBranchGenerator serviceBranchGenerator;
    private final GenderGenerator genderGenerator;
    private final FinalDestinationCountryGenerator finalDestinationCountryGenerator;


    public RecordBuilder() {
        nameGenerator = new NameGenerator();
        rankGenerator = new RankGenerator(config);
        mosGenerator = new MosGenerator(config);
        serviceBranchGenerator = new ServiceBranchGenerator();
        genderGenerator = new GenderGenerator();
        finalDestinationCountryGenerator = new FinalDestinationCountryGenerator(config);
    }

    public Record generateGoodRecord() {
        Record goodRecord = new Record();

        goodRecord.setName(nameGenerator.getRandomName());
        goodRecord.setRank(rankGenerator.getRandomRank());
        goodRecord.setMOS(mosGenerator.getRandomMos());
        goodRecord.setServiceBranch(serviceBranchGenerator.getRandomServiceBranch());
        goodRecord.setGender(genderGenerator.getRandomGender());
        DestinationCountry destinationCountry = finalDestinationCountryGenerator.getRandomDestinationCountry();
        goodRecord.setFinalDestination(destinationCountry.destination);
        goodRecord.setCountry(destinationCountry.country);

        return goodRecord;
    }

    public Record generateBadRecord() {
        Record badRecord = new Record();

        badRecord.setName(nameGenerator.getPossiblyBadName());
        badRecord.setRank(rankGenerator.getPossiblyBadRank());
        badRecord.setMOS(mosGenerator.getPossiblyBadMos());
        badRecord.setServiceBranch(serviceBranchGenerator.getPossiblyBadServiceBranch());
        badRecord.setGender(genderGenerator.getPossiblyBadGender());
        DestinationCountry destinationCountry = finalDestinationCountryGenerator.getPossiblyBadDestinationCountry();
        badRecord.setFinalDestination(destinationCountry.destination);
        badRecord.setCountry(destinationCountry.country);

        return badRecord;
    }

}
