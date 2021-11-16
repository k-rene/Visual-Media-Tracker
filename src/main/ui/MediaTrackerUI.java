package ui;

// This class references code from this TellerApp & AlarmSystem
// TellerApp: https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Alarm Controller: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem


import jdk.nashorn.internal.scripts.JO;
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


public class MediaTrackerUI { //extends JFrame
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    // private MediaTrackerApp mta;
    private JFrame frame;
    //private JInternalFrame controlPanel;

    private MediaList mediaList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/medialist.json";

    /**
     * Constructor sets up main menu panel
     */
    public MediaTrackerUI() {
        frame = new JFrame();

        addButtons();

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("First World Problems");
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Helper to add control buttons.
     */
    private void addButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        buttonPanel.setLayout(new GridLayout(5,1));

        buttonPanel.add(new JButton(new AddMediaAction()));
        buttonPanel.add(new JButton(new UpdateMediaAction()));
        buttonPanel.add(new JButton(new ViewAllMediaAction()));
        buttonPanel.add(new JButton(new SaveMediaAction()));
        buttonPanel.add(new JButton(new LoadMediaAction()));

        buttonPanel.setVisible(true);
        frame.add(buttonPanel);
    }

    // Start the media tracker
    public static void main(String[] args) {
        new MediaTrackerUI();
    }

    /**
     * Represents action to be taken when user wants to add a media
     * to the system.
     */

    private class AddMediaAction extends AbstractAction {

        AddMediaAction() {
            super("Add Media");
        }

        // This method references code from these JOptionPane examples
        // source: https://mkyong.com/swing/java-swing-joptionpane-showinputdialog-example/
        public void actionPerformed(ActionEvent evt) {
            Object[] options = {"Movie", "Show"};
            String type = (String) JOptionPane.showInputDialog(null, "choose one","Media Type",
                    JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);

            addMedia(type);
        }

        private void addMedia(String type) {
            JOptionPane mediaForm = new JOptionPane();

            JLabel nameLabel = new JLabel("Name:");
            JTextField nameField = new JTextField();
            nameLabel.setLabelFor(nameField);
            JLabel platformLabel = new JLabel("Platform:");
            JTextField platformField = new JTextField();
            platformLabel.setLabelFor(platformField);
            JLabel statusLabel = new JLabel("Watch Status:");
            JComboBox<String> statusField = new JComboBox<String>();
            statusField.addItem("To Watch");
            statusField.addItem("Watching");
            statusField.addItem("Watched");
            statusLabel.setLabelFor(statusField);

            mediaForm.add(nameLabel);
            mediaForm.add(nameField);
            mediaForm.add(platformLabel);
            mediaForm.add(platformField);
            mediaForm.add(statusLabel);
            mediaForm.add(statusField);

            if (type.equals("Show")) {
                JLabel bookmarkLabel = new JLabel("Bookmark:");
                JTextField bookmarkField = new JTextField();
                bookmarkLabel.setLabelFor(bookmarkField);
                mediaForm.add(bookmarkLabel);
                mediaForm.add(bookmarkField);
            }

            mediaForm.setVisible(true);
            frame.add(mediaForm);
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

    // This class references code from this TellerApp & AlarmSystem
    // TellerApp: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/SimpleTableDemoProject/src/components/SimpleTableDemo.java
    private class ViewAllMediaAction extends AbstractAction {
        ViewAllMediaAction() {
            super("View All Media");
        }

        @Override //STUB
        public void actionPerformed(ActionEvent evt) {
            //super(new GridLayout(1,0));

            String[] columnNames = {"Media Type",
                    "Media Name",
                    "Platform",
                    "Watch Status",
                    "Bookmark"};

            Object[][] data = {
                   // how do i loop through the mediaList and make a 2D array?
            };

            final JTable table = new JTable(data, columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(500, 70));
            table.setFillsViewportHeight(true);
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

        @Override
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

        @Override
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