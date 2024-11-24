import java.awt.*;

import javax.swing.*;

public class OptimusPrime extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		OptimusPrime w1 = new OptimusPrime();
	}
	
	public OptimusPrime() {
		super("OptimusPrime");
		setSize(400, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(200, 100);
		setResizable(true);
		Panel pan = new Panel();
		setContentPane(pan);
		setVisible(true);
	}
}

class Panel extends JPanel {

	private static final long serialVersionUID = 1L;

	public Panel() {
		System.out.println("Hello"); 
	}

}
