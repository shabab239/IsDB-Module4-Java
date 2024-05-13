
import java.util.Scanner;

/**
 *
 * @author Shabab-1281539
 */
public class Recursive {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        int result = getInputs(scanner, sum);
        System.out.println("Sum: " + result);

    }

    public static int getInputs(Scanner scanner, int sum) {
        System.out.print("Enter Number: ");
        int number = scanner.nextInt();
        if (number < 0) {
            return sum;
        }
        sum += number;
        return getInputs(scanner, sum);
    }

}
