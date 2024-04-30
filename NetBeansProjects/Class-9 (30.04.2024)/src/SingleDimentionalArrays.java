
import java.util.Arrays;
import java.util.Random;


/**
 *
 * @author Shabab (1281539)
 */
public class SingleDimentionalArrays {

    public static void main(String[] args) {
        
        //Array Declaration
        int[] myArray = new int[5];
        
        //Array Initialization
        //int[] myArray = {5, 10, 15};
        
        for (int i = 0; i < myArray.length; i++) {
            myArray[i] = new Random().nextInt(1, 101);
        }
        System.out.println(Arrays.toString(myArray));
        
        
        //Sum
        int sum = 0;
        for(int number : myArray){
            sum += number;
        }
        System.out.println("Sum: " + sum);
        
        
        //Average
        int avg = Math.round(sum / myArray.length);
        System.out.println("Average: " + avg);
        
        
        //Greater Than Average
        int greaterThanAverageCount = 0;
        for(int number : myArray){
            if (number > avg) {
                greaterThanAverageCount++;
            }
        }
        System.out.println("Greater Than Average Count: " + greaterThanAverageCount);
        
        
        //Max Number
        int max = myArray[0];
        for(int number : myArray){
            if (number > max) {
                max = number;
            }
        }
        System.out.println("Max Number: " + max);
        
        
        //Min Number
        int min = myArray[0];
        for(int number : myArray){
            if (number < min) {
                min = number;
            }
        }
        System.out.println("Min Number: " + min);
        
        
        
    }
    
}
