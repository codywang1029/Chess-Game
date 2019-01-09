package Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import Chess.ChessBoard;
import Chess.Position;
import ChessPiece.King;
import ChessPiece.Pawn;

public class TestPawn {
	
	/*
	 * a simple test for the constructor
	 */	
	@Test
	public void testConstructor() throws Exception{
		Pawn p = new Pawn(3,5,1);
		assertEquals(3,p.getPosition().x);
		assertEquals(5,p.getPosition().y);
		assertEquals(1,p.getPlayer());
	}
	
	/*
	 * test accessible area for a pawn
	 * expect:step one and step two
	 * expect:not able to move if front is blocked
	 */
	@Test
	public void testAccessibleArea() throws Exception{
		ChessBoard board=new ChessBoard();
		Pawn p = new Pawn(3,5,1);
		board.placeChessPiece(p, 3, 5);
		List<Position> area=p.accessibleArea(board);
		Position ref = new Position(4,5);
		assertTrue(area.get(0).equals(ref));
		ref.x=5;
		assertTrue(area.get(1).equals(ref));
		Pawn obstacle = new Pawn(4,5,-1);
		board.placeChessPiece(obstacle, 4, 5);
		area=p.accessibleArea(board);
		assertEquals(0,area.size());
	}
	
	/*
	 * test accessible area for a pawn if it can capture enemy piece on this diagonal
	 * expect: capture enemy piece
	 * expect: avoid friendly piece
	 * expect: do not step out side of board
	 */
	@Test
	public void testDiagonalAccessibleArea() throws Exception{
		ChessBoard board=new ChessBoard();
		Pawn p = new Pawn(1,5,-1);
		board.placeChessPiece(p, 1, 5);
		List<Position> area=p.accessibleArea(board);
		Position ref = new Position(0,5);
		assertTrue(area.get(0).equals(ref));
		Pawn enemy = new Pawn(0,6,1);
		board.placeChessPiece(enemy, 0, 6);
		area=p.accessibleArea(board);
		ref.x=0;
		ref.y=6;
		assertEquals(2,area.size());
		assertTrue(area.get(1).equals(ref));
		King k = new King(0,4,-1);
		board.placeChessPiece(k, 0, 4);
		area=p.accessibleArea(board);
		assertEquals(2,area.size());
	}

}
