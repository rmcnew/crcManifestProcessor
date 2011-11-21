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

package net.mcnewfamily.rmcnew.user_interface;

import net.mcnewfamily.rmcnew.model.data.UlnInfo;

import javax.swing.*;

public class UlnQuestionPane {

    public static UlnInfo askUserForUlnInfo(JComponent rootWindow, String hubName) {
        String ulnName;
        do {
            ulnName = JOptionPane.showInputDialog(rootWindow, "Please provide the ULN for " + hubName);
        } while (ulnName == null || ulnName.isEmpty());
        Integer seats = null;
        do {
            try {
                String seatsString = JOptionPane.showInputDialog(rootWindow, "How many seats are available for " + hubName + "?");
                seats = Integer.parseInt(seatsString);
            } catch (NumberFormatException nfe) {
                // do nothing
            }
        } while (seats == null || seats <= 0);
        return new UlnInfo(ulnName, seats);
    }
}