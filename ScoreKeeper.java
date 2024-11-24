//Name: Rishi Saravanan
//Date: 3/22/22
//Program Name: ScoreKeeper
//Program Goal: Keep the score of 2 teams using buttons
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ScoreKeeper extends JFrame {
	
	JFrame frame;
	boolean plusOneT1;
	boolean plusOneT2;
	boolean plusTwoT1;
	boolean plusTwoT2;
	boolean plusThreeT1;
	boolean plusThreeT2;
	JButton plusOneTeam1;
	JButton plusTwoTeam1;
	JButton plusTwoTeam2;
	JButton plusOneTeam2;
	JButton plusThree;
	JButton square;
	JButton reset;
	JPanel panel;
	JLabel teamOne;
	JLabel teamTwo;
	JLabel scoreSign;
	JTextArea score;
	JButton plusThreeTeam1;
	JButton plusThreeTeam2;
	int team1Score = 0; 
	int team2Score = 0;

	public static void main(String[] args) {
		ScoreKeeper s1 = new ScoreKeeper();
	}

	public ScoreKeeper() {

		// Define Frame
		setBackground(Color.GRAY);
		frame = new JFrame("ScoreKeeper");
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane();
		frame.setVisible(true);

		// Define components in the panel. 
		plusOneTeam1 = new JButton("Plus 1 ");
		plusOneTeam2 = new JButton("Plus 1");
		plusTwoTeam1 = new JButton("Plus 2 ");
		plusTwoTeam2 = new JButton("Plus 2");
		plusThreeTeam1 = new JButton("Plus 3 ");
		plusThreeTeam2 = new JButton("Plus 3");
		
		score = new JTextArea("\n\n\n" + "                              Team 1: " + team1Score + "\n" + "                              Team 2: " + team2Score);
		score.setBackground(new Color (100,175,250));

		square = new JButton("Square");
		teamOne = new JLabel("                             Team 1");
		teamOne.setOpaque(true);
		teamOne.setBackground(Color.YELLOW);
		teamTwo = new JLabel("                              Team 2");
		teamTwo.setOpaque(true);
		teamTwo.setBackground(Color.GREEN);
		scoreSign = new JLabel("                               Score ->");
		scoreSign.setOpaque(true);
		scoreSign.setBackground(new Color (100,175,250));
		
		panel = new ButtonPanel();
		
		// Add panel to Frame. 
		frame.add(panel);
		panel.setLayout(new GridLayout(5, 2, 0, 0));

		// Add components to Panel. 
		panel.add(teamOne);
		panel.add(teamTwo);
		panel.add(plusOneTeam1);
		panel.add(plusOneTeam2);
		panel.add(plusTwoTeam1);
		panel.add(plusTwoTeam2);
		panel.add(plusThreeTeam1);
		panel.add(plusThreeTeam2);
		panel.add(scoreSign);
		panel.add(score);
	}
	
	class ButtonPanel extends JPanel {
		
		public ButtonPanel() {
			plusOneTeam1.addActionListener(new Button1Handler());
			plusOneTeam2.addActionListener(new Button1Handler());
			plusTwoTeam1.addActionListener(new Button1Handler());
			plusTwoTeam2.addActionListener(new Button1Handler());
			plusThreeTeam1.addActionListener(new Button1Handler());
			plusThreeTeam2.addActionListener(new Button1Handler());
		}
		@Override
		public void paintComponent(Graphics g) {
			if(plusOneT1 == true) { 
				team1Score++;
				score.setText("\n\n\n" + "                              Team 1: " + team1Score + "\n" + "                              Team 2: " + team2Score);
				plusOneT1 = false;
			} else if(plusTwoT1 == true) { 
				team1Score+=2;
				score.setText("\n\n\n" + "                              Team 1: " + team1Score + "\n" + "                              Team 2: " + team2Score);
				plusTwoT1 = false;
			} else if(plusThreeT1 == true) { 
				team1Score+=3;
				score.setText("\n\n\n" + "                              Team 1: " + team1Score + "\n" + "                              Team 2: " + team2Score);
				plusThreeT1 = false;
			} else if(plusOneT2 == true) { 
				team2Score++;
				score.setText("\n\n\n" + "                              Team 1: " + team1Score + "\n" + "                              Team 2: " + team2Score);
				plusOneT2 = false;
			} else if(plusTwoT2 == true) { 
				team2Score+=2;
				score.setText("\n\n\n" + "                              Team 1: " + team1Score + "\n" + "                              Team 2: " + team2Score);
				plusTwoT2 = false;
			} else if(plusThreeT2 == true) { 
				team2Score+=3;
				score.setText("\n\n\n" + "                              Team 1: " + team1Score + "\n" + "                              Team 2: " + team2Score);
				plusThreeT2 = false;
			}
			super.paintComponents(g);
		}		
		
		class Button1Handler implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();
				if (command.equals("Plus 1 ")) {
					plusOneT1 = true;
				}
				
				if (command.equals("Plus 1")) {
					plusOneT2 = true;
				}
				if (command.equals("Plus 2 ")) {
					plusTwoT1 = true;
				}
				if (command.equals("Plus 2")) {
					plusTwoT2 = true;
				}
				if (command.equals("Plus 3 ")) {
					plusThreeT1 = true;
				}
				if(command.equals("Plus 3")) {
					plusThreeT2 = true;
				}
				repaint();
			}
		}

	}  
}
