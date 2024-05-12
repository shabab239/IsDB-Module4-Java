
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Shabab-1281539
 */
public class Question3 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] array = new int[10];
        for (int i = 0; i < 10; i++) {
            System.out.println("Enter Number " + (i + 1) + ": ");
            int number = scanner.nextInt();
            array[i] = number;

        }

        System.out.println("Array: " + Arrays.toString(array));
    }

}
