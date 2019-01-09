package Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import Chess.ChessBoard;
import Chess.Position;
import ChessPiece.King;
import ChessPiece.Pawn;

public class TestKing {

	/*
	 * test for non blocked king stays in bound
	 */
	@Test
	public void testAccessibleArea() throws Exception {
		ChessBoard board=new ChessBoard();
		King k = new King(0,3,1);
		board.placeChessPiece(k, 0, 3);
		List<Position> area = k.accessibleArea(board);
		assertEquals(5,area.size());
	}
	
	/*
	 * test for king blocked by several friendly pieces
	 */
	@Test
	public void testBlocked() throws Exception {
		ChessBoard board=new ChessBoard();
		King k = new King(3,3,1);
		board.placeChessPiece(k,3,3);
		Pawn f1 = new Pawn(3,4,1);
		Pawn f2 = new Pawn(4,4,1);
		Pawn f3 = new Pawn(2,2,1);
		board.placeChessPiece(f1,3,4);
		board.placeChessPiece(f2,4,4);
		board.placeChessPiece(f3,2,2);
		List<Position> area = k.accessibleArea(board);
		assertEquals(5,area.size());
	}
	
	/*
	 * test for king blocked by 1 friendly pieces and 2 enemies
	 */
	@Test
	public void testIncludeEnemy() throws Exception {
		ChessBoard board=new ChessBoard();
		King k = new King(3,3,1);
		board.placeChessPiece(k,3,3);
		Pawn f1 = new Pawn(3,4,-1);
		Pawn f2 = new Pawn(4,4,-1);
		Pawn f3 = new Pawn(2,2,1);
		board.placeChessPiece(f1,3,4);
		board.placeChessPiece(f2,4,4);
		board.placeChessPiece(f3,2,2);
		List<Position> area = k.accessibleArea(board);
		assertEquals(7,area.size());
	}
	
		

}
