
import calculator.Calculator;
import calculator.Factorial;
import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author Shabab-1281539
 */
public class OOP {

    public static void main(String[] args) {

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter Number 1:");
//        double number1 = scanner.nextDouble();
//        System.out.println("Enter Number 2:");
//        double number2 = scanner.nextDouble();
//        System.out.println("Select Operation:");
//        System.out.println("(+): Press 1\n(-): Press 2\n(*): Press 3\n(/): Press 4\n");
//        int operation = scanner.nextInt();
//
//        Calculator calculator = new Calculator(number1, number2);
//        double result = calculator.doCalculation(operation);
//        System.out.println("The result is " + result);
        Factorial factorial = new Factorial();
        BigInteger result = factorial.getFactorial();
        boolean isPrime = factorial.isPrime(result);
        System.out.println("Factorial: " + result);



        if (isPrime) {
            System.out.println("The number is prime.");
        } else {
            System.out.println("The number is not prime.");
        }

    }

}
