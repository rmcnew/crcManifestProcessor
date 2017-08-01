/*
 * Copyright (c) 2017 Richard Scott McNew.
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

package com.starrypenguin.rmcnew.model.data.record_fields;

/**
 * NameGenerator
 * <p/>
 * Generate names for test Record generation
 */
public class NameGenerator {

    String[] maleNames = new String[]{
            "Cedric Getty",
            "Booker Spells",
            "Luigi Estep",
            "Alvin Ochoa",
            "Jeffery Mcmaster",
            "Reggie Lambrecht",
            "Quintin Zeitz",
            "Quincy Widmer",
            "Carroll Moots",
            "Cary Borges",
            "Hunter Mans",
            "Alberto Lamphear",
            "Santos Senko",
            "Adan Boyd",
            "Ed Putney",
            "Sol Mclees",
            "Danial Epperson",
            "Rickey Dubay",
            "Alphonso Roher",
            "Kenton Bell",
            "Jarvis Cram",
            "Louis Chilton",
            "Shawn Bonn",
            "Mason Mandez",
            "Dave Berkowitz",
            "Ross Messing",
            "Jeffry Sell",
            "Jeromy Beresford",
            "Dylan Borgen",
            "Toby Poynter",
            "Tyree Braden",
            "Ken Perryman",
            "Jame Rumsey",
            "Kraig Kiesel",
            "Hershel Maize",
            "Wendell Squires",
            "Galen Sosa",
            "Jamey Pearson",
            "Vincenzo Hennigan",
            "Maynard Grote",
            "Billy Cayetano",
            "Brian Luis",
            "Alfonso Linhart",
            "Lemuel Dantin",
            "Barton Parchman",
            "Mack Sarno",
            "Bradly Gallegos",
            "Riley Amos",
            "Perry Talbert",
            "Rodney Sieber"};

    String[] femaleNames = new String[]{
            "Iraida Batchelder",
            "Calista Hollandsworth",
            "Cristina Rickerson",
            "Catrina Robles",
            "Micheline Mcclung",
            "Clotilde Piper",
            "Latesha Guard",
            "Melodi Hinz",
            "Patria Haddock",
            "Hollie Bergstrom",
            "Marivel Baham",
            "Armandina Munro",
            "Adriana Wescott",
            "Zola Soller",
            "Vesta Degreenia",
            "Nickole Tierney",
            "Danika Dulaney",
            "Arnette Pinson",
            "Rosalie Paschke",
            "Dani Mosca",
            "Keitha Zheng",
            "Arletha Delamater",
            "Keturah Westfall",
            "Lakenya Bouie",
            "Claretta Kearns",
            "Lin Pendarvis",
            "Myrl Balla",
            "Carmella Faivre",
            "Felicitas Mendes",
            "Sherlyn Brixey",
            "Peg Bohner",
            "Petrina Imes",
            "Pamula Locicero",
            "Yvonne Palmore",
            "Rachelle Westergard",
            "Freida Betz",
            "Palmira Mccaffrey",
            "America Hague",
            "Codi Folk",
            "Julia Liebsch",
            "Nana Cordon",
            "Ellamae Prater",
            "Maragaret Dunford",
            "Susana Merchant",
            "Reatha Barela",
            "Alene Niehaus",
            "Clarice Reifsteck",
            "Elly Shemwell",
            "May Merryman"};

    public NameGenerator() {
        // nothing to do
    }

    public String getRandomMaleName() {
        return maleNames[(int) (Math.random() * (maleNames.length - 1))];
    }

    public String getRandomFemaleName() {
        return femaleNames[(int) (Math.random() * (femaleNames.length - 1))];
    }

}
