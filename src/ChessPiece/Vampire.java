package ChessPiece;

import java.util.ArrayList;
import java.util.List;

import Chess.ChessBoard;
import Chess.Position;

/**
 * Vampire Description: a Vampire moves similarly as a Queen, except that its movement radius 
 * is two instead of infinite. A Vampire can move freely on its first move. After that, the
 * Vampire gets "hungry" and has to capture an enemy piece in order to move. After the capture 
 * move, it can move freely again. In all, a capture grants the Vampire one chance to move freely. 
 */
public class Vampire extends ChessPiece {

	public Vampire(int x,int y,int player){
		this.position=new Position(x,y);
		this.player=player;
		firstMove=true;
	}
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Vampire";
	}

	@Override
	public List<Position> accessibleArea(ChessBoard board) {
		// TODO Auto-generated method stub
		List<Position> accessibleArea = new ArrayList<>();
		checkVampirePath(board,accessibleArea,0,1);
		checkVampirePath(board,accessibleArea,0,-1);
		checkVampirePath(board,accessibleArea,1,0);
		checkVampirePath(board,accessibleArea,-1,0);
		checkVampirePath(board,accessibleArea,1,1);
		checkVampirePath(board,accessibleArea,1,-1);
		checkVampirePath(board,accessibleArea,-1,1);
		checkVampirePath(board,accessibleArea,-1,-1);
		return accessibleArea;
	}
	
	protected void checkVampirePath(ChessBoard board, List<Position> accessibleArea, int incX, int incY) {
		int currX=position.x+incX;
		int currY=position.y+incY;
		int iteration=0;
		while (board.inBound(currX, currY) && board.getChessPiece(currX, currY)==null && iteration!=2) {
			if (this.firstMove) {
				Position available=new Position(currX,currY);
				accessibleArea.add(available);
			}
			currX+=incX;
			currY+=incY;
			iteration++;
		}
		if (iteration!=2 && board.inBound(currX, currY) && board.getChessPiece(currX, currY).getPlayer()!=player) {
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
	
}
