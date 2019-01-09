package ChessPiece;

import java.util.ArrayList;
import java.util.List;

import Chess.ChessBoard;
import Chess.Position;

public class Rook extends ChessPiece {

	public Rook(int x,int y,int player){
		this.position=new Position(x,y);
		this.player=player;
	}
	
	@Override
	public String getType() {
		
		return "Rook";
	}

	@Override
	public List<Position> accessibleArea(ChessBoard board) {
		List<Position> accessibleArea = new ArrayList<Position>();
		checkPath(board,accessibleArea,0,1);
		checkPath(board,accessibleArea,0,-1);
		checkPath(board,accessibleArea,1,0);
		checkPath(board,accessibleArea,-1,0);
		return accessibleArea;
	}
}
