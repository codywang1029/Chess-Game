package ChessPiece;

import java.util.ArrayList;
import java.util.List;

import Chess.ChessBoard;
import Chess.Position;

public class Bishop extends ChessPiece {

	
	public Bishop(int x,int y,int player){
		this.position=new Position(x,y);
		this.player=player;
	}
	
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Bishop";
	}

	@Override
	public List<Position> accessibleArea(ChessBoard board) {
		// TODO Auto-generated method stub
		List<Position> accessibleArea=new ArrayList<Position>();			
		checkPath(board, accessibleArea, 1, 1);
		checkPath(board, accessibleArea, 1,-1);
		checkPath(board, accessibleArea, -1,-1);
		checkPath(board, accessibleArea, -1,1);
		return accessibleArea;
	}

}
