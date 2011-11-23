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

import net.mcnewfamily.rmcnew.model.config.CrcManifestProcessorConfig;
import net.mcnewfamily.rmcnew.model.config.PriorityMOSMap;
import net.mcnewfamily.rmcnew.model.config.RankComparisonMap;
import net.mcnewfamily.rmcnew.model.excel.CellEssence;
import net.mcnewfamily.rmcnew.model.excel.CellSharedStyles;
import net.mcnewfamily.rmcnew.model.excel.RowEssence;
import net.mcnewfamily.rmcnew.shared.Constants;
import net.mcnewfamily.rmcnew.shared.Util;

import java.util.ArrayList;
import java.util.List;

public class Record implements Comparable<Record>{

    private String orderOfMerit;
    private String name;
    private String rank;
    private String intraTheaterULN;
    private String MOS;
    private String serviceBranch;
    private String gender;
    private String finalDestination;
    private String hub;
    private String country;
    private String toTheaterULN;
    private String ftnId;
    private String wiasId;

    public Record() {
    }

    public Record(String orderOfMerit, String name, String rank, String intraTheaterULN, String MOS, String serviceBranch, String gender, String finalDestination, String hub, String country, String toTheaterULN, String ftnId, String wiasId) {
        this.orderOfMerit = orderOfMerit;
        this.name = name;
        this.rank = rank;
        this.intraTheaterULN = intraTheaterULN;
        this.MOS = MOS;
        this.serviceBranch = serviceBranch;
        this.gender = gender;
        this.finalDestination = finalDestination;
        this.hub = hub;
        this.country = country;
        this.toTheaterULN = toTheaterULN;
        this.ftnId = ftnId;
        this.wiasId = wiasId;
    }

    public String getOrderOfMerit() {
        return orderOfMerit;
    }

    public void setOrderOfMerit(String orderOfMerit) {
        this.orderOfMerit = orderOfMerit;
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

    public String getIntraTheaterULN() {
        return intraTheaterULN;
    }

    public void setIntraTheaterULN(String intraTheaterULN) {
        this.intraTheaterULN = intraTheaterULN;
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

    public String getToTheaterULN() {
        return toTheaterULN;
    }

    public void setToTheaterULN(String toTheaterULN) {
        this.toTheaterULN = toTheaterULN;
    }

    public String getFtnId() {
        return ftnId;
    }

    public void setFtnId(String ftnId) {
        this.ftnId = ftnId;
    }

    public String getWiasId() {
        return wiasId;
    }

    public void setWiasId(String wiasId) {
        this.wiasId = wiasId;
    }

    @Override
    public String toString() {
        return "Record{" +
                "orderOfMerit='" + orderOfMerit + '\'' +
                ", name='" + name + '\'' +
                ", rank='" + rank + '\'' +
                ", intraTheaterULN='" + intraTheaterULN + '\'' +
                ", MOS='" + MOS + '\'' +
                ", serviceBranch='" + serviceBranch + '\'' +
                ", gender='" + gender + '\'' +
                ", finalDestination='" + finalDestination + '\'' +
                ", hub='" + hub + '\'' +
                ", country='" + country + '\'' +
                ", toTheaterULN='" + toTheaterULN + '\'' +
                ", ftnId='" + ftnId + '\'' +
                ", wiasId='" + wiasId + '\'' +
                '}';
    }

    public int compareTo(Record that) {
        if (that == null) {
            throw new IllegalArgumentException("Cannot compare null Records!");
        }
        PriorityMOSMap priorityMOSMap = CrcManifestProcessorConfig.getInstance().getMosMap();
        boolean isPriorityThis = priorityMOSMap.get(MOS);
        boolean isPriorityThat = priorityMOSMap.get(that.getMOS());
        if (isPriorityThis && !isPriorityThat) {
            return 1;
        } else if (!isPriorityThis && isPriorityThat) {
            return -1;
        } else {
            RankComparisonMap rankComparisonMap = CrcManifestProcessorConfig.getInstance().getRankComparisonMap();
            Integer levelThis;
            Integer levelThat;
            if (Util.notNullAndNotEmpty(rank) && rankComparisonMap.containsKey(rank)) {
                levelThis = rankComparisonMap.get(rank);
            } else {
                levelThis = 0;
            }
            String thatRank = that.getRank();
            if (Util.notNullAndNotEmpty(thatRank) && rankComparisonMap.containsKey(thatRank)) {
                levelThat = rankComparisonMap.get(thatRank);
            } else {
                levelThat = 0;
            }
            if (levelThis > levelThat) {
                return 1;
            } else if (levelThis < levelThat) {
                return -1;
            } else {
                return 0;
            }
        }
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
        if (ftnId != null ? !ftnId.equals(record.ftnId) : record.ftnId != null) return false;
        if (!gender.equals(record.gender)) return false;
        if (hub != null ? !hub.equals(record.hub) : record.hub != null) return false;
        if (intraTheaterULN != null ? !intraTheaterULN.equals(record.intraTheaterULN) : record.intraTheaterULN != null)
            return false;
        if (!name.equals(record.name)) return false;
        if (orderOfMerit != null ? !orderOfMerit.equals(record.orderOfMerit) : record.orderOfMerit != null)
            return false;
        if (!rank.equals(record.rank)) return false;
        if (!serviceBranch.equals(record.serviceBranch)) return false;
        if (toTheaterULN != null ? !toTheaterULN.equals(record.toTheaterULN) : record.toTheaterULN != null)
            return false;
        if (wiasId != null ? !wiasId.equals(record.wiasId) : record.wiasId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderOfMerit != null ? orderOfMerit.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + rank.hashCode();
        result = 31 * result + (intraTheaterULN != null ? intraTheaterULN.hashCode() : 0);
        result = 31 * result + (MOS != null ? MOS.hashCode() : 0);
        result = 31 * result + serviceBranch.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + (finalDestination != null ? finalDestination.hashCode() : 0);
        result = 31 * result + (hub != null ? hub.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (toTheaterULN != null ? toTheaterULN.hashCode() : 0);
        result = 31 * result + (ftnId != null ? ftnId.hashCode() : 0);
        result = 31 * result + (wiasId != null ? wiasId.hashCode() : 0);
        return result;
    }

    public String hashKey() {
        return name + rank + serviceBranch + gender;
    }

    public List<String> toList() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(orderOfMerit);
        arrayList.add(name);
        arrayList.add(rank);
        arrayList.add(intraTheaterULN);
        arrayList.add(MOS);
        arrayList.add(serviceBranch);
        arrayList.add(gender);
        arrayList.add(finalDestination);
        arrayList.add(hub);
        arrayList.add(country);
        arrayList.add(toTheaterULN);
        arrayList.add(ftnId);
        arrayList.add(wiasId);
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
        arrayList.add(Constants.OML);
        arrayList.add(Constants.NAME);
        arrayList.add(Constants.RANK);
        arrayList.add(Constants.INTRA_THEATER_ULN);
        arrayList.add(Constants.AFSC_MOS);
        arrayList.add(Constants.SERVICE_BRANCH);
        arrayList.add(Constants.GENDER);
        arrayList.add(Constants.FINAL_DESTINATION);
        arrayList.add(Constants.HUB);
        arrayList.add(Constants.COUNTRY);
        arrayList.add(Constants.TO_THEATER_ULN);
        arrayList.add(Constants.FTN);
        arrayList.add(Constants.WIAS);
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

    public boolean isMilitary() {
        return (this.serviceBranch.equalsIgnoreCase(Constants.ARMY) ||
                this.serviceBranch.equalsIgnoreCase(Constants.AIR_FORCE) ||
                this.serviceBranch.equalsIgnoreCase(Constants.NAVY) ||
                this.serviceBranch.equalsIgnoreCase(Constants.MARINE_CORP) ||
                this.serviceBranch.equalsIgnoreCase(Constants.COAST_GUARD) );
    }
}
