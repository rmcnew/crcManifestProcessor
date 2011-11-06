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

public class Constants {

	public final static String UNKNOWN = "UNKNOWN";
	public final static String FINAL_DESTINATION = "FINAL_DESTINATION";
	public final static String HUB = "HUB";
	public final static String COUNTRY = "COUNTRY";
	public final static String NOT_FOUND = "NOT_FOUND";
	public final static String MOS = "MOS";
	public final static String KUWAIT = "KUWAIT";
	public final static String QATAR = "QATAR";
	public final static String AFGHANISTAN = "AFGHANISTAN";
	public final static String ALI_AL_SALEM = "ALI_AL_SALEM";
	public final static String AL_UDEID = "AL_UDEID";
	public final static String BAGRAM = "BAGRAM";

	public static enum ReturnValue {
		SUCCESS("Successful execution"),
		ERROR("An error occurred");

		private final String description;

		public String getDescription() {
			return description;
		}

		ReturnValue(String theDescription) {
			description = theDescription;
		}
	}

}
