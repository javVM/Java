//Javier Verde Marin
package tp.pr1;
import java.util.Random;
import java.util.Scanner;

import tp.pr1.exceptions.MainArgumentsException;

public class PlantsVsZombies {


	@SuppressWarnings("resource")
	public static void main(String[] args){

		int seed = 0;
		Level level = Level.EASY;
		Scanner scanner = new Scanner(System.in);

		try
		{
			if(args.length == 0)
			{
				throw new MainArgumentsException("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]");	
			}
			else
			{
				try
				{

					if(args[0].equals("EASY")){
						level =  Level.EASY;
					}
					else if(args[0].equals("HARD"))
					{
						level = Level.HARD;

					}
					else if(args[0].equals("INSANE"))
					{
						level = Level.INSANE;
					}
					else
					{
						throw new MainArgumentsException("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]: level must be one of: EASY, HARD, INSANE");
					}

					if(args.length == 1 )
					{
						Random rand = new Random();
						seed= rand.nextInt(15);
					}
					else if(args.length == 2)
					{
						try
						{
							seed = Integer.parseInt(args[1]);
						}
						catch(NumberFormatException exception)
						{
							throw new MainArgumentsException("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]: the seed must be a number");
						}
					}
					else
					{
						throw new MainArgumentsException("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]");
					}


					Game game = new Game(level, new Random(seed), seed);
					System.out.println("Welcome to plantsVsZombies v3.0" + "\n");
					Controller control = new Controller(game,scanner);
					
					control.run();
					
				}
				catch(MainArgumentsException except)
				{
					System.err.println(except.getMessage());
				}
			}
		}
		catch(MainArgumentsException except)
		{
			System.err.println(except.getMessage());
		}

	}



}

