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

/**
 * RankGenerator
 * <p/>
 * Generate ranks for test Record generation
 */
public class RankGenerator {

    private final String[] ranks;

    public RankGenerator(CrcManifestProcessorConfig config) {
        ranks = config.getRankComparisonMap().keySet().toArray(new String[0]);
    }

    public String getRandomRank() {
        return ranks[(int) (Math.random() * (ranks.length - 1))];
    }

    public String getPossiblyBadRank() {
        if (Coin.flip()) {
            return getRandomRank();
        } else {
            return null;
        }
    }
}
