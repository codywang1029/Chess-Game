package Chess;

import ChessPiece.ChessPiece;

/**
 * 
 * @author Kexiang Wang(kwang66)
 *
 */
public class ChessBoard {
	private ChessPiece[][] board;
	private int width;
	private int height;
	
	/**
	 * Default constructor for a chess board.
	 * Create a 8x8 chess board with all the values setting to null.
	 */
	public ChessBoard() {
		board=new ChessPiece[8][8];
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				board[i][j]=null;
			}
		}
		this.width=8;
		this.height=8;
	}
	
	/**
	 * Constructor for a chess board.
	 * Create a widthxheight chess board with all the values setting to null.
	 */
	public ChessBoard(int width,int height) {
		board=new ChessPiece[height][width];
		for (int i=0;i<height;i++) {
			for (int j=0;j<width;j++) {
				board[i][j]=null;
			}
		}
		this.width=width;
		this.height=height;
	}
	
	
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public ChessPiece getChessPiece(int x,int y) {
		if (inBound(x,y)) {
			return board[x][y];
		}
		else {
			return null;
		}
	}	
	
	/**
	 * place a chess piece on (x,y)
	 * @param piece	: the piece to be placed
	 * @param x		: x coordinate
	 * @param y		: y coordinate
	 */
	public void placeChessPiece(ChessPiece piece,int x,int y) {
		board[x][y]=piece;
	}
	
	/**
	 * Check if (x,y) is a position inside chess board
	 * @param x
	 * @param y
	 * @return	true	: if (x,y) is in the chess board
	 * 			false	: if (x,y) is not in the chess board
	 */
	public boolean inBound(int x,int y) {
		
		return x>-1 && y>-1 && x<this.height && y<this.width;
	}

}
