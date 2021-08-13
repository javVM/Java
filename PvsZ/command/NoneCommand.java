package tp.pr1.command;

import tp.pr1.Controller;
import tp.pr1.Game;
import tp.pr1.exceptions.CommandParseException;

public class NoneCommand extends NoParamsCommand {

	public NoneCommand(String commandText, String commandTextMsg, String helpTextMsg) {
		super(commandText, commandTextMsg, helpTextMsg);
	}

	public NoneCommand(String commandName) {
		super(commandName);
	}
	
	@Override
	public boolean execute(Game game) {
		
		game.none();
		game.update();
		game.AddCycle();
		return true;
	}

}
