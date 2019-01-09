package Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import Chess.ChessBoard;
import Chess.Position;
import ChessPiece.Bishop;
import ChessPiece.Knight;

public class TestKnight {

	/*
	 * test for knight's accessible area stays in bound
	 */
	@Test
	public void testAccessibleArea()throws Exception  {
		ChessBoard board=new ChessBoard();
		Knight k = new Knight(2,1,1);
		board.placeChessPiece(k, 2, 1);
		List<Position> area = k.accessibleArea(board);
		assertEquals(6,area.size());
	}
	
	/*
	 * test for knight blocked by several friendly peices
	 */
	@Test
	public void testBlock()throws Exception  {
		ChessBoard board=new ChessBoard();
		Knight k = new Knight(2,1,1);
		board.placeChessPiece(k, 2, 1);
		Bishop b1 = new Bishop(4,0,1);
		Bishop b2 = new Bishop(4,2,1);
		board.placeChessPiece(b1, 4, 0);
		board.placeChessPiece(b2, 4, 2);
		List<Position> area = k.accessibleArea(board);
		assertEquals(4,area.size());
	}
	
	/*
	 * test for knight include enemy peices
	 */
	@Test
	public void testIncludeEnemy()throws Exception  {
		ChessBoard board=new ChessBoard();
		Knight k = new Knight(2,1,1);
		board.placeChessPiece(k, 2, 1);
		Bishop b1 = new Bishop(4,0,-1);
		Bishop b2 = new Bishop(4,2,-1);
		board.placeChessPiece(b1, 4, 0);
		board.placeChessPiece(b2, 4, 2);
		List<Position> area = k.accessibleArea(board);
		assertEquals(6,area.size());
		
	}
	
}
