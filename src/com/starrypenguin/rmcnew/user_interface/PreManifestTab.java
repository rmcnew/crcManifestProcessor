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

package com.starrypenguin.rmcnew.user_interface;

import com.starrypenguin.rmcnew.controller.PreManifestController;
import com.starrypenguin.rmcnew.shared.Constants;

public class PreManifestTab extends AbstractManifestTab {

	public PreManifestTab() {
        super(Constants.PRE_MANIFEST_BUTTON_TEXT, Constants.PRE_MANIFEST_SUCCESS_TEXT, new PreManifestController());
	}
}
