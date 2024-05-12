
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Shabab-1281539
 */
public class Question4 {

    public static void main(String[] args) {

        int[] array = new int[10];
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            array[i] = random.nextInt(100);
        }

        System.out.println("Array: " + Arrays.toString(array));
    }

}
