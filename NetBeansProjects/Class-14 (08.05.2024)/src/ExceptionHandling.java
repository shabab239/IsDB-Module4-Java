
import calculators.Factorial;
import calculators.Prime;
import java.util.Scanner;

/**
 *
 * @author Shabab-1281539
 */
public class ExceptionHandling {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter a number");
            Integer number = scanner.nextInt();

            Factorial factorial = new Factorial();
            factorial.setNumber(number);

            int factorialNumber = factorial.getFactorial();

            System.out.println("Factorial: " + factorialNumber);

            Prime prime = new Prime(factorialNumber);
            boolean isPrime = prime.isPrime();
            System.out.println("Is Prime: " + isPrime);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception Handled");
        } finally {
            scanner.close();
        }

        //System.out.printf("LOL %s", "HOLY\n");
    }

}
