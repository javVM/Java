package simulator.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.json.JSONObject;
import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver {

	private Controller _ctrl;
	private volatile java.lang.Thread _thread;

	JToolBar toolbar = new JToolBar();
	private JLabel steplabel = new JLabel("Steps: ");
	private JLabel delaylabel = new JLabel("Delay: ");
	private JLabel deltalabel = new JLabel("Delta-Time: ");
	private JSpinner steps = new JSpinner();
	private JSpinner delay = new JSpinner();
	private JTextField delta = new JTextField();
	private JButton filebutton = new JButton();
	private JButton gravitybutton = new JButton();
	private JButton runbutton = new JButton();
	private JButton stopbutton = new JButton();
	private JButton closebutton = new JButton();



	public ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		initGUI();
		_ctrl.addObserver(this);
	}
	private void initGUI() {
		// TODO build the tool bar by adding buttons, etc.
		this.delta.setPreferredSize(new Dimension(60,30));
		this.steps.setPreferredSize(new Dimension(70,30));
		this.delay.setPreferredSize(new Dimension(55,30));

		this.delta.setMaximumSize(new Dimension(75,30));
		this.delay.setMaximumSize(new Dimension(55,30));
		this.steps.setMaximumSize(new Dimension(75,30));
		SpinnerNumberModel model = new SpinnerNumberModel( 
				new Integer(10000), // valor inicial
				new Integer(0), // Límite inferior 
				new Integer(10000000), // Límite superior 
				new Integer(1000) // incremento-decremento 
				); 
		
		SpinnerNumberModel model2 = new SpinnerNumberModel( 
				new Integer(1), // valor inicial
				new Integer(0), // Límite inferior 
				new Integer(1000), // Límite superior 
				new Integer(1) // incremento-decremento 
				); 
		
		this.steps.setModel(model);
		this.delay.setModel(model2);
		

		this.delta.setVisible(true);
		
		
		
		this.filebutton.setIcon(new ImageIcon("resources\\icons\\open.png"));
		this.filebutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser filechooser = new JFileChooser();
				filechooser.setCurrentDirectory(new File("resources/examples"));
				
				FileNameExtensionFilter choosefile = new FileNameExtensionFilter("text files " , "txt");
				filechooser.setFileFilter(choosefile);
				int selection = filechooser.showOpenDialog(null);

				if(selection == JFileChooser.APPROVE_OPTION)
				{
					File file = filechooser.getSelectedFile();

					try {

						FileInputStream instream = new FileInputStream(file);
						_ctrl.reset();
						_ctrl.loadBodies(instream);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(filebutton, e);
					}
				}

			}
		});




		this.gravitybutton.setIcon(new ImageIcon("resources\\icons\\physics.png"));
		this.gravitybutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Object options[] = new Object[_ctrl.getGravityLawsFactory().getInfo().size()];
				int i = 0;
				for(JSONObject choice: _ctrl.getGravityLawsFactory().getInfo())
				{
				   options[i] = choice.get("desc");
				   i++;
				}
			
				String n = (String) JOptionPane.showInputDialog(gravitybutton, "Select gravity laws to be used:", "Gravity Laws Selector", JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

				
					if(n!= null)
					{
						for(JSONObject lawchange: _ctrl.getGravityLawsFactory().getInfo()) 
						{
							if(n.equals(lawchange.getString("desc")))
							{
								_ctrl.setGravityLaws(lawchange);
								break;
								
							}
						}
					}


			}

		});



		this.runbutton.setIcon(new ImageIcon("resources\\Icons\\run.png"));
		this.runbutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					
					runbutton.setEnabled(false);
					filebutton.setEnabled(false);
					gravitybutton.setEnabled(false);
					closebutton.setEnabled(false);
					_ctrl.setDeltaTime(Double.parseDouble(delta.getText()));
					_thread = new Thread(new Runnable() {		
						public void run()
						{
							
							run_sim(Integer.parseInt(steps.getValue().toString()), Long.parseLong(delay.getValue().toString()));
							runbutton.setEnabled(true);
							filebutton.setEnabled(true);
							gravitybutton.setEnabled(true);
							closebutton.setEnabled(true);
							_thread = null;
							
						}	
					});
					 
				   _thread.start();
					//worker.execute();
					
				}catch(IllegalArgumentException e)
				{
					JOptionPane.showMessageDialog(runbutton, e);
				}
			}
		});



		this.stopbutton.setIcon(new ImageIcon("resources\\icons\\stop.png"));
		this.stopbutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try
				{
					if(_thread != null)
					{
						_thread.interrupt();
					}
				}
				catch(IllegalArgumentException e)
				{
					JOptionPane.showMessageDialog(stopbutton, e);
				}
				

			}
		});




		this.closebutton.setIcon(new ImageIcon("resources\\icons\\exit.png"));
		this.closebutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub


				int n = JOptionPane.showOptionDialog(getRootPane(),
						"Are sure you want to exit?", "Exit",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						null, null);
				if (n == 0) {System.exit(0); }
			}
		});

		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		toolbar.add(filebutton);
		toolbar.addSeparator();
		toolbar.add(gravitybutton);
		toolbar.addSeparator();
		toolbar.add(runbutton);
		toolbar.add(stopbutton);
		toolbar.addSeparator();
		toolbar.add(delaylabel);
		toolbar.add(delay);
		toolbar.addSeparator();
		toolbar.add(steplabel);
		toolbar.add(steps);
		toolbar.addSeparator();
		toolbar.add(deltalabel);
		toolbar.add(delta);
		toolbar.addSeparator();
		toolbar.add(Box.createRigidArea(new Dimension(60, 0)));
		toolbar.add(Box.createHorizontalGlue());
		toolbar.add(closebutton);
		this.add(toolbar);
		this.setVisible(true);

	}
	// other private/protected methods
	// ...
	private void run_sim(int n, long delay) {
		while( n>0 && !Thread.interrupted()) {
			try {
				_ctrl.run(1);
				
			} catch (Exception e) {
				// TODO show the error in a dialog box
				JOptionPane.showMessageDialog(runbutton, e);
				
			}
			
			try {
				Thread.sleep(delay);
				n--;
			} catch (InterruptedException e) {
				
				
				return;
			}
		}
	}
	// SimulatorObserver methods
	// ...

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				
				delta.setText(Double.toString(dt));
			}
		});

	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				
				delta.setText(Double.toString(dt));
			}
		});


	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				
				delta.setText(Double.toString(dt));
			}
		});


	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		// TODO Auto-generated method stub

	}
	

}
