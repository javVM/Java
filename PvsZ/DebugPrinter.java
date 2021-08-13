package tp.pr1;
import tp.pr1.elements.*;
import java.util.Arrays;
import tp.pr1.util.MyStringUtils;

public class DebugPrinter extends BoardPrinter {

	private int size = 0;
	private int cellsize = 21;


	public void setSize(int size) {
		this.size = size;
	}

	public DebugPrinter(Game game) {
		super(game, 1, game.totalSize());

		encodeGame(game);
	}

	public void encodeGame(Game game) {
		
	

		for(int j = 0; j < game.totalSize(); j++)
		{
			debugboard[j] = space;
			String[] board = game.printDebug().split("space");
			debugboard[j] = board[j];

		}
		
		
	}

	@Override
	public String printGame(Game game) {

		return  "\n" + "Current cycle: " + game.getNumCycles()+ "\n" +"Sun coins: " + game.suncoins() + "\n" + "Remaining zombies: " + game.ZombiesLeftToAppear() + "\n" +  "Random seed used:" + game.getSeed() + "\n" + "Level: " + game.getLevel() +  "\n" + ToStringBoard(cellsize);
	}


}


