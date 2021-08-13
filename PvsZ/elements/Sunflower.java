package tp.pr1.elements;
import tp.pr1.*;

public class Sunflower extends Plant{
	
	private int healthPoints = 1;
	private static int COST = 20;
	private static int FREQUENCY = 10;
	private static int DAMAGE = 0;
	private int counter = 2;
	private String PlantMsg = "[S]unflower: Cost: 20 suncoins Harm: 0";

	public Sunflower()
	{
		super(COST, "Sunflower");
	}
	
	



	public Sunflower(int counter)
	{
		super(COST, "Sunflower");
		this.counter = counter;
	}
	
	public Sunflower(int x, int y, Game game) {
		super(x, y, game);
		
	}
	
	public String getPlantMsg() {
		return PlantMsg;
	}
	
	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public static int getCOST() {
		return COST;
	}

	public static int getFREQUENCY() {
		return FREQUENCY;
	}


	public void setCounter(int counter) {
	
	this.counter = counter;
	
	}

	public static int getDAMAGE() {
		return DAMAGE;
	}
	public void Damage()
	{

		if(healthPoints > 0)
		{
			setHealthPoints(getHealthPoints() - Zombie.getDAMAGE());
		}
	}
	


	public void update()
	{

		if(counter > 1)
		{
			counter = counter-1;
		}
		else
		{
			game.SuncoinUpdate();
			
			counter = 2;
		}
		
		
	}

	@Override
	public String getInfo() {
		
		return "S[" + healthPoints + "]";
	}
	
	public String getDebugInfo()
	{
		return "S["+ healthPoints + ":1,x:" + x + ",y:" + y + ",t:" + counter + "]";
	}

	public String externalise()
	{
		StringBuilder externalise = new StringBuilder();
		externalise = externalise.append("S:").append(healthPoints).append(":").append(x).append(":").append(y).append(":").append(counter);
		return externalise.toString();
	}
	
public Plant parse(String plantname){
		
		Plant plant = null;

			if(plantname.equals("s") || plantname.equals("sunflower"))
			{
				plant = new Sunflower();
			}

		return plant;	

	}


}
