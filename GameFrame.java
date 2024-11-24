import javax.swing.JFrame;

public class GameFrame extends JFrame {

	
	public GameFrame(VideoGame videoGame) {
		super("FinalProject");
		setSize(800, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(true);
		setContentPane(videoGame);
		setVisible(true);
	}

	public static void main(String[] args) {
		new FinalProject();
	}
}
