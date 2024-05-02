
/**
 *
 * @author Shabab (1281539)
 */
public class MagicMatrix {

    public static void main(String[] args) {
        int[][] my2DArray = {{2, 7, 6}, {9, 5, 1}, {4, 3, 8}};
        boolean isMagicMatrix = isMagicMatrix(my2DArray);
        System.out.println(isMagicMatrix);
    }

    public static boolean isMagicMatrix(int[][] array) {
        boolean isMagicMatrix = true;
        int prevSum = 0;

        // Checking Rows Sum - Done
        for (int row = 0; row < array.length; row++) {
            int currentSum = 0;
            for (int column = 0; column < array[0].length; column++) {
                currentSum += array[row][column];
            }
            if (row == 0) {
                prevSum = currentSum;
            }
            if (row != 0 && currentSum != prevSum) {
                isMagicMatrix = false;
                break;
            } else if (row != 0 && currentSum == prevSum) {
                prevSum = currentSum;
            }
        }

        // Checking Columns Sum - Done
        for (int column = 0; column < array[0].length; column++) {
            int currentSum = 0;
            for (int row = 0; row < array.length; row++) {
                currentSum += array[row][column];
            }
            if (column == 0) {
                prevSum = currentSum;
            }
            if (column != 0 && currentSum != prevSum) {
                isMagicMatrix = false;
                break;
            } else if (column != 0 && currentSum == prevSum) {
                prevSum = currentSum;
            }
        }

        // Checking Left Diagonal - Done
        int currentSum = 0;
        for (int row = 0; row < array.length; row++) {
            currentSum += array[row][row];
        }
        if (currentSum != prevSum) {
            isMagicMatrix = false;
        }
        
        // Checking Right Diagonal
        

        return isMagicMatrix;
    }
}
