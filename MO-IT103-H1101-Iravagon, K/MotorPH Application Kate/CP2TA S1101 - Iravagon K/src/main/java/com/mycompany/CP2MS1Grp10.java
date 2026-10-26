package com.mycompany;

import javax.swing.SwingUtilities;

public class CP2MS1Grp10 {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            DataManagement.loadData(); //loads csv data

            GUIMainFrame guiMainFrame = new GUIMainFrame(); //Create the main application window
            guiMainFrame.setVisible(true);  //Set the window visible to user

        });
    }
}