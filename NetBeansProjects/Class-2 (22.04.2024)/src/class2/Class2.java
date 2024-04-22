package class2;

import java.util.Scanner;

/**
 *
 * @author Shabab (1281539)
 */
public class Class2 {

//    public static void main(String[] args) {
//        final double PI = 3.1416;
//
//        System.out.println("Please enter the value of radius...");
//        
//        Scanner scanner = new Scanner(System.in);
//        double radius = scanner.nextDouble();
//        
//        double area = Math.pow(radius, 2) * PI;
//
//        System.out.println("Area of the circle is " + area);
//    }
    
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        
//        System.out.println("Please enter length of the rectangle");
//        
//        double length = scanner.nextDouble();
//        
//        System.out.println("Please enter width of the rectangle");
//        
//        double width = scanner.nextDouble();
//        
//        double area = length * width;
//
//        System.out.println("Area of the rectangle is " + area);
//    }
    
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter number 1");
        double number1 = scanner.nextDouble();
        
        System.out.println("Please enter number 2");
        double number2 = scanner.nextDouble();
        
        System.out.println("Please enter number 3");
        double number3 = scanner.nextDouble();
        
        System.out.println("Please enter number 4");
        double number4 = scanner.nextDouble();
        
        double sum = number1 + number2 + number3 + number4;

        System.out.println("Sum is " + sum);
    }

}
