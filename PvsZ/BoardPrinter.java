package tp.pr1;
import java.util.Arrays;

import tp.pr1.elements.Peashooter;
import tp.pr1.elements.Sunflower;
import tp.pr1.elements.Zombie;
import tp.pr1.util.MyStringUtils;

public abstract class BoardPrinter implements GamePrinter{
	protected int dimX; 
	protected int dimY;
	protected String[][] board;
	protected String[] debugboard;
	protected final String space;

	public BoardPrinter(Game game, int dimX, int dimY) {

		this.space  = " ";
		this.board = new String[dimX][dimY];
		this.debugboard = new String[dimY];
		encodeGame(game);
		this.dimX = dimX;
		this.dimY = dimY;

	}

	public abstract void encodeGame(Game game);

	public String ToStringBoard(int cellsize) {

		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";

		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (dimY * (cellsize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);

		StringBuilder str = new StringBuilder();

		str.append(lineDelimiter);

		if(dimX > 1) {
			for(int i=0; i<dimX; i++) {
				str.append(margin).append(vDelimiter);
				for (int j=0; j<dimY; j++) {
					str.append( MyStringUtils.centre(board[i][j], cellsize)).append(vDelimiter);
				}
				str.append(lineDelimiter);
			}
		}
		else if(dimX == 1)
		{
			str.append(margin).append(vDelimiter);
			for (int j=0; j<dimY; j++) {
				str.append( MyStringUtils.centre(debugboard[j], cellsize)).append(vDelimiter);
			}
			str.append(lineDelimiter);
			
		}
		return str.toString();
	}



	public String printGame()
	{
		return toString();
	}

	public void setDimX(int dimX) {
		this.dimX = dimX;
	}

	public void setDimY(int dimY) {
		this.dimY = dimY;
	}


}

