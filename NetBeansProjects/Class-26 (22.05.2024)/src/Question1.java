
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Shabab-1281539
 */
public class Question1 {

    public static void main(String[] args) {

        int[] myArray = new int[100];

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            myArray[i] = random.nextInt(999);
        }
        System.out.println("Array: " + Arrays.toString(myArray));

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter an index number: ");
            int index = scanner.nextInt();
            
            System.out.println(myArray[index]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Out of Bounds");
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}
