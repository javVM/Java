package tp.pr1.command;
import tp.pr1.*;
import tp.pr1.exceptions.CommandParseException;


public class ResetCommand extends NoParamsCommand{

	public ResetCommand(String commandText, String commandTextMsg, String helpTextMsg)
	{
		super(commandText, commandTextMsg, helpTextMsg);
	}

	public ResetCommand(String commandName) {
		super(commandName);
	}

	
	@Override
	public boolean execute(Game game) {
		game.reset();
		return true;
		
	}


}
