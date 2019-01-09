package Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import Chess.ChessBoard;
import Chess.Position;
import ChessPiece.Bishop;
import ChessPiece.Queen;

public class TestQueen {

	/*
	 * Test for non blocked queen
	 */
	@Test
	public void testAccessibleArea() throws Exception{
		ChessBoard board=new ChessBoard();
		Queen b = new Queen(3,4,1);
		board.placeChessPiece(b, 3, 4);
		List<Position> area = b.accessibleArea(board);
		assertEquals(27,area.size());
	}
	
	/*
	 * Test for Bishop blocked by 2 friendly piece
	 */
	@Test
	public void testBlock() throws Exception{
		ChessBoard board=new ChessBoard();
		Queen b = new Queen(3,4,1);
		board.placeChessPiece(b,3,4);
		Bishop block1 = new Bishop(2,3,1);
		Bishop block2 = new Bishop(6,4,1);
		board.placeChessPiece(block1, 2, 3);
		board.placeChessPiece(block2, 6, 4);
		List<Position> area = b.accessibleArea(board);
		assertEquals(22,area.size());
	}
	
	/*
	 * Test for Bishop blocked by a enemy piece
	 */
	@Test
	public void testIncludeEnemy() throws Exception{
		ChessBoard board=new ChessBoard();
		Queen b = new Queen(3,4,1);
		board.placeChessPiece(b,3,4);
		Bishop block1 = new Bishop(2,3,-1);
		Bishop block2 = new Bishop(6,4,1);
		board.placeChessPiece(block1, 2, 3);
		board.placeChessPiece(block2, 6, 4);
		List<Position> area = b.accessibleArea(board);
		assertEquals(23,area.size());
	}

}



