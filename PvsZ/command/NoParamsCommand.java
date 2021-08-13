package tp.pr1.command;

import tp.pr1.Controller;
import tp.pr1.Game;
import tp.pr1.exceptions.CommandParseException;

public abstract class NoParamsCommand extends Command{
	
	public NoParamsCommand(String commandText, String commandTextMsg, String helpTextMsg){
		super(commandText, commandTextMsg, helpTextMsg);
		}
	
	public NoParamsCommand(String commandName) {
		super(commandName);
	}

	public abstract boolean execute(Game game);
	
	
	public Command parse(String[] commandWords) throws CommandParseException
	{
		Command command;
		
		if(commandWords[0].equals(commandName) || (commandWords[0].equals(commandName.substring(0,1))))
		{
			command = this;
			
			if(commandWords.length != 1)
			{
				throw new CommandParseException(commandName + " has no arguments");
			}
			else
			{
				command = this;
			}
		}
		else
		{
			command = null;
		}
		
		return command;
	}
	
}
