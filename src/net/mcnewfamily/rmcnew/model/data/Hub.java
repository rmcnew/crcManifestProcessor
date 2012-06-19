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
import net.mcnewfamily.rmcnew.model.excel.CellStyleEssence;
import net.mcnewfamily.rmcnew.model.excel.RowEssence;
import net.mcnewfamily.rmcnew.shared.Constants;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;

import java.util.HashMap;
import java.util.Iterator;

public class Hub implements Iterable<Record> {

    private String name;
    private Country parentCountry;
    private int milCount = 0;
    private int civCount = 0;
    private PrioritizedRecords prioritizedRecords = new PrioritizedRecords();
    private String ulnName;
    private int ulnSeats;
    private int summaryTextRowCount = 0;

    public Hub(String name, Country parentCountry) {
        this.name = name;
        this.parentCountry = parentCountry;
    }

    public String getName() {
        return name;
    }

    public Country getParentCountry() {
        return parentCountry;
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

    public void appendRecord(Record record) {
        prioritizedRecords.add(record);
    }

    public PrioritizedRecords getPrioritizedRecords() {
        return prioritizedRecords;
    }

    public String getUlnName() {
        return ulnName;
    }

    public void setUlnName(String ulnName) {
        this.ulnName = ulnName;
    }

    public int getUlnSeats() {
        return ulnSeats;
    }

    public void setUlnSeats(int ulnSeats) {
        this.ulnSeats = ulnSeats;
    }

    public void plusOneToMilCount() {
        milCount++;
    }

    public void plusOneToCivCount() {
        civCount++;
    }

    public Iterator<Record> iterator() {
        return prioritizedRecords.iterator();
    }

    @Override
    public String toString() {
        return "Hub{" +
                "name='" + name + '\'' +
                ", milCount=" + milCount +
                ", civCount=" + civCount +
                ", prioritizedRecords=" + prioritizedRecords +
                ", ulnName='" + ulnName + '\'' +
                ", ulnSeats=" + ulnSeats +
                '}';
    }

    // <tab> hubName, MIL total, CIV total, Grand total
    public RowEssence toRowEssence() {
        RowEssence rowEssence = new RowEssence();
        CellEssence hubNameCell = new CellEssence();
        CellStyleEssence styleEssence;
        if (name.equalsIgnoreCase(Constants.UNKNOWN)) {
            hubNameCell.setCellStyleEssence(CellSharedStyles.UNKNOWN_HUB_NAME_STYLE);
            styleEssence = CellSharedStyles.UNKNOWN_HUB_ENTRY_STYLE;
        } else {
            hubNameCell.setCellStyleEssence(CellSharedStyles.HUB_NAME_STYLE);
            styleEssence = CellSharedStyles.HUB_ENTRY_STYLE;
        }
        hubNameCell.setValue(name);
        rowEssence.add(hubNameCell);
        CellEssence milCell = new CellEssence();
        milCell.setCellStyleEssence(styleEssence);
        milCell.setValue(this.getMilCountString());
        rowEssence.add(milCell);
        CellEssence civCell = new CellEssence();
        civCell.setCellStyleEssence(styleEssence);
        civCell.setValue(this.getCivCountString());
        rowEssence.add(civCell);
        CellEssence totalCell = new CellEssence();
        totalCell.setCellStyleEssence(styleEssence);
        totalCell.setValue(this.getTotalCountString());
        rowEssence.add(totalCell);

        return rowEssence;
    }

    private HashMap<String, Integer> getNonHubFinalDestinationCounts() {
        HashMap<String, Integer> nonHubFinalDestinationCounts = new HashMap<String, Integer>();
        for (Record record : prioritizedRecords) {
            String finalDestination = record.getFinalDestination();
            if (!finalDestination.equalsIgnoreCase(name) && !finalDestination.equalsIgnoreCase(Constants.UNKNOWN)) {
                if (nonHubFinalDestinationCounts.containsKey(finalDestination)) {
                    int count = nonHubFinalDestinationCounts.get(finalDestination);
                    count++;
                    nonHubFinalDestinationCounts.put(finalDestination, count);
                } else {
                    nonHubFinalDestinationCounts.put(finalDestination, 1);
                }
            }
        }
        return nonHubFinalDestinationCounts;
    }

    public XSSFClientAnchor getClientAnchor() {
        int startRow = prioritizedRecords.getLastRow();
        int endRow = startRow + summaryTextRowCount + 1;
        return new XSSFClientAnchor(0, 0, 0, 0, 3, startRow, 7, endRow);
    }
}
