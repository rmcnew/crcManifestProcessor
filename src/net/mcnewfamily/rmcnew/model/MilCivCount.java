/*
 * Copyright (c) 2011 Richard Scott McNew.
 *
 * This file is part of crcManifestProcessor.
 *
 *     crcManifestProcessor is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     crcManifestProcessor is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with crcManifestProcessor.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.mcnewfamily.rmcnew.model;

public class MilCivCount {

    private Integer militaryCount = 0;
    private Integer civilianCount = 0;

    public MilCivCount() {
    }

    public MilCivCount(Integer militaryCount, Integer civilianCount) {
        this.militaryCount = militaryCount;
        this.civilianCount = civilianCount;
    }

    public Integer getMilitaryCount() {
        return militaryCount;
    }

    public void setMilitaryCount(Integer militaryCount) {
        this.militaryCount = militaryCount;
    }

    public Integer getCivilianCount() {
        return civilianCount;
    }

    public void setCivilianCount(Integer civilianCount) {
        this.civilianCount = civilianCount;
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

    public Integer getTotal() {
        return this.militaryCount + this.civilianCount;
    }
}
