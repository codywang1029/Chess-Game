package Chess;

import ChessPiece.Bishop;
import ChessPiece.Guard;
import ChessPiece.King;
import ChessPiece.Knight;
import ChessPiece.Pawn;
import ChessPiece.Queen;
import ChessPiece.Rook;
import ChessPiece.Vampire;

/**
 * 
 * @author Kexiang Wang(kwang66)
 *
 */
public class Player {
	private int type;
	private Rook leftRook;
	private Rook rightRook;
	private Bishop leftBishop;
	private Bishop rightBishop;
	private King king;
	private Knight leftKnight;
	private Knight rightKnight;
	private Queen queen;
	private Pawn pawn1;
	private Pawn pawn2;
	private Pawn pawn3;
	private Pawn pawn4;
	private Pawn pawn5;
	private Pawn pawn6;
	private Pawn pawn7;
	private Pawn pawn8;
	private Guard guard;
	private Vampire vampire;
	
	/**
	 * 
	 * @param type:	-1 for white piece holder, 1 for black piece holder
	 * @param custom: false for standard chess game, true for custom game
	 */
	public Player(int type, boolean custom) {
		this.type=type;
		if (type==1) {
			leftRook=new Rook(0,0,type);
			rightRook=new Rook(0,7,type);
			leftBishop=new Bishop(0,2,type);
			rightBishop=new Bishop(0,5,type);
			king=new King(0,4,type);
			queen=new Queen(0,3,type);
			leftKnight=new Knight(0,1,type);
			rightKnight=new Knight(0,6,type);
			pawn1=new Pawn(1,0,type);
			pawn2=new Pawn(1,1,type);
			pawn3=new Pawn(1,2,type);
			
			pawn6=new Pawn(1,5,type);
			pawn7=new Pawn(1,6,type);
			pawn8=new Pawn(1,7,type);
		}
		else {
			leftRook=new Rook(7,0,type);
			rightRook=new Rook(7,7,type);
			leftBishop=new Bishop(7,2,type);
			rightBishop=new Bishop(7,5,type);
			king=new King(7,4,type);
			queen=new Queen(7,3,type);
			leftKnight=new Knight(7,1,type);
			rightKnight=new Knight(7,6,type);
			pawn1=new Pawn(6,0,type);
			pawn2=new Pawn(6,1,type);
			pawn3=new Pawn(6,2,type);
			
			pawn6=new Pawn(6,5,type);
			pawn7=new Pawn(6,6,type);
			pawn8=new Pawn(6,7,type);
		}
		
		if (!custom) {
			if (type==1) {
				pawn4=new Pawn(1,3,type);
				pawn5=new Pawn(1,4,type);
			}
			else {
				pawn4=new Pawn(6,3,type);
				pawn5=new Pawn(6,4,type);
			}
		}
		else {
			if (type==1) {
				vampire=new Vampire(1,3,type);
				guard=new Guard(1,4,type);
			}
			else {
				guard=new Guard(6,4,type);
				vampire=new Vampire(6,3,type);
			}
		}
		
	}

	public Rook getLeftRook() {
		return leftRook;
	}

	public Rook getRightRook() {
		return rightRook;
	}

	public Bishop getLeftBishop() {
		return leftBishop;
	}

	public Bishop getRightBishop() {
		return rightBishop;
	}

	public King getKing() {
		return king;
	}

	public Knight getLeftKnight() {
		return leftKnight;
	}

	public Knight getRightKnight() {
		return rightKnight;
	}

	public Queen getQueen() {
		return queen;
	}

	public Pawn getPawn1() {
		return pawn1;
	}

	public Pawn getPawn2() {
		return pawn2;
	}

	public Pawn getPawn3() {
		return pawn3;
	}

	public Pawn getPawn4() {
		return pawn4;
	}

	public Pawn getPawn5() {
		return pawn5;
	}
	
	public Pawn getPawn6() {
		return pawn6;
	}

	public Pawn getPawn7() {
		return pawn7;
	}

	public Pawn getPawn8() {
		return pawn8;
	}
	
	public Guard getGuard() {
		return guard;
	}
	
	public Vampire getVampire() {
		return vampire;
	}
}
