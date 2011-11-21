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

import net.mcnewfamily.rmcnew.controller.FinalManifestController;
import net.mcnewfamily.rmcnew.model.exception.SheetNotFoundException;
import net.mcnewfamily.rmcnew.shared.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FinalManifestTab extends JComponent implements ActionListener {

    private static final String fromCrcPreManifestPrompt = "Select Pre Manifest Excel file from CRC";
    private static final String fromCrcFinalManifestPrompt = "Select Final Manifest Excel file from CRC";
    private static final String outputPrompt = "Select Output Final Manifest filename (.xlsx)";
    private static final String buttonText = "Generate Final Manifest";
    private static final String fromCrcPreManifestFilenameLabelBasis = "Pre Manifest Excel file from CRC:  ";
    private static final String fromCrcFinalManifestFilenameLabelBasis = "Final Manifest Excel file from CRC:  ";
    private static final String outputFilenameLabelBasis = "Output PreManifest filename:  ";
    private static final String errorMessageTitle = "File Not Specified";
    private static final String fromCrcPreManifestErrorMessage = "Please select the Pre Manifest Excel input file";
    private static final String fromCrcFinalManifestErrorMessage = "Please select the Final Manifest Excel input file";
    private static final String outputErrorMessage = "Please select the desired name for the Pre Manifest output";

    private File fromCrcPreManifestInputFile = null;
    private File fromCrcFinalManifestInputFile = null;
    private File finalManifestOutputFile = null;

    private JButton fromCrcPreManifestButton;
    private JButton fromCrcFinalManifestButton;
    private JButton outputButton;
    private JLabel fromCrcPreManifestFilenameLabel;
    private JLabel fromCrcFinalManifestFilenameLabel;
    private JLabel outputFilenameLabel;
    private JButton generateButton;

    private JFileChooser fromCrcPreManifestFileChooser;
    private JFileChooser fromCrcFinalManifestFileChooser;
    private JFileChooser outputFileChooser;

    public FinalManifestTab() {
        fromCrcPreManifestButton = new JButton(fromCrcPreManifestPrompt);
        fromCrcPreManifestButton.addActionListener(this);
        fromCrcFinalManifestButton = new JButton(fromCrcFinalManifestPrompt);
        fromCrcFinalManifestButton.addActionListener(this);
        outputButton = new JButton(outputPrompt);
        outputButton.addActionListener(this);
        fromCrcPreManifestFilenameLabel = new JLabel(fromCrcPreManifestFilenameLabelBasis);
        fromCrcFinalManifestFilenameLabel = new JLabel(fromCrcFinalManifestFilenameLabelBasis);
        outputFilenameLabel = new JLabel(outputFilenameLabelBasis);
        generateButton = new JButton(buttonText);
        generateButton.addActionListener(this);

        fromCrcPreManifestFileChooser = new JFileChooser();
        fromCrcPreManifestFileChooser.setDialogTitle(fromCrcPreManifestPrompt);
        fromCrcPreManifestFileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        fromCrcPreManifestFileChooser.setFileFilter(Util.EXCEL_FILTER);
        fromCrcPreManifestFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fromCrcFinalManifestFileChooser = new JFileChooser();
        fromCrcFinalManifestFileChooser.setDialogTitle(fromCrcFinalManifestPrompt);
        fromCrcFinalManifestFileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        fromCrcFinalManifestFileChooser.setFileFilter(Util.EXCEL_FILTER);
        fromCrcFinalManifestFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        outputFileChooser = new JFileChooser();
        outputFileChooser.setDialogTitle(outputPrompt);
        outputFileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        outputFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // UI layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setMinimumSize(new Dimension(300,260));
        this.setPreferredSize(new Dimension(300, 260));
        this.add(fromCrcPreManifestButton);
        this.add(fromCrcPreManifestFilenameLabel);
        this.add(Box.createRigidArea(new Dimension(5, 20)));
        this.add(fromCrcFinalManifestButton);
        this.add(fromCrcFinalManifestFilenameLabel);
        this.add(Box.createRigidArea(new Dimension(5, 20)));
        this.add(outputButton);
        this.add(outputFilenameLabel);
        this.add(Box.createRigidArea(new Dimension(5,35)));
        this.add(generateButton);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == fromCrcPreManifestButton) {
            int returnValue = fromCrcPreManifestFileChooser.showOpenDialog(FinalManifestTab.this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                fromCrcPreManifestInputFile = fromCrcPreManifestFileChooser.getSelectedFile();
                fromCrcPreManifestFilenameLabel.setText(fromCrcPreManifestFilenameLabelBasis + fromCrcPreManifestInputFile.getName());
            }
        } else if (actionEvent.getSource() == fromCrcFinalManifestButton) {
            int returnValue = fromCrcFinalManifestFileChooser.showOpenDialog(FinalManifestTab.this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                fromCrcFinalManifestInputFile = fromCrcFinalManifestFileChooser.getSelectedFile();
                fromCrcFinalManifestFilenameLabel.setText(fromCrcFinalManifestFilenameLabelBasis + fromCrcFinalManifestInputFile.getName());
            }
        } else if (actionEvent.getSource() == outputButton) {
            int returnValue = outputFileChooser.showSaveDialog(FinalManifestTab.this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                finalManifestOutputFile = outputFileChooser.getSelectedFile();
                finalManifestOutputFile = Util.attachXlsxExtensionIfMissing(finalManifestOutputFile);
                outputFilenameLabel.setText(outputFilenameLabelBasis + finalManifestOutputFile.getName());
            }
        } else if (actionEvent.getSource() == generateButton) {
            if (fromCrcPreManifestInputFile == null) {
                JOptionPane.showMessageDialog(this, fromCrcPreManifestErrorMessage, errorMessageTitle, JOptionPane.ERROR_MESSAGE);
            } else if (fromCrcFinalManifestInputFile == null) {
                JOptionPane.showMessageDialog(this, fromCrcFinalManifestErrorMessage, errorMessageTitle, JOptionPane.ERROR_MESSAGE);
            } else if (finalManifestOutputFile == null) {
                JOptionPane.showMessageDialog(this, outputErrorMessage, errorMessageTitle, JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    FinalManifestController.runWorkflow(fromCrcPreManifestInputFile, fromCrcFinalManifestInputFile, finalManifestOutputFile);
                    JOptionPane.showMessageDialog(this, "Final Manifest processing is complete", "Success!", JOptionPane.PLAIN_MESSAGE);
                } catch (SheetNotFoundException snfe) {
                    JOptionPane.showMessageDialog(this, snfe.getMessage(), snfe.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e+"\n"+ Util.convertStackTraceToString(e.getStackTrace()), e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
