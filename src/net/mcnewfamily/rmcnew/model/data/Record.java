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
import net.mcnewfamily.rmcnew.model.excel.RowEssence;
import net.mcnewfamily.rmcnew.model.excel.CellSharedStyles;
import net.mcnewfamily.rmcnew.shared.Constants;

import java.util.ArrayList;
import java.util.List;

public class Record {

	String name;
	String rank;
	String MOS;
	String serviceBranch;
	String gender;
	String finalDestination;
	String hub;
	String country;

	public Record() {
	}

	public Record(String name, String rank, String MOS, String serviceBranch, String gender, String finalDestination, String hub, String country) {
		this.name = name;
		this.rank = rank;
		this.MOS = MOS;
		this.serviceBranch = serviceBranch;
		this.gender = gender;
		this.finalDestination = finalDestination;
		this.hub = hub;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getMOS() {
		return MOS;
	}

	public void setMOS(String MOS) {
		this.MOS = MOS;
	}

	public String getServiceBranch() {
		return serviceBranch;
	}

	public void setServiceBranch(String serviceBranch) {
		this.serviceBranch = serviceBranch;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFinalDestination() {
		return finalDestination;
	}

	public void setFinalDestination(String finalDestination) {
		this.finalDestination = finalDestination;
	}

	public String getHub() {
		return hub;
	}

	public void setHub(String hub) {
		this.hub = hub;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

    public String toCSV() {
        StringBuilder builder = new StringBuilder(name);
        builder.append(",");
        builder.append(rank);
        builder.append(",");
        builder.append(MOS);
        builder.append(",");
        builder.append(serviceBranch);
        builder.append(",");
        builder.append(gender);
        builder.append(",");
        builder.append(finalDestination);
        builder.append(",");
        builder.append(hub);
        builder.append(",");
        builder.append(country);
        builder.append("\n");
        return builder.toString();
    }

    public static String getHeadersCSV() {
        StringBuilder builder = new StringBuilder(Constants.NAME);
        builder.append(",");
        builder.append(Constants.RANK);
        builder.append(",");
        builder.append(Constants.MOS);
        builder.append(",");
        builder.append(Constants.SERVICE_BRANCH);
        builder.append(",");
        builder.append(Constants.GENDER);
        builder.append(",");
        builder.append(Constants.FINAL_DESTINATION);
        builder.append(",");
        builder.append(Constants.HUB);
        builder.append(",");
        builder.append(Constants.COUNTRY);
        builder.append("\n");
        return builder.toString();
    }

    public List<String> toList() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(name);
        arrayList.add(rank);
        arrayList.add(MOS);
        arrayList.add(serviceBranch);
        arrayList.add(gender);
        arrayList.add(finalDestination);
        arrayList.add(hub);
        arrayList.add(country);
        return arrayList;
    }

    public RowEssence toRowEssence() {
        RowEssence rowEssence = new RowEssence();
        for (String field : toList()) {
            CellEssence cell = new CellEssence();
            cell.setCellStyleEssence(CellSharedStyles.ENTRY_STYLE);
            cell.setValue(field);
            rowEssence.add(cell);
        }
        return rowEssence;
    }

    public static List<String> getHeaders() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(Constants.NAME);
        arrayList.add(Constants.RANK);
        arrayList.add(Constants.MOS);
        arrayList.add(Constants.SERVICE_BRANCH);
        arrayList.add(Constants.GENDER);
        arrayList.add(Constants.FINAL_DESTINATION);
        arrayList.add(Constants.HUB);
        arrayList.add(Constants.COUNTRY);
        return arrayList;
    }

    public static RowEssence getHeaderRowEssence() {
        RowEssence headerRow = new RowEssence();
        for (String header : getHeaders()) {
            CellEssence cell = new CellEssence();
            cell.setCellStyleEssence(CellSharedStyles.HEADER_STYLE);
            cell.setValue(header);
            headerRow.add(cell);
        }
        return headerRow;
    }

    @Override
    public String toString() {
        return "Record{" +
                "name='" + name + '\'' +
                ", rank='" + rank + '\'' +
                ", MOS='" + MOS + '\'' +
                ", serviceBranch='" + serviceBranch + '\'' +
                ", gender='" + gender + '\'' +
                ", finalDestination='" + finalDestination + '\'' +
                ", hub='" + hub + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (MOS != null ? !MOS.equals(record.MOS) : record.MOS != null) return false;
        if (country != null ? !country.equals(record.country) : record.country != null) return false;
        if (finalDestination != null ? !finalDestination.equals(record.finalDestination) : record.finalDestination != null)
            return false;
        if (!gender.equals(record.gender)) return false;
        if (hub != null ? !hub.equals(record.hub) : record.hub != null) return false;
        if (!name.equals(record.name)) return false;
        if (!rank.equals(record.rank)) return false;
        if (!serviceBranch.equals(record.serviceBranch)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + rank.hashCode();
        result = 31 * result + (MOS != null ? MOS.hashCode() : 0);
        result = 31 * result + serviceBranch.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + (finalDestination != null ? finalDestination.hashCode() : 0);
        result = 31 * result + (hub != null ? hub.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    public boolean isMilitary() {
        return (this.serviceBranch.equalsIgnoreCase(Constants.ARMY) ||
                this.serviceBranch.equalsIgnoreCase(Constants.AIR_FORCE) ||
                this.serviceBranch.equalsIgnoreCase(Constants.NAVY) ||
                this.serviceBranch.equalsIgnoreCase(Constants.MARINE_CORP) ||
                this.serviceBranch.equalsIgnoreCase(Constants.COAST_GUARD) );
    }
}
