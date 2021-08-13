package tp.pr1;
import tp.pr1.command.*;
import tp.pr1.lists.*;
import tp.pr1.elements.*;
import tp.pr1.exceptions.CommandExecuteException;
import tp.pr1.exceptions.CommandParseException;
import tp.pr1.exceptions.FileContentsException;

import java.util.Scanner;
import java.io.IOException;
import java.util.Random;

public class Controller {

	private Game game;
	private Scanner in;
	boolean noPrint = false;
	String[] words = null;
	boolean changeDebug = false;
	boolean changeRelease = false;
	GamePrinter gamePrinter;

	public Controller(Game game, Scanner in)
	{
		this.game = game;
		this.in = in;
		this.gamePrinter = new ReleasePrinter(game);
	}

	public Game getGame()
	{
		return this.game;
	}




	public void run()
	{
		boolean exit = false;
	

	
		draw(game);

		while(!game.endGame() && !exit) {
				

			noPrint = false;
			System.out.print("Command > ");
			words = in.nextLine().toLowerCase().trim().split("\\s+");

			try
			{
				Command command = CommandParser.parseCommand(words);

				if(command != null)
				{

					if(command.execute(game) == true)
					{


						if(game.isPrintdebug())
						{
							gamePrinter = new DebugPrinter(game);
						}
						else if(game.isPrintrelease())
						{
							gamePrinter = new ReleasePrinter(game);
						}
						
						
						draw(game);
					}

				}
				else
				{
					System.err.println("Unknown command. Use ’help’ to see the available commands");
					setNoPrintGameState();
				}

				computerAction();



				if(!game.noZombiewin() || game.plantwin())
				{
					draw(game);
				}


			}
			catch(CommandParseException | CommandExecuteException ex)
			{
				System.err.format(ex.getMessage() + "%n %n");
			}
		}


	}



	public void setNoPrintGameState() {
		this.noPrint = true;

	}




	private void computerAction() {
		game.isZombieAdded();

	}


	private void draw(Game game) {
		if(!noPrint)
		{
			System.out.println(gamePrinter.printGame(game));
		}
	}

}