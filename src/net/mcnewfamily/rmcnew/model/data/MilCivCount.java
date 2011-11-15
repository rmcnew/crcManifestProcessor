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
import net.mcnewfamily.rmcnew.shared.Constants;

import java.util.ArrayList;
import java.util.List;

public class MilCivCount {

    private Integer militaryCount = 0;
    private Integer civilianCount = 0;

    public MilCivCount() {
    }

    public MilCivCount(Integer militaryCount, Integer civilianCount) {
        this.militaryCount = militaryCount;
        this.civilianCount = civilianCount;
    }

    public String getMilitaryCountString() {
        return "" + militaryCount;
    }

    public Integer getMilitaryCount() {
        return militaryCount;
    }

    public void setMilitaryCount(Integer militaryCount) {
        this.militaryCount = militaryCount;
    }

    public String getCivilianCountString() {
        return "" + civilianCount;
    }

    public Integer getCivilianCount() {
        return civilianCount;
    }

    public void setCivilianCount(Integer civilianCount) {
        this.civilianCount = civilianCount;
    }

    public String getGrandTotalString() {
        int subtotal = this.militaryCount + this.civilianCount;
        return "" + subtotal;
    }

    public Integer getGrandTotal() {
        return this.militaryCount + this.civilianCount;
    }

    public void plusOneToMilCount() {
        militaryCount++;
    }

    public void plusOneToCivCount() {
        civilianCount++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MilCivCount that = (MilCivCount) o;

        if (!civilianCount.equals(that.civilianCount)) return false;
        if (!militaryCount.equals(that.militaryCount)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = militaryCount.hashCode();
        result = 31 * result + civilianCount.hashCode();
        return result;
    }

    public List<CellEssence> toCellEssenceList(String hubName) {
        CellStyleEssence styleEssence;
        if (hubName.equalsIgnoreCase(Constants.UNKNOWN)) {
            styleEssence = CellSharedStyles.UNKNOWN_HUB_ENTRY_STYLE;
        } else if (hubName.equalsIgnoreCase(Constants.Grand_Total)) {
            styleEssence = CellSharedStyles.HEADER_STYLE;
        } else {
            styleEssence = CellSharedStyles.HUB_ENTRY_STYLE;
        }
        List<CellEssence> list = new ArrayList<CellEssence>();
        CellEssence milCell = new CellEssence(); 
        milCell.setCellStyleEssence(styleEssence);
        milCell.setValue(this.getMilitaryCountString());
        list.add(milCell);
        
        CellEssence civCell = new CellEssence(); 
        civCell.setCellStyleEssence(styleEssence);
        civCell.setValue(this.getCivilianCountString());
        list.add(civCell);        
        
        CellEssence totalCell = new CellEssence(); 
        totalCell.setCellStyleEssence(styleEssence);
        totalCell.setValue(this.getGrandTotalString());
        list.add(totalCell);
        
        return list;
    }

}
