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

package com.starrypenguin.rmcnew.shared;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static final FileNameExtensionFilter EXCEL_FILTER = new FileNameExtensionFilter("Excel spreadsheets", "xlsx", "xls", "XLSX", "XLS");
    public static String LOCATION_REGEX = "^CAMP|^COB|^FOB|^COP|^FB|^ABP|^PB|^FORWARD OPERATING BASE|^FIRE BASE|^COMBAT OUTPOST|^ANP|^OP|^VSO|^VSP|^RCC|ANP$|AFLD$|AFB$|DC$|OP$|PRT$|AIRBASE$|AIR BASE$|AIRFIELD$|AIR FIELD$";
    public static Pattern locationPattern = Pattern.compile(LOCATION_REGEX);

    public static boolean notNullAndNotEmpty(String string) {
        return (string != null && !string.isEmpty());
    }

    public static String convertStackTraceToString(StackTraceElement[] stackTraceElements) {
        StringBuilder builder = new StringBuilder("");
        if (stackTraceElements != null) {
            for (StackTraceElement element : stackTraceElements) {
                builder.append("\t");
                builder.append(element.toString());
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public static String stripLocationPrefixesAndSuffixes(String string) {
        // remove destination prefixes: CAMP, FOB, COP, FORWARD OPERATING BASE, FIRE BASE, COMBAT OUTPOST, ANP, etc.
        // remove destination suffixes: AIRFIELD, AIRBASE, ANP, OP, etc.
        if (notNullAndNotEmpty(string)) {
            string = string.trim();
            if (string.length() > 0) {
                Matcher locationMatcher = locationPattern.matcher(string);
                string = locationMatcher.replaceFirst("");
                string = string.trim();
            }
        }
        return string;
    }

    public static String getCellValueAsStringOrEmptyString(Cell cell) {
        if (cell == null) {
            return "";
        }
        String value;
        switch (cell.getCellTypeEnum()) {
            case STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    value = cell.getDateCellValue().toString();
                } else {
                    value = Integer.toString((int) cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                value = Boolean.toString(cell.getBooleanCellValue());
                break;
            case FORMULA:
                value = cell.getCellFormula();
                break;
            default:
                value = "";
        }
        return value;
    }

    public static File attachXlsxExtensionIfMissing(File outputFile) {
        String filename = outputFile.getAbsolutePath();
        //System.out.println("Filename is: " + filename);
        if (filename.endsWith(".xlsx") || filename.endsWith(".XLSX")) {
            // do nothing
        } else if (filename.endsWith(".xls") || filename.endsWith(".XLS")) {
            // replace xls with xlsx
            filename = filename + "x";
            outputFile = new File(filename);
        } else {
            filename = filename + ".xlsx";
            outputFile = new File(filename);
        }
        //System.out.println("Fixed file is: " + outputFile.getAbsolutePath());
        return outputFile;
    }

    public static void copyXSSFSheet(XSSFSheet srcSheet, XSSFSheet destSheet) {
        if (srcSheet != null && destSheet != null) {
            for (Row srcRow : srcSheet) {
                Row destRow = destSheet.createRow(srcRow.getRowNum());
                copyXSSFRow((XSSFRow) srcRow, (XSSFRow) destRow);
            }
            //copySheetDrawings(srcSheet, destSheet);
        } else {
            throw new IllegalArgumentException("Cannot copy from / to null XSSFSheet!");
        }
    }

    public static void copySheetDrawings(XSSFSheet srcSheet, XSSFSheet destSheet) {
        if (srcSheet != null && destSheet != null) {
            XSSFDrawing srcDrawing = srcSheet.createDrawingPatriarch();
            XSSFDrawing destDrawing = destSheet.createDrawingPatriarch();
            org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing srcCTDrawing = srcDrawing.getCTDrawing();
            org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing destCTDrawing = destDrawing.getCTDrawing();
            destCTDrawing.set(srcCTDrawing.copy());
        } else {
            throw new IllegalArgumentException("Cannot copy drawings from / to null XSSFSheet!");
        }
    }

    public static void copyXSSFRow(XSSFRow srcRow, XSSFRow destRow) {
        for (Cell srcCell : srcRow) {
            XSSFCell destCell = destRow.createCell(srcCell.getColumnIndex());
            copyXSSFCell((XSSFCell) srcCell, destCell);
        }
        destRow.setHeight(srcRow.getHeight());
    }

    public static void copyXSSFCell(XSSFCell srcCell, XSSFCell destCell) {
        if (srcCell != null && destCell != null) {
            switch (srcCell.getCellTypeEnum()) {
                case STRING:
                    destCell.setCellType(CellType.STRING);
                    destCell.setCellValue(srcCell.getRichStringCellValue());
                    break;
                case NUMERIC:
                    destCell.setCellType(CellType.NUMERIC);
                    if (DateUtil.isCellDateFormatted(srcCell)) {
                        destCell.setCellValue(srcCell.getDateCellValue());
                    } else {
                        destCell.setCellValue(srcCell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    destCell.setCellType(CellType.BOOLEAN);
                    destCell.setCellValue(srcCell.getBooleanCellValue());
                    break;
                case FORMULA:
                    destCell.setCellType(CellType.FORMULA);
                    destCell.setCellValue(srcCell.getCellFormula());
                    break;
            }
            copyXSSFCellStyle(srcCell, destCell);
        } else {
            throw new IllegalArgumentException("Cannot copy from / to null XSSFCell!");
        }
    }

    public static void copyXSSFCellStyle(XSSFCell srcCell, XSSFCell destCell) {
        XSSFCellStyle srcCellStyle = srcCell.getCellStyle();
        XSSFCellStyle destCellStyle = destCell.getCellStyle();
//        destCellStyle.cloneStyleFrom(srcCellStyle);
        destCellStyle.setAlignment(srcCellStyle.getAlignmentEnum());
        destCellStyle.setVerticalAlignment(srcCellStyle.getVerticalAlignmentEnum());
        destCellStyle.setFont(srcCellStyle.getFont());
        destCellStyle.setBorderBottom(srcCellStyle.getBorderBottomEnum());
        destCellStyle.setBorderLeft(srcCellStyle.getBorderLeftEnum());
        destCellStyle.setBorderRight(srcCellStyle.getBorderRightEnum());
        destCellStyle.setBorderTop(srcCellStyle.getBorderTopEnum());
        destCellStyle.setFillPattern(srcCellStyle.getFillPatternEnum());
        // foreground color must be set before background color is set
        destCellStyle.setFillForegroundColor(srcCellStyle.getFillForegroundColor());
        destCellStyle.setFillBackgroundColor(srcCellStyle.getFillBackgroundColor());
        destCellStyle.setIndention(srcCellStyle.getIndention());
        destCellStyle.setWrapText(srcCellStyle.getWrapText());
        destCell.setCellStyle(destCellStyle);
    }

    public static String getCurrentDirectory() {
        return System.getProperty("user.dir");
    }
}
