import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.Timer;


// Home screen panel
class VideoGame extends JPanel {

	
	Player player1 = new Player();
	Player player2 = new Player();
	Player player3 = new Player();
	Player player4 = new Player();

	boolean leftClicked =false; 
	boolean rightClicked = false;

	
	JPanel pa;
	JPanel pa2;
	JLabel title;
	JButton backToGame;
	Image background;
	Image titleColor;
	Image mockingjay;
	Image mockingjay2;
	Image smoke;
	Image gameTitle;
	Image instructionBack;
	Image level1Background;
	Image gun;
	Image plate;
	Image bullet;
	Image plateShattering;
	Image forestBackground;

	Image katniss;
	Image peeta;
	Image kato;

	Image customizeImage;
	Image readyToPlayImage;
	Image instructionsImage;

	JComponent customize;
	JComponent readyToPlay;
	JComponent instructions;

	JLabel customizeLabel;
	JLabel instructionsLabel;
	JLabel readyToPlayLabel;

	GamePanel ga;
	InstructionPanel insPanel;
	CustomizePanel customizePanel;
	Animator an;
	CustomizePanel1 ws;

	int num = 0;

	// Initialize the X and Y coordinates of the plates and bullets. 
	private int mj1X, mj1Y;
	private int mj2X, mj2Y;
	private int welcomeX = 300, welcomeY = 600;
	private int plateX = 750, plateY = 400;
	private int newPlateX = 0; 
	private int newPlateY = 350;
	private int bulletX = 300, bulletY = 650;		

	// Create the required timers for flying plates, shattered plates. 
	private Timer plateTimer;
	private Timer plateTimer2;
	private Timer shatteredPlateTimer;
	private Timer mj1Timer;
	private Timer mj2Timer;
	private Timer welcomeTimer;
	private Timer bulletTimer;

	int mj1Count = 0;
	int mj2Count = 0;
	int bulletCount = 0;
	boolean animationDone = false;
	boolean welcomeAnimationDone = false;
	public int shatteredPlateY;
	public int shatteredPlateX;
	public String plateHit;		

	// Used for game count down. Currently set at one minute
	public long startTime;
	public final int ALLOCATED_TIME = 60*1000;
	boolean startGame = false;
	int frames = 0;
	long lastRefresh = System.currentTimeMillis();

	String imageLocation = "/Users/butterfleye/Downloads/";


	public VideoGame() {
		loadImages();
		initializePlayerInformation();

		pa = new JPanel(new CardLayout());
		this.setPreferredSize(new Dimension(800, 500));
		runIt();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		frames++;
		if ((System.currentTimeMillis() - lastRefresh) >=1000) {
			lastRefresh = System.currentTimeMillis();
			System.out.println("Frame " + frames);
			frames = 0;
		}

	}

	public void runIt() {

		ga = new GamePanel();
		pa.add(ga, "GamePanel");

		insPanel = new InstructionPanel();
		pa.add(insPanel, "InstructionPanel");

		readyToPlay = new ReadyToPlayPanel();
		pa.add(readyToPlay, "ReadyToPlayPanel");

		customizePanel = new CustomizePanel();
		pa.add(customizePanel, "CustomizePanel");

		an = new Animator(imageLocation + "orc.jpeg", 5, 15);
		pa.add(an, "Animator");
		
		ws = new CustomizePanel1();
		pa.add(ws, "WSelectionPanel");

		add(pa);

	}

	//Class which asks user if they are ready to play, and begins Level 1. 
	class ReadyToPlayPanel extends JPanel implements ActionListener, KeyListener, MouseMotionListener {
		JTextArea score;
		JButton level1Ready;
		private PlateMover plateMover;
		private SecondPlateMover plateMover2;
		private BulletMover bulletMover;
		boolean level1ReadyToPlay;

		boolean bulletClicked = false;
		boolean addGun = false;
		boolean moveForward = false;
		boolean moveLeft = false;
		boolean moveRight = false;
		boolean moveBack = true;


		public ReadyToPlayPanel() {
			this.grabFocus();
			this.setFocusable(true);
			this.requestFocus(); 
			addKeyListener(this);

			//this.setLayout(new GridLayout(7, 7));
			plateMover = new PlateMover();
			plateMover2 = new SecondPlateMover();
			plateTimer = new Timer(15, plateMover);
			plateTimer2 = new Timer(15, plateMover2);
			bulletMover = new BulletMover();
			bulletTimer = new Timer(15, bulletMover);

			for (int x = 1; x < 49; x++) {
				if (x != 7) {
					add(new JLabel(""));
				} 
			}
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(forestBackground, 0, 0, 800, 500, null);
			g.drawImage(player1.image, 700, 40, 25, 25, null); 
			g.drawString(player1.score + "", 750, 60);
			g.drawImage(player2.image, 700, 70, 25, 25, null);
			g.drawString(player2.score + "", 750, 90);

			g.drawImage(player3.image, 700, 100, 25, 25, null);
			g.drawString(player3.score + "", 750, 120);

			g.drawImage(gun, 375, 400, 80, 80, null);

			long currentTime = System.currentTimeMillis();
			if(startGame && (currentTime - startTime) >= ALLOCATED_TIME) {  // If time exceeded, stop timers
				plateTimer.stop();
				plateTimer2.stop();
				bulletTimer.stop();
				shatteredPlateTimer.stop();
				g.drawString("TIME UP", 100, 300);
				g.drawString("SCORE: " + player1.score, 100, 350);
			} else  { 
				if (shatteredPlateTimer.isRunning()) {
					g.drawImage(plateShattering,shatteredPlateX, shatteredPlateY, 40, 40, null);
					if (plateHit.equals("Plate1")) {
						g.drawImage(plate, newPlateX, newPlateY, 40, 40, null);
					}
					if (plateHit.equals("Plate2")) {
						g.drawImage(plate, plateX, plateY, 40, 40, null);
					}
				} else if(Math.abs(plateY - bulletY) <= 40 && Math.abs(plateX - bulletX) <= 40) { 
					player1.score++;
					shatteredPlateX = plateX;
					shatteredPlateY = plateY;
					plateTimer.stop();
					plateHit = "Plate1";
					plateX = 750;
					plateY = 400;
					plateTimer.start();

					shatteredPlateTimer.start();
				} else if(Math.abs(newPlateY - bulletY) <= 40 && Math.abs(newPlateX - bulletX) <= 40) { 
					player1.score++;
					plateTimer2.stop();
					shatteredPlateX = newPlateX;
					shatteredPlateY = newPlateY;
					newPlateX = 0;
					newPlateY = 350;
					plateTimer2.start();

					plateHit = "Plate2";
					shatteredPlateTimer.start();
				} else {
					bulletCount = 0;
					g.drawImage(plate, plateX, plateY, 40, 40, null);
					g.drawImage(plate, newPlateX, newPlateY, 40, 40, null);
					if (bulletClicked) {
						g.drawImage(bullet, bulletX, bulletY, 10, 10, null);
					} 
				}
			}

		}

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == level1Ready) { 
				plateTimer.start();
				plateTimer2.start();
			}
		}

		public void keyTyped(KeyEvent e) {
		}

		//Changes the speed and direction of the bullet based on user key pressed.
		public void keyPressed(KeyEvent e) {
			char a = e.getKeyChar();

			switch (a) {
			case 'W': // Forward
			case 'w':
				moveForward = true;
				moveLeft = false;
				moveRight = false;
				moveBack = false;
				break;
			case 'A': // Left
			case 'a':
				moveForward = false;
				moveLeft = true;
				moveRight = false;
				moveBack = false;
				break;
			case 'S': // Right
			case 's':
				moveForward = false;
				moveLeft = false;
				moveRight = true;
				moveBack = false;
				break;
			case 'D': // Back
			case 'd':
				moveForward = false;
				moveLeft = false;
				moveRight = false;
				moveBack = true;
				break;
			case 'Y':
			case 'y':
				bulletClicked = true;
				bulletTimer.start();
				if (!plateTimer.isRunning()) {
					plateTimer.start();
				}
				if (!plateTimer2.isRunning()) {
					plateTimer2.start();
				}
				break;
			case 'B':
			case 'b':
				rightClicked = false;
				leftClicked = true;
				break;
			case 'C':
			case 'c':  
				leftClicked = false;
				rightClicked = true;
			}
		}

		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}


	// Class used to determine the speed and direction of the first plate.
	class PlateMover implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			plateX-=4;
			plateY-=2;
			if (plateX == 0 || plateY == 0) { 
				plateX = 750;
				plateY = 400;
			}

			//repaint();
		}
	}
	//Class used to determine the speed and direction of the second plate.
	class SecondPlateMover implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (newPlateX>790) { 
				newPlateX = 0; 
				newPlateY = 350;
			}

			if (newPlateX < 400) { 
				newPlateX+=2;
				newPlateY-=1;
			} else { 
				newPlateX+=2;
				newPlateY+=2;
			}
		} 
	}

	//Class used to determine the speed and direction of the bullet.
	class BulletMover implements ActionListener { 
		int num = 0;
		public void actionPerformed(ActionEvent e) { 
			if(leftClicked == true) { 
				bulletX-=2;
			} else if (rightClicked == true) { 
				bulletX+=2;
			} else { 
				if(bulletY < 0  || bulletX < 0) { 
					bulletCount++;
					bulletX = 400;
					bulletY = 650;
					bulletTimer.stop();
				}
			}
			bulletY-=6;
			if(bulletY < 20) { 
				leftClicked = false;
				rightClicked = false;
			}
			//repaint();
		}
	}

	class FallingPlate implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			shatteredPlateY+=2;
			if(shatteredPlateY >=750) { 
				shatteredPlateTimer.stop();
			}
			//repaint();
		}
	}

	class MenuPanel extends JPanel implements ActionListener, MouseListener {

		public MenuPanel() {
			setLayout(new GridLayout(20, 1));
			setBackground(getBackground());
			for (int i =0; i < 20; i++) {

				if (i==0) {
					customizeLabel  =new JLabel(new ImageIcon(customizeImage));
					customizeLabel.addMouseListener(this);
					add(customizeLabel);
				}
				else if (i ==4) { 
					readyToPlayLabel =new JLabel(new ImageIcon(readyToPlayImage));
					readyToPlayLabel.addMouseListener(this);
					add(readyToPlayLabel);
				}
				else if (i ==8 ) {
					instructionsLabel  =new JLabel(new ImageIcon(instructionsImage));
					instructionsLabel.addMouseListener(this);
					add(instructionsLabel);

				}
				else {
					JLabel label = new JLabel("");
					add(label);

				}
			}
		}


		@Override
		public void mouseClicked(MouseEvent e) {
			if((e.getSource() == customizeLabel)) { 
				CardLayout c1 = ((CardLayout)pa.getLayout());
				// UNCOMMENT c1.show(pa, "CustomizePanel");
				c1.show(pa, "WSelectionPanel");

			} else if ((e.getSource() == readyToPlayLabel)) { 
				CardLayout c1 = ((CardLayout)pa.getLayout());
				c1.show(pa, "ReadyToPlayPanel");
				String displayMsg = "Ready to Play? \n Press <space> to shoot. \n Press left and right arrow to turn.";
				int readyToStart = JOptionPane.showConfirmDialog(this, displayMsg, 
						"Level1", 
						JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE);
				if(readyToStart == 0) { 
					plateTimer.start();
					plateTimer2.start();
					startTime = System.currentTimeMillis();
					startGame = true;
				} else {
					c1.show(pa, "InstructionPanel");
				}
			} else if ((e.getSource() == instructionsLabel)) { 
				CardLayout c1 = ((CardLayout)pa.getLayout());
				c1.show(pa, "InstructionPanel");
			} 
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void actionPerformed(ActionEvent e) {

		}

	}

	// Main game panel(first card) which is what is seen when the program is run
	class GamePanel extends JPanel implements ActionListener {

		private MJ1Mover mj1Mover;
		private MJ2Mover mj2Mover;
		private WelcomeMover welcomeMover;
		public boolean goingUp;
		public boolean goingDown;
		public String mj1Direction = "down";
		public boolean mj2GoingDown;

		public GamePanel() {
			mj1X = 0;
			mj1Y = 0;
			mj2X = 600;
			mj2Y = 200;

			// create timer for animation of mockingjay
			mj1Mover = new MJ1Mover();
			mj1Timer = new Timer(15, mj1Mover);

			mj2Mover = new MJ2Mover();
			mj2Timer = new Timer(15, mj2Mover);

			welcomeMover = new WelcomeMover();
			welcomeTimer = new Timer(15, welcomeMover);

			FallingPlate shatteredPlateMover = new FallingPlate();
			shatteredPlateTimer = new Timer(15, shatteredPlateMover);

			mj1Timer.start();
			mj2Timer.start();

			this.setPreferredSize(new Dimension(800, 500));

			this.setLayout(new BorderLayout());

			JPanel menuPanel = new MenuPanel();
			menuPanel.setBackground(getBackground());
			this.add(menuPanel, BorderLayout.EAST);
		}

		//Shows each card based on user click.
		public void actionPerformed(ActionEvent e) {
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			requestFocus();
			g.drawImage(background, 0, 0, 800, 500, null);
			g.drawImage(gameTitle, 300, 0, 220, 70, null);
			g.drawImage(mockingjay, Math.abs(mj1X), Math.abs(mj1Y), 100, 100, null);
			g.drawImage(mockingjay2, Math.abs(mj2X), Math.abs(mj2Y), 100, 100, null);
			if (animationDone) {
				g.drawImage(smoke, welcomeX - 300, welcomeY, 150, 150, null);
				g.drawImage(smoke, welcomeX + 350, welcomeY, 150, 150, null);

			}
		}

		// Class used to making the 2nd mockingjay move(animation)
		class MJ1Mover implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (mj1Direction.equals("done")) {
					return;
				}

				if (mj1Count <= 4) {
					if (mj1Direction.equals("down")) {
						mj1Y+=4;
						if (mj1Y >= 400 && mj1Y <= 480) {
							mj1Y += 2;
							mj1X += 6;
						}
						if (mj1Y > 480) {
							mj1Direction = "straight";
						}
						//repaint();
						grabFocus();

						return;
					}


					if (mj1Direction.equals("down")) {
						mj1Direction = "straight";
					}

					if (mj1Direction.equals("straight")) {
						mj1X+=4;
						if (mj1X >=620) {
							mj1Direction = "up";
						} else {
							//repaint();
							grabFocus();
							return;
						}
					}

					if (mj1Direction.equals("up")) {
						mj1Y-=2;
						if (mj1Y == 200) {
							animationDone = true;
							mj1Count = 5;
							welcomeTimer.start();
							mj1Direction = "done";
						}
						//repaint();
						grabFocus();
						return;
					}
					//repaint();
					grabFocus();
				}
			}
		}

		// Class used to making the 2nd mockingjay move(animation)
		class MJ2Mover implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (mj2Count <= 4) {
					if (mj2X <= 20) {
						mj2X = 20;
						mj2Y--;
						if (mj2Y == 200) {
							animationDone = true;
							mj2Count = 5;
							welcomeTimer.start();

						}
					} else {
						if (mj2GoingDown == false && mj2Y >= 400 && mj2Y <= 480) {
							if (mj2Y == 480)
								mj2GoingDown = true;
							mj2Y += 2;
							mj2X -= 6;
						} else if (mj2GoingDown == true && mj2Y >= 400) {
							mj2Y--;
							if (mj2Y <= 400)
								goingDown = false;
							mj2X -= 6;
						} else {
							mj2Y += 2;
						}
					}
				}
				//repaint();
				grabFocus();
			}
		}

		// Class used as a counter for the smoke
		class WelcomeMover implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (welcomeY > 300)
					welcomeY--;
			}
		}
	}

	//Method used to create player information, based on what the user chooses.  
	private void initializePlayerInformation() {
		player1.name = "Katniss";
		player1.score = 0;
		player1.level = 1;
		player1.image = katniss;

		player2.name = "Peeta";
		player2.score = 0;
		player2.level = 1;
		player2.image = peeta;


		player3.name = "Kato";
		player3.score = 0;
		player3.level = 1; 
		player2.image = kato;


	}


	// Instruction panel used to tell rules to the participant. This is the second
	// card in the card list.
	class InstructionPanel extends JPanel implements ActionListener {
		/**
		 * 
		 */
		JTextArea instruction;
		String line1 = "1.       The goal of the game is to be the last one standing.";
		String line2 = "2.       The game starts with 4 players including you.";
		String line2a = "                 The first event is a plate shooting competition. Whoever score the least points is eliminated.";
		String line3 = "3.       The next round consists of a 3 player duel.";
		String line3a = "                 Each player presented with a shooting weapon.";
		String line3b = "                 The players will be moving around and shoot at each other. Whoever is killed first is eliminated.";
		String line4 = "4.       The final round is a showdown between the final 2 players.";
		String line4a = "                 Weapons will be placed around the arena, and you must grab them. Whoever is killed first is eliminated.";
		String line5 = "5.       There is a gambling portion involved. ";
		String line5a = "                 In the final round, you have the option to double your health, or lose it all.";
		String line6 = "6.       Arrow Keys";
		String line7 = "                 W: Go forward";
		String line8 = "                 A: Go Left";
		String line9 = "                 S: Go Right";
		String line10 = "                 D: Go Backwards";
		String line11 = "                 Use shift to aim, and space to shoot";
		String line12 = "Good Luck. Whatever happens, do not be killed.";

		public InstructionPanel() {
			this.setLayout(new BorderLayout());
			this.setPreferredSize(new Dimension(800, 450));
			String instructionSet = "				Instructions\n\n";
			instructionSet = instructionSet + line1 + "\n";
			instructionSet = instructionSet + line2 + "\n";
			instructionSet = instructionSet + line2a + "\n";
			instructionSet = instructionSet + line3 + "\n";
			instructionSet = instructionSet + line3a + "\n";
			instructionSet = instructionSet + line3b + "\n";
			instructionSet = instructionSet + line4 + "\n";
			instructionSet = instructionSet + line4a + "\n";
			instructionSet = instructionSet + line5 + "\n";
			instructionSet = instructionSet + line5a + "\n";
			instructionSet = instructionSet + line6 + "\n";
			instructionSet = instructionSet + line7 + "\n"; 
			instructionSet = instructionSet + line8 + "\n";
			instructionSet = instructionSet + line9 + "\n";
			instructionSet = instructionSet + line10 + "\n";
			instructionSet = instructionSet + line11 + "\n\n";
			instructionSet = instructionSet + line12 + "\n\n\n\n\n\n";
			instruction = new JTextArea(instructionSet);
			add(instruction, BorderLayout.BEFORE_FIRST_LINE);
			instruction.setBackground(new Color(0, 0, 0, 64));
			instruction.setForeground(Color.WHITE);
			backToGame = new JButton("Back To Game");
			backToGame.setOpaque(true);
			backToGame.setBackground(Color.YELLOW);
			backToGame.setPreferredSize(new Dimension(100, 30));
			backToGame.addActionListener(this);
			add(backToGame, BorderLayout.AFTER_LINE_ENDS);

		}
		//Draw instruction background
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(instructionBack, 0, 0, 800, 500, null);

		}

		// Used to check whether back to game has been clicked
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == backToGame) {
				CardLayout c1 = ((CardLayout) pa.getLayout());
				c1.show(pa, "GamePanel");
			}
		}
	}

	//Class to create the panel for choosing your name and character. 
	class CustomizePanel extends JPanel implements ActionListener {
		JRadioButton katnissChoice;
		JRadioButton peetaChoice;
		JRadioButton katoChoice;
		JLabel choice;
		JTextArea enterChoice;
		JButton chi;

		public CustomizePanel() {

			this.setPreferredSize(new Dimension(800, 500));

			setLayout(new BorderLayout());
			choice = new JLabel("Enter your name: ");
			enterChoice = new JTextArea("");

			// Use gridlayout to space the labels and text fields. 
			JPanel inputPanel = new JPanel(new GridLayout(6,6));
			for(int x = 0; x < 36; x++) { 
				if(x == 2) { 
					inputPanel.add(choice);
				} else if (x == 3) { 
					inputPanel.add(enterChoice);
				} else if (x == 26) { 
					JLabel imageText = new JLabel("Select an avatar ");
					inputPanel.add(imageText);
				} else { 
					JLabel label =new JLabel(" ");
					inputPanel.add(label);
				}
			}
			add(inputPanel, BorderLayout.NORTH);

			// Initialize the center panel
			ButtonGroup group = new ButtonGroup();
			katnissChoice = new JRadioButton("Katniss");
			peetaChoice = new JRadioButton("Peeta");
			katoChoice = new JRadioButton("Kato");

			// Use Button group to select one choice
			group.add(katnissChoice);
			group.add(peetaChoice);
			group.add(katoChoice);

			JPanel choicePanel = new JPanel(new GridLayout(7,7));
			choicePanel.setOpaque(false);
			for(int x = 0; x < 49; x++) { 
				if(x == 1) { 
					choicePanel.add(katnissChoice);
				} else if(x == 3) { 
					choicePanel.add(peetaChoice);
				} else if(x == 5) { 
					choicePanel.add(katoChoice);
				} else if (x == 31) {
					chi = new JButton("Ok");
					chi.setSize(10, 10);
					chi.addActionListener(this);
					chi.setOpaque(false);;
					choicePanel.add(chi);
				} else if (x == 8) {
					JLabel label =new JLabel(new ImageIcon(katniss));
					label.setOpaque(false);
					choicePanel.add(label);
				} else if (x == 10) {
					JLabel label =new JLabel(new ImageIcon(peeta));
					label.setOpaque(false);
					choicePanel.add(label);
				} else if (x == 12) {
					JLabel label =new JLabel(new ImageIcon(kato));
					label.setOpaque(false);
					choicePanel.add(label);
				} else {
					JLabel label =new JLabel(" ");
					label.setOpaque(false);
					choicePanel.add(label);
				}
			}
			add(choicePanel, BorderLayout.CENTER);
		}


		public void paintComponent(Graphics g) { 
			super.paintComponent(g);
			g.drawImage(forestBackground, 0, 0, 800, 500, null);
		}

		//gets user input based on what the user types, and sets it equal to player's name. 
		public void actionPerformed(ActionEvent e) {
			final String pName = enterChoice.getText();
			player1.name = pName;

			// Take the player to the Ready To Play panel
			CardLayout c1 = ((CardLayout)pa.getLayout());
			c1.show(pa, "ReadyToPlayPanel");

		}
	}

	private void loadImages() {

		mockingjay2 = new ImageIcon(imageLocation + "mockingjay.gif").getImage();
		mockingjay = new ImageIcon(imageLocation + "mockingjay.gif").getImage();
		background = new ImageIcon(imageLocation + "background.png").getImage();
		smoke = new ImageIcon(imageLocation + "smoke.gif").getImage();
		gameTitle = new ImageIcon(imageLocation + "gametitle.png").getImage();
		titleColor = new ImageIcon(imageLocation + "Images.jpeg").getImage();

		gun = new ImageIcon(imageLocation + "g.png").getImage();
		plate = new ImageIcon(imageLocation + "plate.png").getImage();
		plateShattering = new ImageIcon(imageLocation + "plateShattering.gif").getImage();
		bullet = new ImageIcon(imageLocation + "b.png").getImage();
		level1Background = new ImageIcon(imageLocation + "Level1Background.png").getImage();
		instructionBack = new ImageIcon(imageLocation + "InstructionBackground.png").getImage();
		forestBackground = new ImageIcon(imageLocation + "forestBackground.jpeg").getImage();

		katniss = new ImageIcon(imageLocation + "player1.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		peeta = new ImageIcon(imageLocation + "player2.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);;
		kato = new ImageIcon(imageLocation + "b.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);

		customizeImage = new ImageIcon(imageLocation + "customize.png").getImage().getScaledInstance(75, 30, Image.SCALE_SMOOTH);
		readyToPlayImage = new ImageIcon(imageLocation + "readytoplay.png").getImage().getScaledInstance(75, 20, Image.SCALE_SMOOTH);
		instructionsImage = new ImageIcon(imageLocation + "instructions.png").getImage().getScaledInstance(75, 20, Image.SCALE_SMOOTH);
	}


	//Class to create the panel for choosing your name and character. 
	class CustomizePanel1 extends JPanel implements ActionListener {
		JLabel choice;
		JTextArea enterChoice;
		JButton chi;
		

		ButtonGroup group1 = new ButtonGroup();
		ButtonGroup group2 = new ButtonGroup();
		ButtonGroup group3 = new ButtonGroup();

		JRadioButton group1Weapon1 = new JRadioButton("ABC");
		JRadioButton group1Weapon2 = new JRadioButton("DEF");
		JRadioButton group1Weapon3 = new JRadioButton("GHI");
		JRadioButton group2Weapon1 = new JRadioButton("JKL");
		JRadioButton group2Weapon2 = new JRadioButton("MNO");
		JRadioButton group2Weapon3 = new JRadioButton("PQR");
		JRadioButton group3Weapon1 = new JRadioButton("STU");
		JRadioButton group3Weapon2 = new JRadioButton("VWX");
		JRadioButton group3Weapon3 = new JRadioButton("YZA");

		public CustomizePanel1() {

			this.setPreferredSize(new Dimension(800, 500));
			
			JPanel inputPanel = new JPanel(new GridLayout(10,10));
			group1.add(group1Weapon1);
			group1.add(group1Weapon2);
			group1.add(group1Weapon3);
			
			group2.add(group2Weapon1);
			group2.add(group2Weapon2);
			group2.add(group2Weapon3);
			
			group3.add(group3Weapon1);
			group3.add(group3Weapon2);
			group3.add(group3Weapon3);

			
			// Initialize the center panel
			ButtonGroup group = new ButtonGroup();
			JRadioButton katnissChoice = new JRadioButton("Katniss");
			JRadioButton peetaChoice = new JRadioButton("Peeta");
			JRadioButton katoChoice = new JRadioButton("Kato");

			// Use Button group to select one choice
			group.add(katnissChoice);
			group.add(peetaChoice);
			group.add(katoChoice);

			//JPanel selectionPanel = new JPanel();
			for(int i=0; i< 100; i++) {
				JLabel l = new JLabel(" ");
				l.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				
				if (i == 22) {
					inputPanel.add(group1Weapon1);
				} else if (i == 26) {
					inputPanel.add(group2Weapon1);
				} else if (i == 29) {
					inputPanel.add(group2Weapon1);	
				} else if (i == 42) {
					inputPanel.add(group1Weapon2);
				} else if (i == 46) {
					inputPanel.add(group2Weapon2);
				} else if (i == 49) {
					inputPanel.add(group2Weapon2);	
				} else if (i == 62) {
					inputPanel.add(group1Weapon3);
				} else if (i == 66) {
					inputPanel.add(group2Weapon3);
				} else if (i == 69) {
					inputPanel.add(group2Weapon3);	
				} else if (i == 82) {
					JLabel label =new JLabel(new ImageIcon(katniss));
					label.setOpaque(false);
					inputPanel.add(label);
				} else if (i == 86) {
					JLabel label =new JLabel(new ImageIcon(peeta));
					label.setOpaque(false);
					inputPanel.add(label);
				} else if (i == 89) {
					JLabel label =new JLabel(new ImageIcon(kato));
					label.setOpaque(false);
					inputPanel.add(label);
				} else if (i == 96) {
					chi = new JButton("Ok");
					chi.setSize(10, 10);
					chi.addActionListener(this);
					chi.setOpaque(false);;
					inputPanel.add(chi);
				} else {
 					inputPanel.add(l);
				}
			}
			add(inputPanel);
			/*	
			JPanel selectionPanel1 = new JPanel();
			selectionPanel1.setOpaque(false);
			selectionPanel1.add(group1Weapon1);
			selectionPanel1.add(group1Weapon2);
			selectionPanel1.add(group1Weapon3);
			selectionPanel1.add(katnissChoice);
	
			JPanel selectionPanel2 = new JPanel();
			selectionPanel2.setOpaque(false);
			selectionPanel2.add(group2Weapon1);
			selectionPanel2.add(group2Weapon2);
			selectionPanel2.add(group2Weapon3);
			selectionPanel2.add(peetaChoice);

	
			JPanel selectionPanel3 = new JPanel();
			selectionPanel3.setOpaque(false);
			selectionPanel3.add(group3Weapon1);
			selectionPanel3.add(group3Weapon2);
			selectionPanel3.add(group3Weapon3);
			selectionPanel3.add(katoChoice);
			
			// Use gridlayout to space the labels and text fields. 
			JPanel inputPanel = new JPanel();
			inputPanel.add(selectionPanel1);
			inputPanel.add(selectionPanel2);
			inputPanel.add(selectionPanel3);
			
			add(inputPanel);*/
			
			

		}


		public void paintComponent(Graphics g) { 
			super.paintComponent(g);
			//g.drawImage(forestBackground, 0, 0, 800, 500, null);
		}

		//gets user input based on what the user types, and sets it equal to player's name. 
		public void actionPerformed(ActionEvent e) {
			final String pName = enterChoice.getText();
			player1.name = pName;

			// Take the player to the Ready To Play panel
			CardLayout c1 = ((CardLayout)pa.getLayout());
			c1.show(pa, "ReadyToPlayPanel");

		}
	}
	
	/*Class to create the panel for choosing the weapon. 
	class WSelectionPanel extends JPanel implements ActionListener {
		
		
		public WSelectionPanel() {

			this.setOpaque(false);
			this.setPreferredSize(new Dimension(800, 500));

			GridLayout gl = new GridLayout (3,3);
			setLayout(new GridLayout(3,3));
			
			group1.add(group1Weapon1);
			group1.add(group1Weapon2);
			group1.add(group1Weapon3);
			
			group2.add(group2Weapon1);
			group2.add(group2Weapon2);
			group2.add(group2Weapon3);
			
			group3.add(group3Weapon1);
			group3.add(group3Weapon2);
			group3.add(group3Weapon3);

			JPanel selectionPanel1 = new JPanel();
			selectionPanel1.setOpaque(false);
			selectionPanel1.add(group1Weapon1);
			selectionPanel1.add(group1Weapon2);
			selectionPanel1.add(group1Weapon3);
			
			
			JPanel selectionPanel1 = new JPanel();
			selectionPanel1.add(group2Weapon1);
			selectionPanel1.add(group2Weapon2);
			selectionPanel1.add(group2Weapon3);
			selectionPanel1.add(group3Weapon1);
			selectionPanel1.add(group3Weapon2);
			selectionPanel1.add(group3Weapon3);

			add(selectionPanel, BorderLayout.NORTH);
			
			
			// Initialize the center panel
			ButtonGroup group = new ButtonGroup();
			katnissChoice = new JRadioButton("Katniss");
			peetaChoice = new JRadioButton("Peeta");
			katoChoice = new JRadioButton("Kato");

			// Use Button group to select one choice
			group.add(katnissChoice);
			group.add(peetaChoice);
			group.add(katoChoice);

			JPanel playerPanel = new JPanel();
			playerPanel.setOpaque(false);

			playerPanel.add(katnissChoice);
			playerPanel.add(peetaChoice);
			playerPanel.add(katoChoice);

			JButton chi = new JButton("Ok");
			chi.setSize(10, 10);
			chi.addActionListener(this);
			playerPanel.add(chi);

			add(playerPanel, BorderLayout.SOUTH);
							
		}


		public void paintComponent(Graphics g) { 
			super.paintComponent(g);
			g.drawImage(forestBackground, 0, 0, 800, 500, null);
		}

		//gets user input based on what the user types, and sets it equal to player's name. 
		public void actionPerformed(ActionEvent e) {
			
			//final String pName = enterChoice.getText();
			//player1.name = pName;

			// Take the player to the Ready To Play panel
			CardLayout c1 = ((CardLayout)pa.getLayout());
			c1.show(pa, "ReadyToPlayPanel");

		}
	}*/

	
	
	
	
	//Animator class

	// Home screen panel
	class Animator extends JPanel implements ActionListener, MouseListener {
		BufferedImage[] images;
		int delay;
		int currentImage = 0;
		int x = 0, y = 0;
		int frames = 0;
		int speed = 35;
		long lastRefresh = System.currentTimeMillis();
		int speedCheck = 0;


		public Animator(String imagePrefix,  int imageCount, int delay) {

			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(imagePrefix));
			} catch (IOException e) {
				e.printStackTrace();
			}

			images = new BufferedImage[imageCount];
			int x = 20, y = 240;

			for (int i=0; i < images.length; i++) {
				images[i] = img.getSubimage(x, y, 105, 105);
				x+=100;
			}
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			speedCheck++;
			if (speedCheck >= speed) {
				speedCheck = 0;
				currentImage++;
				if (currentImage >= images.length) {
					currentImage = 0;
					//x=0;
					//y=0;
				}
			}
			g.drawImage(images[currentImage], x, y, 45, 45,null);
			//x++;
			//y++;

		}

		public void actionPerformed(ActionEvent e) {
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
	// Class to capture player information. 
	class Player { 
		String name;
		int score;
		Image image;	
		int level;
	}

}