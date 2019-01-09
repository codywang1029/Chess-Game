package Tests;

import org.junit.Test;
import static org.junit.Assert.*;

import Chess.ChessBoard;
import Chess.CustomGame;
import Chess.Player;

public class TestCustomGame {

	@Test
	public void testConstructor(){
		CustomGame game = new CustomGame();
		assertEquals("Vampire",game.getGameBoard().getChessPiece(1, 3).getType());
		assertEquals("Vampire",game.getGameBoard().getChessPiece(6, 3).getType());
		assertEquals("Guard",game.getGameBoard().getChessPiece(1, 4).getType());
		assertEquals("Guard",game.getGameBoard().getChessPiece(6, 4).getType());
	}
	
	@Test
	public void testGuarded(){
		CustomGame game = new CustomGame();
		Player white = game.getWhite();
		Player black = game.getBlack();
		ChessBoard board=new ChessBoard();
		board.placeChessPiece(white.getGuard(), 1, 1);
		white.getGuard().setPosition(1, 1);
		board.placeChessPiece(white.getKing(), 0, 0);
		white.getKing().setPosition(0, 0);
		board.placeChessPiece(black.getQueen(), 6, 0);
		black.getQueen().setPosition(6, 0);
		board.placeChessPiece(black.getKing(), 0, 2);
		black.getKing().setPosition(0, 2);
		game.setGameBoard(board);
		boolean inCheck = game.isInCheck(-1);
		assertTrue(!inCheck);
		game.movePiece(white.getGuard(), 2, 1,true);
		inCheck = game.isInCheck(-1);
		assertTrue(inCheck);
		assertTrue(!game.isInStalemate(-1));
		assertTrue(!game.isInCheckmate(-1));
	}
	
	@Test
	public void testVampireMovement(){
		CustomGame game = new CustomGame();
		Player white = game.getWhite();
		Player black = game.getBlack();
		game.movePiece(white.getVampire(), 4, 1,true);
		assertTrue(!white.getVampire().isFirstMove());
		assertEquals(0,white.getVampire().accessibleArea(game.getGameBoard()).size());
		game.movePiece(black.getVampire(), 2, 3,true);
		assertEquals(1,black.getVampire().accessibleArea(game.getGameBoard()).size());
		game.movePiece(black.getVampire(), 4, 1,true);
		assertTrue(black.getVampire().isFirstMove());
	}
}
