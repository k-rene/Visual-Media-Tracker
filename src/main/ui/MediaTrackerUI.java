package ui;

// This class references code from this TellerApp & AlarmSystem
// TellerApp: https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Alarm Controller: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem


import jdk.nashorn.internal.scripts.JO;
import model.Media;
import model.MediaList;
import model.Movie;
import model.Show;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class MediaTrackerUI { //extends JFrame
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
        startUp();

        frame = new JFrame();

        addButtons();

        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("First World Problems");
        frame.pack();
        frame.setVisible(true);
    }

    //  MODIFIES: this
    //  EFFECTS: initiates Tracker application
    private void startUp() {
        mediaList = new MediaList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
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
        protected JTextField nameField;
        protected JTextField platformField;
        protected JComboBox<String> statusField;
        protected JTextField bookmarkField;

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
            JFrame mediaForm = new JFrame();

            mediaForm.setSize(500, 300);

            makeMovieForm(mediaForm);

            if (type.equals("Show")) {
                makeShowForm(mediaForm);
            }

            mediaForm.setVisible(true);
        }

        private void makeMovieForm(JFrame mediaForm) {
            JPanel moviePanel = new JPanel();
            moviePanel.setLayout(new GridLayout(3, 2));
            moviePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

            JLabel nameLabel = new JLabel("Name:");
            nameField = new JTextField();
            nameLabel.setLabelFor(nameField);
            JLabel platformLabel = new JLabel("Platform:");
            platformField = new JTextField();
            platformLabel.setLabelFor(platformField);
            JLabel statusLabel = new JLabel("Watch Status:");
            statusField = new JComboBox<String>();
            statusField.addItem("To Watch");
            statusField.addItem("Watching");
            statusField.addItem("Watched");
            statusLabel.setLabelFor(statusField);

            moviePanel.add(nameLabel);
            moviePanel.add(nameField);
            moviePanel.add(platformLabel);
            moviePanel.add(platformField);
            moviePanel.add(statusLabel);
            moviePanel.add(statusField);
            // HELP, how do i trigger the submit action after stuff has been filled in?
            mediaForm.add(new JButton(new SubmitMovieAction(nameField, platformField, statusField)),BorderLayout.SOUTH);
            mediaForm.add(moviePanel, BorderLayout.CENTER);
        }

        private void makeShowForm(JFrame mediaForm) {
            JPanel showPanel = new JPanel();
            showPanel.setLayout(new GridLayout(1, 2));
            showPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));

            JLabel bookmarkLabel = new JLabel("Bookmark:");
            bookmarkField = new JTextField();
            bookmarkLabel.setLabelFor(bookmarkField);
            showPanel.add(bookmarkLabel);
            showPanel.add(bookmarkField);

            mediaForm.add(showPanel, BorderLayout.SOUTH);
            mediaForm.add(new JButton(new SubmitShowAction(nameField, platformField, statusField, bookmarkField)));
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

    // This class references code from this oracle doc & codegrepper
    // oracle doc: https://docs.oracle.com/javase/tutorial/uiswing/examples/
    //              components/SimpleTableDemoProject/src/components/SimpleTableDemo.java
    // codegrepper: https://www.codegrepper.com/code-examples/java/fill+a+2d+array+java
    private class ViewAllMediaAction extends AbstractAction {
        ViewAllMediaAction() {
            super("View All Media");
        }

        // HELP how do i make this show up
        public void actionPerformed(ActionEvent evt) {
            int rows = mediaList.length();
            Object[][] data = new Object[rows][5];

            for (int row = 0; row < rows; row++) {
                Media m = mediaList.getList().get(row);
                data[row][0] = m.getType();
                data[row][1] = m.getName();
                data[row][2] = m.getPlatform();
                data[row][3] = m.getConvertedStatus(m.getStatus());

                if (m.getType() == "Show") {
                    Show s = (Show) m;
                    data[row][4] = s.getBookmark();
                } else {
                    data[row][4] = "n/a";
                }
            }

            String[] columnNames = {"Media Type",
                    "Media Name",
                    "Platform",
                    "Watch Status",
                    "Bookmark"};

            final JTable table = new JTable(data, columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(500, 70));
            table.setFillsViewportHeight(true);
            table.setSize(1000, 1000);
            table.setVisible(true);
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

    private class SubmitMovieAction extends AbstractAction {
        String name;
        String platform;
        String status;

        SubmitMovieAction(JTextField nameField, JTextField platformField, JComboBox<String> statusField) {
            super("Submit");
            name = nameField.getText();
            platform = platformField.getText();
            status = statusField.getSelectedItem().toString();
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            Movie m1 = new Movie(name, status, platform);
            mediaList.addMedia(m1);
        }
    }

    private class SubmitShowAction extends AbstractAction {
        String name;
        String platform;
        String status;
        Integer bookmark;

        SubmitShowAction(JTextField nameField, JTextField platformField,
                         JComboBox<String> statusField, JTextField bookmarkField) {
            super("Submit");
            name = nameField.getText();
            platform = platformField.getText();
            status = statusField.getSelectedItem().toString();
            bookmark = Integer.parseInt(bookmarkField.getText());
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            Show s1 = new Show(name, status, platform, bookmark);
            mediaList.addMedia(s1);
        }
    }
}