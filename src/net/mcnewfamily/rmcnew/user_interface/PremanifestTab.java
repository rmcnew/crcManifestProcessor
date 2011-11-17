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

import net.mcnewfamily.rmcnew.controller.PreManifestController;
import net.mcnewfamily.rmcnew.shared.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PreManifestTab extends JComponent implements ActionListener {
	private final String inputPrompt = "Select Premanifest Excel file from CRC";
	private final String outputPrompt = "Select Output Premanifest filename (.xlsx)";
	private final String buttonText = "Generate Premanifest";
	private final String inputFilenameLabelBasis = "Premanifest Excel file from CRC:  ";
	private final String outputFilenameLabelBasis = "Output Premanifest filename:  ";
	private final String errorMessageTitle = "File Not Specified";
	private final String inputErrorMessage = "Please select the premanifest Excel input file";
	private final String outputErrorMessage = "Please select the desired name for the premanifest output";

	private File preManifestInputFile = null;
	private File preManifestOutputFile = null;

	private JButton inputButton;
	private JButton outputButton;
	private JLabel inputFilenameLabel;
	private JLabel outputFilenameLabel;
	private JButton generateButton;

	private JFileChooser inputFileChooser;
	private JFileChooser outputFileChooser;

	public PreManifestTab() {
		inputButton = new JButton(inputPrompt);
		inputButton.addActionListener(this);
		outputButton = new JButton(outputPrompt);
		outputButton.addActionListener(this);
		inputFilenameLabel = new JLabel(inputFilenameLabelBasis);
		outputFilenameLabel = new JLabel(outputFilenameLabelBasis);
		generateButton = new JButton(buttonText);
		generateButton.addActionListener(this);

		inputFileChooser = new JFileChooser();
		inputFileChooser.setDialogTitle(inputPrompt);
		inputFileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
		inputFileChooser.setFileFilter(Util.EXCEL_FILTER);
		inputFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		outputFileChooser = new JFileChooser();
		outputFileChooser.setDialogTitle(outputPrompt);
		outputFileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
		outputFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		// UI layout
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setMinimumSize(new Dimension(300,200));
		this.setPreferredSize(new Dimension(300, 200));
		this.add(inputButton);
		this.add(inputFilenameLabel);
		this.add(Box.createRigidArea(new Dimension(5, 20)));
		this.add(outputButton);
		this.add(outputFilenameLabel);
		this.add(Box.createRigidArea(new Dimension(5,35)));
		this.add(generateButton);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == inputButton) {
			int returnValue = inputFileChooser.showOpenDialog(PreManifestTab.this);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				preManifestInputFile = inputFileChooser.getSelectedFile();
				inputFilenameLabel.setText(inputFilenameLabelBasis + preManifestInputFile.getName());
			}
		} else if (actionEvent.getSource() == outputButton) {
			int returnValue = outputFileChooser.showSaveDialog(PreManifestTab.this);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				preManifestOutputFile = outputFileChooser.getSelectedFile();
                preManifestOutputFile = Util.attachXlsxExtensionIfMissing(preManifestOutputFile);
				outputFilenameLabel.setText(outputFilenameLabelBasis + preManifestOutputFile.getName());
			}
		} else if (actionEvent.getSource() == generateButton) {
			if (preManifestInputFile == null) {
				JOptionPane.showMessageDialog(this, inputErrorMessage, errorMessageTitle, JOptionPane.ERROR_MESSAGE);
			} else if (preManifestOutputFile == null) {
				JOptionPane.showMessageDialog(this, outputErrorMessage, errorMessageTitle, JOptionPane.ERROR_MESSAGE);
			} else {
                try {
				    PreManifestController.runWorkflow(preManifestInputFile, preManifestOutputFile);
				    JOptionPane.showMessageDialog(this, "Premanifest processing is complete", "Success!", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e+"\n"+ Util.convertStackTraceToString(e.getStackTrace()), e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                }
			}
		}
	}
}
