package simulator.factories;

import org.json.JSONObject;

import simulator.model.FallingToCenterGravity;
import simulator.model.GravityLaws;

public class FallingToCenterGravityBuilder extends Builder<GravityLaws> {
	
	public  FallingToCenterGravityBuilder()
	{
		this._typeTag = "ftcg";
		this._desc = "Falling to center Gravity (ftcg)";
	}

	@Override
	protected GravityLaws createTheInstance(JSONObject jsonObject) {
		
		return new FallingToCenterGravity();
	}

}
