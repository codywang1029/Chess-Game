package ChessPiece;

import java.util.ArrayList;
import java.util.List;

import Chess.ChessBoard;
import Chess.Position;

public class Pawn extends ChessPiece {
	
	
	public Pawn(int x,int y,int player){
		this.position=new Position(x,y);
		this.player=player;
		firstMove=true;
	}
	

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Pawn";
	}

	@Override
	public List<Position> accessibleArea(ChessBoard board) {
		// TODO Auto-generated method stub
		List<Position> accessibleArea=new ArrayList<Position>();
		if (board.inBound(position.x+player, position.y)) {
			ChessPiece existing=board.getChessPiece(position.x+player, position.y);
			if (existing==null) {
				Position available=new Position(position.x+player, position.y);
				accessibleArea.add(available);
			}
		}
		if (this.firstMove && board.inBound(position.x+2*player, position.y)) {
			ChessPiece existing=board.getChessPiece(position.x+2*player, position.y);
			if (existing==null && board.getChessPiece(position.x+player, position.y)==null) {
				Position available=new Position(position.x+player*2, position.y);
				accessibleArea.add(available);
			}
		}
		if (board.inBound(position.x+player, position.y+1)) {
			ChessPiece existing=board.getChessPiece(position.x+player, position.y+1);
			if (existing!=null && existing.getPlayer()!=player) {
				Position available=new Position(position.x+player, position.y+1);
				accessibleArea.add(available);
			}
		}
		if (board.inBound(position.x+player, position.y-1)) {
			ChessPiece existing=board.getChessPiece(position.x+player, position.y-1);
			if (existing!=null && existing.getPlayer()!=player) {
				Position available=new Position(position.x+player, position.y-1);
				accessibleArea.add(available);
			}
		}		
		return accessibleArea;
	}

}
