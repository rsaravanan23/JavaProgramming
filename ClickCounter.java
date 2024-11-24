//Name: Rishi Saravanan
//Date: 3/4/22
//Program Name: ClickCounter.java
//Program Goal: Count number of clicks and mouse outside and inside using mouseListener
import java.awt.Color;
import java.awt.*;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClickCounter extends JFrame {
	public ClickCounter() {
	}

	public static void main(String[] args) {
		ClickCounter hg2016 = new ClickCounter();
		hg2016.run();
	}

	public void run() {
		JFrame frame = new JFrame("ClickCounter.java");
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // or JFrame.EXIT_ON_CLOSE); or frame.EXIT_ON_CLOSE);
		frame.setLocation(400, 50);
		frame.setResizable(true);
		HGPanel hgp = new HGPanel();
		frame.setContentPane(hgp); // OR frame.getContentPane().add(p_in);
		frame.setVisible(true);
	}
}

class HGPanel extends JPanel implements MouseListener {
	private boolean entered;
	private int clickCounter;
	private int enterCounter;

	public HGPanel() // constructor -sets the panel to listen for mouse events on itself (using
						// "this" )
	{
		clickCounter = 0;
		enterCounter = 0;
		entered = false;
		addMouseListener(this);
		setBackground(Color.BLUE);
	}

	public void paintComponent(Graphics g) {
		int increaseClick = 0;
		int increaseCounter = 0; 
		super.paintComponent(g);
		Font f = new Font("Serif", Font.BOLD, 20);
		g.setFont(f);
		g.setColor(Color.BLACK);
		if (entered == true) {
			setBackground(Color.BLUE);
			g.drawString("Inside", 10, 50);
			
		} else {
			setBackground(Color.RED);
			g.drawString("Outside", 10, 50);
		}
		for (int x = 0; x < clickCounter / 5; x++) {
			g.setColor(Color.GREEN);
			g.fillOval(30 + increaseClick, 530, 20, 20);
			increaseClick += 30;
		}
		
		g.setColor(Color.BLACK);
		g.drawString("Click Counter: " + clickCounter, 30, 475);
		g.drawString("Inside Counter: " + enterCounter, 400, 475);
		g.setColor(Color.YELLOW); 
		for(int y= 0; y < enterCounter /6; y++) { 
			int[]arr1 = {30+increaseCounter,37+increaseCounter,40+increaseCounter,44+increaseCounter,50+increaseCounter, 44+increaseCounter,47+increaseCounter,40+increaseCounter, 34+increaseCounter, 36+increaseCounter}; 
			int[]arr2 = {500, 500, 492, 500, 500, 505, 512, 507, 512, 505};
			g.fillPolygon(arr1, arr2, 10);
			increaseCounter+= 30;
			
		}

	}

	public void mouseEntered(MouseEvent e) {
		entered = true;
		enterCounter++;
		repaint();
	}

	public void mouseExited(MouseEvent e) {
		entered = false;
		repaint();
	}

	public void mousePressed(MouseEvent evt) {
	}

	public void mouseClicked(MouseEvent evt) {
		clickCounter++;
		repaint();
	}

	public void mouseReleased(MouseEvent evt) {
	}
}