package Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import Chess.ChessBoard;
import Chess.Position;
import ChessPiece.Bishop;
import ChessPiece.Rook;

public class TestRook {

	/*
	 * Test for non blocked rook
	 */
	@Test
	public void testAccessibleArea() throws Exception{
		ChessBoard board=new ChessBoard();
		Rook r = new Rook(5,5,1);
		board.placeChessPiece(r, 5, 5);
		List<Position> area = r.accessibleArea(board);
		assertEquals(14,area.size());
	}
	
	/*
	 * Test for rook blocked by a friendly piece
	 */
	@Test
	public void testBlock() throws Exception{
		ChessBoard board=new ChessBoard();
		Rook b = new Rook(5,5,1);
		board.placeChessPiece(b, 5, 5);
		Bishop block = new Bishop(3,5,1);
		board.placeChessPiece(block, 3, 5);
		List<Position> area = b.accessibleArea(board);
		assertEquals(10,area.size());
	}
	
	/*
	 * Test for rook blocked by a enemy piece
	 */
	@Test
	public void testIncludeEnemy() throws Exception{
		ChessBoard board=new ChessBoard();
		Rook b = new Rook(5,5,1);
		board.placeChessPiece(b, 5, 5);
		Bishop block = new Bishop(3,5,-1);
		board.placeChessPiece(block, 3, 5);
		List<Position> area = b.accessibleArea(board);
		assertEquals(11,area.size());
	}

}

