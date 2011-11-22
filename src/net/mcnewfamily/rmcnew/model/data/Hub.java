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

public class Hub {

    private String name;
    private int milCount = 0;
    private int civCount = 0;
    private Records records;
    private String ulnName;
    private int ulnSeats;

    public Hub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

    public Records getRecords() {
        return records;
    }

    public void setRecords(Records records) {
        this.records = records;
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

    @Override
    public String toString() {
        return "Hub{" +
                "name='" + name + '\'' +
                ", milCount=" + milCount +
                ", civCount=" + civCount +
                ", records=" + records +
                ", ulnName='" + ulnName + '\'' +
                ", ulnSeats=" + ulnSeats +
                '}';
    }

    // <tab> hubName, MIL total, CIV total, Grand total
    public RowEssence toRowEssence() {
        RowEssence rowEssence = new RowEssence();
        CellEssence hubNameCell = new CellEssence();
        CellStyleEssence styleEssence;
        System.out.println(toString());
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

}
