//Name: Rishi Saravanan
//Date: 3/11/22
//Program Name: AllHandlers
//Program Goal: Use all handlers for mouse and keylistener
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AllHandlers extends JFrame {
	
	public static void main(String[] args) {
		AllHandlers hg2016 = new AllHandlers();
	}

	public AllHandlers() {
		super("AllHandlers");
		setSize(600, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(200, 100);
		setResizable(true);
		newPanel pan = new newPanel();
		setContentPane(pan);
		setVisible(true);
	}
}

class newPanel extends JPanel implements MouseListener, KeyListener, MouseMotionListener {
	
	int[] x = { 50, 75, 25 };
	int[] y = { 400, 450, 450 };
	boolean mouseEntered = false;
	boolean mouseDragged = false;
	boolean mouseMoved = false;
	boolean mouseExited = false;
	boolean mouseClicked = false;
	boolean mousePressed = false;
	boolean mouseReleased = false;
	boolean letter = false;
	boolean character = false;
	boolean number;
	boolean control = false;
	boolean keyCode = false;
	boolean colorChange = false;
	int carX;
	int carY;
	private Timer cartimer1;

	public newPanel() {
		carX = 10;
		carY = 400;
		CarMover carmover1 = new CarMover();
		cartimer1 = new Timer(10, carmover1);
		cartimer1.start();
		addMouseListener(this);
		addKeyListener(this);
		addMouseMotionListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int z = 0;
		for (int x = 0; x < 4; x++) {
			g.drawRect(20 + z, 100, 100, 30);
			z += 100;
		}
		z = 0;
		for (int y = 0; y < 4; y++) {
			g.drawRect(20 + z, 150, 100, 30);
			z += 100;

		}
		z = 0;
		for (int a = 0; a < 4; a++) {
			g.drawRect(20 + z, 250, 100, 30);
			z += 100;
		}
		g.setColor(Color.BLACK);
		
		g.drawString("Mouse", 30, 90);
		g.drawString("Keyboard", 30, 240);
		g.drawString("Moved", 50, 120);
		g.drawString("Dragged", 150, 120);
		g.drawString("Entered", 250, 120);
		g.drawString("Exited", 350, 120);
		g.drawString("Clicked", 50, 170);
		g.drawString("Pressed", 150, 170);
		g.drawString("Released", 250, 170);
		g.drawString("Letter", 50, 270);
		g.drawString("Number", 150, 270);
		g.drawString("Character", 250, 270);
		g.drawString("KeyCode", 350, 270);
		if (colorChange == true) {
			g.setColor(Color.BLACK);
		} else { 
			g.setColor(Color.RED);
		}
		if (mouseEntered == true) {

			g.drawString("Entered", 250, 120);
		}
		if (mouseExited == true) {

			g.drawString("Exited", 350, 120);
		}
		if (mouseMoved == true) {

			g.drawString("Moved", 50, 120);
		}
		if (mouseClicked == true) {
			g.drawString("Clicked", 50, 170);
		}
		if (mousePressed == true) {
			g.drawString("Pressed", 150, 170);
		}
		if (mouseReleased == true) {

			g.drawString("Released", 250, 170);
		}
		if (mouseDragged == true) {
			g.drawString("Dragged", 150, 120);
		}
		if (letter == true) {
			g.drawString("Letter", 50, 270);
		}
		if (number == true) {
			g.drawString("Number", 150, 270);
		}
		if (character == true) {
			g.drawString("Character", 250, 270);
		}
		if (keyCode == true) {
			g.drawString("KeyCode", 350, 270);
		}
		g.setColor(Color.orange);
		g.fillPolygon(x, y, 3);
	}

	class CarMover implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// car XY location
			for (int a = 0; a < 3; a++) {
				x[a] = x[a] + 1;
				if (x[2] > 490 || control == true) {
					x[0] = 25;
					x[1] = 50;
					x[2] = 0;
					control = false;
				}

			}
			repaint();
			grabFocus();
		}
	}

	public void mouseDragged(MouseEvent e) {
		mouseDragged = true;
		repaint();
	}

	public void mouseEntered(MouseEvent e) {
		mouseEntered = true;
		repaint();
	}

	public void mouseClicked(MouseEvent e) {
		mouseClicked = true;
		repaint();
	}

	public void mouseReleased(MouseEvent e) {
		mouseReleased = true;
		repaint();
	}

	public void mouseExited(MouseEvent e) {
		mouseExited = true;
		repaint();
	}

	public void mousePressed(MouseEvent e) {
		mousePressed = true;
	}

	public void mouseMoved(MouseEvent e) {
		mouseMoved = true;
		repaint();
	}

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		char a = e.getKeyChar();
		if (a == 'R' || a == 'r') {
			colorChange = true;
		} else if (((int) a > 64 && (int) a < 91) || ((int) a > 96 && (int) a < 123)) {
			letter = true;
		} else if (((int) a > 47 && (int) a < 58)) {
			number = true;
		} else {
			character = true;
		}
		System.out.println((int) e.getKeyCode());
		switch (e.getKeyCode()) {
		case (KeyEvent.VK_CONTROL):
			control = true;
		case KeyEvent.VK_UP:
			keyCode = true;
			break;
		case KeyEvent.VK_DOWN:
			keyCode = true;
			break;
		case KeyEvent.VK_LEFT:
			keyCode = true;
			break;
		case KeyEvent.VK_RIGHT:
			keyCode = true;
			break;
		case KeyEvent.VK_SHIFT:
			keyCode = true;
			break;
		case KeyEvent.VK_TAB:
			keyCode = true;
			break;
		case KeyEvent.VK_DELETE:
			keyCode = true;
			break;
		default:
			keyCode = true;
		}
		repaint();
	}
	public void keyReleased(KeyEvent e) {
	}
}