package simulator.model;

import java.util.List;

public class FallingToCenterGravity implements GravityLaws{
	
	static final double g= 9.81; 

	public FallingToCenterGravity()
	{
		
	}
	
	@Override
	public void apply(List<Body> bodies) {
		// TODO Auto-generated method stub
		for(Body b: bodies)
		{
			b.setAcceleration(b.getPosition().direction().scale(-g));
		}
		
	}
	
	public String toString()
	{
		return "Falling to center gravity";
	}

}
