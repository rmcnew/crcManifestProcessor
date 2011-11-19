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

package net.mcnewfamily.rmcnew.model.data;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class Hubs implements Iterable<Hub> {

    TreeMap<String, Hub> hubs = new TreeMap<String, Hub>();

    public Hubs() {
    }

    public void clear() {
        hubs.clear();
    }

    public boolean containsKey(Object o) {
        return hubs.containsKey(o);
    }

    public Hub get(Object o) {
        return hubs.get(o);
    }

    public Set<String> keySet() {
        return hubs.keySet();
    }

    public Hub put(String s, Hub hub) {
        return hubs.put(s, hub);
    }

    public Hub remove(Object o) {
        return hubs.remove(o);
    }

    public int size() {
        return hubs.size();
    }

    public Iterator<Hub> iterator() {
        // not synchronized
        return new Iterator<Hub>() {
            private Iterator<String> it = hubs.keySet().iterator();
            private String currentKey;
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Hub next() {
                currentKey = it.next();
                return hubs.get(currentKey);
            }

            @Override
            public void remove() {
                hubs.remove(currentKey);
            }
        };
    }


    public int getMilitaryCount() {
        int militaryCount = 0;
        for (Hub hub : this) {
            militaryCount += hub.getMilitaryCount();
        }
        return militaryCount;
    }

    public int getCivilianCount() {
        int civilianCount = 0;
        for (Hub hub : this) {
            civilianCount += hub.getCivilianCount();
        }
        return civilianCount;
    }
}
