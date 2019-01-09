package Chess;

import java.util.List;
import java.util.Stack;

import ChessPiece.ChessPiece;

/**
 * 
 * @author Kexiang Wang(kwang66)
 *
 */
public class ChessGame {
	protected ChessBoard gameBoard;
	protected Player white;
	protected Player black;
	
	protected Stack<Move> gameRecord;
	
	/**
	 * Generate a default chess game instance
	 * Initialize the board and two players along with their pieces on the initial position
	 */
	public ChessGame() {
		gameBoard=new ChessBoard();
		white=new Player(-1,false);
		black=new Player(1,false);
		
		gameRecord=new Stack<Move>();
		gameBoard.placeChessPiece(black.getLeftRook(), 0, 0);
		gameBoard.placeChessPiece(black.getLeftKnight(), 0, 1);
		gameBoard.placeChessPiece(black.getLeftBishop(), 0, 2);
		gameBoard.placeChessPiece(black.getQueen(), 0, 3);
		gameBoard.placeChessPiece(black.getKing(), 0, 4);
		gameBoard.placeChessPiece(black.getRightBishop(), 0, 5);
		gameBoard.placeChessPiece(black.getRightKnight(), 0, 6);
		gameBoard.placeChessPiece(black.getRightRook(), 0, 7);
		gameBoard.placeChessPiece(black.getPawn1(), 1, 0);
		gameBoard.placeChessPiece(black.getPawn2(), 1, 1);
		gameBoard.placeChessPiece(black.getPawn3(), 1, 2);
		gameBoard.placeChessPiece(black.getPawn4(), 1, 3);
		gameBoard.placeChessPiece(black.getPawn5(), 1, 4);
		gameBoard.placeChessPiece(black.getPawn6(), 1, 5);
		gameBoard.placeChessPiece(black.getPawn7(), 1, 6);
		gameBoard.placeChessPiece(black.getPawn8(), 1, 7);
		
		gameBoard.placeChessPiece(white.getLeftRook(), 7, 0);
		gameBoard.placeChessPiece(white.getLeftKnight(), 7, 1);
		gameBoard.placeChessPiece(white.getLeftBishop(), 7, 2);
		gameBoard.placeChessPiece(white.getQueen(), 7, 3);
		gameBoard.placeChessPiece(white.getKing(), 7, 4);
		gameBoard.placeChessPiece(white.getRightBishop(), 7, 5);
		gameBoard.placeChessPiece(white.getRightKnight(), 7, 6);
		gameBoard.placeChessPiece(white.getRightRook(), 7, 7);
		gameBoard.placeChessPiece(white.getPawn1(), 6, 0);
		gameBoard.placeChessPiece(white.getPawn2(), 6, 1);
		gameBoard.placeChessPiece(white.getPawn3(), 6, 2);
		gameBoard.placeChessPiece(white.getPawn4(), 6, 3);
		gameBoard.placeChessPiece(white.getPawn5(), 6, 4);
		gameBoard.placeChessPiece(white.getPawn6(), 6, 5);
		gameBoard.placeChessPiece(white.getPawn7(), 6, 6);
		gameBoard.placeChessPiece(white.getPawn8(), 6, 7);
	}
	
	/**
	 * undo the last move of the chess game
	 * @return the last move that is successfully undone
	 */
	public Move undo() {
		if (gameRecord.size()==0) {
			return null;
		}
		Move last = gameRecord.pop();
		ChessPiece movedPiece = last.movedPiece;
		ChessPiece killedPiece = last.killedPiece;
		Position currPos = new Position(movedPiece.getPosition());	
		gameBoard.placeChessPiece(movedPiece,last.getPreviousPos().x,last.getPreviousPos().y);
		movedPiece.setFirstMove(last.isPreviousFirstMove());
		movedPiece.setPosition(last.getPreviousPos().x,last.getPreviousPos().y);	
		gameBoard.placeChessPiece(killedPiece, currPos.x,currPos.y);
		return last;
	}
	
	/**
	 * move a piece across the board, capture enemy piece if possible
	 * @param 	piece	: the piece to be moved
	 * @param 	newX	: the new x coordinate
	 * @param 	newY	: the new y coordinate
	 * @return	true	: successful move
	 * 			false	: illegal move
	 */
	public boolean movePiece(ChessPiece piece, int newX, int newY,boolean trueMove) {
		List<Position> accessibleArea=piece.accessibleArea(gameBoard);
		Position newPos = new Position(newX,newY);
		for (Position p:accessibleArea) {
			if (p.equals(newPos)) {
				ChessPiece existing = gameBoard.getChessPiece(newX, newY);
				if (trueMove) {
					Move move = new Move(piece,existing);
					gameRecord.push(move);
				}
				if (existing!=null) {
					if (piece.getType().equals("Vampire")) {
						piece.setFirstMove(true);
					}
				}
				else {
					if (piece.getType().equals("Vampire")) {
						piece.setFirstMove(false);
					}
				}
				gameBoard.placeChessPiece(piece, newX, newY);
				gameBoard.placeChessPiece(null, piece.getPosition().x, piece.getPosition().y);
				piece.setPosition(newX, newY);
				if (piece.getType().equals("Pawn")) {
					piece.setFirstMove(false);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check is one player's King is in check.
	 * @param 	player : the number representing the player 
	 * @return	true   : the player's King is in check
	 * 			false  : the player's King is not in check
	 */
	public boolean isInCheck(int player) {
		Position kingPos;
		if (player==1) {
			kingPos=black.getKing().getPosition();
		}
		else {
			kingPos=white.getKing().getPosition();
		}
		int opponent = player*-1;
		for (int i=0;i<gameBoard.getHeight();i++) {
			for (int j=0;j<gameBoard.getWidth();j++) {
				ChessPiece curr = gameBoard.getChessPiece(i, j);
				if (curr!=null && curr.getPlayer()==opponent) {
					List<Position> attackable = curr.accessibleArea(gameBoard);
					for (Position p : attackable) {
						if (p.equals(kingPos)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * check if player is in checkmate
	 * @param player : the number represent the player that is being tested
	 * @return	true : the player is in checkmate
	 * 			false: the player is not in checkmate
	 */
	public boolean isInCheckmate(int player) {
		if (!isInCheck(player)) {
			return false;
		}
		return hasPossibleMove(player);
	}
	
	/**
	 * check if the game is in stalement when player move
	 * @param player : the number represent the player that is about to move
	 * @return	true : the game is in stalemate
	 * 			false: the player is not in stalemate
	 */
	public boolean isInStalemate(int player) {
		if (isInCheck(player)) {
			return false;
		}
		return hasPossibleMove(player);
	}
	
	/**
	 * check if player has a legal move (i.e iterate through the player's every piece to see
	 * if a certain move of a piece could make the player not in check).
	 * @param player
	 * @return true : the player has at least one legal move
	 * 		   false: the player has no legal move
	 */
	private boolean hasPossibleMove(int player) {
		boolean returnFlag=false;
		for (int i=0;i<gameBoard.getHeight();i++) {
			for (int j=0;j<gameBoard.getWidth();j++) {
				ChessPiece curr = gameBoard.getChessPiece(i, j);
				if (curr!=null && curr.getPlayer()==player) {
					List<Position> possibleMoves = curr.accessibleArea(gameBoard);
					for (Position possibleMove : possibleMoves) {
						ChessPiece existing = gameBoard.getChessPiece(possibleMove.x, possibleMove.y);
						Position currPos = new Position(curr.getPosition());
						boolean currFirstMove=curr.isFirstMove();
						movePiece(curr,possibleMove.x,possibleMove.y,false);
						if (!isInCheck(player)) {
							//System.out.println(curr.getType()+" : "+possibleMove.x+" "+possibleMove.y);
							returnFlag=true;
						}
						gameBoard.placeChessPiece(curr, currPos.x, currPos.y);
						gameBoard.getChessPiece(currPos.x, currPos.y).setFirstMove(currFirstMove);
						curr.setPosition(currPos.x, currPos.y);
						if (existing!=null) {
							existing.setPlayer(player*-1);
						}
						gameBoard.placeChessPiece(existing, possibleMove.x, possibleMove.y);
						if (returnFlag) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * print existing pieces and their locations
	 */
	public void printGame() {
		for (int i=0;i<gameBoard.getHeight();i++) {
			for (int j=0;j<gameBoard.getWidth();j++) {
				ChessPiece curr = gameBoard.getChessPiece(i, j);
				if (curr!=null) {
					if (curr.getPlayer()==1) {
						System.out.print("Black");
					}
					else {
						System.out.print("White");
					}
				System.out.print(" "+curr.getType()+" : ");
				System.out.println("("+curr.getPosition().x+","+curr.getPosition().y+")");
				}
			}
		}
	}

	public ChessBoard getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(ChessBoard gameBoard) {
		this.gameBoard = gameBoard;
	}

	public Player getWhite() {
		return white;
	}

	public Player getBlack() {
		return black;
	}
	
	public Stack<Move> getGameRecord(){
		return gameRecord;
	}
	
	
}
