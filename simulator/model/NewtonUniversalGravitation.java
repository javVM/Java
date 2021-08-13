package simulator.model;

import java.util.List;
import simulator.misc.Vector;


public class NewtonUniversalGravitation implements GravityLaws {

	static final double G = 6.67E-11;


	public NewtonUniversalGravitation()
	{

	}



	@Override
	public void apply(List<Body> bodies) {
		// TODO Auto-generated method stub

		double fij;
		Vector Fij;

		for(Body bi: bodies)
		{
			if(bi.getMass() == 0.0)
			{
				bi.setAcceleration(new Vector(bi.getAcceleration().dim()));
				bi.setVelocity( new Vector(bi.getVelocity().dim())); 
			}
			else {


				for(Body bj: bodies)
				{
					if(bi != bj)
					{
						fij = G * ((bi.getMass() * bj.getMass())/(Math.pow(Math.abs(bi.getPosition().distanceTo(bj.getPosition())) , 2))); 
						Fij = bi.pos.minus(bj.pos).direction().scale(fij);
						bi.setAcceleration(Fij.scale(1/bi.getMass()));
					}
				}
			}
		}

	}



}
