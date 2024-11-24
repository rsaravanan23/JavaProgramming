//Name: Rishi Saravanan
//Date: 1/29/22
//Program Name: BabyNames
//Program Goal: Use file, printWriter, and append to add more babyNames to file
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BabyNames {
	
	Scanner input = null; 
	Scanner s1 = new Scanner(System.in); 
	PrintWriter pw = null; 
	public static void main(String[] args) { 
		BabyNames b1 = new BabyNames(); 
		try {
			b1.runner();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public void runner() throws Exception{ 
		int counter = numOfRows(); 
		openFile();
		printValues();
		closeFile();
		
		// open the file again
		openFile();
		
		System.out.print("\nWould you like to add to the list, yes(1) and no(2)"); 
		int choice = s1.nextInt(); 
		if(choice == 1) { 
			System.out.println("Please enter the name and gender. If done, enter dunzo!"); 
			String newName = s1.next(); 
			String newGender = s1.next(); 
			while(!newName.equals("Dunzo")) {
				pw.println(counter++ + ") " + newName  + "      " + newGender );
				s1.nextLine(); 
				newName = s1.next(); 
				if (newName.equals("Dunzo!")) 
					break;
				newGender = s1.next();
			}
			
			closeFile();
			openFile();
			
			System.out.println("Here is your updated list: ");
			printValues();
			
			closeFile();
			
		}
	}
	public void openFile() {
		File inFileName = new File("BabyNames.txt1");
		try { 
			pw = new PrintWriter(new FileWriter(inFileName, true)); 
			input = new Scanner(inFileName);
		} catch(IOException e) { 
			e.printStackTrace();
			System.err.println("Cannot append to " + "output file"); 
			System.exit(1); 
		}
	}
	
	public void closeFile() {
		pw.close();
		input.close();
	}
	public int numOfRows() { 
		openFile();
		int start = 0; 
		while (input.hasNextLine()) {
			start++; 
			input.nextLine(); 
		}
		closeFile();
		return start; 
	}
	
	public void printValues() throws Exception {
		while (input.hasNextLine()) {
			String str  = input.nextLine(); 
			System.out.println(str);
		}
		throw new Exception ("Test for StackTrace");

	}
}
