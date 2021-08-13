package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import simulator.control.Controller;
import simulator.view.BodiesTable;
import simulator.view.ControlPanel;
import simulator.view.StatusBar;
import simulator.view.Viewer;

public class MainWindow extends JFrame {
	// ...
	Controller _ctrl;
	public MainWindow(Controller ctrl) {
		super("Physics Simulator");
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		ControlPanel panel = new ControlPanel(_ctrl);
		StatusBar bar = new StatusBar(_ctrl);
		BodiesTable table = new BodiesTable(_ctrl);
		Viewer view = new Viewer(_ctrl);
		
		
		setContentPane(mainPanel);
		// TODO complete this method to build the GUI
		// ..
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.add(table);
		centerPanel.add(view);
		centerPanel.setBackground(Color.WHITE);
		mainPanel.add(panel, BorderLayout.PAGE_START);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(bar, BorderLayout.PAGE_END);
	
		this.setVisible(true);
		this.pack();
	}
	
	// other private/protected methods
	// ...
}
