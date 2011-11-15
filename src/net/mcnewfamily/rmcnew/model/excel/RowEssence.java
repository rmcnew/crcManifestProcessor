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

package net.mcnewfamily.rmcnew.model.excel;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// contents and formatting data that will be used to
// create a XSSFRow that is part of a spreadsheet
// these cells have not yet been assigned a location within
// the spreadsheet, hence no row or column fields
public class RowEssence implements Iterable<CellEssence> {

    private LinkedList<CellEssence> cellEssences = new LinkedList<CellEssence>();

    public RowEssence() {
    }

    public RowEssence(List<CellEssence> cellEssences) {
        this.cellEssences = new LinkedList<CellEssence>();
        this.cellEssences.addAll(cellEssences);
    }

    public int size() {
        return cellEssences.size();
    }

    public boolean isEmpty() {
        return cellEssences.isEmpty();
    }

    public Iterator<CellEssence> iterator() {
        return cellEssences.iterator();
    }

    public boolean add(CellEssence cellEssence) {
        return cellEssences.add(cellEssence);
    }

    public boolean remove(Object o) {
        return cellEssences.remove(o);
    }

    public boolean addAll(Collection<? extends CellEssence> cellEssences) {
        return this.cellEssences.addAll(cellEssences);
    }

    public void clear() {
        cellEssences.clear();
    }

    public void push(CellEssence cellEssence) {
        cellEssences.push(cellEssence);
    }

    @Override
    public boolean equals(Object o) {
        return cellEssences.equals(o);
    }

    @Override
    public int hashCode() {
        return cellEssences.hashCode();
    }

    public XSSFRow toXSSFRow(XSSFSheet xssfSheet, int rowIndex) {
        XSSFRow xssfRow = null;
        if (xssfSheet != null && rowIndex >= 0) {
            xssfRow = xssfSheet.createRow(rowIndex);
            int cellIndex = 0;
            for (CellEssence cellEssence : cellEssences) {
                cellEssence.toXSSFCell(xssfRow, cellIndex++);
            }
        } else {
            throw new IllegalArgumentException("Cannot create XSSFRow on null XSSFSheet or using invalid row index!");
        }
        return xssfRow;
    }
}
