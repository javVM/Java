package tp.pr1.elements;
import tp.pr1.*;

public abstract class GameObject {
	
	protected int x;
	protected int y;
	protected Game game;
	
	public GameObject()
	{
		
	}
	
	public GameObject(int x, int y, Game game)
	{
		this.x = x;
		this.y = y;
		this.game = game;	
	}
	
	public abstract void update();
	
	public abstract String getInfo();


	public void setGame(Game game) {
		this.game = game;	
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public abstract String getDebugInfo();
	
	public abstract int getHealthPoints();
	
	public abstract void setHealthPoints(int healthpoints);
	
	public abstract void setCounter(int counter);
	
	public abstract String externalise();

}
