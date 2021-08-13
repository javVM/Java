package tp.pr1;
import tp.pr1.factory.*;
import tp.pr1.elements.*;
import java.util.Random;

public class ZombieManager {
	

	private int ZombiesLeftToAppear;
	
	public ZombieManager(Game game)
	{
		ZombiesLeftToAppear  =	game.getLevel().getNumzombies(game.getLevel());
	}

	public int getZombiesLeftToAppear() {
		return ZombiesLeftToAppear;
	}

	public void setZombiesLeftToAppear(int zombiesLeftToAppear) {
		ZombiesLeftToAppear = zombiesLeftToAppear;
	}
	
	public boolean isZombieAdded(Game game)
	{
		boolean isAdded = false;
		boolean canBeAdded = false;
		boolean isValidPosition = false;
		int posX = 0;
		int posY = 7;
		
		if (game.getRand().nextDouble() < game.getLevel().getFrequency(game.getLevel())) {
			isAdded = true;
		}
		
		if (isAdded) {
			for(int i=0; i < 4; i++)
			{
				if(!game.getZInPosition(i, posY))
				{
					canBeAdded = true;
				}
			}
		
		
			if(canBeAdded && ZombiesLeftToAppear > 0) {
				while(!isValidPosition)
				{
					posX = game.getRand().nextInt(4);
	
					if(!game.getZInPosition(posX, posY))
					{
						int zombierand = game.getRand().nextInt(3);
						Zombie zombie = ZombieFactory.getZombie(zombierand);
						zombie.setGame(game);
						game.addZombie(zombie, posX, posY);
						isValidPosition = true;
						ZombiesLeftToAppear--;
					}
				}
				
			}
			isAdded = canBeAdded;
		}
		
		return isAdded;
	}
}
