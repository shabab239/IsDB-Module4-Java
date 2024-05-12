
import java.util.Scanner;

/**
 *
 * @author Shabab-1281539
 */
public class Question2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter First Number: ");
        int number1 = scanner.nextInt();
        System.out.println("Enter Second Number: ");
        int number2 = scanner.nextInt();
        System.out.println("Enter Third Number: ");
        int number3 = scanner.nextInt();

        int maxNumber;
        if (number1 > number2 && number1 > number3) {
            maxNumber = number1;
        } else if (number2 > number1 && number2 > number3) {
            maxNumber = number2;
        } else {
            maxNumber = number3;
        }
        System.out.println("Max Number: " + maxNumber);

        int minNumber;
        if (number1 < number2 && number1 < number3) {
            minNumber = number1;
        } else if (number2 < number1 && number2 < number3) {
            minNumber = number2;
        } else {
            minNumber = number3;
        }
        System.out.println("Min Number: " + minNumber);
    }

}
