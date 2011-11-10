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

import net.mcnewfamily.rmcnew.shared.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CountryHubCount {

    private String countryName;
    private MilCivCount countryCount = new MilCivCount(0,0);
    private HashMap<String, MilCivCount> hubCounts;

    public CountryHubCount() {
    }

    public void plusOneToMilCount(HubCountry hubCountry) {
        String hub = hubCountry.getHub();
        if (hubCounts.containsKey(hub)) {
            MilCivCount hubCount = hubCounts.get(hub);
            Integer militaryCount = hubCount.getMilitaryCount();
            militaryCount++;
            hubCount.setMilitaryCount(militaryCount);
            hubCounts.put(hub, hubCount);
        } else {
            MilCivCount milCivCount = new MilCivCount(1,0);
            hubCounts.put(hub, milCivCount);
        }
        Integer militaryCount = countryCount.getMilitaryCount();
        militaryCount++;
        countryCount.setMilitaryCount(militaryCount);
    }

    public void plusOneToCivCount(HubCountry hubCountry) {
        String hub = hubCountry.getHub();
        if (hubCounts.containsKey(hub)) {
            MilCivCount hubCount = hubCounts.get(hub);
            Integer civilianCount = hubCount.getCivilianCount();
            civilianCount++;
            hubCount.setCivilianCount(civilianCount);
            hubCounts.put(hub, hubCount);
        } else {
            MilCivCount milCivCount = new MilCivCount(0,1);
            hubCounts.put(hub, milCivCount);
        }
        Integer civilianCount = countryCount.getCivilianCount();
        civilianCount++;
        countryCount.setCivilianCount(civilianCount);
    }

    public List<String> getHeaders() {
        List<String> headers = new ArrayList<String>();
        headers.add(Constants.LOCATION);
        headers.add(Constants.MIL);
        headers.add(Constants.DOD_CIV);
        headers.add(Constants.Grand_Total);
        return headers;
    }

    public List<List<String>> toListOfListOfString() {
        List<List<String>> strings = new ArrayList<List<String>>();
        strings.add(getHeaders());
        // more to do here
        return strings;
    }
}
