package ui;

// This class references code from this TellerApp
// TellerApp: https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Alarm Controller: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem


import model.MediaList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class MediaTrackerUI extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private MediaTrackerApp mta;
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;

    private MediaList mediaList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/medialist.json";

    /**
     * Constructor sets up main menu panel
     */
    public MediaTrackerUI() {
        // remove console UI stuff
        // mta = new MediaTrackerApp();

        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());
        controlPanel = new JInternalFrame("Control Panel", false, false, false, false);
        controlPanel.setLayout(new BorderLayout());

        setContentPane(desktop);
        setTitle("Kae-Rene's Personal Project: First World Problems");
        setSize(WIDTH, HEIGHT);

        addButtonPanel();

        controlPanel.pack();
        controlPanel.setVisible(true);
        desktop.add(controlPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Start the media tracker
    public static void main(String[] args) {
        new MediaTrackerUI();
    }


    /**
     * Helper to add control buttons.
     */
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3,2));
        buttonPanel.add(new JButton(new AddMediaAction()));
        buttonPanel.add(new JButton(new UpdateMediaAction()));
        buttonPanel.add(new JButton(new ViewAllMediaAction()));
        buttonPanel.add(new JButton(new SaveMediaAction()));
        buttonPanel.add(new JButton(new LoadMediaAction()));

        controlPanel.add(buttonPanel, BorderLayout.WEST);
    }

    /**
     * Represents action to be taken when user clicks desktop
     * to switch focus. (Needed for key handling.)
     */
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            MediaTrackerUI.this.requestFocusInWindow();
        }
    }

    /**
     * Represents action to be taken when user wants to add a media
     * to the system.
     */
    private class AddMediaAction extends AbstractAction {

        AddMediaAction() {
            super("Add Media");
        }

        @Override //STUB
        public void actionPerformed(ActionEvent evt) {
            // generate a new window -- form like
            // figure out how to save all that information into mediaList
            // if it fails, throw and error message

//            try {
//                // do the process here
//            } catch (ActionFailedException e) { //
//                // exception thrown proecess here
//            }
        }

    }

    /**
     * Represents action to be taken when user wants to update a media
     * to the system.
     */

    private class UpdateMediaAction extends AbstractAction {

        // if you have time, maybe merge this into the viewing table rather than a seperate thing
        UpdateMediaAction() {
            super("Update Media");
        }

        @Override // STUB
        public void actionPerformed(ActionEvent evt) {
            // any set up things go here
        }
    }

    /**
     * Represents action to be taken when user wants to view all media
     * to the system.
     */

    private class ViewAllMediaAction extends AbstractAction {

        ViewAllMediaAction() {
            super("View All Media");
        }

        @Override //STUB
        public void actionPerformed(ActionEvent evt) {
            // https://docs.oracle.com/javase/tutorial/uiswing/components/table.html

//            try {
//                // do the process here
//            } catch (ActionFailedException e) {
//                // exception thrown proecess here
//            }
        }
    }

    /**
     * Represents action to be taken when user wants to save current media list
     * to the system.
     */

    private class SaveMediaAction extends AbstractAction {

        SaveMediaAction() {
            super("Save Current");
        }

        @Override //STUB
        public void actionPerformed(ActionEvent evt) {
            try {
                jsonWriter.open();
                jsonWriter.write(mediaList);
                jsonWriter.close();
                System.out.println("Saved data to " + JSON_STORE);
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }

    /**
     * Represents action to be taken when user wants to load an existing media list
     * to the system.
     */

    private class LoadMediaAction extends AbstractAction {

        LoadMediaAction() {
            super("Load Existing");
        }

        @Override //STUB
        public void actionPerformed(ActionEvent evt) {
            try {
                mediaList = jsonReader.read();
                System.out.println("Loaded data from " + JSON_STORE);
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }
}