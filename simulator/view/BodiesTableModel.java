package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;


public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver{

	private List<Body> _bodies;
	private String columns[] = {"Id", "Mass", "Position", "Velocity", "Acceleration"};
	
	public BodiesTableModel(Controller ctrl) {
		_bodies = new ArrayList<>();
		ctrl.addObserver(this);
		
	}
	
	public int getRowCount() {
		if(!_bodies.isEmpty())
		{
			return _bodies.size();
		}
		else
		{
			return 0;
		}
	
	}
	
	public int getColumnCount() {
		int column_count = columns.length;
		return column_count;
	}

	public String getColumnName(int column) {
		return columns[column];
	}	
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Object value = null;
		Body body = _bodies.get(rowIndex);
		
		switch ( columnIndex ) {
		case 0: 
			value = body.getId();
			break;
		case 1:
			value = body.getMass();
			break;
		case 2:
			value = body.getPosition();
			break;
		case 3:
			value = body.getVelocity();
			break;
		case 4:
			value = body.getAcceleration();
			break;
		default: assert(false);
		}

		return value;
	}
	// SimulatorObserver methods
	// ...
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub

		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				
				_bodies = bodies;
				fireTableStructureChanged();
				
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
				fireTableStructureChanged();
				
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
				fireTableStructureChanged();
				
			}
		});


	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		
		
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				
				_bodies = bodies;
				fireTableStructureChanged();
				
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
