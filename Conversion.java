//Name: Rishi Saravanan
//Date: 3/2/22
//Program Name: Conversion 
//Program Goal: Convert from binary to decimal to hex
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;

public class Conversion extends JFrame {

	public static void main(String[] args) {
		Conversion obj = new Conversion();
	}

	public Conversion() {
		super("Conversion");
		setSize(900, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(200, 100);
		setResizable(true);
		Panel pan = new Panel();
		setContentPane(pan);
		setVisible(true);
	}
}

class Panel extends JPanel {
	Image elvin = new ImageIcon("/Users/butterfleye/Downloads/elvin_hayes.png").getImage();
	Scanner scan = new Scanner(System.in);
	int num, decimal;
	String dec;
	String binary; 
	int xStart = 30;
	int yStart = 300;
	String hex; 
	int choice; 
	int outputChoice; 
	int[][] zero  = {{1,1,1}, {1,0,1}, {1,0,1}, {1,0,1}, {1,1,1}};
	int[][] one   = {{0,1,0}, {0,1,0}, {0,1,0}, {0,1,0}, {0,1,0}};
	int[][] two   = {{1,1,0}, {0,0,1}, {0,1,0}, {1,0,0}, {1,1,1}};
	int[][] three = {{1,1,0}, {0,0,1}, {0,1,0}, {0,0,1}, {1,1,0}};
	int[][] four  = {{1,1,1}, {1,0,1}, {1,0,1}, {1,0,1}, {1,1,1}};
	int[][] five  = {{1,1,1}, {1,0,0}, {1,1,0}, {0,0,1}, {1,1,0}};
	int[][] six   = {{0,1,1}, {1,0,0}, {1,1,0}, {1,0,1}, {0,1,0}};
	int[][] seven = {{1,1,1}, {0,0,1}, {0,1,0}, {0,1,0}, {0,1,0}};
	int[][] eight = {{0,1,0}, {1,0,1}, {0,1,0}, {1,0,1}, {0,1,0}};
	int[][] nine  = {{0,1,0}, {1,0,1}, {0,1,1}, {0,0,1}, {1,1,0}};
	int[][] A = {{0,1,0}, {1,0,1}, {1,1,1}, {1,0,1}, {1,0,1}};
	int[][] B = {{1,1,0}, {1,0,1}, {1,1,0}, {1,0,1}, {1,1,0}};
	int[][] C = {{0,1,1}, {1,0,0}, {1,0,0}, {1,0,0}, {0,1,1}};
	int[][] D = {{1,1,0}, {1,0,1}, {1,0,1}, {1,0,1}, {1,1,0}};
	int[][] E = {{1,1,1}, {1,0,0}, {1,1,0}, {1,0,0}, {1,1,1}};
	int[][] F = {{1,1,1}, {1,0,0}, {1,1,0}, {1,0,0}, {1,0,0}};

	public Panel() {
		Scanner s1 = new Scanner(System.in); 
		System.out.println("Would you like to enter binary(1), decimal(2), or hex(3)? ");
		choice = s1.nextInt();
		if(choice == 1) {
		System.out.println("Please enter your binary: ");
		binary = s1.next();
		} else if(choice == 2) {
		System.out.println("Please enter a decimal: ");
		decimal = s1.nextInt();
		} else {
		System.out.println("Please enter a hex: ");
		hex = s1.next();
		}
		System.out.println("Would you like to output binary(1), decimal(2), or hex(3)? ");
		outputChoice = s1.nextInt();
	}
	public String decimalToBinary(int dec) { 
		binary = Integer.toBinaryString(dec);
		return binary; 
	}
	public String decimalToHex(int dec) { 
		hex = Integer.toHexString(dec);
		return hex; 
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(outputChoice == 1) {
			if(choice == 1) { 
				findLetters(binary, g); 
				System.out.println("The binary for " + binary + " is " + binary.toUpperCase()); 
			} else if(choice == 2) {
				String newBin = decimalToBinary(decimal); 
				System.out.println("The binary for " + decimal + " is " + newBin.toUpperCase()); 
				findLetters(newBin, g); 
				
			} else { 
				int newDec = Integer.parseInt(hex, 16); 
				String newBin = decimalToBinary(newDec);
				System.out.println("The binary for " + hex + " is " + newBin.toUpperCase()); 
				findLetters(newBin, g); 
			}
		} else if(outputChoice == 2) {
			if(choice == 1) { 
				int newDec = Integer.parseInt(binary, 2);
				String updatedDec = newDec + "";
				System.out.println("The decimal for " + binary + " is " + updatedDec.toUpperCase()); 
				findLetters(updatedDec, g); 
			} else if(choice == 2) { 
				String newDec = decimal + ""; 
				System.out.println("The decimal for " + decimal + " is " + decimal); 
				findLetters(newDec, g); 
			} else { 
				int newDec = Integer.parseInt(hex, 16); 
				String updatedDec = newDec + ""; 
				System.out.println("The decimal for " + hex + " is " + updatedDec.toUpperCase()); 
				findLetters(updatedDec, g); 
			}
		} else { 
			if(choice == 1) { 
				int newDec = Integer.parseInt(binary, 2); 
				String newHex = decimalToHex(newDec); 
				System.out.println("The hex for " + binary + " is " + newHex.toUpperCase());
				findLetters(newHex, g); 
			} else if(choice == 2) { 
				String newDec = decimalToHex(decimal); 
				System.out.println("The hex for " + decimal + " is " + newDec.toUpperCase());
				findLetters(newDec, g); 
			} else { 
				findLetters(hex, g); 
				System.out.println("The hex for " + hex + " is " + hex.toUpperCase());
			}
		} 
	}
	public void findLetters(String num, Graphics g) { 
	
		for (int x = 0; x < num.length(); x++) {
			if (num.charAt(x) == '0') {
				drawImage(zero, g);
			} 
			if (num.charAt(x) == '1') {
				drawImage(one, g);
			} 
			if (num.charAt(x) == '2') {
				drawImage(two,  g);
			} 
			if (num.charAt(x) == '3') {
				drawImage(three,  g);
			} 
			if (num.charAt(x) == '4') {
				drawImage(four,  g);
			} 
			if (num.charAt(x) == '5') {
				drawImage(five,  g);
			} 
			if (num.charAt(x) == '6') {
				drawImage(six,  g);
			} 
			if (num.charAt(x) == '7') {
				drawImage(seven, g);
			} 
			if (num.charAt(x) == '8') {
				drawImage(eight,  g);
			} 
			if (num.charAt(x) == '9') {
				drawImage(nine,  g);
			} 
			if(num.charAt(x) == 'a') { 
				drawImage(A, g); 
			}
			if(num.charAt(x) == 'b') { 
				drawImage(B, g); 
			}
			if(num.charAt(x) == 'c') { 
				drawImage(C, g); 
			}
			if(num.charAt(x) == 'd') { 
				drawImage(D, g); 
			}
			if(num.charAt(x) == 'e') { 
				drawImage(E, g); 
			}
			if(num.charAt(x) == 'f') { 
				drawImage(F, g); 
			}
		}
	}
	
	public void drawImage(int[][] number, Graphics g) {
		for(int y = 0; y < 5; y++) { 
			for(int z = 0; z < 3; z++) { 
				if(number[y][z] == 1) { 
					g.drawImage(elvin, xStart, yStart, 30, 30, null); 
				} 
				xStart = xStart + 30; 
			}
			xStart = xStart - 90; 
			yStart = yStart + 30; 
		}

		xStart = xStart + 120;
		yStart = yStart - 150;

	}
}