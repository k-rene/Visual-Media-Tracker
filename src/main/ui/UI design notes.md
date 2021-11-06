## code snippets

desktop = new JDesktopPane();
desktop.addMouseListener(new DesktopFocusAction());
controlPanel = new JInternalFrame("Control Panel", false, false, false, false);
controlPanel.setLayout(new BorderLayout());

		setContentPane(desktop);
		setTitle("CPSC 210: Alarm System Simulator");
		setSize(WIDTH, HEIGHT);