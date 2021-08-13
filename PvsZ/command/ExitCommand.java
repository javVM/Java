package tp.pr1.command;


import tp.pr1.Game;
import tp.pr1.exceptions.CommandParseException;

public class ExitCommand extends NoParamsCommand {
	
	
	public ExitCommand(String commandText, String commandTextMsg, String helpTextMsg)
	{
		super(commandText, commandTextMsg, helpTextMsg);
	}


	public ExitCommand(String commandName) {
		super(commandName);
	}
	
	@Override
	public boolean execute(Game game) {
		game.exit();
		return false;
	}
	
	

	
	
}
