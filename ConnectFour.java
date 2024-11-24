//Name: Rishi Saravanan
//Date: 1/26/22
//Program Name: ConnectFour
//Program Goal: Play ConnectFour and ConnectThree with 2 players using 2d arrays. 
import java.util.Scanner;
public class ConnectFour {
	public static void main(String[] args) {
		ConnectFour c1 = new ConnectFour();
		c1.runner();
	}

	public void runner() {
		boolean gameOn = true;
		int count = 0;
		String reqBStr = "";
		String reqSStr = "";
		Scanner s1 = new Scanner(System.in);
		char[][] array = new char[6][7];
		String[] finalArray = new String[29];
		
		System.out.println("\n\n\nWould you like to play Connect4(4) or Connect3(3)? "); 
		int choice = s1.nextInt(); 
		if (choice == 4) {
			reqBStr = "BBBB";
			reqSStr = "RRRR";
		}
		if (choice == 3) {
			reqBStr = "BBB";
			reqSStr = "RRR";
		}
		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 7; y++) {
				array[x][y] = 'O';
			}
		}
		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 7; y++) {
				System.out.print(array[x][y] + " ");
			}
			System.out.println();
		}
		int col1 = 0;
		int col2 = 0;
		System.out.println("Player 1, choose a column.");
		col1 = s1.nextInt();
		for (int i = 5; i >= 0; i--) {
			if (array[i][col1 - 1] == 'O') {
				array[i][col1 - 1] = 'R';
				i = -1;
			}
		}

		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 7; y++) {
				System.out.print(array[x][y] + " ");
			}
			System.out.println();
		}

		System.out.println("Player 2, choose a column.");
		col2 = s1.nextInt();
		for (int i = 5; i >= 0; i--) {
			if (array[i][col2 - 1] == 'O') {
				array[i][col2 - 1] = 'B';
				i = -1;
			}
		}

		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 7; y++) {
				System.out.print(array[x][y] + " ");
			}
			System.out.println();
		}
		while (col1 != 0 && col2 != 0) {
			System.out.println("Player 1, choose a column.");
			col1 = s1.nextInt();
			for (int i = 5; i >= 0; i--) {
				if (array[i][col1 - 1] == 'O') {
					array[i][col1 - 1] = 'R';
					i = -1;
				}
			}
			for (int x = 0; x < 6; x++) {
				for (int y = 0; y < 7; y++) {
					System.out.print(array[x][y] + " ");
					finalArray[count] = finalArray[count] + array[x][y];
				}
				System.out.println();
				count++;
			}
			for (int x = 0; x < 7; x++) {
				String str = vert(array, x);
				finalArray[count] = finalArray[count] + str;
				count++;
			}
			String diag2 = array[2][0] + "" + array[1][1] + array[0][2];
			String diag3 = array[3][0] + "" + array[2][1] + array[1][2] + array[0][3];
			String diag4 = array[4][0] + "" + array[3][1] + array[2][2] + array[1][3] + array[0][4];
			String diag5 = array[5][0] + "" + array[4][1] + array[3][2] + array[2][3] + array[1][4] + array[0][5];
			String diag6 = array[5][1] + "" + array[4][2] + array[3][3] + array[2][4] + array[1][5] + array[0][6];
			String diag7 = array[5][2] + "" + array[4][3] + array[3][4] + array[2][5] + array[1][6];
			String diag8 = array[5][3] + "" + array[4][4] + array[3][5] + array[2][6];
			String diag9 = array[5][4] + "" + array[4][5] + array[3][6];
			String[] diagPlayer1 = { diag2, diag3, diag4, diag5, diag6, diag7, diag8, diag9 };
			for (int y = 0; y < 8; y++) {
				finalArray[count] = diagPlayer1[y];
				count++;
			}

			for (int i = 0; i < 29; i++) {
				if (finalArray[i] != null) {
					if (finalArray[i].contains(reqBStr)) {
						System.out.println("Player 2 Wins!!!");
						gameOn = false;
						break;
					}
					if (finalArray[i].contains(reqSStr)) {
						System.out.println("Player 1 Wins!!!");
						gameOn = false;
						break;
					}
				}
			}
			if (!gameOn)
				return;
			
			System.out.println("Player 2, choose a column.");
			col2 = s1.nextInt();
			for (int i = 5; i >= 0; i--) {
				if (array[i][col2 - 1] == 'O') {
					array[i][col2 - 1] = 'B';
					i = -1;
				}
			}
			for (int x = 0; x < 6; x++) {
				for (int y = 0; y < 7; y++) {
					System.out.print(array[x][y] + " ");
					finalArray[count] = finalArray[count] + array[x][y];
				}
				System.out.println();
				count++;
			}

			diag2 = array[2][0] + "" + array[1][1] + array[0][2];
			diag3 = array[3][0] + "" + array[2][1] + array[1][2] + array[0][3];
			diag4 = array[4][0] + "" + array[3][1] + array[2][2] + array[1][3] + array[0][4];
			diag5 = array[5][0] + "" + array[4][1] + array[3][2] + array[2][3] + array[1][4] + array[0][5];
			diag6 = array[5][1] + "" + array[4][2] + array[3][3] + array[2][4] + array[1][5] + array[0][6];
			diag7 = array[5][2] + "" + array[4][3] + array[3][4] + array[2][5] + array[1][6];
			diag8 = array[5][3] + "" + array[4][4] + array[3][5] + array[2][6];
			diag9 = array[5][4] + "" + array[4][5] + array[3][6];
			String[] diagPlayer2 = { diag2, diag3, diag4, diag5, diag6, diag7, diag8, diag9 };
			for (int z = 0; z < 8; z++) {
				finalArray[count] = diagPlayer2[z];
			}

			for (int i = 0; i < 29; i++) {
				if (finalArray[i] != null) {
					if (finalArray[i].contains(reqBStr)) {
						System.out.println("Player 2 Wins!!!");
						gameOn = false;
						col2 = 0; 
					}
					if (finalArray[i].contains(reqSStr)) {
						System.out.println("Player 1 Wins!!!");
						gameOn = false;
						col1 = 0; 
					}
				}
			}
			if (!gameOn)
				return;

			count = 0;
		}
		System.out.println("Thanks for playing!"); 
		s1.close(); 
	}

	public String vert(char[][] arr, int start) {
		String str = "";
		for (int a = 0; a < 6; a++) {
			str = str + arr[a][start];
		}
		return str;
	}
}