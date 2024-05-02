
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Shabab (1281539)
 */
public class MultiDimentionalArray {

    public static void main(String[] args) {
//        int[][] my2DArray = new int[3][4];
//        
//        for (int row = 0; row < my2DArray.length; row++) { //Length of Rows
//            for (int column = 0; column < my2DArray[0].length; column++) { //Length of Columns in the first row
//                my2DArray[row][column] = new Random().nextInt(20) + 1;
//            }
//        }
//        
//        System.out.println(Arrays.deepToString(my2DArray));

        int[][] my2DArray = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int sum = doSumOnMultiDimenArrays(my2DArray);
        System.out.println(sum);
    }

    public static int doSumOnMultiDimenArrays(int[][] array) {
        int sum = 0;

        for (int row = 0; row < array.length; row++) {
            for (int column = 0; column < array[0].length; column++) {
                sum += array[row][column];
            }
        }
        return sum;
    }

}
