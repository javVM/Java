package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.MassLossingBody;

public class MassLosingBodyBuilder extends Builder<Body> {

	public MassLosingBodyBuilder()
	{
		this._typeTag = "mlb";
		this._desc = "Mass Losing Body";
	}

	@Override
	protected Body createTheInstance(JSONObject data) {

		String id = data.getString("id");
		double m = data.getDouble("mass");
		double[] p = jsonArrayTodoubleArray(data.getJSONArray("pos"));
		double[] v = jsonArrayTodoubleArray(data.getJSONArray("vel"));
		double[] a = jsonArrayTodoubleArray(data.getJSONArray("acc"));
		double freq = data.getDouble("freq");
		double fac = data.getDouble("factor");	
		Vector velocity = new Vector(v);
		Vector position = new Vector(p);
		Vector acceleration = new Vector(a);

		return new MassLossingBody(id, velocity, acceleration , position, m , freq, fac);
	}

	protected JSONObject createData() {

		JSONObject data = new JSONObject();
		data.put("id", "the identifier");
		data.put("mass", "the mass");
		data.put("pos", "the position vector");
		data.put("vel", "the velocity vector");
		data.put("acc", "the acceleration vector");
		data.put("freq", "the frequency");
		data.put("factor", "the mass loss factor");

		return data;
	}

}
