package ChessPiece;

import java.util.List;

import Chess.ChessBoard;
import Chess.Position;

/**
 * 
 * @author Kexiang Wang(kwang66)
 *
 */
public abstract class ChessPiece {
	
	protected Position position;
	protected int player;
	protected boolean firstMove;
	
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(int x,int y) {
		position.x=x;
		position.y=y;
	}
	
	public int getPlayer() {
		return player;
	}
	
	public void setPlayer(int player) {
		this.player = player;
	}
	
	public boolean isFirstMove() {
		return firstMove;
	}

	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}
	
	/**
	 * 
	 * @return the type of the piece
	 */
	public abstract String getType();
	
	/**
	 * 
	 * @param board	: the game board
	 * @return	a list of position containing all the accessible position of the piece
	 * 			including possible capture of enemy's piece
	 */
	public abstract List<Position> accessibleArea(ChessBoard board);
	
	/**
	 * add (piece.position.x+xOffset,piece.position.y+yOffset) to accessibleArea when it is valid
	 * @param board	:	the game board
	 * @param accessibleArea : accessibleArea of the piece
	 * @param xOffset
	 * @param yOffset
	 */
	protected void addNewAvailable(ChessBoard board, List<Position> accessibleArea, int xOffset, int yOffset) {
		if (validatePosition(board,position.x+xOffset,position.y+yOffset)) {
			Position available=new Position(position.x+xOffset,position.y+yOffset);
			accessibleArea.add(available);
		}
	}
	
	/**
	 * check if a position on the board is a valid move
	 * @param board	:	the game board
	 * @param x 	:	x coordinate
	 * @param y		:	y coordinate
	 * @return	true if (x,y) is a valid move, false otherwise
	 */
	protected boolean validatePosition(ChessBoard board,int x, int y) {
		if (!board.inBound(x, y)) {
			return false;
		}
		if (board.getChessPiece(x, y)!=null && board.getChessPiece(x, y).getPlayer()==player) {
			return false;
		}
		if (board.getChessPiece(x, y)!=null && board.getChessPiece(x, y).getType()=="King" && board.getChessPiece(x, y).getPlayer()!=player) {
			return !guarded(board,x,y);
		}
		return true;
	}
	
	/**
	 * check the horizontal, vertical and diagonal axis, and enqueue any available positions to
	 * accessibleArea
	 * @param board		: the game board
	 * @param accessibleArea :	accessibleArea of the piece
	 * @param incX		: the difference of x after each iteration
	 * @param incY		: the difference of y after each iteration
	 */
	protected void checkPath(ChessBoard board, List<Position> accessibleArea, int incX, int incY) {
		int currX=position.x+incX;
		int currY=position.y+incY;
		while (board.inBound(currX, currY) && board.getChessPiece(currX, currY)==null) {
			Position available=new Position(currX,currY);
			accessibleArea.add(available);
			currX+=incX;
			currY+=incY;
		}
		if (board.inBound(currX, currY) && board.getChessPiece(currX, currY).getPlayer()!=player) {
			if (board.getChessPiece(currX, currY).getType()=="King") {
				if (!guarded(board,currX,currY)) {
					Position available=new Position(currX,currY);
					accessibleArea.add(available);
				}
			}
			else {
				Position available=new Position(currX,currY);
				accessibleArea.add(available);
			}
		}
	}
	
	/**
	 * check if a chess piece at (x,y) is guarded by a friendly guard
	 * @param board		:	the game board
	 * @param x			:	x coordination of the piece being checked
	 * @param y			:	y coordination of the piece being checked
	 * @return	true 	:	the piece is guarded by the guard
	 * 			false	:	otherwise
	 */
	protected boolean guarded(ChessBoard board,int x, int y) {
		ChessPiece piece = board.getChessPiece(x, y);
		if (piece==null) {
			return false;
		}
		if (guardExist(board, x, y, piece.getPlayer(), -1, 0)) {
			return true;
		}
		if (guardExist(board, x, y, piece.getPlayer(), -1, 1)) {
			return true;
		}
		if (guardExist(board, x, y, piece.getPlayer(), -1, -1)) {
			return true;
		}
		if (guardExist(board, x, y, piece.getPlayer(), 0, -1)) {
			return true;
		}
		if (guardExist(board, x, y, piece.getPlayer(), 0, 1)) {
			return true;
		}
		if (guardExist(board, x, y, piece.getPlayer(), 1, 1)) {
			return true;
		}
		if (guardExist(board, x, y, piece.getPlayer(), 1, 0)) {
			return true;
		}
		if (guardExist(board, x, y, piece.getPlayer(), 1, -1)) {
			return true;
		}
		return false;	
	}
	
	/**
	 * check if there is a friendly guard at (x+xOffset,y+yOffset)
	 * @param board		:	the game board
	 * @param x			:	x coordination of the piece being checked
	 * @param y			:	y coordination of the piece being checked
	 * @param player	:	int representing the player
	 * @param xOffset	:	xOffset
	 * @param yOffset	:	yOffset
	 * @return	true	:	there is a friendly guard at (x+xOffset,y+yOffset)
	 * 			false	:	otherwise
	 */
	private boolean guardExist(ChessBoard board, int x, int y, int player, int xOffset, int yOffset) {
		ChessPiece around =  board.getChessPiece(x+xOffset, y+yOffset);
		if (around!=null && around.getPlayer()==player && around.getType().equals("Guard")) {
			return true;
		}
		else {
			return false;
		}
	}
}
