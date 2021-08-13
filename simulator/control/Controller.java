package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import simulator.factories.*;
import simulator.model.*;

public class Controller {

	private PhysicsSimulator _sim;
	private Factory<Body> _bodiesFactory; 
	private Factory<GravityLaws> gravitylaws;
	
	public Controller(PhysicsSimulator sim, Factory<Body> fact)
	{
		this._sim = sim;
		this._bodiesFactory = fact;

	}
	
	public Controller(PhysicsSimulator sim, Factory<Body> fact, Factory<GravityLaws> gravlaws )
	{
		this._sim = sim;
		this._bodiesFactory = fact;
		this.gravitylaws = gravlaws;

	}
	
	
	
	public void reset()
	{
		_sim.reset();
	}
	
	public void setDeltaTime(double dt)
	{
		_sim.setDeltaTime(dt);
	}
	
	public void addObserver(SimulatorObserver o)
	{
		_sim.addObserver(o);
	}
	
	public void loadBodies(InputStream in) {
		JSONObject jsonInupt = new JSONObject(new JSONTokener(in));
		JSONArray bodies = jsonInupt.getJSONArray("bodies");
		for (int i = 0; i < bodies.length(); i++)
			_sim.addBody(_bodiesFactory.createInstance(bodies.getJSONObject(i)));
	}

	public void run(int steps, OutputStream out)
	{
		int pasos = 0;
		PrintStream p = (out == null) ? null : new PrintStream(out);
		String estados = "{\"states\": [";
		while(pasos < steps)
		{
			estados = estados + _sim.toString() + ", ";
			_sim.advance();
			pasos++;
		}
		estados = estados + _sim.toString() + "] }";
		p.println(estados);
		out = p;
	}
	
	public void run(int n)
	{
		int pasos = 0;
		
		
		while(pasos < n)
		{
			_sim.advance();
			pasos++;
		}
		
	}
	
	public Factory<GravityLaws> getGravityLawsFactory()
	{
		return this.gravitylaws;
	}
	
	public void setGravityLaws(JSONObject info)
	{
		_sim.setGravityLaws(gravitylaws.createInstance(info));
	}
	
	
}
