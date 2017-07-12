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

import org.apache.poi.ss.usermodel.FontCharset;
import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.ss.usermodel.FontUnderline;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// formatting data that will be used to create a XSSFFont
public class FontEssence {

    public static final String MS_FONT_CALIBRI = "Calibri";
    public static final String MS_FONT_CAMBRIA = "Cambria";
    public static final String MS_FONT_ARIAL = "Arial";
    public static final String MS_FONT_BOOKMAN_OLD = "Bookman Old Style";
    public static final String MS_FONT_COPPERPLATE_GOTHIC_BOLD = "Copperplate Gothic Bold";
    public static final String MS_FONT_COURIER_NEW = "Courier New";
    public static final String MS_FONT_IMPACT = "Impact";
    public static final String MS_FONT_TAHOMA = "Tahoma";
    public static final String MS_FONT_TIMES_NEW_ROMAN = "Times New Roman";
    public static final String MS_FONT_VERDANA = "Verdana";

    private FontFamily fontFamily;
    private XSSFColor color;
    private boolean bold;
    private boolean italic;
    private boolean underline;
    private boolean strikeout;
    private String fontName;
    private short fontHeightInPoints;

    public FontEssence() {
    }

    public FontFamily getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(FontFamily fontFamily) {
        this.fontFamily = fontFamily;
    }

    public XSSFColor getColor() {
        return color;
    }

    public void setColor(XSSFColor color) {
        this.color = color;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public boolean isUnderline() {
        return underline;
    }

    public void setUnderline(boolean underline) {
        this.underline = underline;
    }

    public boolean isStrikeout() {
        return strikeout;
    }

    public void setStrikeout(boolean strikeout) {
        this.strikeout = strikeout;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public short getFontHeightInPoints() {
        return fontHeightInPoints;
    }

    public void setFontHeightInPoints(short fontHeightInPoints) {
        this.fontHeightInPoints = fontHeightInPoints;
    }

    public XSSFFont toXSSFFont(XSSFWorkbook workbook) {
        XSSFFont xssfFont = null;
        if (workbook != null) {
            xssfFont = workbook.createFont();
            xssfFont.setCharSet(FontCharset.DEFAULT);
            xssfFont.setFamily(this.fontFamily);
            xssfFont.setBold(this.bold);
            xssfFont.setItalic(this.italic);
            if (this.underline) {
                xssfFont.setUnderline(FontUnderline.SINGLE);
            }
            xssfFont.setStrikeout(this.strikeout);
            xssfFont.setColor(this.color);
            xssfFont.setFontHeightInPoints(this.fontHeightInPoints);
        } else {
            throw new IllegalArgumentException("Cannot create XSSFFont in a null XSSFWorkbook!");
        }
        return xssfFont;
    }
}
