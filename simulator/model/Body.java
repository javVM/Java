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
		return this.id;
	}

	public Vector getVelocity() {
		return this.vel;
	}

	public Vector getAcceleration() {
		return this.acc;
	}

	public Vector getPosition() {
		return this.pos;
	}

	public double getMass() {
		return this.mass;
	}

	void setVelocity(Vector v) {
		this.vel = v;
	}

	void setAcceleration(Vector a) {
		this.acc = a;
	}

	void setPosition(Vector p) {
		this.pos = p;
	}

	void move(double t) {
		this.pos = this.pos.plus((this.vel.scale(t)).plus((this.acc.scale(t * t / 2))));
		this.vel = this.vel.plus(this.acc.scale(t));
	}

	public String toString() {
		String body = "{ \"id\": " + this.id + ", \"mass\": " + this.mass + ", \"pos\": "
				+ this.pos + ", \"vel\": " + this.vel + ", \"acc\": " + this.acc + " }";

		return body;
	}

}
