package tp.pr1.command;

import tp.pr1.Controller;
import tp.pr1.Game;
import tp.pr1.exceptions.CommandParseException;
import tp.pr1.factory.ZombieFactory;

public class zombieListCommand extends NoParamsCommand {

	public zombieListCommand(String commandText, String commandTextMsg, String helpTextMsg)
	{
		super(commandText, commandTextMsg, helpTextMsg);
	}


	@Override
	public boolean execute(Game game) {

		game.zombielist();
		return false;
	}

}
