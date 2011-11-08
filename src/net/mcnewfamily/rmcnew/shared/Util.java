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

package net.mcnewfamily.rmcnew.shared;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	public static boolean notNullAndNotEmpty(String string) {
		return (string != null && !string.isEmpty());
	}

    public static String convertStackTraceToString(StackTraceElement[] stackTraceElements) {
        StringBuilder builder = new StringBuilder("");
        if (stackTraceElements != null) {
            for (StackTraceElement element : stackTraceElements ) {
                builder.append("\t");
                builder.append(element.toString());
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public static String LOCATION_REGEX = "^CAMP|^COB|^FOB|^COP|^FB|^ABP|^PB|^FORWARD OPERATING BASE|^FIRE BASE|^COMBAT OUTPOST|^ANP|^OP|^VSO|^VSP|ANP$|AFLD$|DC$|OP$|PRT$";
    public static Pattern locationPattern = Pattern.compile(LOCATION_REGEX);

    public static String stripLocationPrefixesAndSuffixes(String string) {
    // remove destination prefixes: CAMP, FOB, COP, FORWARD OPERATING BASE, FIRE BASE, COMBAT OUTPOST, ANP, etc.
    // remove destination suffixes: AIRFIELD, AIRBASE, ANP, OP, etc.
        if (notNullAndNotEmpty(string)) {
            string = string.trim();
            if (string.length() > 0) {
                Matcher locationMatcher = locationPattern.matcher(string);
                string = locationMatcher.replaceFirst("");
                string = string.trim();
            }
        }
        return string;
    }
}
