package ChessPiece;

import java.util.ArrayList;
import java.util.List;

import Chess.ChessBoard;
import Chess.Position;

public class King extends ChessPiece {

	public King(int x,int y,int player){
		this.position=new Position(x,y);
		this.player=player;
	}
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "King";
	}

	@Override
	public List<Position> accessibleArea(ChessBoard board) {
		// TODO Auto-generated method stub
		List<Position> accessibleArea=new ArrayList<Position>();
		addNewAvailable(board, accessibleArea, 0, 1);
		addNewAvailable(board, accessibleArea, 0, -1);
		addNewAvailable(board, accessibleArea, 1, 1);
		addNewAvailable(board, accessibleArea, 1, 0);
		addNewAvailable(board, accessibleArea, 1, -1);
		addNewAvailable(board, accessibleArea, -1, 1);
		addNewAvailable(board, accessibleArea, -1, 0);
		addNewAvailable(board, accessibleArea, -1, -1);
		
		return accessibleArea;
	}

	
}
