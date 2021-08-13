package simulator.model;
import java.util.List;

public class PhysicsSimulator {
	
	private double realtime;
	private GravityLaws gravitylaws;
	private double T;
	private List<Body> bodylist;
	
	public PhysicsSimulator(double realtime, GravityLaws gravitylaws)
	{
		this.realtime = realtime;
		this.gravitylaws = gravitylaws;
		this.T = 0.0;
	}
	
	public void advance()
	{
		gravitylaws.apply(bodylist);
		for(Body body: bodylist)
		{
			body.move(realtime);
			this.T = this.T + this.realtime;
		}
	}
	
	public void addBody(Body b) throws IllegalArgumentException
	{
		for(Body body: bodylist)
		{
			if(b.getId().equals(body.getId()) )
			{
				throw new IllegalArgumentException("can't be added");
			}
		}
		
		bodylist.add(b);
		
	}
	
	public String toString()
	{
		StringBuilder simulatorbuild = new StringBuilder("{ \time\": " + this.T + ", \bodies\": [");
		
		for(Body body: bodylist)
		{
			simulatorbuild.append(body.toString()).append(", ");
		}
		simulatorbuild.append("] }");
		
		return simulatorbuild.toString();
		
	}

}
