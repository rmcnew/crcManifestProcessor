/*
 * Copyright (c) 2011 Richard Scott McNew.
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

package net.mcnewfamily.rmcnew.model.config;

import java.util.HashMap;
import java.util.Set;

public class RankComparisonMap {

    private HashMap<String, Integer> rankComparisonMap = new HashMap<String, Integer>();

    public RankComparisonMap() {
    }

    public int size() {
        return rankComparisonMap.size();
    }

    public boolean isEmpty() {
        return rankComparisonMap.isEmpty();
    }

    public Integer get(Object o) {
        return rankComparisonMap.get(o);
    }

    public Integer put(String s, Integer integer) {
        return rankComparisonMap.put(s, integer);
    }

    public boolean containsKey(Object o) {
        return rankComparisonMap.containsKey(o);
    }

    public void clear() {
        rankComparisonMap.clear();
    }

    public Set<String> keySet() {
        return rankComparisonMap.keySet();
    }
}
