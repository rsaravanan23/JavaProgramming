//Name: Rishi Saravanan
//Date: 2/1/22
//Program Name: Hangman
//Program Goal: Play and create a hangman game using File IO
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Hangman {
	PrintWriter pw = null;
	File outFile = new File("dictionary.txt");
	Scanner input = null;
	Scanner s1 = new Scanner(System.in);
	
	public static void main(String[] args) {
		Hangman h1 = new Hangman();
		h1.runner();
	}
	public void runner() {
		int life = 5;
		System.out.print("Would you like to add to the dictionary, yes(1) or no(2)? ");
		int choice = s1.nextInt();
		if (choice == 1) {
			System.out.print("\nWhat is your new word? ");
			String word = s1.next();
			append(word); 
		}
		int randomNum = (int) (Math.random() * 808);
		String randomWord = findRandomWord(randomNum);
		String start = "";
		System.out.println("\nHere is your random word: ");
		for (int x = 0; x < randomWord.length(); x++) {
			start = start + "-";
		}
		String newWord = start;
		System.out.print(start);
		String letterGuessed = "()"; 
		while (life != 0) {
			System.out.print("\nPlease guess a letter: ");
			String letter = s1.next();
			letterGuessed = letterGuessed.substring(0,letterGuessed.length()-1) + letter + "," + letterGuessed.charAt(letterGuessed.length()-1);
			if (randomWord.contains(letter)) {
				for (int x = 0; x < randomWord.length(); x++) {
					if (randomWord.substring(x, x + 1).equals(letter)) {
						newWord = newWord.substring(0, x) + letter + "" + newWord.substring(x + 1);
					}
					if(x == randomWord.length()-1) { 
						System.out.println(newWord); 
					}
					if (randomWord.equals(newWord)) {
						System.out.println("That is Correct!! You Win! ");
						System.out.println(randomWord); 
						x = randomWord.length(); 
						randomWord = ""; 
						life = 0; 
					}
				}
				if(life != 0) System.out.println("\nHere are the words you have guessed so far " +letterGuessed.substring(0,letterGuessed.length()-2) + ")"); 
			} else {
				life--;
				System.out.println("\nThat is incorrect. You have " + life + " guesses left");
				System.out.println("Here are the words you have guessed so far " +letterGuessed.substring(0,letterGuessed.length()-2) + ")"); 
			}
		}
		if(randomWord != "") System.out.println("The word was " + randomWord + "!");
	}
	public void append(String str) {
		try {
			pw = new PrintWriter(new FileWriter(outFile, true));
			input = new Scanner(outFile);
			pw.println(str); 
			pw.close();
		} catch (IOException e) {
			System.err.println("Cannot append to " + "dictionary.txt");
		}
	}

	public int numOfWords() {
		int counter = 0;
		while (input.hasNext()) {
			counter++;
			input.next();
		}
		return (counter);
	}
	public String findRandomWord(int start) {
		int counter = 0;
		append("");
		while (input.hasNext()) {
			if (counter == start - 1) {
				String value = input.next();
				return value;
			}
			counter++;
			input.next();
		}
		return "No Word";
	}
}