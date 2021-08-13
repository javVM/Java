package tp.pr1;
import tp.pr1.elements.*;
import java.util.Arrays;
import tp.pr1.util.MyStringUtils;

public class ReleasePrinter extends BoardPrinter {
	
	private final static int dimX = 4;
	private final static int dimY = 8;
	private int cellsize = 7;

	
	public ReleasePrinter(Game game) {
		super(game, dimX, dimY);
		encodeGame(game);
	}

	public void encodeGame(Game game) {
		 

				for(int i = 0; i < dimX; i++)
				{
					for(int j = 0; j < dimY; j++)
					{
						board[i][j] = space;
						board[i][j] = game.findCharacter(i, j);

					}
				}
			
	}
	
	
	
	public String printGame(Game game) {
		
		return "Current cycle: " + game.getNumCycles()+ "\n" +"Sun coins: " + game.suncoins() + "\n" + "Remaining zombies: " + game.ZombiesLeftToAppear() + "\n" + ToStringBoard(cellsize);
	}



}
