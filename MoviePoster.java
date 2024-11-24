//Name: Rishi Saravanan
//Date: 2/18/22
//Program Name: MoviePoster
//Program Goal: Draw movie posters with movie characters using images 
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
public class MoviePoster extends JFrame {
	public static void main(String[] args) {
		MoviePoster w1 = new MoviePoster();
	}

	public MoviePoster() {
		super("MoviePoster");
		setSize(800,800);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(200, 100);
		setResizable(true);
		PosterPanel pan = new PosterPanel();
		setContentPane(pan);
		setVisible(true);
	}
}

class PosterPanel extends JPanel {
	Scanner s1 = new Scanner(System.in);
	int choice = 0; 
	int bgChoice = 0; 

	public PosterPanel() {
		System.out.println("Which movie poster would you like?");
		System.out.print("Lord of the Rings(1), Harry Potter(2), or Star Wars(3)? ");
		choice = s1.nextInt(); 
		System.out.print("\nWould you like Paris(1), London(2), or Egypt(3)? "); 
		bgChoice = s1.nextInt(); 
		System.out.println("Here is your poster."); 
	}
	public void paintComponent(Graphics g) { 
		Image paris = new ImageIcon("bg_Paris.jpg").getImage();
		Image london = new ImageIcon("bg_London.jpg").getImage();
		Image egypt = new ImageIcon("bg_Egypt.jpg").getImage();

		Image luke = new ImageIcon("sw_luke.png").getImage();
		Image leia = new ImageIcon("sw_leia.png").getImage();
		Image trooper = new ImageIcon("sw_stormtrooper.png").getImage();
		Image vader = new ImageIcon("sw_darth.png").getImage();
		Image harry = new ImageIcon("hp_harry.png").getImage();
		Image ron = new ImageIcon("hp_ron.png").getImage();
		Image hermione = new ImageIcon("hp_hermoine.png").getImage();
		Image voldemort = new ImageIcon("hp_voldemort.png").getImage(); 
		Image gollum = new ImageIcon("lr_gollum.png").getImage(); 
		Image arwen = new ImageIcon("lr_arwen.png").getImage(); 
		Image gandalf = new ImageIcon("lr_gandalf.png").getImage(); 
		Image frodo = new ImageIcon("lr_frodo.png").getImage(); 
		String str; 
		if(bgChoice == 1) { 
			str = "Paris"; 
			g.drawImage(paris, 0,0, 800, 800, null); 
		} else if(bgChoice == 2) { 
			g.drawImage(london, 0,0, 800, 800, null); 
			str = "London"; 
		} else { 
			g.drawImage(egypt, 0,0, 800, 800, null); 
			str = "Egypt"; 
		}
		if(choice == 2) { 
			g.drawImage(harry, 20, 290, 90, 90, null); 
			g.drawImage(ron, 120, 300, 70, 70, null); 
			g.drawImage(hermione, 210, 300, 70, 70, null); 
			g.drawImage(voldemort, 300, 300, 70, 70, null); 
			g.drawString("Harry Potter in " + str, 20, 20);
		} else if(choice == 3) { 
			g.drawImage(luke, 30, 200, 70, 70, null); 
			g.drawImage(leia, 120, 200, 90, 90, null); 
			g.drawImage(trooper, 210, 200, 70, 70, null); 
			g.drawImage(vader, 300, 200, 70, 70, null); 
			g.drawString("Star Wars in " + str, 20, 20);

		} else { 
			g.drawImage(arwen, 30, 300, 70, 70, null); 
			g.drawImage(gollum, 120, 300, 70, 70, null);
			g.drawImage(gandalf, 210, 250, 70, 220, null); 
			g.drawImage(frodo, 300, 280, 70, 120, null); 
			g.drawString("Lord of the Rings  in " + str, 20, 20);

		}
	}
}
