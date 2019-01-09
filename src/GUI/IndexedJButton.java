package GUI;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import Chess.Position;

public class IndexedJButton extends JButton {
	
	private static final long serialVersionUID = 3883413395642890052L;
	
	Position position;
	
	public IndexedJButton(Position index) {
		this.position=index;
	}
	
	
}
