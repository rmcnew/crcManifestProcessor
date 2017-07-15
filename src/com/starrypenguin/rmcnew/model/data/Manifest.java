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

package com.starrypenguin.rmcnew.model.data;

import com.starrypenguin.rmcnew.model.excel.*;
import com.starrypenguin.rmcnew.shared.Constants;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Manifest implements Iterable<Country> {

    private Records records = new Records();
    private TreeMap<String, Country> countries = new TreeMap<>();
    private int milTotal = 0;
    private int civTotal = 0;
    private XSSFSheet instructions;

	public Manifest()  {
	}

    public Records getRecords() {
        return records;
    }

    public void setRecords(Records records) {
        this.records = records;
    }

    public int getMilTotal() {
        return milTotal;
    }

    public String getMilTotalString() {
        return "" + milTotal;
    }

    public int getCivTotal() {
        return civTotal;
    }

    public String getCivTotalString() {
        return "" + civTotal;
    }

    public int getGrandTotal() {
        return milTotal + civTotal;
    }

    public String getGrandTotalString() {
        return "" + (milTotal + civTotal);
    }

    public XSSFSheet getInstructions() {
        return instructions;
    }

    public void setInstructions(XSSFSheet instructions) {
        this.instructions = instructions;
    }

    @Override
    public Iterator<Country> iterator() {
        return new Iterator<Country>() {
            private Iterator<String> it = countries.keySet().iterator();
            private String currentKey;
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Country next() {
                currentKey = it.next();
                return countries.get(currentKey);
            }

            @Override
            public void remove() {
                countries.remove(currentKey);
            }
        };
    }

    public void doSummaryCounts() {
        for (Record record : records) {
            if (record.isMilitary()) {
                plusOneToMilCount(record);
            } else {
                plusOneToCivCount(record);
            }
        }
    }

    public void plusOneToMilCount(Record record) {
        String countryName = record.getCountry();
        if (countries.containsKey(countryName)) {
            Country country = countries.get(countryName);
            country.plusOneToMilCountAndAppendRecord(record);
            countries.put(countryName, country);
        } else {
            Country country = new Country(countryName);
            country.plusOneToMilCountAndAppendRecord(record);
            countries.put(countryName, country);
        }
        milTotal++;
    }

    public void plusOneToCivCount(Record record) {
        String countryName = record.getCountry();
        if (countries.containsKey(countryName)) {
            Country country = countries.get(countryName);
            country.plusOneToCivCountAndAppendRecord(record);
            countries.put(countryName, country);
        } else {
            Country country = new Country(countryName);
            country.plusOneToCivCountAndAppendRecord(record);
            countries.put(countryName, country);
        }
        civTotal++;
    }

    public RowEssence getGrandTotalRowEssence() {
        RowEssence rowEssence = new RowEssence();
        CellEssence hubNameCell = new CellEssence();
        CellStyleEssence styleEssence = CellSharedStyles.COUNTRY_STYLE;
        hubNameCell.setCellStyleEssence(styleEssence);
        hubNameCell.setValue(Constants.Grand_Total);
        rowEssence.add(hubNameCell);
        CellEssence milCell = new CellEssence();
        milCell.setCellStyleEssence(styleEssence);
        milCell.setValue(this.getMilTotalString());
        rowEssence.add(milCell);
        CellEssence civCell = new CellEssence();
        civCell.setCellStyleEssence(styleEssence);
        civCell.setValue(this.getCivTotalString());
        rowEssence.add(civCell);
        CellEssence totalCell = new CellEssence();
        totalCell.setCellStyleEssence(styleEssence);
        totalCell.setValue(this.getGrandTotalString());
        rowEssence.add(totalCell);

        return rowEssence;
    }


    public SheetEssence toSheetEssence(String sheetName) {
        SheetEssence sheetEssence = new SheetEssence(sheetName);
        sheetEssence.add(Country.getHeadersRowEssence());
        for (Map.Entry<String, Country> entry : countries.entrySet()) {
            sheetEssence.addAll(entry.getValue().toListOfRowEssences());
        }
        sheetEssence.add(getGrandTotalRowEssence());
        return sheetEssence;
    }

    public boolean isEmpty() {
        return countries.isEmpty();
    }
}
