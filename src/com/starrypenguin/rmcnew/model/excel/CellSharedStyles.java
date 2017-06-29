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

import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.xssf.usermodel.XSSFColor;

import java.awt.*;

// the CSS class for this application :)
public class CellSharedStyles {

    // colors
    private static final byte[] LIGHT_STEEL_BLUE_ARGB = {(byte)0, (byte)176, (byte)196, (byte)240};
    public static final XSSFColor XSSF_COLOR_LIGHT_STEEL_BLUE = new XSSFColor(LIGHT_STEEL_BLUE_ARGB);
    public static final XSSFColor XSSF_COLOR_RED = new XSSFColor(Color.RED);
    public static final XSSFColor XSSF_COLOR_BLACK = new XSSFColor(Color.BLACK);

    // header fields for manifest listing
    public static CellStyleEssence HEADER_STYLE = new CellStyleEssence();
    public static FontEssence HEADER_FONT = new FontEssence();
    static {
        HEADER_STYLE.setHorizontalAlignment(CellStyleEssence.CellHorizontalAlignment.ALIGN_LEFT);
        HEADER_STYLE.setVerticalAlignment(CellStyleEssence.CellVerticalAlignment.VERTICAL_CENTER);
        HEADER_STYLE.setFillPattern(CellStyleEssence.CellFillPattern.FINE_DOTS);
        HEADER_STYLE.setBackgroundColor(XSSF_COLOR_LIGHT_STEEL_BLUE);
        HEADER_STYLE.setForegroundColor(XSSF_COLOR_LIGHT_STEEL_BLUE);
        HEADER_STYLE.setBottomBorder(CellStyleEssence.CellBorder.BORDER_THIN);
        HEADER_FONT.setBold(true);
        HEADER_FONT.setFontFamily(FontFamily.MODERN);
        HEADER_FONT.setFontName(FontEssence.MS_FONT_ARIAL);
        HEADER_FONT.setFontHeightInPoints((short) 12);
        HEADER_STYLE.setFontEssence(HEADER_FONT);
    }

    // field entries for manifest listing
    public static CellStyleEssence ENTRY_STYLE = new CellStyleEssence(); // just use defaults for now
    public static FontEssence ENTRY_FONT = new FontEssence();
    static {
        ENTRY_FONT.setFontFamily(FontFamily.MODERN);
        ENTRY_FONT.setFontName(FontEssence.MS_FONT_ARIAL);
        ENTRY_FONT.setFontHeightInPoints((short) 11);
        ENTRY_STYLE.setFontEssence(ENTRY_FONT);
    }

    // country entries for summary table
    public static CellStyleEssence COUNTRY_STYLE = new CellStyleEssence(); // if it was orange juice, it would have extra pulp :)
    public static FontEssence COUNTRY_FONT = new FontEssence();
    static {
        COUNTRY_STYLE.setHorizontalAlignment(CellStyleEssence.CellHorizontalAlignment.ALIGN_LEFT);
        COUNTRY_STYLE.setVerticalAlignment(CellStyleEssence.CellVerticalAlignment.VERTICAL_CENTER);
        COUNTRY_STYLE.setBottomBorder(CellStyleEssence.CellBorder.BORDER_THIN);
        COUNTRY_FONT.setBold(true);
        COUNTRY_FONT.setFontFamily(FontFamily.MODERN);
        COUNTRY_FONT.setFontName(FontEssence.MS_FONT_ARIAL);
        COUNTRY_FONT.setFontHeightInPoints((short) 12);
        COUNTRY_STYLE.setFontEssence(COUNTRY_FONT);
    }

    // hub entries for summary table
    public static CellStyleEssence HUB_NAME_STYLE = new CellStyleEssence();
    public static FontEssence HUB_NAME_FONT = new FontEssence();
    public static CellStyleEssence HUB_ENTRY_STYLE = new CellStyleEssence();
    public static FontEssence HUB_ENTRY_FONT = new FontEssence();
    static {
        HUB_NAME_FONT.setFontFamily(FontFamily.MODERN);
        HUB_NAME_FONT.setFontName(FontEssence.MS_FONT_ARIAL);
        HUB_NAME_FONT.setFontHeightInPoints((short) 11);
        HUB_NAME_STYLE.setHorizontalAlignment(CellStyleEssence.CellHorizontalAlignment.ALIGN_LEFT);
        HUB_NAME_STYLE.setVerticalAlignment(CellStyleEssence.CellVerticalAlignment.VERTICAL_CENTER);
        HUB_NAME_STYLE.setIndentationInSpaces((short) 4);
        HUB_NAME_STYLE.setFontEssence(HUB_NAME_FONT);

        HUB_ENTRY_FONT.setFontFamily(FontFamily.MODERN);
        HUB_ENTRY_FONT.setFontName(FontEssence.MS_FONT_ARIAL);
        HUB_ENTRY_FONT.setFontHeightInPoints((short) 11);
        HUB_ENTRY_STYLE.setHorizontalAlignment(CellStyleEssence.CellHorizontalAlignment.ALIGN_LEFT);
        HUB_ENTRY_STYLE.setVerticalAlignment(CellStyleEssence.CellVerticalAlignment.VERTICAL_CENTER);
        HUB_ENTRY_STYLE.setFontEssence(HUB_ENTRY_FONT);
    }

    // field entries for UNKNOWN destinations
    public static CellStyleEssence UNKNOWN_HUB_NAME_STYLE = new CellStyleEssence();
    public static FontEssence UNKNOWN_HUB_NAME_FONT = new FontEssence();
    public static CellStyleEssence UNKNOWN_HUB_ENTRY_STYLE = new CellStyleEssence();
    public static FontEssence UNKNOWN_HUB_ENTRY_FONT = new FontEssence();
    static {
        UNKNOWN_HUB_NAME_FONT.setFontFamily(FontFamily.MODERN);
        UNKNOWN_HUB_NAME_FONT.setFontName(FontEssence.MS_FONT_ARIAL);
        UNKNOWN_HUB_NAME_FONT.setFontHeightInPoints((short) 11);
        UNKNOWN_HUB_NAME_FONT.setColor(XSSF_COLOR_RED);
        UNKNOWN_HUB_NAME_STYLE.setHorizontalAlignment(CellStyleEssence.CellHorizontalAlignment.ALIGN_LEFT);
        UNKNOWN_HUB_NAME_STYLE.setVerticalAlignment(CellStyleEssence.CellVerticalAlignment.VERTICAL_CENTER);
        UNKNOWN_HUB_NAME_STYLE.setIndentationInSpaces((short) 4);
        UNKNOWN_HUB_NAME_STYLE.setFontEssence(UNKNOWN_HUB_NAME_FONT);

        UNKNOWN_HUB_ENTRY_FONT.setFontFamily(FontFamily.MODERN);
        UNKNOWN_HUB_ENTRY_FONT.setFontName(FontEssence.MS_FONT_ARIAL);
        UNKNOWN_HUB_ENTRY_FONT.setFontHeightInPoints((short) 11);
        UNKNOWN_HUB_ENTRY_FONT.setColor(XSSF_COLOR_RED);
        UNKNOWN_HUB_ENTRY_STYLE.setHorizontalAlignment(CellStyleEssence.CellHorizontalAlignment.ALIGN_LEFT);
        UNKNOWN_HUB_ENTRY_STYLE.setVerticalAlignment(CellStyleEssence.CellVerticalAlignment.VERTICAL_CENTER);
        UNKNOWN_HUB_ENTRY_STYLE.setFontEssence(UNKNOWN_HUB_ENTRY_FONT);
    }

}
