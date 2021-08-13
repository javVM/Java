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

	public Controller()
	{

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
		String estados = "{ \"states\": [" + _sim.toString();
		while(pasos < steps)
		{
			_sim.advance();
			estados = estados + _sim.toString() + ", ";
			pasos++;
		}
		estados = estados + "] }";
		p.println(estados);
		out = p;
	}
}
