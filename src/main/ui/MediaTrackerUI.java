package ui;

// This class references code from this TellerApp & AlarmSystem
// TellerApp: https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Alarm Controller: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem

import model.Media;
import model.MediaList;
import model.Movie;
import model.Show;
import ui.exceptions.EmptyMediaListException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class MediaTrackerUI {
    private JFrame frame;
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

        startUp();
        addHeader();
        addButtons();

        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("First World Problems");
        frame.pack();
        frame.setVisible(true);
    }

    // this method references code from the below oracle doc
    // source: https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html
    // MODIFIES: this
    // EFFECTS: adds header image to frame
    private void addHeader() {
        // image source: https://animationsedts.tumblr.com/post/619679416987025408/rebloglike
        frame.add(new JLabel(new ImageIcon("./data/images/header.png")), BorderLayout.NORTH);
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
        buttonPanel.setLayout(new GridLayout(1,4));

        buttonPanel.add(new JButton(new AddMediaAction()));
        buttonPanel.add(new JButton(new UpdateStatusAction()));
        buttonPanel.add(new JButton(new ViewAllMediaAction()));
        buttonPanel.add(new JButton(new SaveMediaAction()));
        buttonPanel.add(new JButton(new LoadMediaAction()));

        buttonPanel.setVisible(true);
        frame.add(buttonPanel, BorderLayout.SOUTH);
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
            Object[] options = {"movie", "show"};
            String type = (String) JOptionPane.showInputDialog(null, "choose one","Media Type",
                    JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);

            try {
                addMedia(type);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // REQUIRES: type be a valid media type -- 'movie' or 'show'
        // MODIFIES: this
        // EFFECTS: generates an input form for new media details to be inputted
        private void addMedia(String type) {
            JFrame mediaForm = new JFrame();

            mediaForm.setSize(500, 300);

            makeMediaForm(mediaForm);

            if (type.equals("show")) {
                makeShowForm(mediaForm);
            }

            JButton button = new JButton("Submit");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mediaForm.dispose();
                    if (type.equals("show")) {
                        Show s = new Show(nameField.getText(), statusField.getSelectedItem().toString(),
                                platformField.getText(), Integer.valueOf(bookmarkField.getText()));
                        mediaList.addMedia(s);
                    } else {
                        Movie m = new Movie(nameField.getText(), statusField.getSelectedItem().toString(),
                                platformField.getText());
                        mediaList.addMedia(m);
                    }

                    //new SubmitMovieAction(nameField, platformField, statusField);
                }
            });
            mediaForm.add(button, BorderLayout.SOUTH);
            mediaForm.setVisible(true);
        }

        // this method references code from this StackExchange post
        // source: https://stackoverflow.com/questions/284899/how-do-you-add-an-actionlistener-onto-a-jbutton-in-java
        // EFFECTS: helper method to generate media form -- excludes details from show
        private void makeMediaForm(JFrame mediaForm) {
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

            mediaForm.add(moviePanel, BorderLayout.NORTH);
        }

        // EFFECTS: helper method to generate parts of input form only relevant to show
        private void makeShowForm(JFrame mediaForm) {
            JPanel showPanel = new JPanel();
            showPanel.setLayout(new GridLayout(1, 2));
            showPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));

            JLabel bookmarkLabel = new JLabel("Bookmark:");
            bookmarkField = new JTextField();
            bookmarkLabel.setLabelFor(bookmarkField);
            showPanel.add(bookmarkLabel);
            showPanel.add(bookmarkField);

            mediaForm.add(showPanel, BorderLayout.CENTER);
        }

    }

    /**
     * Represents action to be taken when user wants to update viewing status
     * to the system.
     */

    private class UpdateStatusAction extends AbstractAction {

        UpdateStatusAction() {
            super("UpdateStatus");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                updateStatus();
            } catch (EmptyMediaListException e) {
                JOptionPane.showMessageDialog(null, "No Media To Update", "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        private void updateStatus() throws EmptyMediaListException {
            List<Media> medias = mediaList.getList();
            int length = mediaList.length();
            Object[] mediaNameOptions = new Object[length];
            Object[] watchStatusOptions = {"To Watch", "Watching", "Watched"};
            Integer mediaIndex;

            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    mediaNameOptions[i] = medias.get(i).getName();
                }
            } else {
                throw new EmptyMediaListException();
            }

            String mediaName = (String) JOptionPane.showInputDialog(null, "choose one",
                    "Media Picker", JOptionPane.QUESTION_MESSAGE,null, mediaNameOptions, mediaNameOptions[0]);

            mediaIndex = mediaList.getIndex(mediaName);

            String newStatus = (String) JOptionPane.showInputDialog(null, "choose one",
                    "Update Status", JOptionPane.QUESTION_MESSAGE,null,
                    watchStatusOptions, watchStatusOptions[0]);
            medias.get(mediaIndex).updateStatus(newStatus);
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

        public void actionPerformed(ActionEvent evt) {
            JFrame viewer = new JFrame();
            JTable table = generateMediaTable();
            JScrollPane scrollPane = new JScrollPane(table);

            table.setPreferredScrollableViewportSize(new Dimension(500, 70));
            table.setFillsViewportHeight(true);
            table.setSize(400, 400);
            table.setVisible(true);

            viewer.setSize(500, 500);
            viewer.setVisible(true);
            viewer.add(scrollPane);
        }

        // EFFECTS: helper method to generate the JTable
        private JTable generateMediaTable() {
            int rows = mediaList.length();

            String[] columnNames = {"Media Type", "Media Name","Platform","Watch Status", "Bookmark"};
            Object[][] data = new Object[rows][5];

            for (int row = 0; row < rows; row++) {
                Media m = mediaList.getList().get(row);
                data[row][0] = m.getType();
                data[row][1] = m.getName();
                data[row][2] = m.getPlatform();
                data[row][3] = m.getConvertedStatus(m.getStatus());

                if (m.getType().equals("show")) {
                    Show s = (Show) m;
                    data[row][4] = s.getBookmark();
                } else {
                    data[row][4] = "n/a";
                }
            }
            return new JTable(data, columnNames);
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