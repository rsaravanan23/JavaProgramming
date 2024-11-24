import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ButtonSlider extends JFrame implements ActionListener {
	public static void main(String[] args) {
		ButtonSlider pL2 = new ButtonSlider();
	}

	public ButtonSlider() {
		super("Pillsbury Layout Example");

		setSize(900, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(80, 50);
		setResizable(true);
		PillsburyLayoutP2 pillsPanel2 = new PillsburyLayoutP2();
		setContentPane(pillsPanel2); // OR frame.getContentPane().add(p_in);
		setVisible(true);
	}
}

class PillsburyLayoutP2 extends JPanel {
	private boolean pressed1, pressed2, pressed3;
	private JButton button1, button2, button3, button4;
	private Font font;
	private int counter;
	private JSlider slider;
	private int sliderValue;
	private int helloCounter = 0;
	private int bellyCounter = 0;
	Image doughboy = new ImageIcon("/Users/butterfleye/Downloads/Pillbury-Doughboy1.jpg").getImage();

	public PillsburyLayoutP2() {

		pressed1 = pressed2 = false;
		font = new Font("Serif", Font.BOLD, 30);

		setBackground(Color.BLUE);
		setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		// setLayout( new FlowLayout( FlowLayout.LEFT, 50, 50) );

		button1 = new JButton("Press my belly."); // construct button
		button1.setPreferredSize(new Dimension(150, 50));
		Button1Handler b1handler = new Button1Handler(); // this is so the actionPerformed is dedicated to this button
															// only
		button1.addActionListener(b1handler); // add listener to button
		add(button1); // add button to panel (Pillsbury2)

		button2 = new JButton("Hello");
		button2.setPreferredSize(new Dimension(150, 50));
		Button2Handler b2handler = new Button2Handler();
		button2.addActionListener(b2handler);
		add(button2);

		button3 = new JButton("Press my belly.");
		button3.setPreferredSize(new Dimension(150, 50));
		Button1Handler b3handler = new Button1Handler();
		button3.addActionListener(b1handler);
		add(button3);

		button4 = new JButton("Hello");
		button4.setPreferredSize(new Dimension(150, 50));
		Button2Handler b4handler = new Button2Handler();
		button4.addActionListener(b2handler);
		add(button4);

		JPanel jp = new JPanel();
		add(jp);

		slider = new JSlider(JSlider.HORIZONTAL, 5, 105, 12); // construct slider bar
		slider.setMajorTickSpacing(50); // create tick marks on slider every 40 units
		slider.setMinorTickSpacing(10);
		slider.setForeground(Color.BLUE);
		slider.setPaintTicks(true);
		slider.setLabelTable(slider.createStandardLabels(50)); // create labels on tick marks

		slider.setPaintLabels(true);
		SliderHandler sHandler = new SliderHandler();
		add(slider); // add button to panel
		slider.addChangeListener(sHandler); // add listener to slider
		sliderValue = 12;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Font f = new Font("Serif", Font.BOLD, sliderValue);
		g.setFont(f);
		g.drawString("Move the slider to see ", 100, 250);
		g.drawString("the font size change", 100, 320);

		if (pressed2) {
			g.setFont(font);
			if (helloCounter % 4 == 0 && helloCounter != 0) {
				g.setFont(font);
				g.drawString("Stop saying hello", 100, 150);
			} else {
				g.drawString("You Say", 100, 150);
			}

			pressed1 = false;
			helloCounter++;
		}

		if (pressed1) {
			g.setFont(font);
			if (bellyCounter % 5 == 0 && bellyCounter != 0) {
				g.drawString("Stop pressing my belly", 100, 150);
			} else {
				g.drawString("hee hee", 200, 150);
			}

			pressed1 = true;
			bellyCounter++;
		}
	}
 
	class Button1Handler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if (command.equals("Press my belly.")) {
				pressed1 = true;
				button1.setText("reset");
			} else
				button1.setText("Press my belly.");
			repaint();
		}
	} // end class Button1Handler

	class Button2Handler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command2 = e.getActionCommand();
			if (command2.equals("Hello")) {
				pressed2 = true;
				button2.setText("Goodbye");
			} else
				button2.setText("Hello");
			repaint();
		}
	} // end class Button2Handler

	class SliderHandler implements ChangeListener {
		public void stateChanged(ChangeEvent e) { // event handler for JSliderBar (stateChanged)
			sliderValue = slider.getValue();
			repaint();
		}
	}
}










