
import java.util.Scanner;

public class Question2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int sum = 0;
        while (true) {
            System.out.print("Enter A Number: ");
            int number = scanner.nextInt();
            if (number < 0) {
                break;
            }
            sum += number;
        }

        System.out.println("Sum: " +sum);

    }

}
