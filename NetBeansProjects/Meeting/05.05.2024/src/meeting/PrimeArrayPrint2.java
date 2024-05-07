package meeting;

import java.util.Arrays;

public class PrimeArrayPrint2 {

    public static void main(String[] args) {
        int[] primeArray = getPrimeArray();
        System.out.println(Arrays.toString(primeArray));
    }

    public static int[] getPrimeArray() {
        int[] primeArray = new int[100];
        
        int index = 0;
        for (int number = 1; number <= 100; number++) {
            int count = 0;
            for (int i = 1; i <= number; i++) {
                if (number % i == 0) {
                    count++;
                }
            }
            if (count == 2) {
                primeArray[index] = number;
                index++;
            }
        }
        return primeArray;
    }
}
