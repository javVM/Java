package simulator.model;
import java.util.ArrayList;
import java.util.List;



public class PhysicsSimulator {

	private double realtime;
	private GravityLaws gravitylaws;
	private double T;
	private List<Body> bodylist = new ArrayList<>();
	private List<SimulatorObserver> observerlist = new ArrayList<>();

	public PhysicsSimulator(double realtime, GravityLaws gravitylaws)
	{
		if(gravitylaws != null)
		{
			this.gravitylaws = gravitylaws;
		}
		else
		{
			throw new IllegalArgumentException("empty law");
		}
		if(realtime >= 0)
		{
			this.realtime = realtime;
		}
		else
		{
			throw new IllegalArgumentException("invalid time");
		}

		this.T = 0.0;

	}

	public void advance()
	{
		gravitylaws.apply(bodylist);
		for(Body body: bodylist)
		{
			body.move(realtime);	
		}


		T += realtime;
		
		for(SimulatorObserver obs: observerlist)
		{
			obs.onAdvance(bodylist, T);
		}
	


	}

	public void addBody(Body b) throws IllegalArgumentException
	{
			if(!bodylist.contains(b))
			{
				bodylist.add(b);
			}
			else
			{
				throw new IllegalArgumentException("body already exists");
			}
		
			for(SimulatorObserver obs: observerlist)
			{
				obs.onBodyAdded(bodylist, b);
			}
		
	}

	public String toString()
	{
		StringBuilder simulatorbuild = new StringBuilder("{ \"time\": " + this.T + ", \"bodies\": [ ");

		for(Body body: bodylist)
		{
			if(body != bodylist.get(bodylist.size()-1))
			{
				simulatorbuild.append(body.toString()).append(", ");
			}
			else
			{
				simulatorbuild.append(body.toString());
			}
		}
		simulatorbuild.append(" ] }");

		return simulatorbuild.toString();

	}



	public void setT(double t) {
		T = t;
	}

	public  void reset()
	{
		bodylist.clear();
		setT(0);
		
		for(SimulatorObserver obs: observerlist)
		{
			obs.onReset(bodylist, T, realtime, gravitylaws.toString());
		}
	}

	public void setDeltaTime(double dt) throws IllegalArgumentException
	{
		if(dt >= 0)
		{
			this.realtime = dt;
		}
		else
		{
			throw new IllegalArgumentException("Invalid Delta time");
		}
		
		
		for(SimulatorObserver obs: observerlist)
		{
			obs.onDeltaTimeChanged(this.realtime);
		}
	
	}

	public void setGravityLaws(GravityLaws gravityLaws) throws IllegalArgumentException
	{
		if(gravityLaws != null)
		{
			this.gravitylaws = gravityLaws;
		}
		else
		{
			throw new IllegalArgumentException("Invalid gravity law");
		}
		
		for(SimulatorObserver obs: observerlist)
		{
			obs.onGravityLawChanged(this.gravitylaws.toString());
		}
	
	}

	public void addObserver(SimulatorObserver o) throws IllegalArgumentException
	{ 
		if(!observerlist.contains(o))
		{
			observerlist.add(o);
		}
		else
		{
			throw new IllegalArgumentException("Observer already in list");
		}
		
		o.onRegister(bodylist , T, realtime , gravitylaws.toString());
	}

}
