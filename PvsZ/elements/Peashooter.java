package tp.pr1.elements;
import tp.pr1.*;
import tp.pr1.command.Command;
import tp.pr1.command.PrintModeCommand;
import tp.pr1.exceptions.CommandParseException;

public class Peashooter extends Plant{

	private int healthPoints = 3;
	private static int COST = 50;
	private static int FREQUENCY = 1;
	private static int DAMAGE = 1;
	private static int counter = 1;
	private String PlantMsg = "[P]eashooter: Cost: 50 suncoins Harm: 1";

	public Peashooter(int x, int y, Game game)
	{
		super(x, y, game);
	}


	
	public Peashooter() {
		super(COST, "Peashooter");
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

	public static int getDAMAGE() {
		return DAMAGE;
	}


	public void setCounter(int counter) {

		this.counter = counter;

	}

	public void update()
	{
		int j = 0;
		boolean firstzombie = false;
		while( j < 8  && !firstzombie)
		{
			if(game.getZInPosition(x, j))
			{
				game.zombieDamaged(x, j);
				firstzombie = true;
			}
			else
			{
				j++;
			}
		}
	}


	public void Damage()
	{
		if(healthPoints > 0)
		{
			setHealthPoints(getHealthPoints() - Zombie.getDAMAGE());
		}

	}

	public String getInfo() {
		return "P["+ healthPoints + "]";
	}

	public String getDebugInfo()
	{
		return "P["+ healthPoints + ":3,x:" + x + ",y:" + y + ",t:" + counter + "]";
	}

	public String externalise()
	{
		StringBuilder externalise = new StringBuilder();
		externalise = externalise.append("P:").append(healthPoints).append(":").append(x).append(":").append(y).append(":").append(counter);
		return externalise.toString();
	}

	public Plant parse(String plantname){

		Plant plant = null;

		if(plantname.equals("p") || plantname.equals("peashooter"))
		{
			plant = new Peashooter();
		}

		return plant;	

	}



}
