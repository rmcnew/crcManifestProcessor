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

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;

// contents and formatting data that will be used to
// create a XSSFCell that is part of a spreadsheet
// these cells have not yet been assigned a location within
// the spreadsheet, hence no row or column fields
// TODO: add support for cell comments
public class CellEssence {


    private CellType cellType = CellType.STRING; // default to string CellType
    private CellStyleEssence cellStyleEssence = new CellStyleEssence();
    private String value;

    public CellEssence() {
    }

    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public CellStyleEssence getCellStyleEssence() {
        return cellStyleEssence;
    }

    public void setCellStyleEssence(CellStyleEssence cellStyleEssence) {
        this.cellStyleEssence = cellStyleEssence;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public XSSFCell toXSSFCell(XSSFRow row, int columnIndex) {
        if (row != null && columnIndex >= 0) {
            XSSFCell cell = row.createCell(columnIndex);
            cell.setCellType(this.cellType);
            if (this.cellStyleEssence != null) {
                XSSFCellStyle cellStyle = this.cellStyleEssence.toXSSFCellStyle(row.getSheet().getWorkbook());
                cell.setCellStyle(cellStyle);
            }
            cell.setCellValue(this.value);
            return cell;
        } else {
            throw new IllegalArgumentException("Cannot create XSSFCell on null XSSFRow cannot be null or using invalid columnIndex!");
        }
    }
}
