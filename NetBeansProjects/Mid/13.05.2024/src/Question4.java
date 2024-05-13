
import java.util.Scanner;

public class Question4 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] numberArray = new int[5];
        for (int i = 0; i < numberArray.length; i++) {
            System.out.print("Enter Number " + (i + 1) + ": ");
            numberArray[i] = scanner.nextInt();
        }

        int lowestNumber = numberArray[0];
        int highestNumber = numberArray[0];
        for (int number : numberArray) {
            if (number < lowestNumber) {
                lowestNumber = number;
            }
            if (number > highestNumber) {
                highestNumber = number;
            }
        }
        System.out.println("Lowest Number: " + lowestNumber);
        System.out.println("Highest Number: " + highestNumber);

    }

}
