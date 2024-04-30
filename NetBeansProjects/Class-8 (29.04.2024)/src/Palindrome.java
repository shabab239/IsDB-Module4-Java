
import java.util.Scanner;


/**
 *
 * @author Shabab (1281539)
 */
public class Palindrome {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a Word: ");
        String word = scanner.next();
        
        if (isPalindrome(word)) {
            System.out.println(word + " is palindrome");
        } else {
            System.out.println(word + " is not palindrome");
        }
        
    }
    
    public static boolean isPalindrome(String word){
        String[] wordArray = word.split("");
        String reversedWord = "";
       
        for (int i = (wordArray.length - 1); i >= 0 ; i--) {
            String character = wordArray[i];
            reversedWord += character;
        }
        
        return word.equalsIgnoreCase(reversedWord);
    }
    
}
