package tp.pr1.command;
import tp.pr1.*;
import tp.pr1.exceptions.CommandParseException;

public class CommandParser {

	private static Command[] availableCommands = {
			new AddCommand("Add","[A]dd <plant> <x> <y>", "Adds a plant in position x, y."),
			new HelpCommand("help", "[H]elp",  "print this help message."),
			new ResetCommand("reset", "[R]eset", "resets game"),
			new ExitCommand("exit", "[E]xit", "terminate the program"),
			new ListCommand("list",  "[L]ist", "print the list of available plants."),
			new PrintModeCommand("printmode", "[P]rintMode", "change print mode [Release|Debug]."),
			new zombieListCommand("zombielist",  "zombielist", "print the list of zombies."),
			new NoneCommand("none",  "none", "skips cycle"),
			new SaveCommand("save", "[S]ave <filename>", "Save the state of the game to a file."),
			new LoadCommand("load", "[Lo]ad <filename>", "Load the state of the game from a file.")
			
	};

	public static Command parseCommand(String[] commandWords) throws CommandParseException
	{
		Command command = null;

		for(int i = 0; i < availableCommands.length; i++) 
		{
			command = availableCommands[i].parse(commandWords);
			if(command != null) {
				return command;
			}
		}
		
		return null;
	}


	public static String commandHelp()
	{
		String helpText = availableCommands[0].helpText();

		for(int i = 1 ; i < availableCommands.length; i++) 
		{
		   helpText = helpText + "\n" + availableCommands[i].helpText();
			
		}
		
		return helpText;
	}

}
