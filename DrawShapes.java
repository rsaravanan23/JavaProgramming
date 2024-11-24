//Name: Rishi Saravanan
//Date: 2/8/21
//Program Name: DrawShapes 
//Program Goal: Draw shapes and trace out your name using GUI
import java.awt.*;
import javax.swing.*;
public class DrawShapes extends JFrame {
	public static void main(String[] args) {
		DrawShapes d1 = new DrawShapes();
	}

	public DrawShapes() {
		super("Draw Shapes");
		setSize(600, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(200, 100);
		setResizable(true);
		Panel pan = new Panel();
		setContentPane(pan);
		setVisible(true);
	}
}

class Panel extends JPanel {
	public Panel() {
		setBackground(Color.YELLOW);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < 600; i += 20) {
			g.setColor(Color.BLACK);
			g.drawLine(i, 0, i, 600);
			g.drawLine(0, i, 600, i);
		}
		g.drawRect(220, 60, 140, 80);
		g.setColor(new Color(165, 42, 42));
		g.fillRect(220, 60, 140, 80);
		g.setColor(new Color(125, 32, 32));
		((Graphics2D) g).setStroke(new BasicStroke(5));
		g.drawRect(220, 60, 140, 80);
		g.setColor(new Color(165, 42, 42));
		
		g.drawOval(100, 60, 60, 60);
		g.setColor(new Color(165, 42, 42));
		g.fillOval(100, 60, 60, 60);
		g.setColor(new Color(125, 32, 32));
		((Graphics2D) g).setStroke(new BasicStroke(5));
		g.drawOval(100, 60, 60, 60);
		g.setColor(new Color(165, 42, 42));
		

		((Graphics2D) g).setStroke(new BasicStroke(4));
		g.drawLine(120, 180, 120, 280);
		g.drawLine(120, 180, 200, 180);
		g.drawLine(200, 180, 200, 240);
		g.drawLine(200, 240, 120, 240);
		g.drawLine(120, 240, 200, 280);
		g.drawLine(220, 280, 300, 280);
		g.drawLine(260, 280, 260, 180);
		g.drawLine(220, 180, 300, 180);
		g.drawLine(400, 180, 320, 180);
		g.drawLine(320,180, 320, 230);
		g.drawLine(320, 230, 400, 230);
		g.drawLine(400,230, 400 ,280); 
		g.drawLine(400, 280, 320, 280); 
		g.drawOval(120, 320, 140, 100);
		g.fillOval(120, 320, 140, 100);
		g.setColor(Color.BLACK);
		Font sansSerif = new Font("SansSerif" , Font.PLAIN, 20); 
		g.setFont(sansSerif);
		g.drawString("DrawShapes.java", 280, 40);
		g.setColor(new Color(165, 42, 42));
		g.drawArc(320 ,320 ,120, 80 ,270, 180); 

	}
}