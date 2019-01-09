package Tests;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

import Chess.ChessBoard;
import Chess.ChessGame;
import Chess.Move;
import Chess.Player;
import Chess.Position;
import ChessPiece.ChessPiece;

public class TestChessGame {

	/*
	 * test for initializer.
	 * Check board and pieces's initial condition
	 */
	@Test
	public void testInitializer() {
		ChessGame game=new ChessGame();
		assertEquals(8,game.getGameBoard().getHeight());
		assertTrue(("Queen").equals(game.getGameBoard().getChessPiece(0, 3).getType()));
		assertTrue(("Pawn").equals(game.getGameBoard().getChessPiece(6, 3).getType()));
		assertEquals(-1,game.getGameBoard().getChessPiece(6, 5).getPlayer());
		assertEquals(1,game.getGameBoard().getChessPiece(0, 5).getPlayer());
	}
	
	/*
	 * test for simple pawn move
	 * check for all updates of the board and the piece
	 */
	@Test
	public void testSimpleMove() {
		ChessGame game=new ChessGame();
		Player white = game.getWhite();
		boolean move=game.movePiece(white.getPawn2(), 4, 1,true);
		assertTrue(move);
		assert(null==game.getGameBoard().getChessPiece(6, 1));
		assert(null!=game.getGameBoard().getChessPiece(4, 1));
		assertTrue(!game.getGameBoard().getChessPiece(4, 1).isFirstMove());
		ChessPiece p = white.getPawn2();
		Position pPos=p.getPosition();
		assertEquals(4,pPos.x);
		assertEquals(1,pPos.y);
	}
	
	
	@Test
	public void testIllegalMove() {
		ChessGame game=new ChessGame();
		Player white = game.getWhite();
		Player black = game.getBlack();
		boolean move=game.movePiece(white.getPawn2(), 1, 1,true);
		assertTrue(!move);
		move=game.movePiece(white.getKing(), 6, 3,true);
		assertTrue(!move);
		move=game.movePiece(black.getQueen(), 0, 5,true);
		assertTrue(!move);
		move=game.movePiece(white.getPawn2(), 4, 1,true);
		assertTrue(move);
		move=game.movePiece(white.getPawn2(), 2, 1,true);
		assertTrue(!move);
		move=game.movePiece(black.getLeftKnight(), 2, 0,true);
		assertTrue(move);
		move=game.movePiece(black.getLeftKnight(), 2, 1,true);
		assertTrue(!move);
		move=game.movePiece(black.getPawn1(), 3, 0,true);
		assertTrue(!move);
	}
	
	
	@Test
	public void testCapture() {
		ChessGame game=new ChessGame();
		Player white = game.getWhite();
		Player black = game.getBlack();
		boolean move=game.movePiece(white.getPawn2(), 4, 1,true);
		move=game.movePiece(white.getPawn2(), 3, 1,true);
		move=game.movePiece(white.getPawn2(), 2, 1,true);
		move=game.movePiece(white.getPawn2(), 1, 1,true);
		assertTrue(!move);
		move=game.movePiece(white.getPawn2(), 1, 2,true);
		assertTrue(move);
		move=game.movePiece(white.getLeftBishop(), 5, 0,true);
		assertTrue(move);
		move=game.movePiece(white.getLeftBishop(), 1, 4,true);
		assertTrue(move);
	}
	
	/*
	 * test for King in check and not in check
	 */
	@Test
	public void testInCheck() {
		ChessGame game=new ChessGame();
		Player white = game.getWhite();
		boolean move=game.movePiece(white.getPawn3(), 4, 2,true);
		move=game.movePiece(white.getQueen(), 4, 0,true);
		assertTrue(move);
		move=game.movePiece(white.getQueen(), 1, 3,true);
		assertTrue(move);
		assertTrue(game.isInCheck(1));
		assertTrue(!game.isInCheck(-1));
	}
	
	
	@Test
	public void testCheckmate() {
		ChessGame game=new ChessGame();
		Player white = game.getWhite();
		Player black = game.getBlack();
		ChessBoard board=new ChessBoard();
		board.placeChessPiece(white.getKing(), 3, 5);
		white.getKing().setPosition(3, 5);
		board.placeChessPiece(black.getKing(), 3, 7);
		black.getKing().setPosition(3, 7);
		game.setGameBoard(board);
		boolean checkmate = game.isInCheckmate(1);
		assertTrue(!checkmate);
		board.placeChessPiece(white.getRightRook(), 7, 7);
		white.getRightRook().setPosition(7, 7);
		checkmate = game.isInCheckmate(1);
		assertTrue(checkmate);
		game.getGameBoard().placeChessPiece(black.getLeftRook(), 7, 3);
		black.getLeftRook().setPosition(7, 3);
		checkmate = game.isInCheckmate(1);
		assertTrue(!checkmate);
		game = new ChessGame();
		white = game.getWhite();
		black = game.getBlack();
		game.movePiece(white.getPawn6(), 5, 5,true);
		game.movePiece(white.getPawn7(), 4, 6,true);
		game.movePiece(black.getPawn5(), 3, 4,true);
		game.movePiece(black.getQueen(), 4, 7,true);
		checkmate=game.isInCheckmate(-1);
		assertTrue(checkmate);	
	}
	
	@Test
	public void testStalemate() {
		ChessGame game=new ChessGame();
		Player white = game.getWhite();
		Player black = game.getBlack();
		ChessBoard board=new ChessBoard();
		board.placeChessPiece(white.getKing(), 0, 0);
		white.getKing().setPosition(0, 0);
		board.placeChessPiece(white.getLeftBishop(), 0, 1);
		white.getLeftBishop().setPosition(0, 1);
		board.placeChessPiece(black.getKing(), 2, 1);
		black.getKing().setPosition(2, 1);
		boolean stalemate = game.isInStalemate(-1);
		game.setGameBoard(board);
		assertTrue(!stalemate);
		board.placeChessPiece(black.getRightRook(), 0, 7);
		black.getRightRook().setPosition(0, 7);
		stalemate = game.isInStalemate(-1);
		assertTrue(stalemate);
	}
	
	@Test
	public void testGameRecord(){
		ChessGame game=new ChessGame();
		Player white = game.getWhite();
		boolean move=game.movePiece(white.getPawn3(), 4, 2,true);
		move=game.movePiece(white.getQueen(), 4, 0,true);
		Stack<Move> moves = game.getGameRecord();
		assertEquals(2,moves.size());
		Move last = moves.pop();
		assertTrue(last.getKilledPiece()==null);
		assertTrue(last.getMovedPiece().getType().equals("Queen"));
		last = moves.pop();
		assertTrue(last.getKilledPiece()==null);
		assertTrue(last.getMovedPiece().getType().equals("Pawn"));
		assertTrue(last.isPreviousFirstMove());
		
	}
	@Test
	public void testUndo(){
		ChessGame game=new ChessGame();
		Player white = game.getWhite();
		boolean move=game.movePiece(white.getPawn3(), 4, 2,true);
		game.undo();
		assertEquals(0,game.getGameRecord().size());
		assertTrue(game.getGameBoard().getChessPiece(4, 2)==null);
		assertTrue(white.getPawn3().isFirstMove());
		move=game.movePiece(white.getPawn3(), 4, 2,true);
		move=game.movePiece(white.getQueen(), 4, 0,true);
		game.undo();
		assertTrue(game.getGameBoard().getChessPiece(4, 0)==null);
		move=game.movePiece(white.getQueen(), 4, 0,true);
		move=game.movePiece(white.getQueen(), 1, 0,true);
		assertTrue(move);
		game.undo();
		assertEquals(1,game.getGameBoard().getChessPiece(1, 0).getPlayer());
		assertTrue(game.getGameBoard().getChessPiece(1, 0).getType().equals("Pawn"));
		game.undo();
		game.undo();
		Move noRecord = game.undo();
		assertTrue(noRecord==null);
	}
	

}
