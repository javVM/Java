package tp.pr1.elements;

import tp.pr1.Game;

public class BuckletheadZombie extends Zombie {

	private int healthPoints = 8;
	private int counter = 4;
	
	public BuckletheadZombie(int x, int y, Game game) {
		super(x, y, game);
	}
	
	
	
	public BuckletheadZombie() {
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}
	
	
	
	public void setCounter(int counter) {
		this.counter = counter;
	}

	

	
	public void update() {
		
		
		if(counter > 1)
		{
			counter = counter - 1;
		}
		else
		{
			if(game.isEmpty(x, y-1))
			{

				setY(y-1);
			}
			
			counter = 4;
					
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
		return "W["+ healthPoints + "]";
	}
	
	public String getDebugInfo()
	{
		return "W["+ healthPoints + ":8,x:" + x + ",y:" + y + ",t:" + counter + "]";
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
	
	public String externalise()
	{
		StringBuilder externalise = new StringBuilder();
		externalise = externalise.append("W:").append(healthPoints).append(":").append(x).append(":").append(y).append(":").append(counter);
		return externalise.toString();
	}
}
