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

import javax.swing.*;
import java.awt.*;

public class AboutTab extends JComponent {

    private static final Dimension LICENSE_TAB_SIZE = new Dimension(295, 195);
	public AboutTab() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JEditorPane editorPane = new JEditorPane("text/html", GPL3_LICENSE);
		editorPane.setEditable(false);
        editorPane.setPreferredSize(LICENSE_TAB_SIZE);
        editorPane.setVisible(true);
		JScrollPane editorScrollPane = new JScrollPane(editorPane);
		editorScrollPane.setPreferredSize(LICENSE_TAB_SIZE);
        editorScrollPane.setVisible(true);
        this.add(editorScrollPane);
	}

    private final static String GPL3_LICENSE = "<html>" +
        "<h2>CRC Manifest Processor, Copyright (c) 2011 Richard Scott McNew.</h2>" +
        "<p>" +
        "This application handles the manual process of assigning transportation" +
        " hubs for incoming NPRs from the CRC." +
        "</p><p>" +
        "The hub assignments are used to create ULNs needed to allocate transportation" +
        " assets so that the incoming NPRs can travel quickly to their final destinations." +
        "</p><p>" +
        "CRC Manifest Processor is free software: you can redistribute it and/or modify" +
        " it under the terms of the GNU General Public License as published by" +
        " the Free Software Foundation, either version 3 of the License, or" +
        " (at your option) any later version." +
        "</p><p>" +
        "CRC Manifest Processor is distributed in the hope that it will be useful," +
        " but WITHOUT ANY WARRANTY; without even the implied warranty of" +
        " MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the" +
        " GNU General Public License for more details." +
        "</p><p>" +
        "You should have received a copy of the GNU General Public License" +
        " along with CRC Manifest Processor.  If not, see" + 
        " <a href=\"http://www.gnu.org/licenses/\">http://www.gnu.org/licenses/</a>. " +
        "</p></html>";



}
