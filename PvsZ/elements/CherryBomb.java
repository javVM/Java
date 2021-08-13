package tp.pr1.elements;

import tp.pr1.Game;

public class CherryBomb extends Plant {
	
	private int healthPoints = 2;
	private static int COST = 50;
	private static int DAMAGE = 10;
	private int counter = 2;
	private String PlantMsg = "Peta[c]ereza: Cost: 50 suncoins Harm: 10";
	
	public CherryBomb(int x, int y, Game game) {
		super(x, y, game);
	}
	
	
	public CherryBomb() {
		super(COST, "Cherrybomb");
	}
	
	public String getPlantMsg() {
		return PlantMsg;
	}


	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public int getHealthPoints() {
		return healthPoints;
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
	
	public void update()
	{
		if(counter > 1)
		{
			setCounter(counter-1);
		}
		else if(counter == 1)
		{
			if(game.getZInPosition(x, y+1))
			{
				game.zombieDamaged(x, y +1);
			}
			
			if(game.getZInPosition(x, y-1))
			{
				game.zombieDamaged(x, y -1);
			}
			
			if(game.getZInPosition(x+1, y))
			{
				game.zombieBombDamaged(x+1, y);
			}
			
			if(game.getZInPosition(x-1, y))
			{
				game.zombieBombDamaged(x-1, y);
			}
			
			if(game.getZInPosition(x-1, y+1))
			{
				game.zombieBombDamaged(x-1, y+1);
			}
			
			if(game.getZInPosition(x-1, y-1))
			{
				game.zombieBombDamaged(x-1, y-1);
			}
			
			if(game.getZInPosition(x+1, y+1))
			{
				game.zombieBombDamaged(x+1, y+1);
			}
			
			if(game.getZInPosition(x+1, y-1))
			{
				game.zombieBombDamaged(x+1, y-1);
			}
			
			setHealthPoints(0);
		}
	}


	@Override
	public String getInfo() {
		
		return "C[" + healthPoints + "]";
	}
	
	public String getDebugInfo()
	{
		return "C["+ healthPoints + ":2,x:" + x + ",y:" + y + ",t:" + counter + "]";
	}
	

	public static int getDAMAGE() {
		return DAMAGE;
	}
	
	public String externalise()
	{
		StringBuilder externalise = new StringBuilder();
		externalise = externalise.append("C:").append(healthPoints).append(":").append(x).append(":").append(y).append(":").append(counter);
		return externalise.toString();
	}
	
public Plant parse(String plantname){
		
		Plant plant = null;

			if(plantname.equals("c") || plantname.equals("cherrybomb"))
			{
				plant = new CherryBomb();
			}

		return plant;	

	}

}
