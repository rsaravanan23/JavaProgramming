//Name: Rishi Saravanan
//Date: 1/29/22
//Program Name: PrintTwice
//Program Goal: Print File twice in different ways using files and printWriter
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class PrintTwice {
	Scanner input = null;
	public static void main(String[] args) {
		PrintTwice p1 = new PrintTwice();
		p1.runIt();
	}

	public void runIt() {
		String[] rows = new String[7];
		tryCatchIt();
		int counterRow = 0;
		int counterWords = 0;
		while (input.hasNext()) {
			rows[counterRow] = input.nextLine();
			counterRow++;
		}
		for (int x = rows.length - 1; x >= 0; x--) {
			System.out.println(rows[x]);
		}
		tryCatchIt();
		
		while (input.hasNext()) {
			input.next();
			counterWords++;
		}
		String[] words = new String[counterWords];
		counterWords = 0;
		tryCatchIt(); 
		while (input.hasNext()) {
			words[counterWords] = input.next();
			counterWords++; 
		}
		System.out.println(); 
		for (int y = words.length - 1; y >= 0; y--) {
			System.out.print(words[y] + " ");
		}
		
		input.close();

	}

	public void tryCatchIt() {

		String inFileName = "input.txt";
		input = null;
		try {
			File inFile = new File(inFileName);
			input = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Cannot find " + inFileName + " file.");
		}

	}
}