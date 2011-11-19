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

import java.util.Comparator;

public class Country {

    private String name;
    private Hubs hubs = new Hubs();

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Hubs getHubs() {
        return hubs;
    }

    public void setHubs(Hubs hubs) {
        this.hubs = hubs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (!name.equals(country.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public int getMilitaryCount() {
        return hubs.getMilitaryCount();
    }

    public int getCivilianCount() {
        return hubs.getCivilianCount();
    }

    private static final Comparator<Country> comparator  = new Comparator<Country>() {
        @Override
        public int compare(Country countryA, Country countryB) {
            if (countryA == null || countryB == null) {
                throw new IllegalArgumentException("Cannot compare a null Country!");
            }
            return countryA.getName().compareTo(countryB.getName());
        }
    };

    public static Comparator<Country> getComparator() {
        return comparator;
    }
}
