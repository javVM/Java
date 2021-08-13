package tp.pr1.elements;

import tp.pr1.Game;

public class SportyZombie extends Zombie {

	private int healthPoints = 2;
	private static int DAMAGE = 1;
	private static int counter = 1;

	public SportyZombie(int x, int y, Game game) {
		super(x, y, game);
	}

	public SportyZombie() {
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public void setCounter(int counter) {
		
		this.counter = counter;
		
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public static int getDAMAGE() {
		return DAMAGE;
	}

	public void PeashooterDamage()
	{
		if(healthPoints > 0)
		{
			setHealthPoints(healthPoints - Peashooter.getDAMAGE());
		}
	}

	public void CherryBombDamage()
	{
		if(healthPoints > 0)
		{
			if(healthPoints - CherryBomb.getDAMAGE() > 0)
			{
				setHealthPoints(healthPoints - CherryBomb.getDAMAGE());
			}
			else
			{
				setHealthPoints(0);
			}
		}

	}

	public void update() {

		if(game.isEmpty(x, y-1))
		{

			setY(y-1);
		}	

		attack();
	}

	public void attack()
	{
		if(game.getPInPosition(x, y-1))
		{
			game.plantDamaged(x, y-1);
		}
	}

	public String getInfo() {
		return "X["+ healthPoints + "]";
	}

	public String getDebugInfo()
	{
		return "X["+ healthPoints + ":2,x:" + x + ",y:" + y + ",t:" + counter + "]";
	}
	
	public String externalise()
	{
		StringBuilder externalise = new StringBuilder();
		externalise = externalise.append("X:").append(healthPoints).append(":").append(x).append(":").append(y).append(":").append(counter);
		return externalise.toString();
	}

}
