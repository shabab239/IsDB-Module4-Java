package calculator;

/**
 *
 * @author Shabab-1281539
 */
public class Calculator {

    double number1;
    double number2;

    public Calculator(double number1, double number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    public double doCalculation(int operation) {
        double result = 0.0;
        switch (operation) {
            case 1:
                result = number1 + number2;
                break;
            case 2:
                result = number1 - number2;
                break;
            case 3:
                result = number1 * number2;
                break;
            case 4:
                result = number1 / number2;
                break;
                
        }
        return result;
    }

}
