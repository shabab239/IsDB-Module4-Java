
import java.util.Scanner;

/**
 *
 * @author Shabab (1281539)
 */
public class StringClass {

//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter Your Username");
//        String userName = scanner.next();
//
//        if (userName.equalsIgnoreCase("SkipKhan")) {
//            String message = String.format("Hello %s", userName);
//            System.out.println(message);
//            System.out.println("Enter Your Password");
//            String password = scanner.next();
//            if (password.equals("OMG123")) {
//                message = String.format("Welcome %s", userName);
//                System.out.println(message);
//            } else {
//                System.out.println("Wrong Password");
//            }
//        } else {
//            System.out.println("No Account Found for " + userName);
//        }
//
//    }
//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter Your Username");
//        String userName = scanner.next();
//        System.out.println("Enter Your Password");
//        String password = scanner.next();
//        
//
//        if (userName.trim().equalsIgnoreCase("SkipKhan") && password.equals("OMG123")) {
//            String message = String.format("Welcome %s", userName);
//            System.out.println(message);
//        } else {
//            System.out.println("Wrong Credentials (Invalid Username or Password)");
//        }
//
//    }
//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter Your Name");
//        String name = scanner.nextLine(); // nextLine() captures line, next() captures word before first whitespace
//
//        System.out.println("Welcome " + name);
//
//    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter A Word");
        String word = scanner.next();
        word = word.toLowerCase();

        String message = word + " starts with a ";

        if (word.startsWith("a")
                || word.startsWith("e")
                || word.startsWith("i")
                || word.startsWith("o")
                || word.startsWith("u")) {
            message += "vowel";
        } else {
            message += "consonant";
        }
        System.out.println(message);

    }

}
