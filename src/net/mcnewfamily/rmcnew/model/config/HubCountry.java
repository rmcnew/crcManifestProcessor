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

package net.mcnewfamily.rmcnew.model.config;

public class HubCountry {

	String hub;
	String country;

	public HubCountry() {
	}

	public HubCountry(String hub, String country) {
		this.hub = hub;
		this.country = country;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		HubCountry that = (HubCountry) o;

		if (country != null ? !country.equals(that.country) : that.country != null) return false;
		if (hub != null ? !hub.equals(that.hub) : that.hub != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = hub != null ? hub.hashCode() : 0;
		result = 31 * result + (country != null ? country.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "HubCountry{" +
				"hub='" + hub + '\'' +
				", country='" + country + '\'' +
				'}';
	}
}
