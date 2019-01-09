package Chess;

import ChessPiece.ChessPiece;

public class Move {
	protected ChessPiece movedPiece;
	protected Position previousPos;
	protected boolean previousFirstMove;
	protected ChessPiece killedPiece;
	
	public Move(ChessPiece movedPiece, ChessPiece killedPiece) {
		this.movedPiece=movedPiece;
		this.previousFirstMove=movedPiece.isFirstMove();
		this.previousPos=new Position(movedPiece.getPosition());
		this.killedPiece=killedPiece;
	}

	public Position getPreviousPos() {
		return previousPos;
	}

	public boolean isPreviousFirstMove() {
		return previousFirstMove;
	}

	public ChessPiece getMovedPiece() {
		return movedPiece;
	}

	public ChessPiece getKilledPiece() {
		return killedPiece;
	}

	
}
