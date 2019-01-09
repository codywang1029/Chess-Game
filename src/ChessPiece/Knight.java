package ChessPiece;

import java.util.ArrayList;
import java.util.List;

import Chess.ChessBoard;
import Chess.Position;

public class Knight extends ChessPiece {

	
	public Knight(int x,int y,int player){
		this.position=new Position(x,y);
		this.player=player;
	}
	
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Knight";
	}


	@Override
	public List<Position> accessibleArea(ChessBoard board) {
		// TODO Auto-generated method stub
		List<Position> accessibleArea=new ArrayList<Position>();
		addNewAvailable(board,accessibleArea,1,2);
		addNewAvailable(board,accessibleArea,1,-2);
		addNewAvailable(board,accessibleArea,-1,2);
		addNewAvailable(board,accessibleArea,-1,-2);
		addNewAvailable(board,accessibleArea,2,1);
		addNewAvailable(board,accessibleArea,2,-1);
		addNewAvailable(board,accessibleArea,-2,-1);
		addNewAvailable(board,accessibleArea,-2,1);
		return accessibleArea;
	}

}
