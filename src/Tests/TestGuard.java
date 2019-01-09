package Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import Chess.ChessBoard;
import Chess.Position;
import ChessPiece.Bishop;
import ChessPiece.Guard;

public class TestGuard {

	/*
	 * Test for non blocked Guard
	 */
	@Test
	public void testAccessibleArea() throws Exception{
		ChessBoard board=new ChessBoard();
		Guard g = new Guard(5,5,1);
		board.placeChessPiece(g, 5, 5);
		List<Position> area = g.accessibleArea(board);
		assertEquals(8,area.size());
	}
	
	/*
	 * Test for Guard blocked by pieces
	 */
	@Test
	public void testBlock() throws Exception{
		ChessBoard board=new ChessBoard();
		Guard g = new Guard(5,5,1);
		board.placeChessPiece(g, 5, 5);
		Bishop block = new Bishop(4,5,1);
		board.placeChessPiece(block, 4, 5);
		Bishop enemyBlock = new Bishop(5,6,-1);
		board.placeChessPiece(enemyBlock, 5, 6);
		List<Position> area = g.accessibleArea(board);
		assertEquals(6,area.size());
	}
	
	/*
	 * Test for Guard stays in bound
	 */
	@Test
	public void testGuardInBound() throws Exception{
		ChessBoard board=new ChessBoard();
		Guard g = new Guard(0,0,1);
		board.placeChessPiece(g, 0, 0);
		Bishop enemyBlock = new Bishop(1,0,-1);
		board.placeChessPiece(enemyBlock, 1, 0);
		List<Position> area = g.accessibleArea(board);
		assertEquals(2,area.size());
	}

}
