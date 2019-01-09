package Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import Chess.ChessBoard;
import Chess.Position;
import ChessPiece.Bishop;
import ChessPiece.Guard;
import ChessPiece.King;
import ChessPiece.Vampire;

public class TestVampire {

	/*
	 * Test for non blocked Vampire
	 */
	@Test
	public void testAccessibleArea() throws Exception{
		ChessBoard board=new ChessBoard();
		Vampire v = new Vampire(4,4,1);
		board.placeChessPiece(v, 4, 4);
		List<Position> area = v.accessibleArea(board);
		assertEquals(16,area.size());
		
		v = new Vampire(0,0,1);
		board.placeChessPiece(v, 0, 0);
		area = v.accessibleArea(board);
		assertEquals(6,area.size());
	}
	
	/*
	 * Test for Vampire blocked by pieces
	 */
	@Test
	public void testBlock() throws Exception{
		ChessBoard board=new ChessBoard();
		Vampire v = new Vampire(4,4,1);
		board.placeChessPiece(v, 4, 4);
		Guard g = new Guard(4,5,1);
		board.placeChessPiece(g, 4, 5);
		List<Position> area = v.accessibleArea(board);
		assertEquals(14,area.size());
		
		Guard enemy = new Guard(5,5,-1);
		board.placeChessPiece(enemy, 5, 5);
		area = v.accessibleArea(board);
		assertEquals(13,area.size());
	}
	
	/*
	 * Test for hungry Vampire
	 */
	@Test
	public void testHungry() throws Exception{
		ChessBoard board=new ChessBoard();
		Vampire v = new Vampire(4,4,1);
		board.placeChessPiece(v, 4, 4);
		v.setFirstMove(false);
		List<Position> area = v.accessibleArea(board);
		assertEquals(0,area.size());
		
		Guard enemy = new Guard(5,5,-1);
		board.placeChessPiece(enemy, 5, 5);
		area = v.accessibleArea(board);
		assertEquals(1,area.size());
		
		Guard enemy2 = new Guard(0,0,-1);
		board.placeChessPiece(enemy2, 0, 0);
		area = v.accessibleArea(board);
		assertEquals(1,area.size());
		
		Guard enemy3 = new Guard(6,6,-1);
		board.placeChessPiece(enemy3, 4, 6);
		area = v.accessibleArea(board);
		assertEquals(2,area.size());
		
	}
	
	/*
	 * Test for Vampire/Guard interaction
	 */
	@Test
	public void testGuarded() throws Exception{
		ChessBoard board=new ChessBoard();
		Vampire v = new Vampire(4,4,1);
		board.placeChessPiece(v, 4, 4);
		v.setFirstMove(false);
		List<Position> area = v.accessibleArea(board);
		assertEquals(0,area.size());
		
		Guard enemy = new Guard(5,5,-1);
		board.placeChessPiece(enemy, 5, 5);
		area = v.accessibleArea(board);
		assertEquals(1,area.size());
		
		Guard enemy2 = new Guard(0,0,-1);
		board.placeChessPiece(enemy2, 0, 0);
		area = v.accessibleArea(board);
		assertEquals(1,area.size());
		
		Guard enemy3 = new Guard(4,6,-1);
		board.placeChessPiece(enemy3, 4, 6);
		area = v.accessibleArea(board);
		assertEquals(2,area.size());
		
		King k = new King(5,4,-1);
		board.placeChessPiece(k, 5, 4);
		area = v.accessibleArea(board);
		assertEquals(2,area.size());
		
		King k2 = new King(4,2,-1);
		board.placeChessPiece(k2, 4, 2);
		area = v.accessibleArea(board);
		assertEquals(3,area.size());
		
	}

}
