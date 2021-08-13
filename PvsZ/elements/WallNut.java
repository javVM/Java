package tp.pr1.elements;

import tp.pr1.Game;

public class WallNut extends Plant {

	private int healthPoints = 10;
	private static int COST = 50;
	private static int DAMAGE = 0;
	private static int counter = 0;
	private String PlantMsg = "[N]uez: Cost: 50 suncoins Harm: 0";

	public WallNut(int x, int y, Game game) {
		super(x, y, game);
	}

	public WallNut() {
		super(COST, "Wall-nut");
	}

	public int getHealthPoints() {
		return healthPoints;
	}
	
	public String getPlantMsg() {
		return PlantMsg;
	}

	public void setCounter(int counter) {

		this.counter = counter;

	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public void Damage()
	{

		if(healthPoints > 0)
		{
			setHealthPoints(getHealthPoints() - Zombie.getDAMAGE());
		}
	}

	public String getInfo() {
		return "N[" + healthPoints + "]";
	}

	public String getDebugInfo()
	{
		return "N[" + healthPoints + ":10,x:" + x + ",y:" + y + ",t:0" +"]";
	}


	public void update()
	{

	}

	public String externalise()
	{
		StringBuilder externalise = new StringBuilder();
		externalise = externalise.append("N:").append(healthPoints).append(":").append(x).append(":").append(y).append(":").append(counter);
		return externalise.toString();
	}

	public Plant parse(String plantname){

		Plant plant = null;

		if(plantname.equals("n") || plantname.equals("wallnut"))
		{
			plant = new WallNut();
		}

		return plant;	

	}

}
