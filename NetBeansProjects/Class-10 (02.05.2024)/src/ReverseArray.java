
import java.util.Arrays;

/**
 *
 * @author Shabab (1281539)
 */
public class ReverseArray {
    
    public static void main(String[] args) {
       
        int[] myArray = new int[100];
        
        for (int i = 0; i < myArray.length; i++) {
            myArray[i] = i + 1;
        }
        
        System.out.println(Arrays.toString(myArray));
        System.out.println(Arrays.toString(getReversedArray(myArray)));
        
    }
    
    public static int[] getReversedArray(int[] array){
        int[] reversedArray = new int[array.length];
        
        int index = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            reversedArray[index] = array[i];
            index++;
        }
        
        return reversedArray;
    }
    
}
