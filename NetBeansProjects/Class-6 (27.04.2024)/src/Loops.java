
import java.util.Scanner;

/**
 *
 * @author Shabab (1281539)
 */
public class Loops {

//    public static void main(String[] args) {
//        
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter Starting Number: ");
//        int startingNumber = scanner.nextInt();
//        System.out.print("Enter Ending Number: ");
//        int endingNumber = scanner.nextInt();
//        
//        if (startingNumber >= endingNumber) {
//            System.out.print("Starting number needs to be higher than ending number");
//            return;
//        }
//        
//        while (startingNumber <= endingNumber) {            
//            System.out.println(startingNumber + ". Yo Whaddup?");
//            startingNumber++;
//        }
//        
//    }
//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter Starting Number: ");
//        int startingNumber = scanner.nextInt();
//        System.out.print("Enter Ending Number: ");
//        int endingNumber = scanner.nextInt();
//
//        if (startingNumber >= endingNumber) {
//            System.out.print("Starting number needs to be higher than ending number");
//            return;
//        }
//
//        while (startingNumber <= endingNumber) {
//            if (startingNumber % 3 != 0 || startingNumber % 5 != 0) {
//                System.out.println(startingNumber);
//            }
//            startingNumber++;
//        }
//
//    }
//    public static void main(String[] args) {
//        System.out.println("========== Guess The Number Game ==========");
//        final int computerGuess = (int) Math.round(Math.random() * 100);
//
//        Scanner scanner = new Scanner(System.in);
//        
//        boolean hasLost = true;
//        
//        int attempts = 0;
//        int maxAttempts = 5;
//        while (attempts < maxAttempts) {
//            System.out.print("Enter Your Guess: ");
//            int userGuess = scanner.nextInt();
//            if (userGuess < computerGuess) {
//                System.out.println("Your number is less than the computer's guess.");
//            } else if (userGuess > computerGuess) {
//                System.out.println("Your number is greater than the computer's guess.");
//            } else if (userGuess == computerGuess) {
//                System.out.println("Congratlations! Your guessed the correct number.");
//                hasLost = false;
//                break;
//            }
//            attempts++;
//        }
//        if (hasLost) {
//            System.out.println(String.format("Sorry Mate. You Lost. The number was %s.", computerGuess));
//        }
//
//    }
//     public static void main(String[] args) {
//        System.out.println("========== Guess The Number Game (Do-While) ==========");
//        final int computerGuess = (int) Math.round(Math.random() * 100);
//
//        Scanner scanner = new Scanner(System.in);
//        
//        boolean hasLost = true;
//        
//        int attempts = 0;
//        int maxAttempts = 5;
//        do {
//            System.out.print("Enter Your Guess: ");
//            int userGuess = scanner.nextInt();
//            if (userGuess < computerGuess) {
//                System.out.println("Your number is less than the computer's guess.");
//            } else if (userGuess > computerGuess) {
//                System.out.println("Your number is greater than the computer's guess.");
//            } else if (userGuess == computerGuess) {
//                System.out.println("Congratlations! Your guessed the correct number.");
//                hasLost = false;
//                break;
//            }
//            attempts++;
//        } while (attempts < maxAttempts);
//        
//        if (hasLost) {
//            System.out.println(String.format("Sorry Mate. You Lost. The number was %s.", computerGuess));
//        }
//
//    }
//    public static void main(String[] args) {
//        
//        int factorialOf = 17;
//        
//        long result = 1;
//        for (int i = 1; i <= factorialOf; i++) {
//            
//            result *= i;
//            
//        }
//        
//        System.out.println("Factorial: " + result);
//
//    }
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter An Integer Number: ");
//        int number = scanner.nextInt();
//        
//        if (number < 1) {
//            System.out.println("Invalid Number");
//            return;
//        }
//        if (number == 1) {
//            System.out.println(number + " is a not prime number.");
//            return;
//        }
//        boolean isPrime = true;
//        for (int i = 2; i < number; i++) {
//            if (number % i == 0) {
//                isPrime = false;
//            }
//        }
//        if (isPrime) {
//            System.out.println(number + " is a prime number.");
//        } else {
//            System.out.println(number + " is not a prime number.");
//        }
//
//    }
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter Prime Limit: ");
//        int limit = scanner.nextInt();
//
//        for (int i = 2; i < limit; i++) {
//            boolean isPrime = true;
//            for (int j = 2; j < i; j++) {
//                if (i % j == 0) {
//                    isPrime = false;
//                }
//            }
//            if (isPrime) {
//                System.out.println(i);
//            }
//        }
//    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Starting Number: ");
        int start = scanner.nextInt();
        System.out.print("Enter Ending Number: ");
        int limit = scanner.nextInt();

        if (start < 2) {
            start = 2;
        }

        for (int i = start; i < limit; i++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                }
            }
            if (isPrime) {
                System.out.println(i);
            }
        }
    }

}
