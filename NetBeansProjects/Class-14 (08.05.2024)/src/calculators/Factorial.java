package calculators;

/**
 *
 * @author Shabab-1281539
 */
public class Factorial {

    int number;

    public int getFactorial() throws Exception {
        int factorial = 1;
        
        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }

        return factorial;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    

}
