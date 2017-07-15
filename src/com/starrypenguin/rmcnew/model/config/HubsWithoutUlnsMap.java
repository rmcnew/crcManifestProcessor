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

package com.starrypenguin.rmcnew.model.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HubsWithoutUlnsMap {

    private HashMap<String, Boolean> hubsWithoutUlns = new HashMap<String, Boolean>();

    public HubsWithoutUlnsMap() {
    }

    public int size() {
        return hubsWithoutUlns.size();
    }

    public boolean isEmpty() {
        return hubsWithoutUlns.isEmpty();
    }

    public Boolean get(Object o) {
        Boolean result =  hubsWithoutUlns.get(o);
        if (result == null) {
            result = false;
        }
        return result;
    }

    public Boolean put(String s, Boolean aBoolean) {
        return hubsWithoutUlns.put(s, aBoolean);
    }

    public boolean containsKey(Object o) {
        return hubsWithoutUlns.containsKey(o);
    }

    public void clear() {
        hubsWithoutUlns.clear();
    }

    public Set<String> keySet() {
        return hubsWithoutUlns.keySet();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("HubsWithoutUlnsMap {");
        for (Map.Entry<String, Boolean> entry : hubsWithoutUlns.entrySet()) {
            builder.append(entry.getKey());
            builder.append(" => ");
            builder.append(entry.getValue());
        }
        builder.append("}\n");
        return builder.toString();
    }
}
