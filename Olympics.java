//Name: Rishi Saravanan
//Date: 2/5/22
//Program Name: Olympics.java
//Program Goal: Change olympics medals based on input and find totals using File IO
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
public class Olympics {
	File inFile = null; 
	File totalFile = null; 
	Scanner input;
	Scanner s1 = new Scanner(System.in);
	String[] countries = new String[89];
	int[][] arrMedals = new int[88][3];
	String row = "";
	int countriesCounter = 0;
	int rowCounter = 0;
	int colCounter = 0;
	String userInputCountry = "";
	int userInputGold;
	int userInputSilver;
	int userInputBronze;
	
	public static void main(String[] args) {
		Olympics o1 = new Olympics();
		o1.runner();
	}
	public void runner() {
		readFile();
		fillArray();
		getInput();
		checkAndEditFile();
		printFile(inFile, "update");
		printFile(totalFile, "Total");
		closeFiles();
	}
	public void readFile() {

		inFile = new File("olympics.txt");
		totalFile = new File("total.txt"); 
		String inFileName = "olympics.txt";
		try {
			input = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			System.err.println("Cannot find " + inFileName + " file.");
		}
	}			
	/**
	 * Read the file and fill in the array
	 */
	public void fillArray() {
		String countryName = "";
		while (input.hasNext()) {
			String word = input.next(); 
			String tmpWord = word.toLowerCase();
			if(tmpWord.contains("e") || tmpWord.contains("i") || tmpWord.contains("u") || tmpWord.contains("a") || tmpWord.contains("o")) {					
				countryName+=word;
				countryName+=" ";
				countries[countriesCounter] = countryName;
				if (word.equals("Bronze")) {
					countryName = "";
					countriesCounter++;
				}
			} else { 
				arrMedals[rowCounter][colCounter] = Integer.parseInt(word); 
				colCounter++; 
			}
			if(colCounter == 3) { 
				colCounter = 0; 
				rowCounter++; 
				countriesCounter++;
				countryName = "";
			}
		}
	}
	public void getInput() {
		System.out.print("\n\n\nWould you like to make a change, yes(1) or no(2)? ");
		int choice = s1.nextInt();
		s1.nextLine();
		if (choice != 2) {
			System.out.print("\nCountry: ");
			userInputCountry = s1.nextLine(); 
			System.out.print("\nGold: "); 
			userInputGold = s1.nextInt(); 
			System.out.print("\nSilver: "); 
			userInputSilver = s1.nextInt(); 
			System.out.print("\nBronze: "); 
			userInputBronze = s1.nextInt(); 
		}
		System.out.println("\nYour file has been updated and a new file total.txt has been created"); 
		System.out.print("\n\n");
	}
	public void checkAndEditFile() { 
		for(int x = 0; x < 89; x++) { 

			if(countries[x].trim().equals(userInputCountry)) { 
				arrMedals[x-1][0] = userInputGold; 
				arrMedals[x-1][1] = userInputSilver; 
				arrMedals[x-1][2] = userInputBronze; 
				x = 89; 
			}
		}
	}
	public void printFile(File inFile, String outputType) { 
		PrintWriter makesOutput = null; 
		try { 
			makesOutput = new PrintWriter(inFile); 
		} catch(FileNotFoundException e) { 
			System.err.println("Cannot create file to be written."); 
			System.exit(1);
		}
		if (outputType.equals("Total")) {
			makesOutput.printf("%-25s", "Country");
			makesOutput.printf("%5s\n", "Totals");
			for(int x = 1; x < 89; x++) { 
				int sum = arrMedals[x-1][0] + arrMedals[x-1][1] +  arrMedals[x-1][2]; 
				makesOutput.printf("%-25s", countries[x]);
				makesOutput.printf("%-3d\n", sum);
			}
		} else {
			makesOutput.printf("%-25s", "Country");
			makesOutput.printf("%3s", "Gold");
			makesOutput.printf("%12s", "Silver");
			makesOutput.printf("%10s\n", "Bronze");
			for(int x = 1; x < 89; x++) { 
				makesOutput.printf("%-25s", countries[x]);
				makesOutput.printf("%-10d", arrMedals[x-1][0]);
				makesOutput.printf("%-10d", arrMedals[x-1][1]);
				makesOutput.printf("%-10d\n", arrMedals[x-1][2]);
			}
		}
		makesOutput.close();
	}
	public void closeFiles() {
		s1.close(); 
		input.close(); 
	}
}