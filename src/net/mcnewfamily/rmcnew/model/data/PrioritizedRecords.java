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

import net.mcnewfamily.rmcnew.model.excel.CellSharedStyles;
import net.mcnewfamily.rmcnew.model.excel.RowEssence;
import net.mcnewfamily.rmcnew.model.excel.SheetEssence;
import net.mcnewfamily.rmcnew.shared.Util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class PrioritizedRecords implements Iterable<Record> {

    private TreeSet<Record> prioritizedRecords = new TreeSet<Record>();
    int lastRow = 0;

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
        boolean ulnEmpty = false;
        List<RowEssence> list = new ArrayList<RowEssence>();
        for (Record record : prioritizedRecords) {
            String intraTheaterUln = record.getIntraTheaterULN();
            if (!ulnEmpty && (intraTheaterUln == null || intraTheaterUln.isEmpty() )) {
                ulnEmpty = true;
                list.add(RowEssence.EMPTY_ROW());
            }
            if (ulnEmpty) {
                list.add(record.toRowEssence(CellSharedStyles.UNKNOWN_HUB_ENTRY_STYLE));
            } else {
                list.add(record.toRowEssence());
            }
        }
        lastRow = list.size() + 2;
        return list;
    }

    public SheetEssence toSheetEssence(String sheetName) {
        SheetEssence sheetEssence = new SheetEssence();
        sheetEssence.setSheetName(sheetName);
        sheetEssence.add(Record.getHeaderRowEssence());
        sheetEssence.addAll(toRowEssenceList());
        return sheetEssence;
    }

    public int getAssignedUlnCount() {
        int count = 0;
        for (Record record : prioritizedRecords) {
            if (Util.notNullAndNotEmpty(record.getIntraTheaterULN())) {
                count++;
            }
        }
        return count;
    }
}
