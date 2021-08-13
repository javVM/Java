package tp.pr1;

public class SuncoinManager {

	private int suncoins = 50;
	private Game game;
	
	public int getSuncoins() {
		return suncoins;
	}
	
	public void setSuncoins(int suncoins) {
		this.suncoins = suncoins;
	}

	public void updateSuncoins()
	{
		this.suncoins = this.suncoins + 10;
	}
	
	public void buyPlant(int cost)
	{
		this.suncoins = this.suncoins - cost;
	}
}


