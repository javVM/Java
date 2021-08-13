package simulator.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class StatusBar extends JPanel implements SimulatorObserver {
	// ...
	private JLabel _currTime = new JLabel("Time: 0"); // for current time
	private JLabel _currLaws = new JLabel("Laws: none"); // for gravity laws
	private JLabel _numOfBodies = new JLabel("Bodies: 0"); // for number of bodies
	
	public StatusBar(Controller ctrl) {
		
		initGUI();
		ctrl.addObserver(this);
	}
	
	private void initGUI() {
		this.setLayout( new FlowLayout( FlowLayout.LEFT ));
		this.setBorder( BorderFactory.createBevelBorder( 1 ));
		// TODO complete the code to build the tool bar
		
		this._currTime.setPreferredSize(new Dimension(95, 20));
		this._currTime.setMaximumSize(new Dimension(95, 20));
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JToolBar toolbar = new JToolBar();
		toolbar.add(_currTime);
		toolbar.addSeparator();
		toolbar.add(Box.createRigidArea(new Dimension(40, 0)));
		toolbar.add(_numOfBodies);
		toolbar.add(Box.createRigidArea(new Dimension(40, 0)));
		toolbar.addSeparator();
		toolbar.add(_currLaws);
		toolbar.add(Box.createHorizontalGlue());
		this.add(toolbar);
		this.setVisible(true);
	}
	
	// other private/protected methods
	// ...
	
	// SimulatorObserver methods
	// ...
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				
				_numOfBodies.setText("Bodies: " + bodies.size());
				_currLaws.setText("Laws: " + gLawsDesc);
				
			}
		});


	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		
		
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				
				_currTime.setText("Time: " + time);
				_numOfBodies.setText("Bodies: " + bodies.size());
				_currLaws.setText("Laws: " + gLawsDesc);
				
			}
		});


	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		

		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				
				_numOfBodies.setText("Bodies: " + bodies.size());
			}
		});

	
		

	}
	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		
		
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				
				_currTime.setText("Time: " + time);
			}
		});

	}
	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		// TODO Auto-generated method stub
	
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				
				_currLaws.setText("Laws: " + gLawsDesc);	
			}
		});

		
		

	}

}