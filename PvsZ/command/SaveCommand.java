package tp.pr1.command;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import tp.pr1.Game;
import tp.pr1.exceptions.CommandExecuteException;
import tp.pr1.exceptions.CommandParseException;
import tp.pr1.util.MyStringUtils;


public class SaveCommand extends Command {

	private String filename;



	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public SaveCommand(String commandText, String commandTextMsg, String helpTextMsg){
		super(commandText, commandTextMsg, helpTextMsg);
	}

	public SaveCommand(String commandName) {
		super(commandName);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {

		FileWriter fw;
		
		try
		{
			fw = new FileWriter(filename + ".dat");
		}
		catch (IOException ex)
		{
			throw new CommandExecuteException("Couldn't write in file");
		}
		
		if(MyStringUtils.isValidFilename(filename))
		{
			try(BufferedWriter bw = new BufferedWriter(fw)) {

				StringBuilder content = new StringBuilder();


				content = content.append("Plants Vs Zombies v3.0").append("space").append(" ").append("space").append("cycle: ").append(game.getNumCycles()).append("space").append("sunCoins: ").append(game.suncoins()).append("space").append("level: ").append(game.getLevel()).append("space").append("remZombies: ").append(game.ZombiesLeftToAppear()).append("space").append("plantList: ").append(game.Plantexternalise()).append("space").append("ZombieList: ").append(game.Zombieexternalise());
				String[] lines = content.toString().split("space");
				for(int i = 0; i < lines.length; i++){
					bw.write(lines[i]);
					bw.newLine();
				}
				System.out.println("Game successfully saved in file " + filename + ".dat. Use the load command to reload it");

			} 
			catch (IOException e) 
			{

				throw new CommandExecuteException("File could not open");
			}
		}
		else
		{
			throw new CommandExecuteException("File is not valid");
		}


		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {

		SaveCommand command = null;

		if(commandWords.length > 0)
		{

			String commandName = commandWords[0];

			if(commandName.equals("s") || commandName.equals("save"))
			{
				if(commandWords.length!= 2)
				{
					throw new CommandParseException("Incorrect number of arguments for save command: [S]ave FileName");
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
