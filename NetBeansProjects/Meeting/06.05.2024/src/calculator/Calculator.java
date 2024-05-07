package calculator;

/**
 *
 * @author shaba
 */
public class Calculator {

    double number1;
    double number2;
    int choice;

    public Calculator() {
    }

    public Calculator(double number1, double number2, int choice) {
        this.number1 = number1;
        this.number2 = number2;
        this.choice = choice;
    }

    public double doCalculation() {
        double result = 0.0;
        switch (choice) {
            case 1:
                result = number1 + number2;
                break;
            case 2:
                result = number1 - number2;
                break;
        }
        return result;
    }

    public boolean checkPrime(int number) {
        
        int count = 0;
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                count++;
            }
        }
        
        if (count == 2) {
            return true;
        } else {
            return false;
        }
    }

}
