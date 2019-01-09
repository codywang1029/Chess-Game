package ChessPiece;

import java.util.ArrayList;
import java.util.List;

import Chess.ChessBoard;
import Chess.Position;


/**
 * Guard Description: a Guard is a piece that cannot capture other pieces.
 * It moves the way the King does. When a friendly King is within the accessible area of the Guard,
 * the King cannot be checked. 
 */
public class Guard extends ChessPiece {
	
	public Guard(int x,int y,int player){
		this.position=new Position(x,y);
		this.player=player;
	}
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Guard";
	}

	@Override
	public List<Position> accessibleArea(ChessBoard board) {
		// TODO Auto-generated method stub
		List<Position> accessibleArea=new ArrayList<Position>();
		
		availableForGuard(board, accessibleArea, -1, 1);
		availableForGuard(board, accessibleArea, -1, 0);
		availableForGuard(board, accessibleArea, -1, -1);
		availableForGuard(board, accessibleArea, 0, 1);
		availableForGuard(board, accessibleArea, 0, -1);
		availableForGuard(board, accessibleArea, 1, 0);
		availableForGuard(board, accessibleArea, 1, -1);
		availableForGuard(board, accessibleArea, 1, 1);
		
		return accessibleArea;
	}

	private void availableForGuard(ChessBoard board, List<Position> accessibleArea, int xOffset, int yOffset) {
		if (board.inBound(position.x+xOffset, position.y+yOffset) && board.getChessPiece(position.x+xOffset, position.y+yOffset)==null) {
			Position available = new Position(position.x+xOffset,position.y+yOffset);
			accessibleArea.add(available);
		}
	}
	

}
