package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NoGravity;

public class NoGravityBuilder extends Builder<GravityLaws> {
	
	public NoGravityBuilder()
	{
		this._typeTag = "ng";
		this._desc = "No gravity (ng)";
	}

	@Override
	protected GravityLaws createTheInstance(JSONObject jsonObject) {
		
		return new NoGravity();
	}

}
