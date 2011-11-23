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

import net.mcnewfamily.rmcnew.model.excel.CellEssence;
import net.mcnewfamily.rmcnew.model.excel.CellSharedStyles;
import net.mcnewfamily.rmcnew.model.excel.RowEssence;
import net.mcnewfamily.rmcnew.shared.Constants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class Country implements Iterable<Hub> {

    private String name;
    private TreeMap<String, Hub> hubs = new TreeMap<String, Hub>();
    private int milCount = 0;
    private int civCount = 0;

    public Country(String name) {
        this.name = name;
    }

    public int getMilCount() {
        return milCount;
    }

    public String getMilCountString() {
        return "" + milCount;
    }

    public int getCivCount() {
        return civCount;
    }

    public String getCivCountString() {
        return "" + civCount;
    }

    public int getTotalCount() {
        return milCount + civCount;
    }

    public String getTotalCountString() {
        return "" + (milCount + civCount);
    }

    public String getName() {
        return name;
    }

    public Iterator<Hub> iterator() {
        return new Iterator<Hub>() {
            private Iterator<String> it = hubs.keySet().iterator();
            private String currentKey;
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Hub next() {
                currentKey = it.next();
                return hubs.get(currentKey);
            }

            @Override
            public void remove() {
                hubs.remove(currentKey);
            }
        };
    }

    public void plusOneToMilCountAndAppendRecord(Record record) {
        String hubName = record.getHub();
        Hub hub;
        if (hubs.containsKey(hubName)) {
            hub = hubs.get(hubName);
            hub.plusOneToMilCount();
            hubs.put(hubName, hub);
        } else {
            hub = new Hub(hubName);
            hub.plusOneToMilCount();
            hubs.put(hubName, hub);
        }
        milCount++;
        hub.appendRecord(record);
    }

    public void plusOneToCivCountAndAppendRecord(Record record) {
        String hubName = record.getHub();
        Hub hub;
        if (hubs.containsKey(hubName)) {
            hub = hubs.get(hubName);
            hub.plusOneToCivCount();
            hubs.put(hubName, hub);
        } else {
            hub = new Hub(hubName);
            hub.plusOneToCivCount();
            hubs.put(hubName, hub);
        }
        civCount++;
        hub.appendRecord(record);
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

    // name, MIL total, CIV total, Grand total
    private List<String> getCountryTotals() {
        List<String> countryTotals = new ArrayList<String>();
        countryTotals.add(name);
        countryTotals.add(getMilCountString());
        countryTotals.add(getCivCountString());
        countryTotals.add(getTotalCountString());
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

    private List<RowEssence> getHubTotalsRowEssences() {
        List<RowEssence> hubTotals = new ArrayList<RowEssence>();
        for (String hubName : hubs.keySet()) {
            Hub hub = hubs.get(hubName);
            hubTotals.add(hub.toRowEssence());
        }
        return hubTotals;
    }

    public List<RowEssence> toListOfRowEssences() {
        List<RowEssence> list = new ArrayList<RowEssence>();
        list.add(this.getCountryTotalsRowEssence());
        list.addAll(this.getHubTotalsRowEssences());
        return list;
    }
}
