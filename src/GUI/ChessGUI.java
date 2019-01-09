package GUI;

/**
 * This GUI code are written after I learned and understood the example code at
 * https://stackoverflow.com/questions/21142686/making-a-robust-resizable-swing-chess-gui
 * The idea of keeping a JButton[][] and modify the icon to place pieces on the board
 * was learned from the stackoverflow, but implemented by me.
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Chess.ChessBoard;
import Chess.ChessGame;
import Chess.Move;
import Chess.Position;
import ChessPiece.ChessPiece;

 
 
public class ChessGUI implements ActionListener{
 
	private static IndexedJButton[][] board = new IndexedJButton[8][8];
	private static IndexedJButton selected;
	private static IndexedJButton[] twoClick;
	private static JButton start;
	private static List<Position> accessibleArea;
	private static ChessGame game;
	private static int winner;
	private static boolean firstMessage;
	private static int currPlayer;
	private static String player1="player1";
	private static int player1Score=0;
	private static String player2="player2";
	private static int player2Score=0;
	private static JLabel player1Status=new JLabel(player1+" : "+player1Score+" ");
	private static JLabel player1Message=new JLabel("  ");
	private static JLabel player2Status=new JLabel(player2+" : "+player2Score+" ");
	private static JLabel player2Message=new JLabel("  ");
	
	
    public ChessGUI(boolean firstStart) throws IOException{
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            //silently ignore
        }
        JFrame window = new JFrame("Chess");
        window.setSize(800, 800);
        JPanel myPanel = initializePanel();
        addToolBar(myPanel);
        createEmptyBoard();
        ChessPanel(myPanel);
        window.setContentPane(myPanel);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (firstStart) {
			Image blackImg = ImageIO.read(getClass().getResource("../png/b_king.png"));
        	blackImg=blackImg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        	ImageIcon black = new ImageIcon(blackImg);
        	Image whiteImg = ImageIO.read(getClass().getResource("../png/w_king.png"));
        	whiteImg=whiteImg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        	ImageIcon white = new ImageIcon(whiteImg);
			player1=(String) JOptionPane.showInputDialog(null, "Black: ", "Black Player's Name",3,black,null,new String("Player1"));
			player2=(String) JOptionPane.showInputDialog(null, "White: ", "White Player's Name",3,white,null,new String("Player2"));
			updatePlayerText(player1Status,player1,player1Score);
			updatePlayerText(player2Status,player2,player2Score);
        }
    }
    
    /**
     * Create board[][] to hold the buttons in the board
     */
    private void createEmptyBoard() {
    	int color=1;
    	for (int i =0;i<8;i++) {
    		for (int j=0;j<8;j++) {
    			Position index = new Position(i,j);
    			IndexedJButton button = new IndexedJButton(index);
        		if (color%2==0) {
        			button.setBackground(new Color(204,119,34));
        		}
        		else {
        			button.setBackground(new Color(242, 180, 94));
        		}
        		
        		button.setOpaque(true);
        		button.setBorder(BorderFactory.createLineBorder(Color.red));
        		button.setBorderPainted(false);
        		
        		board[i][j]=button;
        		button.addActionListener(new ActionListener() {
        			@Override
            		public void actionPerformed(ActionEvent e) {
        				if (selected!=null) {
        					selected.setBorderPainted(false);
        				}
        				selected=button;
    					selected.setBorderPainted(true);
    					updateAccessibleArea();
    					twoClick[0]=twoClick[1];
    					twoClick[1]=selected;
        			}
        		});
        		color++;
    		}
    		color++;
    	}
    }
    
    /**
     * paint the borders of accessible area of selected piece to green
     */
    private static void updateAccessibleArea() {
    	
    	if (accessibleArea!=null) {
    		for (Position p : accessibleArea) {
    			board[p.x][p.y].setBorder(BorderFactory.createLineBorder(Color.red));
    			board[p.x][p.y].setBorderPainted(false);
    		}
    	}
    	ChessBoard gameBoard=game.getGameBoard();
    	ChessPiece piece=gameBoard.getChessPiece(selected.position.x, selected.position.y);
    	accessibleArea=new ArrayList<Position>();
    	if (piece==null) {
    		return;
    	}
    	accessibleArea=piece.accessibleArea(gameBoard);
    	for (Position p : accessibleArea) {
			board[p.x][p.y].setBorder(BorderFactory.createLineBorder(Color.green));
			board[p.x][p.y].setBorderPainted(true);
		}
    }
    
    /**
     * helper for updating message for players including their name and their score
     * @param playerText	: message container
     * @param player		: player name
     * @param playerScore	: player score
     */
    private static void updatePlayerText(JLabel playerText, String player, int playerScore) {
    	playerText.setText(player+" : "+playerScore+" ");
    }
    
    
    /**
     * add a JToolBar to myPanel.
     * @param myPanel : the main panel of the frame
     */
    private void addToolBar(JPanel myPanel) throws IOException {
    	JToolBar tools = new JToolBar();
    	startButton(tools);
    	tools.addSeparator();
    	tools.add(player1Status);
    	tools.add(player1Message);
    	forfeitButton(tools,1);
    	tools.addSeparator();
    	tools.add(player2Status);
    	tools.add(player2Message);
    	forfeitButton(tools,-1);
    	tools.addSeparator();
    	undoButton(tools);
        myPanel.add(tools, BorderLayout.NORTH);
    }
    /**
     * generate start new button and add it to tools
     * @param tools : button container
     */
	private void startButton(JToolBar tools) {
		start=new JButton("Start New");
    	start.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
				try {
					board[0][0].setIcon(getIcon("../png/b_rook.png"));
					board[0][1].setIcon(getIcon("../png/b_knight.png"));
	    			board[0][2].setIcon(getIcon("../png/b_bishop.png"));
	    			board[0][3].setIcon(getIcon("../png/b_queen.png"));
	    			board[0][4].setIcon(getIcon("../png/b_king.png"));
	    			board[0][5].setIcon(getIcon("../png/b_bishop.png"));
	    			board[0][6].setIcon(getIcon("../png/b_knight.png"));
	    			board[0][7].setIcon(getIcon("../png/b_rook.png"));
	    			
	    			board[7][0].setIcon(getIcon("../png/w_rook.png"));
	    			board[7][1].setIcon(getIcon("../png/w_knight.png"));
	    			board[7][2].setIcon(getIcon("../png/w_bishop.png"));
	    			board[7][3].setIcon(getIcon("../png/w_queen.png"));
	    			board[7][4].setIcon(getIcon("../png/w_king.png"));
	    			board[7][5].setIcon(getIcon("../png/w_bishop.png"));
	    			board[7][6].setIcon(getIcon("../png/w_knight.png"));
	    			board[7][7].setIcon(getIcon("../png/w_rook.png"));
	    			for(int i=0;i<8;i++) {
	    				board[1][i].setIcon(getIcon("../png/b_pawn.png"));
	    				board[6][i].setIcon(getIcon("../png/w_pawn.png"));
	    			}
	    			for(int i=2;i<6;i++) {
	    				for (int j=0;j<8;j++) {
	    					board[i][j].setIcon(null);
	    				}
	    			}
	    			start.setText("Restart");
	    			start.addActionListener(new ActionListener() {
	    				@Override
	    	    		public void actionPerformed(ActionEvent e) {
	    					winner=2;
	    				}
	    			});
				} catch (IOException e1) {
					e1.printStackTrace();
				}   			
    		}
    	});
    	tools.add(start);
	}
    
    /**
     * generate forfeit button for player and add it to tools
     * @param tools	:	button container
     * @param player:	player name
     */
    private void forfeitButton(JToolBar tools, int player) {
    	JButton forfeit=new JButton("Forfeit");	
    	forfeit.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			String forfeitPlayer = (player==-1)?player2:player1;
    			int confirmation = JOptionPane.showConfirmDialog(null, forfeitPlayer+", are you sure you want to forfeit?","Forfeit Confirmation",2,3);    			
    			if(confirmation==0) {
    				winner = (player==-1)?1:-1;
    			}
    		}
    	}); 
    	tools.add(forfeit);
    }
    
    /**
     * update the GUI board according to the game board
     * @throws IOException
     */
    private void updateBoard() throws IOException {
    	for (int i=0;i<8;i++) {
    		for (int j=0;j<8;j++) {
    			ChessPiece piece = game.getGameBoard().getChessPiece(i, j);
    			if (piece==null) {
    				board[i][j].setIcon(null);
    			}
    			else {
    				String iconPath="../png/";
    				if (piece.getPlayer()==-1) {
    					iconPath+="w_";
    				}
    				else {
    					iconPath+="b_";
    				}
    				String type = piece.getType().toLowerCase();
    				iconPath=iconPath+type+".png";
    				board[i][j].setIcon(getIcon(iconPath));
    			}
    		}
    	}
    }
    
    /**
     * add undo button to tools
     * @param tools	: button container
     */
    private void undoButton(JToolBar tools) {
    	JButton undo=new JButton("Undo");
    	tools.add(undo);
    	undo.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			Move last = game.undo();
    			if (last==null) {
    				JOptionPane.showMessageDialog(null, "Cannot undo any further", "Undo", JOptionPane.WARNING_MESSAGE);
    			}
    			else {
    				try {
    					if (currPlayer==1) {
    						player1Message.setText(" ");
    					}
    					else {
    						player2Message.setText(" ");
    					}
    					currPlayer=(currPlayer==1)?-1:1;
    					firstMessage=true;
    					
						updateBoard();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
    			}
    		}
    	});
    }
    
    /**
     * get ImageIcon from a path
     * @param path	:	relative path of where the icon is stored
     * @return	an ImageIcon according to the path
     * @throws IOException
     */
    private ImageIcon getIcon(String path) throws IOException {
    	if (path.equals("")) {
    		return null;
    	}
    	Image img = ImageIO.read(getClass().getResource(path));
    	img=img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
    	return new ImageIcon(img);
    }
    
    /**
     * place the buttons on the panel. 
     * @param myPanel : the main panel of the frame
     * @throws IOException
     */
    private void ChessPanel(JPanel myPanel) throws IOException {
    	JPanel chessPanel = new JPanel();
    	chessPanel.setPreferredSize(new Dimension(500,500));
    	chessPanel.setLayout(new GridLayout(0,8));
    	chessPanel.setBorder(new EmptyBorder(8,8,8,8));        
    	chessPanel.setBackground(Color.white);
    	for (int i =0;i<8;i++) {
    		for (int j=0;j<8;j++) {
    			chessPanel.add(board[i][j]);
    		}		
    	}
    	myPanel.add(chessPanel, BorderLayout.CENTER);
    }
    
    
    
    /**
     * create a set up main panel
     * @return myPanel
     */
    private JPanel initializePanel() {
        JPanel myPanel = new JPanel(new BorderLayout(3,3));
        myPanel.setPreferredSize(new Dimension(800,800));
        myPanel.setBorder(new EmptyBorder(8, 5, 5, 5));
        return myPanel;
    }
 
    public static void main(String[] args) throws IOException {
        new ChessGUI(true);
        while(true) {
        	game=new ChessGame();
        	ChessBoard chessBoard=game.getGameBoard();
        	winner=0;
        	twoClick=new IndexedJButton[2];
        	currPlayer = -1;
        	firstMessage=true;
        	while(winner==0) {
        		JLabel currMessage;
    			if (currPlayer==1) {
    				currMessage=player1Message;
    			}
    			else {
    				currMessage=player2Message;
    			}
    			if (firstMessage) {
    				if (game.isInCheck(currPlayer)) {
    					if (game.isInCheckmate(currPlayer)) {
    						winner=(currPlayer==1)?-1:1;
    						break;
    					}
    					else {
    						currMessage.setText("You are in check  ");
    					}
    				}
    				else {
    					if (game.isInStalemate(currPlayer)) {
    						winner=2;
    						break;
    					}
    					else {
    						currMessage.setText("Your Turn  ");
    					}
    				}
    				firstMessage=false;
    			}
    			//don't know how this work
    			System.out.flush();
        		if (twoClick[1]!=null && twoClick[0]!=null) {
        			//validate move
        			ChessPiece piece = chessBoard.getChessPiece(twoClick[0].position.x, twoClick[0].position.y);
        			if (piece!=null && piece.getPlayer()!=currPlayer) {
        				currMessage.setText("Illegal Move  ");
        				continue;
        			}
        			if (piece!=null) {
        				boolean move = game.movePiece(piece, twoClick[1].position.x, twoClick[1].position.y,true);
        				if (!move) {
        					currMessage.setText("Illegal Move  ");
        					continue;
        				}
        				else {
        					twoClick[1].setIcon(twoClick[0].getIcon());
        					twoClick[0].setIcon(null);
        					twoClick=new IndexedJButton[2];
        					currPlayer=(currPlayer==1)?-1:1;
        					currMessage.setText("  ");
        					firstMessage=true;
        				}
        			}		
        		}
        	}
        	endRound();
        }
        
    }
    
    /**
     * check winner for winning condition, update scores and restart game.
     */
	private static void endRound() {
		if(winner==1) {
			player1Score++;
			updatePlayerText(player1Status,player1,player1Score);
			JOptionPane.showMessageDialog(null, player1+" wins!", "Game Result", 2);
		}
		else if (winner==-1){
			player2Score++;
			updatePlayerText(player2Status,player2,player2Score);
			JOptionPane.showMessageDialog(null, player2+" wins!", "Game Result", 2);
		}
		else {
			JOptionPane.showMessageDialog(null, "Tie!", "Game Result", 2);
		}
		start.doClick();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}