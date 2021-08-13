package tp.pr1.command;
import tp.pr1.*;
import tp.pr1.elements.*;
import tp.pr1.exceptions.CommandExecuteException;
import tp.pr1.exceptions.CommandParseException;
import tp.pr1.factory.PlantFactory;

public class AddCommand extends Command{
	private int x;
	private int y;
	private String plantName;


	public AddCommand(String commandText, String commandTextMsg, String helpTextMsg){
		super(commandText, commandTextMsg, helpTextMsg);
	}

	public AddCommand(String commandName) {
		super(commandName);
	}

	public Command parse(String[] commandWords) throws CommandParseException{

		AddCommand command = null;

		if(commandWords.length > 0)
		{

			String commandName = commandWords[0];

			if(commandName.equals("a") || commandName.equals("add"))
			{
				if(commandWords.length!= 4)
				{
					throw new CommandParseException("Incorrect number of arguments for add command: [A]dd <plant> <x> <y>\"");
				}
				else

				{
					command = this;
					command.setPlantName(commandWords[1]);

					try
					{

						command.setX(Integer.parseInt(commandWords[2]));
						command.setY(Integer.parseInt(commandWords[3]));
					}	
					catch(NumberFormatException exception)
					{
						throw new CommandParseException("Invalid argument for add commmand, number expected: [A]dd <plant> <x> <y>");
					}
				}
			}




		}


		return command;	
	}

	private void setX(int x) {
		this.x = x;
	}

	private void setY(int y) {
		this.y = y;
	}

	private void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		boolean executes = false;
		Plant plant = PlantFactory.getPlant(plantName);
		if(plant != null)
		{
			plant.setGame(game);
			game.addPlant(plant, x , y);
			game.update();
			executes = true;
			game.AddCycle();
		}
		else
		{
			throw new CommandExecuteException("Unknown plant name: " + plantName);
		}
		
		return executes;
	}

}
