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

public class Hub {

    private String name;
    private Country country;
    private Records military = new Records();
    private Records civilian = new Records();

    public Hub(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hub hub = (Hub) o;

        if (!name.equals(hub.name)) return false;
        if (!country.equals(hub.country)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }

    public int getMilitaryCount() {
        return military.size();
    }

    public int getCivilianCount() {
        return civilian.size();
    }

    public void add(Record record) {
        if (record == null) {
            throw new IllegalArgumentException("Cannot add null Record to Hub!");
        }
        if (record.isMilitary()) {
            military.put(record.hashKey(), record);
        } else {
            civilian.put(record.hashKey(), record);
        }
    }

    public boolean containsRecord(Record record) {
        return military.containsKey(record.hashKey()) || civilian.containsKey(record.hashKey());
    }
}
