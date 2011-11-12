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

import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.xssf.usermodel.XSSFColor;

import java.awt.*;

// the CSS class for this application :)
public class CellSharedStyles {

    // header fields for manifest listing
    public static CellStyleEssence HEADER_STYLE = new CellStyleEssence();
    public static FontEssence HEADER_FONT = new FontEssence();
    static {
        HEADER_STYLE.setHorizontalAlignment(CellStyleEssence.CellHorizontalAlignment.ALIGN_LEFT);
        HEADER_STYLE.setVerticalAlignment(CellStyleEssence.CellVerticalAlignment.VERTICAL_CENTER);
        HEADER_STYLE.setBackgroundColor(new XSSFColor(Color.LIGHT_GRAY));
        HEADER_FONT.setBold(true);
        HEADER_FONT.setFontFamily(FontFamily.MODERN);
        HEADER_FONT.setFontName(FontEssence.MS_FONT_ARIAL);
        HEADER_FONT.setUnderline(true);
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
}
