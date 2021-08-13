package tp.pr1.factory;
import tp.pr1.elements.*; 

public class ZombieFactory {
	
	//private static Zombie[] availableZombies = {new CommonZombie(), new SportyZombie(), new BuckletheadZombie()};
	
	public static Zombie getZombie(int choice){

		Zombie zombie;
		
		if(choice == 0)
		{
			zombie= new CommonZombie();
		}
		else if(choice == 1)
		{
			zombie = new SportyZombie();
		}
		else
		{
			zombie = new BuckletheadZombie();
		}
	

		return zombie;
	}
	
	public static String listOfAvilableZombies() 
	{
		return "[Z]ombie comun: speed: 2 Harm: 1 Life: 5" + "\n" + "[Z]ombie deportista: speed: 1 Harm: 1 Life: 2" + "\n" + "[Z]ombie caracubo: speed: 4 Harm: 1 Life: 8";
	}

}
