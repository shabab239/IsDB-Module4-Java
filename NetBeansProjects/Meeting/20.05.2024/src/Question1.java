
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author shaba
 */
public class Question1 {

    public static void main(String[] args) {

        List<Integer> myList = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            myList.add(random.nextInt(201));
        }
        System.out.println("List: " + myList);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Index: ");

        try {
            int index = scanner.nextInt();
            System.out.println(myList.get(index));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Out of Bounds");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
