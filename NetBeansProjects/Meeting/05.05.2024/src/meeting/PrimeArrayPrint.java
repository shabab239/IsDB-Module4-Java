package meeting;

import java.util.Arrays;

public class PrimeArrayPrint {

    public static void main(String[] args) {
        int[] myArray = new int[100];
        for (int i = 0; i < myArray.length; i++) {
            myArray[i] = i + 1;
        }

        System.out.println(Arrays.toString(getPrimeArray(myArray)));
    }

    public static int[] getPrimeArray(int[] array) {
        int[] primeArray = new int[array.length];
        
        int index = 0;
        for (int number : array) {
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
