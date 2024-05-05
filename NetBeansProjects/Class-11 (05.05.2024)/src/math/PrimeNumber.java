package math;

/**
 *
 * @author Shabab-1281539
 */
public class PrimeNumber {

    int number = 3;

    public PrimeNumber() {
    }

    public PrimeNumber(int number) {
        this.number = number;
    }

    public String checkPrimeNumber() {
        int count = 0;
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                count++;
            }
        }
        if(count == 2){
            return number + " is a prime number.";
        } else {
            return number + " is not a prime number.";
        }
    }

}
