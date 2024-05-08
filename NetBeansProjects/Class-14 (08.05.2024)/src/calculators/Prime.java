package calculators;
import java.math.BigInteger;


/**
 *
 * @author Shabab (1281539)
 */
public class Prime {

    int number;

    public Prime(int number) {
        this.number = number;
    }
    
    public boolean isPrime() throws Exception{
        int count = 0;
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                count++;
            }
        }
        return count == 2;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    
    
    
}
