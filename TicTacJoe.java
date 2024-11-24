//Name: Rishi Saravanan
//Date: 4/5/22
//Program Name: TicTacJoe.java
//Program Goal: Use grid and border layout
import java.awt.*;
import javax.swing.*;
public class TicTacJoe extends JFrame {
	private static final long serialVersionUID = 1L;
	public TicTacJoe() {
		super("TicTacJoe.java");
		setSize(600, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(400, 50);
		setResizable(true);
		Practice33 pc = new Practice33();
		setContentPane(pc);
		setVisible(true);
	}
	public static void main(String[] args) {
		TicTacJoe t1 = new TicTacJoe();
	}
}
class Practice33 extends JPanel {
	
	private static final long serialVersionUID = 1L;
	int numLeft = 1;
	int numRight = 1;
	int numTop = 1;
	int numBottom = 1;
	int leftChoice = 0; 
	int rightChoice = 0;
	JTextArea[] leftArray = new JTextArea[5];
	JTextArea[] rightArray = new JTextArea[5];
	JLabel[] topArray = new JLabel[5];
	JLabel[] bottomArray = new JLabel[5];
	JLabel tic = new JLabel("\n\n\n\n TIC");
	JLabel toe = new JLabel("\n\n\n\n TOE");
	JPanel p1 = new JPanel(new BorderLayout());
	JPanel p2 = new JPanel(new GridLayout(3, 3));
	JPanel p3 = new JPanel(new BorderLayout());
	JPanel p4 = new JPanel(new GridLayout(3, 3));
	JPanel p5 = new JPanel(new BorderLayout());
	JPanel p6 = new JPanel(new GridLayout(3, 3));
	JPanel p7 = new JPanel(new BorderLayout());
	JPanel p8 = new JPanel(new GridLayout(3, 3));
	JPanel p9 = new JPanel(new BorderLayout());

	public Practice33() {
		runIt();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public void runIt() {
		setLayout(new GridLayout(3, 3));
		p2.setBackground(Color.YELLOW);
		p4.setBackground(new Color(100, 175, 250));
		p6.setBackground(Color.MAGENTA);
		p8.setBackground(Color.BLACK);
		p8.setForeground(Color.WHITE);
		for (int x = 0; x < 5; x++) {
			leftArray[x] = left();
		}
		for (int x = 0; x < 5; x++) {
			rightArray[x] = right();
		}
		for (int x = 0; x < 5; x++) {
			topArray[x] = top();
		}
		for (int x = 0; x < 5; x++) {
			bottomArray[x] = bottom();
		}
		for(int b = 0; b < 5; b++) { 
			if(b == 0) { 
				p1.add(rightArray[0], BorderLayout.EAST);
				p1.add(leftArray[0], BorderLayout.WEST);
				p1.add(topArray[0], BorderLayout.NORTH);
				p1.add(bottomArray[0], BorderLayout.SOUTH);	
				JLabel tic1 = new JLabel("TIC");
				tic1.setOpaque(true);
				tic1.setBackground(Color.GREEN);
				tic1.setHorizontalAlignment(JLabel.CENTER);
				p1.add(tic1, BorderLayout.CENTER); 
			} else if(b == 1) { 
				p3.add(rightArray[1], BorderLayout.EAST);
				p3.add(leftArray[1], BorderLayout.WEST);
				p3.add(topArray[1], BorderLayout.NORTH);
				p3.add(bottomArray[1], BorderLayout.SOUTH);
				JLabel toe = new JLabel("TOE");
				toe.setOpaque(true);
				toe.setBackground(Color.RED);
				p3.add(toe, BorderLayout.CENTER);
				toe.setHorizontalAlignment(JLabel.CENTER);
			} else if(b == 2) { 
				p5.add(rightArray[2], BorderLayout.EAST);
				p5.add(leftArray[2], BorderLayout.WEST);
				p5.add(topArray[2], BorderLayout.NORTH);	
				p5.add(bottomArray[2], BorderLayout.SOUTH);
				JLabel tic = new JLabel("TIC");
				tic.setOpaque(true);
				tic.setBackground(Color.GRAY);
				p5.add(tic, BorderLayout.CENTER);
				tic.setHorizontalAlignment(JLabel.CENTER);
			} else if(b == 3) { 
				p7.add(rightArray[3], BorderLayout.EAST);
				p7.add(leftArray[3], BorderLayout.WEST);
				p7.add(topArray[3], BorderLayout.NORTH);	
				p7.add(bottomArray[3], BorderLayout.SOUTH);
				JLabel toe = new JLabel("TOE");
				toe.setOpaque(true);
				toe.setBackground(Color.WHITE);
				p7.add(toe, BorderLayout.CENTER);
				toe.setHorizontalAlignment(JLabel.CENTER);				
			} else if(b == 4) { 
				p9.add(rightArray[4], BorderLayout.EAST);
				p9.add(leftArray[4], BorderLayout.WEST);
				p9.add(topArray[4], BorderLayout.NORTH);	
				p9.add(bottomArray[4], BorderLayout.SOUTH);	
				JLabel tic = new JLabel("TIC");
				tic.setOpaque(true);
				tic.setBackground(Color.ORANGE);
				p9.add(tic, BorderLayout.CENTER);
				tic.setHorizontalAlignment(JLabel.CENTER);
			}	
		}
		for(int x = 0; x < 9; x++) { 
			JLabel t1 = new JLabel("TAC");
			t1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			t1.setHorizontalAlignment(JLabel.CENTER);				
			p2.add(t1);
		}
		for(int x = 0; x < 9; x++) { 
			JLabel t1 = new JLabel("TIC");
			t1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			t1.setHorizontalAlignment(JLabel.CENTER);				
			p4.add(t1);
		}
		for(int x = 0; x < 9; x++) { 
			JLabel t1 = new JLabel("TOE");
			t1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			t1.setHorizontalAlignment(JLabel.CENTER);				
			p6.add(t1);
		}
		for(int x = 0; x < 9; x++) { 
			JLabel t1 = new JLabel("JOE");
			t1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			t1.setOpaque(true);
			t1.setBackground(Color.BLACK);
			t1.setForeground(Color.WHITE);
			t1.setHorizontalAlignment(JLabel.CENTER);				
			p8.add(t1);
		}
		add(p1); add(p2); add(p3); add(p4); add(p5); add(p6); add(p7); add(p8); add(p9);
	}

	public JTextArea right() {
		JTextArea j1 = new JTextArea("\n   R\n   I\n   G\n   H\n   T\n" +  "   " + numRight, 5, 3);
		j1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		numRight++;
		return j1;
	}

	public JTextArea left() {
		JTextArea j1 = new JTextArea("\n   L\n   E\n   F\n   T\n" + "   " + numLeft,5,3);
		j1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		numLeft++;
		return j1;
	}

	public JLabel top() {
		JLabel j1 = new JLabel("	Top " + numTop);
		j1.setOpaque(true);
		j1.setPreferredSize(new Dimension(100,30));
		j1.setBackground(Color.WHITE);
		j1.setHorizontalAlignment(JLabel.CENTER);
		j1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		numTop++;
		return j1;
	}

	public JLabel bottom() {
		JLabel j1 = new JLabel("Bottom " + numBottom);
		j1.setOpaque(true);
		j1.setHorizontalAlignment(JLabel.CENTER);	
		j1.setBackground(Color.WHITE);
		j1.setPreferredSize(new Dimension(100,30));
		j1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		numBottom++;
		return j1;
	}
}