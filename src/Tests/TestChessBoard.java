package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Chess.ChessBoard;

public class TestChessBoard {
	
	@Test
	public void TestConstructor() throws Exception{
		ChessBoard board=new ChessBoard();
		assertEquals(8,board.getHeight());
		assertEquals(8,board.getWidth());
		board=new ChessBoard(4,10);
		assertEquals(10,board.getHeight());
		assertEquals(4,board.getWidth());
	}
	
	@Test
	public void TestInBound() throws Exception{
		ChessBoard board=new ChessBoard();
		assertEquals(false,board.inBound(0, -1));
		assertEquals(false,board.inBound(9, 4));
		assertEquals(true,board.inBound(0, 0));
		assertEquals(true,board.inBound(7, 7));
	}
}
