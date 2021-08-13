package simulator.model;

import simulator.misc.Vector;

public class MassLossingBody extends Body {

	private double lossfactor;
	private double lossfrecuency;
	private double c;

	public MassLossingBody(String id, Vector v, Vector a, Vector p, double m, double lossfrecuency, double lossfactor) {
		super(id, v, a, p, m);
		this.lossfactor = lossfactor;
		this.lossfrecuency = lossfrecuency;	
		this.c = 0.0;
	}

	void move(double t) {
		super.move(t);
		
		this.c = this.c + t;

			
			if(c>= this.lossfrecuency)
			{
				setMass(super.getMass() * (1 - this.lossfactor));
				c = 0.0;
			}
			
			
	}
	
	public String toString() {
		
		return super.toString();
	}


}
