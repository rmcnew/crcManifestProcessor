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

package com.starrypenguin.rmcnew.model.data.record_fields;

import com.starrypenguin.rmcnew.model.config.CrcManifestProcessorConfig;

import java.util.Arrays;
import java.util.Set;

/**
 * MosGenerator
 * <p/>
 * Generate MOS for test Record generation
 */
public class MosGenerator {

    private static final String[] otherMOSs = new String[]{
            "11A",
            "11B",
            "11C",
            "11Z",
            "12A",
            "12B",
            "13A",
            "13B",
            "15A",
            "25A",
            "25C",
            "25N",
            "27A",
            "27D",
            "35D",
            "35F",
            "42B",
            "42A",
            "42H",
            "74A",
            "74D",
            "88M",
            "90A",
            "92F",
            "92G",
            "92S",
            "92Y"
    };

    private final String[] MOSs;

    public MosGenerator(CrcManifestProcessorConfig config) {
        // Read in MOS's from config
        Set<String> mosSet = config.getMosMap().keySet();
        // Then add some other MOS's
        mosSet.addAll(Arrays.asList(otherMOSs));
        MOSs = mosSet.toArray(new String[0]);
    }

    public String getRandomMos() {
        return MOSs[(int) (Math.random() * (MOSs.length - 1))];
    }


}
