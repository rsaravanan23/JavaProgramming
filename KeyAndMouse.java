//Name: Rishi Saravanan
//Date: 3/9/22
//Program Name: KeyAndMouse
//Program Goal- Use mouseListener and keyListener
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;

public class KeyAndMouse{

	JFrame keyFrame;
	JFrame mouseFrame;
	DrawingPanel canvas2;
	MousePanel canvas1;
	Scanner s1 = new Scanner(System.in);

	public static void main(String[] args) {
		KeyAndMouse kt = new KeyAndMouse();
		kt.Run();
	} // end main

	public void Run() {
		System.out.println("Would you like to run mouse(1) or key(2)? ");
		int choice = s1.nextInt();
		if (choice == 1) {
			System.out.println("Here you go.");
			
			mouseFrame = new JFrame("SimpleJFrame");
			mouseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			canvas1 = new MousePanel();
			mouseFrame.getContentPane().add(canvas1, BorderLayout.CENTER); 
			canvas1.addMouseListener(canvas1);
			canvas1.addMouseMotionListener(canvas1);

			mouseFrame.setSize(500, 500);
			mouseFrame.setVisible(true);
		} else if (choice == 2) {
			System.out.println("Here you go.");
			keyFrame = new JFrame("KeyTimer");
			keyFrame.setSize(600, 600);
			keyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			canvas2 = new DrawingPanel();
			keyFrame.getContentPane().add(canvas2);

			keyFrame.setVisible(true);
		} else {
			System.out.println("Invalid option. Please enter 1 or 2");
		}

	}
}

class MousePanel extends JPanel implements MouseListener, MouseMotionListener 
{
	private int xloc, yloc; 
	private int width, height; 
	private boolean dragging;
	private int xMouse, yMouse; 
	private Rectangle rect;
	private boolean clicked;

	public MousePanel() 
	{
		xloc = yloc = 100;
		dragging = false;
		xMouse = yMouse = 0;
		clicked = false;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g); // execute the superclass method first
		width = getWidth();   // width of JPanel
		height = getHeight();  // height of JPanel
		setBackground(Color.white);
		if(clicked) { 
			g.setColor(Color.PINK);
			clicked = false;
		} else { 
			g.setColor(Color.BLUE);
		}
		g.fillRect(xloc, yloc, 150, 150);
		int yoffset = 24;
		rect = new Rectangle(xloc, yloc+yoffset, 150, 150);
		g.setColor(Color.BLACK);
		if(dragging) { 
			g.drawString("Dragging", 10, 40);
		} else { 
			g.drawString("Not Dragging", 10, 40);
		}

	} // end paintComponent

	public void mousePressed (MouseEvent e) {
		xMouse = e.getX();
		yMouse = e.getY();
		// determine if mouse is pressed inside drawn rectangle
		if (rect.contains(e.getX(), e.getY())) {
			dragging = true;
		}

	}
	public void mouseReleased (MouseEvent e) {
		////////////////////////////////////////
		// Stop dragging
		dragging = false;  // stop dragging
		repaint(); // repaint when dragging

	}
	public void mouseClicked (MouseEvent e) {}
	public void mouseEntered (MouseEvent e) {}
	public void mouseExited (MouseEvent e) {}
	public void mouseMoved (MouseEvent e) { 
		if (!rect.contains(e.getX(), e.getY())) {
			dragging = false;
		} else { 
			clicked = true;
		}
		repaint(); // repaint when dragging
	}
	public void mouseDragged (MouseEvent e) {
		if (dragging) {
			xloc = xloc + (e.getX() - xMouse);
			yloc = yloc + (e.getY() - yMouse);
			xMouse = e.getX();  // reset mouse to new location
			yMouse = e.getY();
		} 
		repaint();
	}
} 


class DrawingPanel extends JPanel implements KeyListener {
	private int ballX, ballY;
	private int carX1, carY1, carX2;
	private boolean ballMoveIt; // starts and stops ball movement
	private boolean carMoveIt1;
	private boolean carMoveIt2;
	// starts and stops car movement
	private Timer balltimer, cartimer1, cartimer2;
	boolean shiftClicked = false;
	boolean upClicked = false;
	boolean downClicked = false;
	int speed1 = 0;
	int speed2 = 0;
	int ballSpeed = 0;

	public DrawingPanel() {
		ballX = 200;
		ballY = 10;
		carX1 = 10;
		carY1 = 400;
		ballMoveIt = true;
		carMoveIt1 = true;
		carMoveIt2 = true;
		addKeyListener(this);
		
		// create timer for animation of ball
		BallMover ballmover = new BallMover();
		balltimer = new Timer(5, ballmover);
		balltimer.start();
		
		CarMover carmover1 = new CarMover();
		CarMover carmover2 = new CarMover();
		
		cartimer1 = new Timer(10, carmover1);
		cartimer1.start();
		
		cartimer2 = new Timer(10, carmover2);
		cartimer2.start();

	}

	class BallMover implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// ball XY location
			ballY++;
			if (ballSpeed <= 0) {
				ballSpeed = 0;
			}
			if(ballMoveIt == true) { 
				ballY += ballSpeed;
			}

			if (ballY > 470) {
				ballY = 10;
				ballX = (int) (Math.random() * 470) + 1;
			}
			repaint();
			grabFocus();
		}
	} // end BallMover

	class CarMover implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (carMoveIt2 == true) { // car shouldn't move
				carX2++;
				carX2+= speed2;
			}
			if (carMoveIt1 == true) {
				// car XY location
				carX1++;
				carX1 += speed1;
			}
		
			if (speed1 <= 0) {
				speed1 = 0;
			}
			if (speed2 <= 0) {
				speed2 = 0;
			}

			if (carX1 > 490)
				carX1 = 0;
			if (carX2 > 490)
				carX2 = 0;
			repaint();
			grabFocus();
		}
	} // end CarMover

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.gray);

		g.setColor(Color.red);
		g.setFont(new Font("Serif", Font.BOLD, 14));
		g.drawString("Press any number to stop/start the ball", 100, 50);
		g.drawString("Press any vowel to stop/start car 1", 100, 100);
		g.drawString("Shift key changes ball color.", 100, 150);
		g.drawString("Up down changes car1 speed. Left and right changes ball speed.", 100, 200);
		g.drawString("Car2 start/stops when letter from zxcvbnm is pressed", 100, 250);
		g.drawString("f makes car2 faster, s makes car2 slower", 100, 300);




		// paint ball
		if (shiftClicked == true) {
			g.setColor(Color.green);
		} else {
			g.setColor(Color.pink);
		}
		g.fillOval(ballX, ballY, 30, 30);

		// paint car
		g.setColor(Color.orange);
		g.fillOval(carX1 + 2, carY1, 20, 20); // back wheel
		g.fillOval(carX1 + 40, carY1, 20, 20); // front wheel
		g.fillRect(carX1, carY1 - 20, 60, 20); // car body
		g.fillRect(carX1 + 10, carY1 - 35, 40, 15); // car top

		g.setColor(Color.blue);
		g.fillOval(600 - (carX2 + 2), carY1, 20, 20); // back wheel
		g.fillOval(600 - (carX2 + 40), carY1, 20, 20); // front wheel
		g.fillRect(600 - (carX2 + 40), carY1 - 20, 60, 20); // car body
		g.fillRect(600 - (carX2 + 28), carY1 - 35, 40, 15); // car top

	} // end paintComponent

	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		switch (c) {
		case 'a':
			if (carMoveIt1)
				cartimer1.stop();
			else
				cartimer1.start();
			carMoveIt1 = !carMoveIt1;
			break;
		case 'e':
			if (carMoveIt1)
				cartimer1.stop();
			else
				cartimer1.start();
			carMoveIt1 = !carMoveIt1;
			break;
		case 'i':
			if (carMoveIt1)
				cartimer1.stop();
			else
				cartimer1.start();
			carMoveIt1 = !carMoveIt1;
			break;
		case 'o':
			if (carMoveIt1)
				cartimer1.stop();
			else
				cartimer1.start();
			carMoveIt1 = !carMoveIt1;
			break;
		case 'u':
			if (carMoveIt1)
				cartimer1.stop();
			else
				cartimer1.start();
			carMoveIt1 = !carMoveIt1;
			break;
		case '1':
			if (ballMoveIt)
				balltimer.stop();
			else
				balltimer.start();
			ballMoveIt = !ballMoveIt;
			break;
		case '2':
			if (ballMoveIt)
				balltimer.stop();
			else
				balltimer.start();
			ballMoveIt = !ballMoveIt;
			break;
		case '3':
			if (ballMoveIt)
				balltimer.stop();
			else
				balltimer.start();
			ballMoveIt = !ballMoveIt;
			break;
		case '4':
			if (ballMoveIt)
				balltimer.stop();
			else
				balltimer.start();
			ballMoveIt = !ballMoveIt;
			break;
		case '5':
			if (ballMoveIt)
				balltimer.stop();
			else
				balltimer.start();
			ballMoveIt = !ballMoveIt;
			break;
		case '6':
			if (ballMoveIt)
				balltimer.stop();
			else
				balltimer.start();
			ballMoveIt = !ballMoveIt;
			break;
		case '7':
			if (ballMoveIt)
				balltimer.stop();
			else
				balltimer.start();
			ballMoveIt = !ballMoveIt;
			break;
		case '8':
			if (ballMoveIt)
				balltimer.stop();
			else
				balltimer.start();
			ballMoveIt = !ballMoveIt;
			break;
		case '9':
			if (ballMoveIt)
				balltimer.stop();
			else
				balltimer.start();
			ballMoveIt = !ballMoveIt;
			break;
		case 'z':
			if (carMoveIt2)
				cartimer2.stop();
			else
				cartimer2.start();
			carMoveIt2 = !carMoveIt2;
			break;
		case 'x':
			if (carMoveIt2)
				cartimer2.stop();
			else
				cartimer2.start();
			carMoveIt2 = !carMoveIt2;
			break;
		case 'c':
			if (carMoveIt2)
				cartimer2.stop();
			else
				cartimer2.start();
			carMoveIt2 = !carMoveIt2;
			break;
		case 'v':
			if (carMoveIt2)
				cartimer2.stop();
			else
				cartimer2.start();
			carMoveIt2 = !carMoveIt2;
			break;
		case 'b':
			if (carMoveIt2)
				cartimer2.stop();
			else
				cartimer2.start();
			carMoveIt2 = !carMoveIt2;
			break;
		case 'n':
			if (carMoveIt2)
				cartimer2.stop();
			else
				cartimer2.start();
			carMoveIt2 = !carMoveIt2;
			break;
		case 'm':
			if (carMoveIt2)
				cartimer2.stop();
			else
				cartimer2.start();
			carMoveIt2 = !carMoveIt2;
			break;
		case 'f':
			speed2++; 
			break;
		case 's': 
			speed2--;
			break;
		}
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SHIFT:
			shiftClicked = !shiftClicked;
			break;
		case KeyEvent.VK_UP:
			speed1++;
			break;
		case KeyEvent.VK_DOWN:
			speed1--;
			break;
		case KeyEvent.VK_LEFT:
			ballSpeed--;
			break;
		case KeyEvent.VK_RIGHT:
			ballSpeed++;
			break;
		}
	}

	public void keyReleased(KeyEvent e) {

	}
}