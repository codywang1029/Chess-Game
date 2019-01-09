package Chess;

public class CustomGame extends ChessGame {
	
	public CustomGame(){
		gameBoard=new ChessBoard();
		white=new Player(-1,true);
		black=new Player(1,true);
		
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
		gameBoard.placeChessPiece(black.getVampire(), 1, 3);
		gameBoard.placeChessPiece(black.getGuard(), 1, 4);
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
		gameBoard.placeChessPiece(white.getVampire(), 6, 3);
		gameBoard.placeChessPiece(white.getGuard(), 6, 4);
		gameBoard.placeChessPiece(white.getPawn6(), 6, 5);
		gameBoard.placeChessPiece(white.getPawn7(), 6, 6);
		gameBoard.placeChessPiece(white.getPawn8(), 6, 7);
		
	}
}
