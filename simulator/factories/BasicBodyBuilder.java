package simulator.factories;

import org.json.JSONObject;
import simulator.misc.*;

import simulator.model.Body;

public class BasicBodyBuilder extends Builder<Body> {


	public BasicBodyBuilder()
	{
		this._typeTag = "basic";
		this._desc = "Default Body";
	}
	@Override
	protected Body createTheInstance(JSONObject data) {

		String id = data.getString("id");
		double m = data.getDouble("mass");
		double[] p = jsonArrayTodoubleArray(data.getJSONArray("pos"));
		double[] v = jsonArrayTodoubleArray(data.getJSONArray("vel"));
		Vector velocity = new Vector(v);
		Vector position = new Vector(p);
		Vector acceleration = new Vector(2);

		return new Body(id, velocity, acceleration , position, m );
	}

	protected JSONObject createData() {

		JSONObject data = new JSONObject();
		data.put("id", "the identifier");
		data.put("mass", "the mass");
		data.put("pos", "the position vector");
		data.put("vel", "the velocity vector");
		


		return data;
	}


}
