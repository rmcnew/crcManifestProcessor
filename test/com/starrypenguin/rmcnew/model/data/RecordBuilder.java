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
import com.starrypenguin.rmcnew.model.data.record_fields.NameGenerator;
import com.starrypenguin.rmcnew.model.data.record_fields.RankGenerator;
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

    private NameGenerator nameGenerator = new NameGenerator();
    private RankGenerator rankGenerator;

    public RecordBuilder() {
        rankGenerator = new RankGenerator(config);
    }

    public Record generateGoodRecord() {
        Record goodRecord = new Record();
        // name

        // rank
        goodRecord.setRank(rankGenerator.getRandomRank());

        // MOS

        // service branch

        // gender

        // final destination

        // country

        return goodRecord;
    }

    public Record generateBadRecord() {
        Record badRecord = new Record();
        // name

        // rank

        // MOS

        // service branch

        // gender

        // final destination

        // country

        return badRecord;
    }

}
