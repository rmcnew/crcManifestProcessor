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

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;

// formatting data that is used to style a CellEssence
public class CellStyleEssence {
// TODO:  add support for border colors and hiding

    // we mirror parts of the POI CellStyle interface that
    // are relevant to the cell style we need to model
    public enum CellBorder {
        BORDER_DASH_DOT (CellStyle.BORDER_DASH_DOT),
        BORDER_DASH_DOT_DOT (CellStyle.BORDER_DASH_DOT_DOT),
        BORDER_DASHED (CellStyle.BORDER_DASHED),
        BORDER_DOTTED (CellStyle.BORDER_DOTTED),
        BORDER_DOUBLE (CellStyle.BORDER_DOUBLE),
        BORDER_HAIR (CellStyle.BORDER_HAIR),
        BORDER_MEDIUM (CellStyle.BORDER_MEDIUM),
        BORDER_MEDIUM_DASH_DOT (CellStyle.BORDER_DASH_DOT),
        BORDER_MEDIUM_DASH_DOT_DOT (CellStyle.BORDER_DASH_DOT_DOT),
        BORDER_MEDIUM_DASHED (CellStyle.BORDER_MEDIUM_DASHED),
        BORDER_NONE (CellStyle.BORDER_NONE),
        BORDER_SLANTED_DASH_DOT (CellStyle.BORDER_SLANTED_DASH_DOT),
        BORDER_THICK (CellStyle.BORDER_THICK),
        BORDER_THIN (CellStyle.BORDER_THIN);

        private short enumShort;

        CellBorder(short enumShort) {
            this.enumShort = enumShort;
        }

        public short toPoiCellStyle() {
            return this.enumShort;
        }
    }

    public enum CellHorizontalAlignment {
        ALIGN_CENTER (CellStyle.ALIGN_CENTER),
        ALIGN_CENTER_SELECTION (CellStyle.ALIGN_CENTER_SELECTION),
        ALIGN_FILL (CellStyle.ALIGN_FILL),
        ALIGN_GENERAL (CellStyle.ALIGN_GENERAL),
        ALIGN_JUSTIFY (CellStyle.ALIGN_JUSTIFY),
        ALIGN_LEFT (CellStyle.ALIGN_LEFT),
        ALIGN_RIGHT (CellStyle.ALIGN_RIGHT);

        private short enumShort;

        CellHorizontalAlignment(short enumShort) {
            this.enumShort = enumShort;
        }

        public short toPoiCellStyle() {
            return this.enumShort;
        }
    }

    public enum CellVerticalAlignment {
        VERTICAL_BOTTOM (CellStyle.VERTICAL_BOTTOM),
        VERTICAL_CENTER (CellStyle.VERTICAL_CENTER),
        VERTICAL_JUSTIFY (CellStyle.VERTICAL_JUSTIFY),
        VERTICAL_TOP (CellStyle.VERTICAL_TOP);

        private short enumShort;

        CellVerticalAlignment(short enumShort) {
            this.enumShort = enumShort;
        }

        public short toPoiCellStyle() {
            return this.enumShort;
        }
    }

    public enum CellFillPattern {
        ALT_BARS (CellStyle.ALT_BARS),
        BIG_SPOTS (CellStyle.BIG_SPOTS),
        BRICKS (CellStyle.BRICKS),
        DIAMONDS (CellStyle.DIAMONDS),
        FINE_DOTS (CellStyle.FINE_DOTS),
        LEAST_DOTS (CellStyle.LEAST_DOTS),
        LESS_DOTS (CellStyle.LESS_DOTS),
        NO_FILL (CellStyle.NO_FILL),
        SOLID_FOREGROUND (CellStyle.SOLID_FOREGROUND),
        SPARSE_DOTS (CellStyle.SPARSE_DOTS),
        SQUARES (CellStyle.SQUARES),
        THICK_BACKWARD_DIAG (CellStyle.THICK_BACKWARD_DIAG),
        THICK_FORWARD_DIAG (CellStyle.THICK_FORWARD_DIAG),
        THICK_HORZ_BANDS (CellStyle.THICK_HORZ_BANDS),
        THICK_VERT_BANDS (CellStyle.THICK_VERT_BANDS),
        THIN_BACKWARD_DIAG (CellStyle.THICK_BACKWARD_DIAG),
        THIN_FORWARD_DIAG (CellStyle.THICK_FORWARD_DIAG),
        THIN_HORZ_BANDS (CellStyle.THICK_HORZ_BANDS),
        THIN_VERT_BANDS (CellStyle.THIN_VERT_BANDS);

        private short enumShort;

        CellFillPattern(short enumShort) {
            this.enumShort = enumShort;
        }

        public short toPoiCellStyle() {
            return this.enumShort;
        }
    }

    // defaults
    private CellBorder topBorder = CellBorder.BORDER_NONE;
    private CellBorder bottomBorder = CellBorder.BORDER_NONE;
    private CellBorder leftBorder = CellBorder.BORDER_NONE;
    private CellBorder rightBorder = CellBorder.BORDER_NONE;
    private CellVerticalAlignment verticalAlignment = CellVerticalAlignment.VERTICAL_BOTTOM;
    private CellHorizontalAlignment horizontalAlignment = CellHorizontalAlignment.ALIGN_LEFT;
    private CellFillPattern fillPattern = CellFillPattern.NO_FILL;
    private XSSFColor foregroundColor = new XSSFColor(Color.BLACK);
    private XSSFColor backgroundColor = new XSSFColor(Color.WHITE);
    private boolean wrappedText = false;
    private short indentationInSpaces = 0;

    public CellStyleEssence() {
    }

    public CellBorder getTopBorder() {
        return topBorder;
    }

    public void setTopBorder(CellBorder topBorder) {
        this.topBorder = topBorder;
    }

    public CellBorder getBottomBorder() {
        return bottomBorder;
    }

    public void setBottomBorder(CellBorder bottomBorder) {
        this.bottomBorder = bottomBorder;
    }

    public CellBorder getLeftBorder() {
        return leftBorder;
    }

    public void setLeftBorder(CellBorder leftBorder) {
        this.leftBorder = leftBorder;
    }

    public CellBorder getRightBorder() {
        return rightBorder;
    }

    public void setRightBorder(CellBorder rightBorder) {
        this.rightBorder = rightBorder;
    }

    public CellVerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(CellVerticalAlignment verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public CellHorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public void setHorizontalAlignment(CellHorizontalAlignment horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public CellFillPattern getFillPattern() {
        return fillPattern;
    }

    public void setFillPattern(CellFillPattern fillPattern) {
        this.fillPattern = fillPattern;
    }

    public XSSFColor getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(XSSFColor foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public XSSFColor getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(XSSFColor backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean isWrappedText() {
        return wrappedText;
    }

    public void setWrappedText(boolean wrappedText) {
        this.wrappedText = wrappedText;
    }

    public short getIndentationInSpaces() {
        return indentationInSpaces;
    }

    public void setIndentationInSpaces(short indentationInSpaces) {
        this.indentationInSpaces = indentationInSpaces;
    }

    public XSSFCellStyle toXSSFCellStyle(XSSFWorkbook workbook) {
        if (workbook != null) {
            XSSFCellStyle xssfCellStyle = workbook.createCellStyle();
            xssfCellStyle.setBorderTop(topBorder.toPoiCellStyle());
            xssfCellStyle.setBorderBottom(bottomBorder.toPoiCellStyle());
            xssfCellStyle.setBorderLeft(leftBorder.toPoiCellStyle());
            xssfCellStyle.setBorderRight(rightBorder.toPoiCellStyle());
            xssfCellStyle.setAlignment(horizontalAlignment.toPoiCellStyle());
            xssfCellStyle.setVerticalAlignment(verticalAlignment.toPoiCellStyle());
            xssfCellStyle.setFillForegroundColor(foregroundColor.getIndexed());
            xssfCellStyle.setFillBackgroundColor(backgroundColor.getIndexed());
            xssfCellStyle.setFillPattern(fillPattern.toPoiCellStyle());
            xssfCellStyle.setWrapText(this.wrappedText);
            xssfCellStyle.setIndention(this.indentationInSpaces);
            return xssfCellStyle;
        } else {
            throw new IllegalArgumentException("Cannot create XSSFCellStyle using a null XSSFWorkbook!");
        }
    }
}
