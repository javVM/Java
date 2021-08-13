package tp.pr1.elements;
import tp.pr1.*;

public class CommonZombie extends Zombie{

	private int healthPoints = 5;
	private static int SPEED = 1;
	private int counter = 2;



	public CommonZombie(int x, int y, Game game) {
		super(x, y, game);
	}

	public CommonZombie() {

	}

	public int getX() {
		return x;
	}

	public void setPos_x(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setPos_y(int y) {
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

	public static int getSPEED() {
		return SPEED;
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
			if(getHealthPoints() - CherryBomb.getDAMAGE() > 0)
			{
				setHealthPoints(getHealthPoints() - CherryBomb.getDAMAGE());
			}
			else
			{
				setHealthPoints(0);
			}
		}

	}


	public void update()
	{
		if(counter > 1)
		{
			counter = counter - 1;
		}
		else
		{
			if(game.isEmpty(x, y-1))
			{

				setPos_y(y-1);
			}	

			counter = 2;
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
		return "Z["+ healthPoints + "]";
	}

	public String getDebugInfo()
	{
		return "Z["+ healthPoints + ":5,x:" + x + ",y:" + y + ",t:" + counter + "]";
	}

	public String externalise()
	{
		StringBuilder externalise = new StringBuilder();
		externalise = externalise.append("Z:").append(healthPoints).append(":").append(x).append(":").append(y).append(":").append(counter);
		return externalise.toString();
	}


}