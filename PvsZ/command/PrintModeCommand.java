package tp.pr1.command;

import tp.pr1.Controller;
import tp.pr1.Game;
import tp.pr1.exceptions.CommandExecuteException;
import tp.pr1.exceptions.CommandParseException;

public class PrintModeCommand extends Command {

	private String printmode = null;

	public PrintModeCommand(String commandText, String commandTextMsg, String helpTextMsg) {
		super(commandText, commandTextMsg, helpTextMsg);
	}

	public PrintModeCommand(String commandName) {
		super(commandName);
	}


	private String getPrintmode() {
		return printmode;
	}

	private void setPrintmode(String printmode) {
		this.printmode = printmode;
	}

	@Override
	public Command parse(String[] commandWords)throws CommandParseException {

		PrintModeCommand command = null;

		if(commandWords.length > 0)
		{
			String commandName = commandWords[0];

			if(commandName.equals("p") || commandName.equals("printmode"))
			{
				if(commandWords.length != 2)
				{
					throw new CommandParseException("Incorrect number of arguments for printmode command: [P]rintmode Release|Debug");
				}
				else
				{

					command = this;
					command.setPrintmode(commandWords[1]);
				}
			}

		}

		return command;	

	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {

		boolean executes; 
		
		if(game.switchPrintMode(printmode) == true)
		{
			executes = true;
		}
		else
		{
			executes = false;
			throw new CommandExecuteException("Invalid printmode");
		}
		
		return executes;

	}



}
