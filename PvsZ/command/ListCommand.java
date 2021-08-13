package tp.pr1.command;
import tp.pr1.factory.*;

import tp.pr1.Controller;
import tp.pr1.Game;
import tp.pr1.exceptions.CommandParseException;

public class ListCommand extends NoParamsCommand{

	public ListCommand(String commandText, String commandTextMsg, String helpTextMsg)
	{
		super(commandText, commandTextMsg, helpTextMsg);
	}

	public ListCommand(String commandName) {
		super(commandName);
	}


	@Override
	public boolean execute(Game game) {	

		game.list();
		return false;
	}

}
