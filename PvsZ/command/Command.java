package tp.pr1.command;
import java.io.IOException;

import tp.pr1.*;
import tp.pr1.exceptions.CommandExecuteException;
import tp.pr1.exceptions.CommandParseException;
import tp.pr1.exceptions.FileContentsException;

public abstract class Command {

	protected String helpText;
	protected String commandText;
	protected final String commandName;

	public Command(String commandText, String commandInfo, String helpInfo){
		this.helpText = helpInfo;
		this.commandText = commandInfo;
		String[] commandInfoWords = commandText.split("\\s+");
		this.commandName = commandInfoWords[0];
	}
	
	public Command(String commandName) {
		this.commandName = commandName;
	}
	
	// Some commands may generate an error in the execute or parse methods.
	// In the absence of exceptions, they must the tell the controller not to print the board.
	
	public abstract boolean execute(Game game) throws CommandExecuteException, NumberFormatException;
	public abstract Command parse(String[] commandWords) throws CommandParseException;
	
	public String helpText()
	{
		return " " + commandText + ": " + helpText;
	}
	
}
