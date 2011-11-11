/*
 * Copyright (c) 2011 Richard Scott McNew.
 *
 * This file is part of crcManifestProcessor.
 *
 *     crcManifestProcessor is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     crcManifestProcessor is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with crcManifestProcessor.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.mcnewfamily.rmcnew.model.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

// contents and formatting data that will be used to
// create a XSSFCell that is part of a spreadsheet
// these cells have not yet been assigned a location within
// the spreadsheet, hence no row or column fields
public class CellEssence {

    // we mirror some parts of the POI Cell interface that are relevant to
    // the part of the cell we need to model
    public enum CellType {
        CELL_TYPE_NUMERIC (Cell.CELL_TYPE_NUMERIC),
        CELL_TYPE_STRING (Cell.CELL_TYPE_STRING),
        CELL_TYPE_FORMULA (Cell.CELL_TYPE_FORMULA),
        CELL_TYPE_BLANK (Cell.CELL_TYPE_BLANK),
        CELL_TYPE_BOOLEAN (Cell.CELL_TYPE_BOOLEAN),
        CELL_TYPE_ERROR (Cell.CELL_TYPE_ERROR);

        private int enumInt;

        CellType(int enumInt) {
            this.enumInt = enumInt;
        }

        public int toPoiCellType() {
            return this.enumInt;

        }
    }

    private CellType cellType = CellType.CELL_TYPE_STRING; // default to string CellType
    private CellStyleEssence cellStyleEssence = null;
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
            cell.setCellType(this.cellType.toPoiCellType());
            cell.setCellValue(this.value);
            cell.setCellStyle(this.cellStyleEssence.toXSSFCellStyle());
            return cell;
        } else {
            throw new IllegalArgumentException("row cannot be null and columnIndex must be non-negative!");
        }
    }
}
