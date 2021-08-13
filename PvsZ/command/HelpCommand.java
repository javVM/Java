package tp.pr1.command;

import tp.pr1.Game;
import tp.pr1.exceptions.CommandParseException;

public class HelpCommand extends NoParamsCommand{

	public HelpCommand(String commandText, String commandTextMsg, String helpTextMsg)
	{
		super(commandText, commandTextMsg, helpTextMsg);
	}



	public HelpCommand(String commandName) {
		super(commandName);
	}



	@Override
	public boolean execute(Game game) {	
		game.help();
		return false;
	}


}
