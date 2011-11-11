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

package net.mcnewfamily.rmcnew.model.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class LocationAliasMap {

	protected HashMap<String, String> map = new HashMap<String, String>();

	public LocationAliasMap() {
	}

	public int size() {
		return map.size();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public String get(Object o) {
		return map.get(o);
	}

	public boolean containsKey(Object o) {
		return map.containsKey(o);
	}

	public String put(String alias, String finalDestination) {
		return map.put(alias, finalDestination);
	}

	public String remove(Object o) {
		return map.remove(o);
	}

	public void clear() {
		map.clear();
	}

	public Collection<String> values() {
		return map.values();
	}

	public Set<String> keySet() {
		return map.keySet();
	}

	@Override
	public String toString() {
		Set<String> keys = this.keySet();
		StringBuilder builder = new StringBuilder();
		builder.append("{\n");
		for (String key : keys) {
            builder.append("\t");
			builder.append(key);
			builder.append(" => ");
			builder.append(map.get(key).toString());
			builder.append("\n");
		}
		builder.append("}\n");
		return builder.toString();
	}
}
