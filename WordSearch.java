//Name: Rishi Saravanan 
//Date: 1/22/21
//Program Name: WordSearch
//Program Goal: Use a 2d array to play a word search game with 2 people
import java.util.Scanner;
public class WordSearch {
	public static void main(String[] args) {
		WordSearch w1 = new WordSearch();
		w1.runner();
	}

	public void runner() {
		Scanner s1 = new Scanner(System.in);
		char[][] array = new char[8][8];
		String[] finalArray = new String[84];
		String[] wordsUsed = new String[100]; 
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				array[x][y] = (char) (int) (Math.random() * 26 + 65);
			}
		}
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				System.out.print(array[x][y] + " ");
			}
			System.out.println();
		}

		for (int x = 0; x < finalArray.length; x++) {
			finalArray[x] = "";
		}
		int count = 0;
		for (int s = 0; s < 8; s++) {
			for (int t = 0; t < 8; t++) {
				finalArray[count] = finalArray[count] + array[s][t] + "";
			}
			count++;
		}
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				finalArray[count] = finalArray[count] + array[y][x] + "";
			}
			count++;
		}
		for (int s = 0; s < 8; s++) {
			for (int t = 0; t < 8; t++) {
				finalArray[count] = array[s][t] + "" + finalArray[count];
			}
			count++;
		}
		for (int s = 0; s < 8; s++) {
			for (int t = 0; t < 8; t++) {
				finalArray[count] = array[t][s] + "" + finalArray[count];
			}
			count++;
		}
		
		int startCount = 2; 
		for(int a = 2; a < 9; a++) { 
			String str = firstDiag(array, startCount);
			String newStr = secDiag(array, startCount); 
			String reverseStr = reverse(str); 
			String reverseNewStr = reverse(newStr); 

			finalArray[count] = str; 
			startCount++; 
			count++; 
			finalArray[count] = newStr; 
			count++; 
			finalArray[count] = reverseStr; 
			count++; 
			finalArray[count] = reverseNewStr; 
		}
		int player1Score = 0;
		int player2Score = 0;
		String inputTwo = ""; 
		System.out.println("\nPlayer 1,");
		System.out.println("Please enter a word you found in the puzzle(at least 2 chars):");
		String inputOne = s1.next();
		if(!inputOne.equals("Dunzo!")) {
			int count2 = 0; 
			if (!addWordUsed(inputOne, wordsUsed)) {
				for (int x = 0; x < finalArray.length; x++) {
					if (finalArray[x].contains(inputOne.toUpperCase())) {
						count2++; 
						player1Score = player1Score + inputOne.length();
						x = finalArray.length; 
					}
				}
				printScore(player1Score, player2Score,  count2);
			}

			System.out.println("\nPlayer 2,");
			System.out.println("Please enter a word you found in the puzzle(at least 2 chars):");
			inputTwo = s1.next();
			int count3 = 0; 

			if (!addWordUsed(inputTwo, wordsUsed)) {
				for (int x = 0; x < finalArray.length; x++) {
					if (finalArray[x].contains(inputTwo.toUpperCase())) {
						count3++; 
						player2Score = player2Score + inputTwo.length();
						x = finalArray.length; 
					} 
				}
				printScore(player1Score, player2Score,  count3);
			}

		}	
		while(!inputOne.equals("Dunzo!") && !inputTwo.equals("Dunzo!")) { 
			if(!inputOne.equals("Dunzo!") && !inputTwo.equals("Dunzo!")) { 
				System.out.println("\nPlayer 1,");
				System.out.println("Please enter a word you found in the puzzle(at least 2 chars):");
				inputOne = s1.next();
				if(!inputOne.equals("Dunzo!")) {
					int count4 = 0; 
					if (!addWordUsed(inputOne, wordsUsed)) {
						for (int x = 0; x < finalArray.length; x++) {
							if (finalArray[x].contains(inputOne.toUpperCase())) {
								player1Score = player1Score + inputOne.length();
								x = finalArray.length; 
								count4++; 
							}
						}
						printScore(player1Score, player2Score,  count4);
					}

					System.out.println("\nPlayer 2,");
					System.out.println("Please enter a word you found in the puzzle(at least 2 chars):");
					inputTwo = s1.next();
					int count5 = 0; 
					if (!addWordUsed(inputTwo, wordsUsed)) {
						for (int x = 0; x < finalArray.length; x++) {
							if (finalArray[x].contains(inputTwo.toUpperCase())) {
								player2Score = player2Score + inputTwo.length();
								x = finalArray.length; 	
								count5++; 
							}
						}
						printScore(player1Score, player2Score,  count5);

					}
				}	
			}
				
		}
		if (player1Score > player2Score) 
			System.out.println("\nPlayer 1 Wins!"); 
		else if (player1Score < player2Score) 
			System.out.println("\nPlayer 2 Wins"); 
		else 
			System.out.println("\nIts a Tie!"); 
		
		System.out.println("Thanks for playing"); 
	}
	public String firstDiag(char[][] array, int start) { 
		String updatedStr = ""; 
		int count = start; 
		for(int x = 0; x < start; x++) { 
			updatedStr = updatedStr + array[x][count-1]; 
			count--; 
		}
		return updatedStr; 
	}
	public String secDiag(char[][] array, int count) { 
		String updatedStr = ""; 
		for(int x = 7; x > -1; x--) { 
			updatedStr = updatedStr + array[x][count-1]; 
			count--; 
			if(count == 0) x = -1;
		}
		return updatedStr; 
	}
	
	public String reverse(String str) { 
		String newStr = "";
		for(int x = str.length()-1; x > -1; x--) { 
			newStr+=str.charAt(x); 
		}
		return newStr; 
	}
	
	public void printScore(int player1Score, int player2Score, int count) {
		if (count == 0) {
			System.out.println("That word is not in the puzzle");
		}
		System.out.println("Player 1 Score: " + player1Score);
		System.out.println("Player 2 Score: " + player2Score);
	}
	public boolean addWordUsed(String word, String[] wordsUsed) {
		int indexToAdd = 0;
		boolean wordFound = false;
		for (int i=0; i < wordsUsed.length; i++) {
			if (wordsUsed[i] != null && wordsUsed[i].equals(word))  {
				System.out.println("That word has already been used. Please try again.");
				wordFound = true;
				indexToAdd++;
			} 
		}
		if (!wordFound)
			wordsUsed[indexToAdd] = word; 
		
		return wordFound;
	}
	
}