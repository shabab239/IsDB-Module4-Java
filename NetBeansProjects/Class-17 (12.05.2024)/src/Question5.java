
import java.util.Scanner;

/**
 *
 * @author Shabab-1281539
 */
public class Question5 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Number To Find Factorial: ");
        int number = scanner.nextInt();

        int factorial = 1;
        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }

        System.out.println("Factorial: " + factorial);
    }

}
