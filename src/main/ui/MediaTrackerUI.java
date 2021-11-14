package ui;

// This class references code from this TellerApp & AlarmSystem
// TellerApp: https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Alarm Controller: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem


import model.Media;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    //!! https://www.tutorialspoint.com/
    // what-are-the-differences-between-jtextfield-and-jtextarea-in-java
    private class AddMediaAction extends AbstractAction {

        AddMediaAction() {
            super("Add Media");
        }

        @Override //STUB
        public void actionPerformed(ActionEvent evt) {
            JComboBox<String> comboBox = new JComboBox<String>();
            comboBox.addItem("Movie");
            comboBox.addItem("Show");

//            String mediaType = JOptionPane.showInputDialog(null,
//                    comboBox,
//                    "Choose Media Type",
//                    JOptionPane.QUESTION_MESSAGE);

            JPanel mediaForm = new JPanel();

            // Media Name Input
            JLabel nameLabel = new JLabel("Name:");
            JTextField nameField = new JTextField();
            nameLabel.setLabelFor(nameField);
            mediaForm.add(nameLabel);
            mediaForm.add(nameField);

            // Streaming Platform Input
            JLabel platformLabel = new JLabel("Platform:");
            JTextField platformField = new JTextField();
            platformLabel.setLabelFor(platformField);
            mediaForm.add(platformLabel);
            mediaForm.add(platformField);

            //Watch Status Input
            JLabel statusLabel = new JLabel("Watch Status:");
            JComboBox<String> statusField = new JComboBox<String>();
            comboBox.addItem("To Watch");
            comboBox.addItem("Watching");
            comboBox.addItem("Watched");
            statusLabel.setLabelFor(statusField);
            mediaForm.add(statusLabel);
            mediaForm.add(statusField);

//            if (Objects.equals(mediaType, "Show")) {
//                // Bookmark Input for Shows
//                JLabel bookmarkLabel = new JLabel("Bookmark:");
//                JTextField bookmarkField = new JTextField();
//                bookmarkLabel.setLabelFor(bookmarkField);
//                mediaForm.add(bookmarkLabel);
//                mediaForm.add(bookmarkField);
//            }

            mediaForm.setVisible(true);
            //addMedia();
        }

        private void addMedia() {
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