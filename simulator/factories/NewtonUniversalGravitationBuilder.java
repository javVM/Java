package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<GravityLaws> {
	
	public NewtonUniversalGravitationBuilder()
	{
		this._typeTag = "nlug";
		this._desc = "Newton's Universal Gravitation (nlug)";
	}

	@Override
	protected GravityLaws createTheInstance(JSONObject jsonObject) {
		
		return new NewtonUniversalGravitation();
	}

}
