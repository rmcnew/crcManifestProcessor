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

package net.mcnewfamily.rmcnew.model.data;

import net.mcnewfamily.rmcnew.model.config.HubCountry;
import net.mcnewfamily.rmcnew.model.excel.CellEssence;
import net.mcnewfamily.rmcnew.model.excel.CellSharedStyles;
import net.mcnewfamily.rmcnew.model.excel.RowEssence;
import net.mcnewfamily.rmcnew.model.excel.SheetEssence;
import net.mcnewfamily.rmcnew.shared.Constants;
import net.mcnewfamily.rmcnew.shared.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class CountryHubCountMap {

    private TreeMap<String, CountryHubCount> countryHubCounts = new TreeMap<String, CountryHubCount>();
    private MilCivCount grandTotal = new MilCivCount();

    public CountryHubCountMap() {
    }

    public void plusOneToMilCount(Record record) {
        if (record != null && Util.notNullAndNotEmpty(record.getHub()) && Util.notNullAndNotEmpty(record.getCountry())) {
            HubCountry hubCountry = new HubCountry(record.getHub(), record.getCountry());
            this.plusOneToMilCount(hubCountry);
        } else {
            throw new IllegalArgumentException("Cannot count null or empty countries or hubs!");
        }
    }

    public void plusOneToMilCount(HubCountry hubCountry) {
        String country = hubCountry.getCountry();
        if (countryHubCounts.containsKey(country)) {
            CountryHubCount countryHubCount = countryHubCounts.get(country);
            countryHubCount.plusOneToMilCount(hubCountry);
            countryHubCounts.put(country, countryHubCount);
        } else {
            CountryHubCount countryHubCount = new CountryHubCount(country);
            countryHubCount.plusOneToMilCount(hubCountry);
            countryHubCounts.put(country, countryHubCount);
        }
        grandTotal.plusOneToMilCount();
    }

    public void plusOneToCivCount(Record record) {
        if (record != null && Util.notNullAndNotEmpty(record.getHub()) && Util.notNullAndNotEmpty(record.getCountry())) {
            HubCountry hubCountry = new HubCountry(record.getHub(), record.getCountry());
            this.plusOneToCivCount(hubCountry);
        } else {
            throw new IllegalArgumentException("Cannot count null or empty countries or hubs!");
        }
    }

    public void plusOneToCivCount(HubCountry hubCountry) {
            String country = hubCountry.getCountry();
        if (countryHubCounts.containsKey(country)) {
            CountryHubCount countryHubCount = countryHubCounts.get(country);
            countryHubCount.plusOneToCivCount(hubCountry);
            countryHubCounts.put(country, countryHubCount);
        } else {
            CountryHubCount countryHubCount = new CountryHubCount(country);
            countryHubCount.plusOneToCivCount(hubCountry);
            countryHubCounts.put(country, countryHubCount);
        }
        grandTotal.plusOneToCivCount();
    }

    public List<List<String>> toListOfListOfString() {
        List<List<String>> strings = new ArrayList<List<String>>();
        strings.add(CountryHubCount.getHeaders());
        for (String country : countryHubCounts.keySet()) {
            strings.addAll(countryHubCounts.get(country).toListOfListOfString());
        }
        return strings;
    }

    public SheetEssence toSheetEssence(String sheetName) {
        SheetEssence sheetEssence = new SheetEssence(sheetName);
        sheetEssence.add(CountryHubCount.getHeadersRowEssence());
        for (String country : countryHubCounts.keySet()) {
            sheetEssence.addAll(countryHubCounts.get(country).toListOfRowEssences());
        }
        RowEssence grandTotalRowEssence = new RowEssence();
        CellEssence grandTotalName = new CellEssence();
        grandTotalName.setValue(Constants.Grand_Total);
        grandTotalName.setCellStyleEssence(CellSharedStyles.HEADER_STYLE);
        grandTotalRowEssence.add(grandTotalName);
        grandTotalRowEssence.addAll(grandTotal.toCellEssenceList(Constants.Grand_Total));
        sheetEssence.add(grandTotalRowEssence);
        return sheetEssence;
    }

    public SheetEssence toSheetEssence() {
        return this.toSheetEssence("");
    }

    public boolean isEmpty() {
        return countryHubCounts.isEmpty();
    }
}
