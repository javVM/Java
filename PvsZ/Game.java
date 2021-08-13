package tp.pr1;
import tp.pr1.factory.*;
import tp.pr1.command.*;
import tp.pr1.elements.*;
import tp.pr1.exceptions.CommandExecuteException;
import tp.pr1.exceptions.FileContentsException;
import tp.pr1.lists.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class Game {

	private Level level;
	private GameObjectList zombielist = new GameObjectList();
	private GameObjectList plantlist = new GameObjectList();
	private SuncoinManager suncoinmanager;
	private ZombieManager zombiemanager;
	private int numCycles;
	private Random rand;
	private int seed;
	private boolean printdebug = false;
	private boolean printrelease = true;


	public Game(Level level, Random rand, int seed)
	{
		this.numCycles = 0;
		this.level = level;
		this.rand = rand;
		this.seed = seed;
		this.suncoinmanager = new SuncoinManager();
		this.zombiemanager = new ZombieManager(this);
	}



	public int getSeed() {
		return seed;
	}



	public Level getLevel() {
		return level;
	}




	public void setLevel(Level level) {
		this.level = level;
	}



	public Random getRand() {
		return rand;
	}


	public int getNumCycles() {
		return numCycles;
	}

	public int totalSize()
	{
		int size = plantlist.getSize() + zombielist.getSize();

		return size;
	}



	public void update()
	{

		zombielist.update();
		plantlist.update();
		zombielist.erase();
		plantlist.erase();


	}

	public String printDebug()
	{
		return plantlist.debugString() + "space" +  zombielist.debugString();
	}
	
	public String findCharacterDebug(int x, int y)
	{
		String character;

		if(!zombielist.gameObjectEmpty(x, y))
		{
			character = zombielist.debugString();
		}
		else if(!plantlist.gameObjectEmpty(x, y))
		{
			character = plantlist.debugString();
		}
		else
		{
			character = " ";
		}


		return character;
	}



	public void setNumCycles(int numCycles) {
		this.numCycles = numCycles;
	}

	public void addZombie(Zombie zombie)
	{
		zombielist.add(zombie);
	}


	public void addPlant(Plant plant)
	{
		plantlist.add(plant);
	}


	public boolean getPInPosition(int x, int y)
	{
		boolean isPlant = false;

		if(!plantlist.gameObjectEmpty(x, y))
		{
			isPlant = true;
		}

		return isPlant;
	}


	public boolean getZInPosition(int x, int y)
	{
		boolean isZombie = false;


		if(!zombielist.gameObjectEmpty(x, y))
		{
			isZombie = true;
		}

		return isZombie;

	}


	public String findCharacter(int x, int y)
	{
		String character;

		if(!zombielist.gameObjectEmpty(x, y))
		{
			character = zombielist.infoString(zombielist.position(x, y));
		}
		else if(!plantlist.gameObjectEmpty(x, y))
		{
			character = plantlist.infoString(plantlist.position(x, y));
		}
		else
		{
			character = " ";
		}


		return character;
	}


	public boolean isEmpty(int x, int y)
	{
		boolean empty = false;

		if(zombielist.gameObjectEmpty(x, y) && plantlist.gameObjectEmpty(x, y))
		{
			empty = true;
		}

		return empty;
	}

	public void plantDamaged(int x, int y)
	{
		plantlist.damaged(x, y);
	}

	public void zombieDamaged(int x, int y)
	{
		zombielist.damaged(x, y);
	}

	public void zombieBombDamaged(int x, int y)
	{
		zombielist.bombdamaged(x,y);
	}

	public boolean noZombiewin()
	{
		boolean nowin = true;
		for(int i = 0; i < 4; i++)
		{
			if(!zombielist.gameObjectEmpty(i, 0))
			{
				nowin = false;
			}
		}

		return nowin;
	}

	public boolean plantwin()
	{
		boolean plantwon = false;

		if(zombiemanager.getZombiesLeftToAppear() == 0 && zombielist.getSize() == 0)
		{
			plantwon = true;
		}

		return plantwon;
	}

	public boolean endGame()
	{
		boolean end = false;

		for(int i = 0; i < 4 ; i++)
		{

			if(!zombielist.gameObjectEmpty(i, 0))
			{
				end = true;
				System.out.println("Zombies win");
				System.exit(0);
			}
		}

		if(zombiemanager.getZombiesLeftToAppear() == 0 && zombielist.getSize() == 0)
		{
			end = true;
			System.out.println("Player wins");
			System.exit(0);
		}

		return end;

	}

	public void none()
	{

	}

	public void reset()
	{
		Level level = getLevel();
		zombiemanager.setZombiesLeftToAppear(level.getNumzombies(level));
		suncoinmanager.setSuncoins(50);
		setNumCycles(0);
		plantlist.setSize(0);
		zombielist.setSize(0);
		update();
	}

	public void exit()
	{
		System.out.println("****** Game over!: User exit ******");
		System.exit(0); 
	}

	public void list()
	{
		System.out.println(PlantFactory.listOfAvilablePlants());
	}

	public void zombielist()
	{
		System.out.println(ZombieFactory.listOfAvilableZombies());
	}

	public void help()
	{
		System.out.println(CommandParser.commandHelp());
	}

	public void addZombie(Zombie zombie, int x, int y)
	{
		zombie.setX(x);
		zombie.setY(y);
		addZombie(zombie);

	}
	
	public int suncoins()
	{
		return suncoinmanager.getSuncoins();
		
	}
	
	public void SuncoinUpdate()
	{
		suncoinmanager.updateSuncoins();
	}

	public int ZombiesLeftToAppear()
	{
		return zombiemanager.getZombiesLeftToAppear();
	}
	
	public void isZombieAdded()
	{
		zombiemanager.isZombieAdded(this);
	}


	public void addPlant(Plant plant, int x, int y) throws CommandExecuteException
	{
		if(suncoinmanager.getSuncoins() >= plant.getCost())
		{

			if(x < 4 && y < 7 && x >= 0 && y >= 0 && isEmpty(x, y))
			{
				plant.setX(x);
				plant.setY(y);
				addPlant(plant);
				suncoinmanager.buyPlant(plant.getCost());
			}
			else
			{
				throw new CommandExecuteException("Failed to add " + plant.getName() + ": (" + x + "," + y + ") is an invalid position");




			}
		}
		else
		{
			throw new CommandExecuteException("Failed to add " + plant.getName() + ": not enough suncoins available");

		}


	}



	public boolean isPrintdebug() {
		return printdebug;
	}



	public boolean isPrintrelease() {
		return printrelease;
	}



	public boolean switchPrintMode(String printmode)
	{
		boolean switches;


		if(printmode.equals("debug"))
		{
			printdebug = true;
			printrelease = false;
			switches = true;
		}
		else if(printmode.equals("release"))
		{
			printrelease = true;
			printdebug = false;
			switches = true;
		}
		else
		{
			switches = false;
		}

		return switches;

	}


	public String Plantexternalise()
	{
		return plantlist.externalise();
	}

	public String Zombieexternalise()
	{

		return zombielist.externalise();
	}

	public void load(BufferedReader buffer) throws IOException, FileContentsException
	{
		try
		{

			String[] line = loadLine(buffer, "cycle", false);
			setNumCycles(Integer.parseInt(line[0]));

			line = loadLine(buffer, "sunCoins", false);
			suncoinmanager.setSuncoins(Integer.parseInt(line[0]));

			line = loadLine(buffer, "level", false);
			setLevel(Level.parse(line[0]));

			line = loadLine(buffer, "remZombies", false);
			zombiemanager.setZombiesLeftToAppear(Integer.parseInt(line[0]));

			plantlist = new GameObjectList();
			line = loadLine(buffer, "plantList", true);
			for(int i = 0; i < line.length; i++)
			{
				line[i].split(":");
				String[]objects = line[i].split(":");
				Objectload(objects);
			}

			zombielist = new GameObjectList();
			line = loadLine(buffer, "ZombieList", true);
			for(int i = 0; i < line.length; i++)
			{
				line[i].split(":");
				String[]objects = line[i].split(":");
				Objectload(objects);	
			}
		}
		catch(NumberFormatException ex)
		{
			throw new FileContentsException("Invalid parameters");
		}
		



	}

	public void Objectload(String[] object) throws IOException, FileContentsException
	{
		if(isEmpty(Integer.parseInt(object[2]),  Integer.parseInt(object[3])))
		{
			if(object[0].equals("P"))
			{
				Plant peashooter = PlantFactory.getPlant("peashooter");
				PutPObject(peashooter,Integer.parseInt(object[1]), Integer.parseInt(object[2]), Integer.parseInt(object[3]), Integer.parseInt(object[4]));
				addPlant(peashooter);
			}
			else if(object[0].equals("S"))
			{
				Plant sunflower = PlantFactory.getPlant("sunflower");
				PutPObject(sunflower,Integer.parseInt(object[1]), Integer.parseInt(object[2]), Integer.parseInt(object[3]), Integer.parseInt(object[4]));	
				addPlant(sunflower);
			}
			else if(object[0].equals("N"))
			{
				Plant wallnut = PlantFactory.getPlant("wallnut");;
				PutPObject(wallnut,Integer.parseInt(object[1]), Integer.parseInt(object[2]), Integer.parseInt(object[3]), Integer.parseInt(object[4]));
				addPlant(wallnut);
			}
			else if(object[0].equals("C"))
			{
				Plant cherrybomb = PlantFactory.getPlant("cherrybomb");
				PutPObject(cherrybomb,Integer.parseInt(object[1]), Integer.parseInt(object[2]), Integer.parseInt(object[3]), Integer.parseInt(object[4]));
				addPlant(cherrybomb);
			}
			else if(object[0].equals("Z"))
			{
				Zombie zombie = ZombieFactory.getZombie(0);
				PutPObject(zombie,Integer.parseInt(object[1]), Integer.parseInt(object[2]), Integer.parseInt(object[3]), Integer.parseInt(object[4]));
				addZombie(zombie);
			}
			else if(object[0].equals("X"))
			{
				Zombie sportyzombie = ZombieFactory.getZombie(1);
				PutPObject(sportyzombie,Integer.parseInt(object[1]), Integer.parseInt(object[2]), Integer.parseInt(object[3]), Integer.parseInt(object[4]));
				addZombie(sportyzombie);
			}
			else
			{
				Zombie buckletheadzombie = ZombieFactory.getZombie(2);
				PutPObject(buckletheadzombie,Integer.parseInt(object[1]), Integer.parseInt(object[2]), Integer.parseInt(object[3]), Integer.parseInt(object[4]));
				addZombie(buckletheadzombie);
			}
		}
		else
		{
			throw new FileContentsException("Objects in same position");
		}

	}





	public void PutPObject(GameObject object, int healthpoints, int x, int y, int counter)
	{
		object.setGame(this);
		object.setHealthPoints(healthpoints);
		object.setX(x);
		object.setY(y);
		object.setCounter(counter);
	}

	public static final String wrongPrefixMsg = "unknown game attribute: ";
	public static final String lineTooLongMsg = "too many words on line commencing: ";
	public static final String lineTooShortMsg = "missing data on line commencing: ";

	public String[] loadLine(BufferedReader inStream, String prefix, boolean isList)
			throws IOException, FileContentsException{
		String[] words;
		String line = inStream.readLine().trim();
		// absence of the prefix is invalid
		if (!line.startsWith(prefix + ":") )
			throw new FileContentsException(wrongPrefixMsg + prefix);
		// cut the prefix and the following colon off the line then trim it to get attribute contents
		String contentString = line. substring(prefix . length()+1).trim();
		// the attribute contents are not empty
		if (! contentString. equals("")) {
			if (! isList ) {
				// split non−list attribute contents into words
				// using 1−or−more−white−spaces as separator
				words = contentString.split ("\\s+");
				// a non−list attribute with contents of more than one word is invalid
				if (words.length != 1)
					throw new FileContentsException(lineTooLongMsg + prefix);
			} else
				// split list attribute contents into words
				// using comma+0−or−more−white−spaces as separator
				words = contentString.split (",\\s*");
			// the attribute contents are empty
		} else {
			// a non−list attribute with empty contents is invalid
			if (! isList )
				throw new FileContentsException(lineTooShortMsg + prefix);
			// a list attribute with empty contents is valid; use a zero−length array to store its words
			words = new String[0];
		}
		return words;
	}
	
	
	public void AddCycle()
	{
		setNumCycles(this.numCycles+1);
	}



}

