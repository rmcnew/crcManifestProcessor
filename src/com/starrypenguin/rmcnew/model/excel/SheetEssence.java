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

package com.starrypenguin.rmcnew.model.excel;

import com.starrypenguin.rmcnew.shared.Util;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SheetEssence implements Iterable<RowEssence> {

    private List<RowEssence> rowEssences = new ArrayList<RowEssence>();
    private String sheetName;

    public SheetEssence() {
    }

    public SheetEssence(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public int size() {
        return rowEssences.size();
    }

    public boolean isEmpty() {
        return rowEssences.isEmpty();
    }

    public Iterator<RowEssence> iterator() {
        return rowEssences.iterator();
    }

    public boolean contains(Object o) {
        return rowEssences.contains(o);
    }

    public boolean add(RowEssence rowEssence) {
        return rowEssences.add(rowEssence);
    }

    public boolean remove(Object o) {
        return rowEssences.remove(o);
    }

    public boolean addAll(Collection<? extends RowEssence> rowEssences) {
        return this.rowEssences.addAll(rowEssences);
    }

    @Override
    public boolean equals(Object o) {
        return rowEssences.equals(o);
    }

    @Override
    public int hashCode() {
        return rowEssences.hashCode();
    }

    public void clear() {
        rowEssences.clear();
    }

    public XSSFSheet toXSSFSheet(XSSFWorkbook workbook) {
        XSSFSheet xssfSheet = null;
        if (workbook != null)  {
            if (Util.notNullAndNotEmpty(this.sheetName)) {
                xssfSheet = workbook.createSheet(this.sheetName);
            } else {
                xssfSheet = workbook.createSheet();
            }
            int rowIndex = 0;
            for (RowEssence rowEssence : rowEssences) {
                rowEssence.toXSSFRow(xssfSheet, rowIndex++);
            }
        } else {
            throw new IllegalArgumentException("Cannot create XSSFSheet on null XSSFWorkbook!");
        }
        return xssfSheet;
    }
}
