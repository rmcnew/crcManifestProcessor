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
import net.mcnewfamily.rmcnew.shared.Constants;
import net.mcnewfamily.rmcnew.shared.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class CountryHubCount {

    private String countryName;
    private MilCivCount countryCount = new MilCivCount(0,0);
    private TreeMap<String, MilCivCount> hubCounts = new TreeMap<String, MilCivCount>();

    public CountryHubCount(String countryName) {
        this.countryName = countryName;
    }

    public void plusOneToMilCount(HubCountry hubCountry) {
        String hub = hubCountry.getHub();
        if (hubCounts.containsKey(hub)) {
            MilCivCount hubCount = hubCounts.get(hub);
            hubCount.plusOneToMilCount();
            hubCounts.put(hub, hubCount);
        } else {
            MilCivCount milCivCount = new MilCivCount(1,0);
            hubCounts.put(hub, milCivCount);
        }
        countryCount.plusOneToMilCount();
    }

    public void plusOneToCivCount(HubCountry hubCountry) {
        String hub = hubCountry.getHub();
        if (hubCounts.containsKey(hub)) {
            MilCivCount hubCount = hubCounts.get(hub);
            hubCount.plusOneToCivCount();
            hubCounts.put(hub, hubCount);
        } else {
            MilCivCount milCivCount = new MilCivCount(0,1);
            hubCounts.put(hub, milCivCount);
        }
        countryCount.plusOneToCivCount();
    }

    public static List<String> getHeaders() {
        List<String> headers = new ArrayList<String>();
        headers.add(Constants.LOCATION);
        headers.add(Constants.MIL);
        headers.add(Constants.DOD_CIV);
        headers.add(Constants.Grand_Total);
        return headers;
    }

    public static RowEssence getHeadersRowEssence() {
        RowEssence rowEssence = new RowEssence();
        for (String header : getHeaders()) {
            CellEssence cell = new CellEssence();
            cell.setCellStyleEssence(CellSharedStyles.HEADER_STYLE);
            cell.setValue(header);
            rowEssence.add(cell);
        }
        return rowEssence;
    }

    // countryName, MIL total, CIV total, Grand total
    private List<String> getCountryTotals() {
        List<String> countryTotals = new ArrayList<String>();
        countryTotals.add(this.countryName);
        countryTotals.add(this.countryCount.getMilitaryCountString());
        countryTotals.add(this.countryCount.getCivilianCountString());
        countryTotals.add(this.countryCount.getGrandTotalString());
        return countryTotals;
    }

    public RowEssence getCountryTotalsRowEssence() {
        RowEssence rowEssence = new RowEssence();
        for (String field : getCountryTotals()) {
            CellEssence cell = new CellEssence();
            cell.setCellStyleEssence(CellSharedStyles.COUNTRY_STYLE);
            cell.setValue(field);
            rowEssence.add(cell);
        }
        return rowEssence;
    }

    private List<List<String>> getHubTotals () {
        List<List<String>> hubTotals = new ArrayList<List<String>>();
        for (String hubName : hubCounts.keySet()) {
            hubTotals.add(getSingleHubTotal(hubName, hubCounts.get(hubName) ));
        }
        return hubTotals;
    }

    private List<RowEssence> getHubTotalsRowEssences() {
        List<RowEssence> hubTotals = new ArrayList<RowEssence>();
        for (String hubName : hubCounts.keySet()) {
            hubTotals.add(getSingleHubTotalRowEssence(hubName, hubCounts.get(hubName) ));
        }
        return hubTotals;
    }


    // <tab> hubName, MIL total, CIV total, Grand total
    private List<String> getSingleHubTotal(String hubName, MilCivCount hubCount) {
        List<String> singleHubTotals = new ArrayList<String>();
        singleHubTotals.add(hubName);
        singleHubTotals.add(hubCount.getMilitaryCountString());
        singleHubTotals.add(hubCount.getCivilianCountString());
        singleHubTotals.add(hubCount.getGrandTotalString());
        return singleHubTotals;
    }

    public RowEssence getSingleHubTotalRowEssence(String hubName, MilCivCount hubCount) {
        RowEssence rowEssence = null;
        if (Util.notNullAndNotEmpty(hubName) && hubCount!= null) {
        rowEssence = new RowEssence();
        CellEssence hubNameCell = new CellEssence();
        if (hubName.equalsIgnoreCase(Constants.UNKNOWN)) {
            hubNameCell.setCellStyleEssence(CellSharedStyles.UNKNOWN_HUB_NAME_STYLE);
        } else {
            hubNameCell.setCellStyleEssence(CellSharedStyles.HUB_NAME_STYLE);
        }
        hubNameCell.setValue(hubName);
        rowEssence.add(hubNameCell);
        rowEssence.addAll(hubCount.toCellEssenceList(hubName));
        } else {
            throw new IllegalArgumentException("Cannot create RowEssence using null or empty hub name and/or hub count!");
        }
        return rowEssence;
    }

    public List<List<String>> toListOfListOfString() {
        List<List<String>> strings = new ArrayList<List<String>>();
        strings.add(this.getCountryTotals());
        strings.addAll(this.getHubTotals());
        return strings;
    }

    public List<RowEssence> toListOfRowEssences() {
        List<RowEssence> list = new ArrayList<RowEssence>();
        list.add(this.getCountryTotalsRowEssence());
        list.addAll(this.getHubTotalsRowEssences());
        return list;
    }
}
