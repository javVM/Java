package tp.pr1.command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import tp.pr1.Game;
import tp.pr1.exceptions.CommandExecuteException;
import tp.pr1.exceptions.CommandParseException;
import tp.pr1.exceptions.FileContentsException;
import tp.pr1.util.MyStringUtils;

public class LoadCommand extends Command {


	private final String gameheader = "Plants Vs Zombies v3.0";
	private String filename;



	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename + ".dat";
	}

	public LoadCommand(String commandText, String commandTextMsg, String helpTextMsg){
		super(commandText, commandTextMsg, helpTextMsg);
	}

	public LoadCommand(String commandName) {
		super(commandName);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{

		if(MyStringUtils.isValidFilename(filename) && MyStringUtils.fileExists(filename))
		{

			String cadena;
			try(BufferedReader buffer = new BufferedReader(new FileReader(filename)))
			{
				cadena = buffer.readLine();
				if(cadena.equals(gameheader))
				{
					cadena = buffer.readLine();
					game.load(buffer);
					System.out.println("Game successfully loaded from file " + filename);
					System.out.println(cadena);
				}
				else
				{
					throw new CommandExecuteException("Different header");
				}
			}
			catch(FileContentsException ex)
			{
				throw new CommandExecuteException(ex.getMessage());

			}
			catch(IOException ex)
			{
				throw new CommandExecuteException("Could not find or read file");
			}



		}
		else
		{
			throw new CommandExecuteException("Invalid file");
		}
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		LoadCommand command = null;

		if(commandWords.length > 0)
		{

			String commandName = commandWords[0];

			if(commandName.equals("lo") || commandName.equals("load"))
			{
				if(commandWords.length!= 2)
				{
					throw new CommandParseException("Incorrect number of arguments for load command: [Lo]ad FileName");
				}
				else

				{
					command = this;
					command.setFilename(commandWords[1]);
				}
			}

		}

		return command;	
	}


}
