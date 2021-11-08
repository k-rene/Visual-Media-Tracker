package ui;

// This class references code from this TellerApp
// TellerApp: https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Alarm Controller: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MediaTrackerUI extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private MediaTrackerApp mta;
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;

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
        
        setVisible(true);
    }

    /**
     * Helper to add control buttons.
     */
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,2));
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

    // Star the media tracker
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

        @Override //STUB
        public void actionPerformed(ActionEvent evt) {
            // any set up things go here

            try {
                // do the process here
            } catch (ActionFailedException e) { //
                // exception thrown proecess here
            }
        }

    }

    /**
     * Represents action to be taken when user wants to update a media
     * to the system.
     */

    private class UpdateMediaAction extends AbstractAction {

        UpdateMediaAction() {
            super("Update Media");
        }

        @Override // STUB
        public void actionPerformed(ActionEvent evt) {
            // any set up things go here

            try {
                // do the process here
            } catch (ActionFailedException e) { //MediaNotFoundException?
                // exception thrown proecess here
            }
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
            // any set up things go here

            try {
                // do the process here
            } catch (ActionFailedException e) {
                // exception thrown proecess here
            }
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
            // any set up things go here

            try {
                // do the process here
            } catch (ActionFailedException e) {
                // exception thrown proecess here
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
            // any set up things go here

            try {
                // do the process here
            } catch (ActionFailedException e) {
                // exception thrown proecess here
            }
        }
    }
}