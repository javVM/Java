package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<GravityLaws> {

	@Override
	protected GravityLaws createTheInstance(JSONObject jsonObject) {
		
		return new NewtonUniversalGravitation();
	}

}
