package Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import Chess.ChessBoard;
import Chess.Position;
import ChessPiece.Bishop;

public class TestBishop {

	/*
	 * Test for non blocked Bishop
	 */
	@Test
	public void testAccessibleArea() throws Exception{
		ChessBoard board=new ChessBoard();
		Bishop b = new Bishop(5,5,1);
		board.placeChessPiece(b, 5, 5);
		List<Position> area = b.accessibleArea(board);
		assertEquals(11,area.size());
	}
	
	/*
	 * Test for Bishop blocked by a friendly piece
	 */
	@Test
	public void testBlock() throws Exception{
		ChessBoard board=new ChessBoard();
		Bishop b = new Bishop(5,5,1);
		board.placeChessPiece(b, 5, 5);
		Bishop block = new Bishop(3,3,1);
		board.placeChessPiece(block, 3, 3);
		List<Position> area = b.accessibleArea(board);
		assertEquals(7,area.size());
	}
	
	/*
	 * Test for Bishop blocked by a enemy piece
	 */
	@Test
	public void testIncludeEnemy() throws Exception{
		ChessBoard board=new ChessBoard();
		Bishop b = new Bishop(5,5,1);
		board.placeChessPiece(b, 5, 5);
		Bishop block = new Bishop(3,3,-1);
		board.placeChessPiece(block, 3, 3);
		List<Position> area = b.accessibleArea(board);
		assertEquals(8,area.size());
	}

}
