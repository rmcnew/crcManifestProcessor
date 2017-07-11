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

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;

// formatting data that is used to style a CellEssence
public class CellStyleEssence {
// TODO:  add support for border colors and hiding

    // defaults
    private BorderStyle topBorder = BorderStyle.NONE;
    private BorderStyle bottomBorder = BorderStyle.NONE;
    private BorderStyle leftBorder = BorderStyle.NONE;
    private BorderStyle rightBorder = BorderStyle.NONE;
    private VerticalAlignment verticalAlignment = VerticalAlignment.BOTTOM;
    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEFT;
    private FillPatternType fillPattern = FillPatternType.NO_FILL;
    private XSSFColor foregroundColor = new XSSFColor(Color.BLACK);
    private XSSFColor backgroundColor = new XSSFColor(Color.WHITE);
    private boolean wrappedText = false;
    private short indentationInSpaces = 0;
    private FontEssence fontEssence = new FontEssence();

    public CellStyleEssence() {
    }

    public BorderStyle getTopBorder() {
        return topBorder;
    }

    public void setTopBorder(BorderStyle topBorder) {
        this.topBorder = topBorder;
    }

    public BorderStyle getBottomBorder() {
        return bottomBorder;
    }

    public void setBottomBorder(BorderStyle bottomBorder) {
        this.bottomBorder = bottomBorder;
    }

    public BorderStyle getLeftBorder() {
        return leftBorder;
    }

    public void setLeftBorder(BorderStyle leftBorder) {
        this.leftBorder = leftBorder;
    }

    public BorderStyle getRightBorder() {
        return rightBorder;
    }

    public void setRightBorder(BorderStyle rightBorder) {
        this.rightBorder = rightBorder;
    }

    public VerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public FillPatternType getFillPattern() {
        return fillPattern;
    }

    public void setFillPattern(FillPatternType fillPattern) {
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

    public FontEssence getFontEssence() {
        return fontEssence;
    }

    public void setFontEssence(FontEssence fontEssence) {
        this.fontEssence = fontEssence;
    }

    public XSSFCellStyle toXSSFCellStyle(XSSFWorkbook workbook) {
        if (workbook != null) {
            XSSFCellStyle xssfCellStyle = workbook.createCellStyle();
            xssfCellStyle.setBorderTop(topBorder);
            xssfCellStyle.setBorderBottom(bottomBorder);
            xssfCellStyle.setBorderLeft(leftBorder);
            xssfCellStyle.setBorderRight(rightBorder);
            xssfCellStyle.setAlignment(horizontalAlignment);
            xssfCellStyle.setVerticalAlignment(verticalAlignment);
            xssfCellStyle.setFillPattern(fillPattern);
            // foreground color must be set before background color is set
            xssfCellStyle.setFillForegroundColor(foregroundColor);
            xssfCellStyle.setFillBackgroundColor(backgroundColor);
            xssfCellStyle.setWrapText(this.wrappedText);
            xssfCellStyle.setIndention(this.indentationInSpaces);
            if (this.fontEssence != null) {
                    XSSFFont font = fontEssence.toXSSFFont(workbook);
                    xssfCellStyle.setFont(font);
            }
            return xssfCellStyle;
        } else {
            throw new IllegalArgumentException("Cannot create XSSFCellStyle using a null XSSFWorkbook!");
        }
    }
}
