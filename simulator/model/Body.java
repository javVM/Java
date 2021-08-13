package simulator.model;

import simulator.misc.Vector;

public class Body {
	protected String id;
	protected Vector vel;
	protected Vector acc;
	protected Vector pos;
	protected double mass;

	public Body(String id, Vector v, Vector a, Vector p, double m){
		this.id = id;
		this.vel = v;
		this.acc = a;
		this.pos = p;
		this.mass = m;
	}

	public String getId() {
		return new String(id);
	}

	public Vector getVelocity() {
		return new Vector(vel);
	}

	public Vector getAcceleration() {
		return new Vector(acc);
	}

	public Vector getPosition() {
		return new Vector(pos);
	}

	public double getMass() {
		return this.mass;
	}

	void setVelocity(Vector v) {
		vel = new Vector(v);
	}

	void setAcceleration(Vector a) {
	   acc = new Vector(a);
	}

	void setPosition(Vector p) {
	  pos = new Vector(p);
	}
	
	public double setMass(double m)
	{
		return this.mass = m;
	}

	void move(double t) {
		this.pos = this.pos.plus((this.vel.scale(t)).plus((this.acc.scale((t * t) / 2))));
		this.vel = this.vel.plus(this.acc.scale(t));
	}

	public String toString() {
		String body = "{  \"id\": " +  "\"" + this.id +"\"" + ", \"mass\": " + this.mass + ", \"pos\": "
				+ this.pos + ", \"vel\": " + this.vel + ", \"acc\": " + this.acc + " }";

		return body;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Body other = (Body) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
