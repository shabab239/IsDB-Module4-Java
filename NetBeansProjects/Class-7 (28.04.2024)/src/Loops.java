
import java.util.Scanner;

/**
 *
 * @author Shabab (1281539)
 */
public class Loops {

//    public static void main(String[] args) {
//        
//        Scanner scanner = new Scanner(System.in);
//        
//        int sum = 0;
//        
//        while (sum <= 100) { 
//            System.out.print("Enter Number: ");
//            int input = scanner.nextInt();
//            sum += input;
//        }
//        
//        System.out.println("Sum: " + sum);
//        
//    }
//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//
//        int sum = 0;
//
//        while (true) {
//            System.out.print("Enter Number: ");
//            int input = scanner.nextInt();
//            sum += input;
//            if (sum >= 100) {
//                System.out.println("Sum: " + sum);
//                break;
//            }
//        }
//
//    }
//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//
//        int sum = 0;
//
//        while (true) {
//            System.out.print("Enter Number: ");
//            int input = scanner.nextInt();
//            sum += input;
//            if (sum >= 300) {
//                break;
//            }
//            if (sum >= 100 && sum <= 200) {
//                continue;
//            }
//            System.out.println("Sum: " + sum);
//        }
//    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Starting Number: ");
        int startingNum = scanner.nextInt();

        System.out.print("Enter Ending Number: ");
        int endingNum = scanner.nextInt();

        for (int i = startingNum; i <= endingNum; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                continue;
            }
            System.out.println(i);
        }
    }
}
