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

import java.util.*;

public class Countries implements Iterable<Country>{

    TreeMap<String, Country> countries = new TreeMap<String, Country>();

    public Countries() {
    }

    public void clear() {
        countries.clear();
    }

    public boolean containsKey(Object o) {
        return countries.containsKey(o);
    }

    public Set<String> keySet() {
        return countries.keySet();
    }

    public Country put(String s, Country country) {
        return countries.put(s, country);
    }

    public int size() {
        return countries.size();
    }

    public Country remove(Object o) {
        return countries.remove(o);
    }

    public Country get(Object o) {
        return countries.get(o);
    }

    public Iterator<Country> iterator() {
        // not synchronized
        return new Iterator<Country>() {
            private Iterator<String> it = countries.keySet().iterator();
            private String currentKey;
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Country next() {
                currentKey = it.next();
                return countries.get(currentKey);
            }

            @Override
            public void remove() {
                countries.remove(currentKey);
            }
        };
    }
}
