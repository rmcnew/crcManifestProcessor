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

import net.mcnewfamily.rmcnew.model.excel.SheetEssence;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class Records implements Iterable<Record> {

    TreeMap<String, Record> records = new TreeMap<String, Record>();

    public Records() {
    }

    public boolean isEmpty() {
        return records.isEmpty();
    }

    public void clear() {
        records.clear();
    }

    public boolean containsRecord(Record record) {
        return records.containsKey(record.hashKey());
    }

    public Record getRecord(Record record) {
        return records.get(record.hashKey());
    }

    public Record addRecord(Record record) {
        return records.put(record.hashKey(), record);
    }

    public Set<String> keySet() {
        return records.keySet();
    }

    public int size() {
        return records.size();
    }

    public Iterator<Record> iterator() {
        return new Iterator<Record>() {
            private Iterator<String> it = records.keySet().iterator();
            private String currentKey;
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Record next() {
                currentKey = it.next();
                return records.get(currentKey);
            }

            @Override
            public void remove() {
                records.remove(currentKey);
            }
        };
    }

    public SheetEssence toSheetEssence(String sheetName) {
        SheetEssence sheetEssence = new SheetEssence(sheetName);
        sheetEssence.add(Record.getHeaderRowEssence());
        for (Record record : this) {
            sheetEssence.add(record.toRowEssence());
        }
        return sheetEssence;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Records {");
        for (Record record : this) {
            builder.append(record.toString());
            builder.append("\n");
        }
        builder.append("}\n");
        return builder.toString();
    }
}
