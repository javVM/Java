package tp.pr1.elements;

import tp.pr1.Game;

public abstract class Zombie extends GameObject {

	private static int DAMAGE = 1;	
	
	public Zombie(int x, int y, Game game) {
		super(x, y, game);
	}
	
	public Zombie()
	{
		
	}

	public abstract void update();

	public abstract String getInfo();
	
	public static int getDAMAGE() {
		return DAMAGE;
	}
	
	public abstract String getDebugInfo();

	public abstract int getHealthPoints();

	public abstract void PeashooterDamage();
	
	public abstract void CherryBombDamage();

}
