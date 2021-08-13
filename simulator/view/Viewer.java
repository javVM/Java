package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import simulator.model.*;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;
import simulator.misc.Vector;

public class Viewer extends JComponent implements SimulatorObserver{

	// ...
	private int _centerX;
	private int _centerY;

	boolean first_time_resize = true;
	private double _scale;
	private List<Body> _bodies;
	private boolean _showHelp;
	public Viewer(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
	}
	private void initGUI() {
		// TODO add border with title
		this.setPreferredSize(new Dimension(200,250));

		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black, 2),
				"Viewer",
				TitledBorder.LEFT, TitledBorder.TOP));



		_bodies = new ArrayList<>();
		_scale = 1.0;
		_showHelp = true;
		addKeyListener(new KeyListener() {
			// ...
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyChar()) {
				case '-':
					_scale = _scale * 1.1;
					break;
				case '+':
					_scale = Math.max(1000.0, _scale / 1.1);
					break;
				case '=':
					autoScale();

					break;
				case 'h':
					_showHelp = !_showHelp;
					break;
				default:
				}
				repaint();
			}



			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		addMouseListener(new MouseListener() {
			// ...
			@Override
			public void mouseEntered(MouseEvent e) {
				requestFocus();
			}


			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}


			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		this.setBackground(Color.WHITE);
		this.setForeground(Color.WHITE);
		this.setVisible(true);
	}



	// other private/protected methods
	// ...
	private void autoScale() {
		double max = 1.0;
		for (Body b : _bodies) {
			Vector p = b.getPosition();
			for (int i = 0; i < p.dim(); i++)
				max = Math.max(max,
						Math.abs(b.getPosition().coordinate(i)));
		}
		double size = Math.max(1.0, Math.min((double) getWidth(),
				(double) getHeight()));
		_scale = max > size ? 4.0 * max / size : 1.0;
	}
	// SimulatorObserver methods
	// ...


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		// use ’gr’ to draw not ’g’
		// calculate the center
		_centerX = getWidth() / 2;
		_centerY = getHeight() / 2;
		// TODO draw a cross at center
		gr.setColor(Color.RED);
		gr.drawLine(_centerX-1, _centerY, _centerX+1, _centerY);
		gr.drawLine(_centerX, _centerY-1, _centerX, _centerY+1);
		// TODO draw bodies

		if(first_time_resize == true)
		{
			autoScale();
			first_time_resize = false;
		}

		for(Body b: _bodies)
		{
			gr.setColor(Color.BLUE);
			gr.fillOval( _centerX + (int)(b.getPosition().coordinate(0)/_scale), _centerY - (int)(b.getPosition().coordinate(1)/_scale), 10, 10 ); //Tamaño 5,5 es muy pequeño, así se ve mejor

		}


		// TODO draw help if _showHelp is true

		if(_showHelp == true)
		{

			gr.setColor(Color.RED);
			gr.drawString("h: toggle help, +: zoom-in, -: zoom-out, =: fit", 10, 25);
			gr.drawString("Scaling ratio:" + _scale , 10, 40);
		}

	}
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {

		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				
				_bodies = bodies;
				autoScale();
				repaint();
				
			}
		});



	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				
				_bodies = bodies;
				autoScale();
				repaint();
				
			}
		});
	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				
				_bodies = bodies;
				autoScale();
				repaint();
				
			}
		});

	}
	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				
				repaint();
				
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

	}
}