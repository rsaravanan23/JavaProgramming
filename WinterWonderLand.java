//Name: Rishi Saravanan
//Date: 2/13/22
//Program Name: WinterWonderLand
//Program Goal: Draw a winterWonderLand with snowmen and moonlit background using GUI and based on input. 
import java.awt.*;

import javax.swing.*;
import java.util.Scanner;
public class WinterWonderLand extends JFrame {
	
	public static void main(String[] args) {
		WinterWonderLand w1 = new WinterWonderLand();
	}
	
	public WinterWonderLand() {
		super("WinterWonderLand");
		setSize(800, 800);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(200, 100);
		setResizable(true);
		FirstPanel pan = new FirstPanel();
		setContentPane(pan);
		setVisible(true);
	}
}

class FirstPanel extends JPanel {
	int start = -1; 
	boolean moon = false; 
	private static final long serialVersionUID = 1L;
	Scanner s2 = new Scanner(System.in);
	Color bgColor = Color.WHITE;

	public FirstPanel() {
		System.out.print("What background color would you like? ");
		String color = s2.next().toUpperCase();
		System.out.print("How many snowman would you like(1-4)? ");
		start = s2.nextInt(); 
		System.out.print("Would you like a moonlit night, yes(1) or no(2)? ");
		int answer = s2.nextInt(); 
		if(answer == 1) moon = true; 
		if (color.equals("BLUE")) {
			bgColor = Color.BLUE;
		} else if (color.equals("YELLOW")) {
			bgColor = Color.YELLOW;
		} else if (color.equals("RED")) {
			bgColor = Color.RED;
		} else if (color.equals("BLACK")) {
			bgColor = Color.BLACK;
		} else if (color.equals("GREEN")) {
			bgColor = Color.GREEN;
		} else if (color.equals("PINK")) {
			bgColor = Color.PINK;
		} else if (color.equals("ORANGE")) {
			bgColor = Color.ORANGE;
		} else if (color.equals("MAGENTA")) {
			bgColor = Color.MAGENTA;
		} else if (color.equals("WHITE")) {
			bgColor = Color.WHITE;
		} else if(color.equals("GRAY")) { 
			bgColor = Color.GRAY; 
		} else if(color.equals("CYAN")) { 
			bgColor = Color.CYAN; 
		}
		System.out.println("\nHere is your " + color.charAt(0) + color.substring(1,color.length()).toLowerCase() + " background with " + start + " snowmen."); 
		setBackground(bgColor);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawSnowman(g);
		drawStars(g);
		if(moon == true) { 
			drawMoon(g); 
		}
	}
	
	public void drawSnowman(Graphics g) {
		g.setColor(Color.WHITE);
		int xPos1 = 70;
		int xPos2 = 57;
		int xPos3 = 43;

		for (int i=1; i <= start; i++) {
			g.fillOval(xPos1, 350, 50, 50);
			drawEyes(g, xPos1);
			g.fillOval(xPos2, 400, 80, 80);
			drawButtons(g, xPos2, 400);
			g.fillOval(xPos2, 480, 80, 80);
			g.fillOval(xPos3, 560, 110, 80);

			xPos1 = 68 + 180*i;
			xPos2 = 55 + 180*i;
			xPos3 = 41 + 180*i;
		}
	}

	public void drawEyes(Graphics g, int xPos1) {
		g.setColor(Color.BLACK);
		g.fillOval(xPos1+10, 363, 12, 12);
		g.fillOval(xPos1+30, 363, 12, 12);
		g.drawLine(58, 440, 20, 500);
		g.drawLine(138, 440, 178, 500);
		g.drawLine(82, 385, 108, 385);
		if(start >= 2) { 
			g.drawLine(261, 385, 287, 385);
			g.drawLine(236, 440, 206, 500);
			g.drawLine(316, 440, 356, 500);
			if(start >= 3) { 
				g.drawLine(441, 385, 467, 385);
				g.drawLine(415, 440, 385, 500);
				g.drawLine(494, 440, 534, 500);
				if(start == 4) { 
					g.drawLine(621, 385, 646, 385);
					g.drawLine(594, 440, 564, 500);
					g.drawLine(672, 440, 712, 500);
				}
			}
		}
		g.setColor(Color.WHITE);
	}
	public void drawStars(Graphics g) { 
		int [] arrX = {0, 5, 8, 6, 8, 5, 0, -5, -8, -6, -8, -5};
		  int [] arrY = {0, 5, 5, 10, 15, 15, 20, 15, 15, 10, 5, 5};
		  int[] startOfX = {80, 120, 160, 280, 320, 440, 520, 560};
		  int[] startOfY = {110, 230, 150, 30, 250, 150, 70, 230};
		for (int j = 0; j < 8; j ++)
	    {
			for ( int i = 0; i < 12; i ++)
			{
				arrX[i] = startOfX[j] + arrX[i];
				arrY[i] = startOfY[j] + arrY[i];
			}		
			g.drawPolygon(arrX, arrY, 12);
			g.setColor(Color.WHITE);
			g.fillPolygon(arrX, arrY, 12);
			for ( int i = 0; i < 12; i ++)
			{
				arrX[i] = arrX[i] - startOfX[j];
				arrY[i] = arrY[i] - startOfY[j];
			}
		}
	}

	public void drawButtons(Graphics g, int xPos2, int yPos2) {
		g.setColor(Color.BLACK);
		g.fillOval(xPos2+32, yPos2+15, 12, 12);
		g.fillOval(xPos2+32, yPos2+35, 12, 12);
		g.fillOval(xPos2+32, yPos2+55, 12, 12);
		g.setColor(Color.WHITE);
	}

	public void drawMoon(Graphics g) {
		g.fillArc(600, 0, 190, 150, 270, 180);
		g.setColor(bgColor); 
		g.fillArc(650, 0, 90, 150, 270, 180);
	}
}