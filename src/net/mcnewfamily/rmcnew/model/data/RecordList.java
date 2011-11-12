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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecordList implements Iterable<Record> {

    List<Record> records = new ArrayList<Record>();

    public RecordList() {
    }

    public RecordList(List<Record> records) {
        this.records = records;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public int size() {
        return records.size();
    }

    public boolean isEmpty() {
        return records.isEmpty();
    }

    public boolean add(Record record) {
        return records.add(record);
    }

    public boolean remove(Object o) {
        return records.remove(o);
    }

    public void add(int i, Record record) {
        records.add(i, record);
    }

    public Record set(int i, Record record) {
        return records.set(i, record);
    }

    public Record get(int i) {
        return records.get(i);
    }

    public Record remove(int i) {
        return records.remove(i);
    }

    public Iterator<Record> iterator() {
        return records.iterator();
    }

    public List<List<String>> toListOfListOfString() {
        List<List<String>> strings = new ArrayList<List<String>>();
        strings.add(Record.getHeaders());
        for (Record record : records) {
            strings.add(record.toList());
        }
        return strings;
    }

    public SheetEssence toSheetEssence(String sheetName) {
        SheetEssence sheetEssence = new SheetEssence(sheetName);
        sheetEssence.add(Record.getHeaderRowEssence());
        for (Record record : records) {
            sheetEssence.add(record.toRowEssence());
        }
        return sheetEssence;
    }

    public SheetEssence toSheetEssence() {
        return this.toSheetEssence("");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("RecordList {");
        for (Record record : records) {
            builder.append(record.toString());
            builder.append("\n");
        }
        builder.append("}\n");
        return builder.toString();
    }
}
