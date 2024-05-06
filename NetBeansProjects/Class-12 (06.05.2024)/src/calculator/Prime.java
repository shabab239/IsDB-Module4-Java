
import java.math.BigInteger;


/**
 *
 * @author Shabab (1281539)
 */
public class Prime {

    BigInteger number;

    public Prime(BigInteger number) {
        this.number = number;
    }
   
    public boolean isPrime(){
        int count = 0;
        for (BigInteger i = BigInteger.ONE; i.compareTo(number) <= 0; i = i.add(BigInteger.ONE)) {
            if (number.mod(i).equals(BigInteger.ZERO)) {
                count++;
            }
        }
        return count == 2;
    }
    
}
