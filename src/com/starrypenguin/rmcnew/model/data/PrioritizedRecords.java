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

package com.starrypenguin.rmcnew.model.data;

import com.starrypenguin.rmcnew.model.excel.RowEssence;
import com.starrypenguin.rmcnew.model.excel.SheetEssence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class PrioritizedRecords implements Iterable<Record> {

    int lastRow = 0;
    private TreeSet<Record> prioritizedRecords = new TreeSet<>();

    public PrioritizedRecords() {
    }

    public int size() {
        return prioritizedRecords.size();
    }

    public boolean isEmpty() {
        return prioritizedRecords.isEmpty();
    }

    public boolean contains(Object o) {
        return prioritizedRecords.contains(o);
    }

    public boolean add(Record record) {
        return prioritizedRecords.add(record);
    }

    public void clear() {
        prioritizedRecords.clear();
    }

    public Iterator<Record> iterator() {
        return prioritizedRecords.iterator();
    }

    public int getLastRow() {
        return lastRow;
    }

    public List<RowEssence> toRowEssenceList() {
        List<RowEssence> list = new ArrayList<>();
        for (Record record : prioritizedRecords) {
            list.add(record.toRowEssence());
        }
        list.add(RowEssence.EMPTY_ROW());
        lastRow = list.size() + 2; // size + header + blank line at end
        return list;
    }

    public SheetEssence toSheetEssence(String sheetName) {
        SheetEssence sheetEssence = new SheetEssence();
        sheetEssence.setSheetName(sheetName);
        sheetEssence.add(Record.getHeaderRowEssence());
        sheetEssence.addAll(toRowEssenceList());
        return sheetEssence;
    }

}
