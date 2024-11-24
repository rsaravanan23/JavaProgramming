//Name: Rishi Saravanan
//Date: 3/4/22
//Program Name: ClickCounter.java
//Program Goal: Count number of clicks and mouse outside and inside using mouseListener
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessGame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChessGame() {
	}

	public static void main(String[] args) {
		ChessGame hg2016 = new ChessGame();
		hg2016.run();
	}

	public void run() {
		JFrame frame = new JFrame("ChessGame.java");
		frame.setSize(600, 620);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // or JFrame.EXIT_ON_CLOSE); or frame.EXIT_ON_CLOSE);
		frame.setLocation(400, 50);
		frame.setResizable(true);
		NBA hgp = new NBA();
		frame.setContentPane(hgp); // OR frame.getContentPane().add(p_in);
		frame.setVisible(true);
	}
}

class NBA extends JPanel implements MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int[][] currentPositions = new int[8][8];
	
	public NBA() // constructor -sets the panel to listen for mouse events on itself (using
						// "this" )
	{
		
	}
	

	public void paintComponent(Graphics g) {
		Image blackPawn = new ImageIcon("/Users/butterfleye/Desktop/pawn.png").getImage();
		Image whitePawn = new ImageIcon("/Users/butterfleye/Desktop/pawn2.png").getImage();
		Image whiteRook = new ImageIcon("/Users/butterfleye/Desktop/rook2.png").getImage();
		Image blackRook = new ImageIcon("/Users/butterfleye/Desktop/rook.png").getImage();

		int start = 1; 
		int rows = 0; 
		g.setColor(Color.BLACK); 
		for(int x = 0; x < 600; x+=75) { 
			g.drawLine(x, 0, x, 600);
			g.drawLine(0, x, 600, x);
		}
		int y = 0;
		while(rows < 8) { 
			for(int x = 0; x < 600; x+= 75) { 
				if(start % 2 == 1) { 
					g.setColor(Color.BLACK);
				} else { 
					g.setColor(Color.WHITE);
				}
				g.fillRect(x, y, 75, 75);
				start++;
				if(rows == 1) { 
					g.drawImage(blackPawn, x,y,75, 70, null);
				}
				if(rows == 6) { 
					g.drawImage(whitePawn, x,y,75, 70, null);
				}
				if((rows == 0 && x == 0) || (rows == 0 && x == 525)) { 
					g.drawImage(blackRook, x,y,75, 70, null);
				}
				if((rows == 7 && x == 0) || (rows == 7 && x == 525)) { 
					g.drawImage(whiteRook, x,y,75, 70, null);
				}
			}
			if(rows % 2 == 0) { 
				start = 0; 
			} else { 
				start = 1;
			}
			rows++;
			y+= 75;
		}	
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent evt) {
	}

	public void mouseClicked(MouseEvent evt) {
		
	}

	public void mouseReleased(MouseEvent evt) {
		
	}
}