package tp.pr1.elements;

import tp.pr1.Game;

public abstract class Plant extends GameObject {

	private int cost;
	private String name;
	
	public Plant()
	{
		
	}
	
	public Plant(int x, int y, Game game) {
		super(x, y, game);
	}

	public Plant(int cost, String name) {
		this.cost = cost;
		this.name = name;
	}

	public abstract String getPlantMsg();
	


	public abstract void update();
	
	public abstract String getInfo();
	
	public abstract String getDebugInfo();
	
	public abstract void Damage();
	
	public abstract int getHealthPoints();
	

	public int getCost() {
	 return cost;
	}
	
	public String getName() {
		return name;
	}

	public abstract Plant parse(String plantName);
	
	
}
