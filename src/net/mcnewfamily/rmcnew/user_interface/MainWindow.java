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

package net.mcnewfamily.rmcnew.user_interface;

import net.mcnewfamily.rmcnew.model.CrcManifest;
import net.mcnewfamily.rmcnew.shared.Util;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
	public static final String mainTitle = "CRC Manifest Processor";
	public static final String licenseTabTitle = "License";
	private static CrcManifest manifest;

	private JTabbedPane tabbedPane = new JTabbedPane();

	public MainWindow(String s) throws HeadlessException {
		super(s);
	}

	public void addTab(String componentTitle, JComponent component) {
		if (component != null && Util.notNullAndNotEmpty(componentTitle)) {
			tabbedPane.addTab(componentTitle, component);
		}
	}

	private static void init() {
		try {
			manifest = new CrcManifest();
		} catch (Exception e) {

		}
	}

	public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, IllegalAccessException, InstantiationException {
		init();
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		MainWindow mainWindow = new MainWindow(mainTitle);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = mainWindow.getContentPane();
		contentPane.add(mainWindow.tabbedPane);
		// add content
		mainWindow.addTab("CRC Premanifest", new PremanifestTab());
		mainWindow.addTab("License", new LicenseTab());


		mainWindow.pack();
		mainWindow.setVisible(true);
	}
}
