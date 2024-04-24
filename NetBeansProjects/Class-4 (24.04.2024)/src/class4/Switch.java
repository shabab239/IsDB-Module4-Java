
package class4;

import java.util.Scanner;

/**
 *
 * @author Shabab (1281539)
 */
public class Switch {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter Day Number of a Week: ");
        
        int dayNumber = scanner.nextInt();
        
        String dayName = "";
        switch(dayNumber){
            case 1:
                dayName = "Saturday";
                break;
            case 2:
                dayName = "Sunday";
                break;
            case 3:
                dayName = "Monday";
                break;
            case 4:
                dayName = "Tuesday";
                break;
            case 5:
                dayName = "Wednesday";
                break;
            case 6:
                dayName = "Thursday";
                break;
            case 7:
                dayName = "Friday";
                break;
            default:
                dayName = "Invalid Day Number";
        }
        
        System.out.println(dayName);   
    }
    
}
