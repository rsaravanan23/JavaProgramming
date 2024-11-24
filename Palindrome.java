//Name: Rishi S.
//Date: 1/4/21
//Program Name: Palindrome.java
import java.util.Scanner;
public class Palindrome {
        Scanner s1 = new Scanner(System.in);
        public static void main(String[] args) {
                Palindrome p1 = new Palindrome();
                p1.runner();
        }
        public void runner() {
                String finalStr = "";
                System.out.print("\n\n\nWould you like to run Palindrome(1) or ReverseIt(2)? "); 
                int choice = s1.nextInt(); 
                if(choice == 1) { 
                		System.out.println("\nPlease enter a String:");
                		String str = s1.nextLine();
                        String newStr = str.replace(" ", "");
                        System.out.println("Size of Palindrome: ");
                        int size = s1.nextInt();
                        for(int x = 0; x<= newStr.length()-size; x++) {
                                if(!newStr.substring(x , x + size).contains(",")) {
                                        boolean result = reverse(newStr.substring(x , x + size));
                                        if(result == true) {
                                                finalStr = finalStr + newStr.substring(x , x + size)  + ", ";
                                        }
                                }
                        }
                        String finalAnswer = finalStr.substring(0, finalStr.length()-1);
                        System.out.println("Here are your palindromes from your list:");
                        System.out.println(finalAnswer);
                        System.out.print("\n\n\n");
                } else if(choice == 2) { 
                		String finalString = ""; 
                		String str = ""; 
                		String newStr = ""; 
                		System.out.println("Please enter your string(s) followed by an enter:");
                		str = s1.next(); 
                		if(str.equals("#Done#")) { 
                			System.out.println("Please enter at least one string other than #Done#.");
                		} else { 
                			finalString  = "_" + str + finalString; 
                		}
                		
                		while(!str.equals("#Done#")) { 
                			str = s1.nextLine(); 
                			if (!str.equals("#Done#")) {
                				newStr = reverse2(str); 
                				finalString = "_" + newStr + finalString; 
                			}
            
                		} 
                			if(!finalString.equals(""))  { 
                				System.out.println("Here is your word reversed: "); 
                				System.out.println(finalString.substring(1, finalString.length()-1)); 
                			}
                		}
                }
		
                
        public String reverse2(String str) {
            String reversedStr = "";
            for(int x = str.length()-1; x>=0; x--) {
                    reversedStr = reversedStr + str.charAt(x);
            }
            return reversedStr; 
        }
        public boolean reverse(String str) {
                String reversedStr = "";
                for(int x = str.length()-1; x>=0; x--) {
                        reversedStr = reversedStr + str.charAt(x);
                }
                if(reversedStr.equals(str)) {
                        return true;
                }
                return false;
        }
}