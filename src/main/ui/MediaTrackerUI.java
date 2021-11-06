package ui;

// This class references code from this TellerApp
// TellerApp: https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Alarm Controller: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MediaTrackerUI extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private MediaTrackerApp mta;
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;

    /**
     * Constructor sets up ???
     */
    public MediaTrackerUI() {
        mta = new MediaTrackerApp();

        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());
        controlPanel = new JInternalFrame("Control Panel", false, false, false, false);
        controlPanel.setLayout(new BorderLayout());

        setContentPane(desktop);
        setTitle("Kae-Rene's Personal Project: First World Problems");
        setSize(WIDTH, HEIGHT);
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

}