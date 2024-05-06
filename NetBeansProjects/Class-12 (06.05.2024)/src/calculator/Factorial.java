package calculator;

import java.math.BigInteger;

/**
 *
 * @author Shabab-1281539
 */
public class Factorial {

    int number = 2;

    public BigInteger getFactorial() {
        BigInteger factorial = BigInteger.ONE;
        
        for (int i = 1; i <= number; i++) {
            factorial = factorial.multiply(new BigInteger(String.valueOf(i)));
        }

        return factorial;
    }
    
    public boolean isPrime(BigInteger bigInteger){
        int count = 0;
        for (BigInteger i = BigInteger.ONE; i.compareTo(bigInteger) <= 0; i = i.add(BigInteger.ONE)) {
            if (bigInteger.mod(i).equals(BigInteger.ZERO)) {
                count++;
            }
        }
        return count == 2;
    }

}
