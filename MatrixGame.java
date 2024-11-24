//Name: Rishi Saravanan 
//Date: 1/20/21
//Program Name: MatrixGame
//Program Goal: Use a 2d array to play a Matrix Game with 2 people 
import java.util.Scanner;
public class MatrixGame {
	int[][] array = new int[9][9];

	int[] rowOrderedArray;
	int[] colOrderedArray;
	int[] row = new int[7];
	int[] col = new int[7];

	int playerOneScore = 0;
	int playerTwoScore = 0; 
	int pla1col1 = 0;
	int pla1col2 = 0;
	int pla1row1 = 0;
	int pla1row2 = 0;
	int pla2col1 = 0;
	int pla2col2 = 0;
	int pla2row1 = 0;
	int pla2row2 = 0;
	Scanner s1 = new Scanner(System.in);

	public static void main(String[] args) {
		MatrixGame m1 = new MatrixGame();
		m1.runner();
	}

	public void runner() {
		System.out.print("\n\n\n");
		fillMatrix (array);

		calculateSum(array, row, col);
		int[] colArray = copy(col); 

		markTopThreeRowsAndColumns(row, col, array);
		printValues(array, colArray);
		printPlayerScores();

		do {
			System.out.println("Player1(row), it's your turn:"); 
			System.out.print("Column first then row: "); 
			pla1col1 = s1.nextInt();
			pla1row1  = s1.nextInt();
			pla1col2 = s1.nextInt();
			pla1row2 = s1.nextInt();

			boolean validRowCol = checkRowDistance(pla1col1, pla1col2, pla1row1, pla1row2);

			if (validRowCol) {
				int num = array[pla1row2-1][pla1col2-1]; 
				array[pla1row2-1][pla1col2-1] = array[pla1row1-1][pla1col1-1];
				array[pla1row1-1][pla1col1-1] = num; 

				
				calculateSum(array, row, col);
				colArray = copy(col); 
				markTopThreeRowsAndColumns(row, col, array);
				printValues(array, colArray);
				printPlayerScores();
				
				System.out.println("Player2(col), it's your turn:"); 
				System.out.print("Column first then row: "); 
				
				pla2col1 = s1.nextInt();
				pla2row1  = s1.nextInt();
				pla2col2 = s1.nextInt();
				pla2row2 = s1.nextInt();				
				validRowCol = checkColDistance(pla1col1, pla1col2, pla1row1, pla1row2);

				if (validRowCol) {
					int num2 = array[pla2row2-1][pla2col2-1]; 
					array[pla2row2-1][pla2col2-1] = array[pla2row1-1][pla2col1-1];
					array[pla2row1-1][pla2col1-1] = num2; 
					
					calculateSum(array, row, col);
					colArray = copy(col); 

					markTopThreeRowsAndColumns(row, col, array);
					printValues(array, colArray);
					printPlayerScores();
				}
			} else { 
				System.out.print("\nThat's not a legal move. Try Again: "); 
				pla1col1 = s1.nextInt();
				pla1col2 = s1.nextInt();
				pla1row1 = s1.nextInt();
				pla1row2 = s1.nextInt();
			}
		} while (pla1col1 != 0 && pla1col2 != 0 && pla1row1 != 0 && pla1row2 != 0 && pla2col1 != 0 && pla2col2 != 0 && pla2row1 != 0 && pla2row2 != 0);
		
		if (playerOneScore > playerTwoScore ) { 
			System.out.println("Player 1 Wins!"); 
		} else if(playerOneScore == playerTwoScore) { 
			System.out.println("Its a tie!"); 
		} else { 
			System.out.println("Player 2 Wins!"); 
		}
		System.out.println("Thanks For Playing.");
		System.out.print("\n\n");
	}

	public int[] maxSum(int[] array) {
		for (int z = 0; z < 5; z++) {
			for (int y = 0; y < array.length - 1; y++) {
				int max = Math.max(array[y], array[y + 1]);
				int min = Math.min(array[y], array[y + 1]);
				array[y] = min;
				array[y + 1] = max;
			}
		}
		return array;
	}

	public int[] copy(int[] array) {
		int[] retVal = new int[array.length];
		for (int z = 0; z < array.length; z++) {
			retVal[z] = array[z];
		}
		return retVal;
	}

	public void printValues(int[][] array, int[] colArray) {
		String rowHeader = "| |";
		for (int i = 1; i <= 7; i++) {
			rowHeader+=i;
			rowHeader+="|";
		}
		rowHeader+=" ";

		System.out.println(rowHeader);

		for (int a = 0; a < 7; a++) {
			System.out.print("|"+ (a+1) + "|");
			for (int b = 0; b < 7; b++) {
				System.out.print(array[a][b] + " ");
			}
			if(array[a][7] == -1) 
				System.out.print("*");
			System.out.println();
		}
		System.out.print("   ");
		for (int a=0; a < array.length; a++) {			
			if(array[7][a] == -1) 
				System.out.print("*");
			System.out.print(" ");
		}
		System.out.println();
	}
	
	public void printPlayerScores() {
		System.out.println("Player 1 score = " + playerOneScore);
		System.out.println("Player 2 score = " + playerTwoScore);
	}

	public void fillMatrix(int[][] array) {
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {
				int num = (int) (Math.random() * 10);
				array[x][y] = num;
			}
		}
	}

	public void calculateSum(int[][] array, int[] row, int[] col) {
		int rowSum = 0;
		int colSum = 0;

		for (int i = 0; i < 7; i++) {
			rowSum = 0;
			for (int j = 0; j < 7; j++) {
				rowSum = rowSum + array[i][j];
			}
			row[i] = rowSum;
		}
		for (int i = 0; i < 7; i++) {
			colSum = 0;
			for (int j = 0; j < 7; j++) {
				colSum = colSum + array[j][i];
			}
			col[i] = colSum;
		}
		for (int r = 0; r < 7; r++) {
			array[r][7] = row[r];
		}
		for (int r = 0; r < 7; r++) {
			array[7][r] = col[r];
		}
	}

	public void markTopThreeRowsAndColumns(int[] row, int[] col, int[][]array) {
		rowOrderedArray = maxSum(row);
		colOrderedArray = maxSum(col);
		playerOneScore = 0;
		playerTwoScore = 0;

		for(int c = 0; c < array.length; c++) { 
			if(array[c][7] == rowOrderedArray[5] || array[c][7] == rowOrderedArray[4] || array[c][7] == rowOrderedArray[6]) { 
				playerOneScore+=array[c][7];
				array[c][7] = -1; 
			} else { 
				array[c][7] = 0; 
			}
		}
		for(int c = 0; c < array.length; c++) { 
			if(array[7][c] == colOrderedArray[5] || array[7][c] == colOrderedArray[4] || array[7][c] == colOrderedArray[6]) { 
				playerTwoScore+=array[7][c];
				array[7][c] = -1; 
			} else { 
				array[7][c] = 0; 
			}
		}
	}

	public boolean checkRowDistance(int pla1col1, int pla1col, int pla1row1, int pla1row2) {
		int pla1ColDiff = Math.abs(pla1col1-pla1col2); 
		int pla1RowDiff = Math.abs(pla1row1-pla1row2); 


		if(pla1ColDiff == 0 || pla1RowDiff == 0 && (pla1ColDiff <= 1 || pla1RowDiff <= 1))
			return true;
		else
			return false;
	}
	public boolean checkColDistance(int pla1col1, int pla1col, int pla1row1, int pla1row2) {
		int pla2ColDiff = Math.abs(pla2col1-pla2col2); 
		int pla2RowDiff = Math.abs(pla2row1-pla2row2); 
		if(pla2ColDiff == 0 || pla2RowDiff == 0 && (pla2ColDiff <= 1 || pla2RowDiff <= 1))
			return true;
		else
			return false;
	}
}