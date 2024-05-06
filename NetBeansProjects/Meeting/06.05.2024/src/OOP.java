
import calculator.Calculator;
import java.util.Scanner;


/**
 *
 * @author shaba
 */
public class OOP {


    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Number 1");
        double number1 = scanner.nextDouble();
        
        System.out.println("Enter Number 2");
        double number2 = scanner.nextDouble();
        
        System.out.println("Enter Operation:\n(+)- Press 1\n(-) Press 2");
        int choice = scanner.nextInt();
        
        Calculator calculator = new Calculator(number1, number2, choice);
        
        double result = calculator.doCalculation();
        System.out.println("Result: " + result);
        
        boolean isPrime = calculator.checkPrime((int) result);
        
        if (isPrime) {
            System.out.println("The number is prime");
        } else {
            System.out.println("The number is not prime");
        }
    }
    
}
