package class3;

import java.util.Scanner;

/**
 *
 * @author Shabab (1281539)
 */
public class Selection {

//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Enter Number 1");
//        double num1 = scanner.nextDouble();
//
//        System.out.println("Enter Number 2");
//        double num2 = scanner.nextDouble();
//
//        System.out.println("Enter Number 3");
//        double num3 = scanner.nextDouble();
//
//        double maxNumber;
//        if (num1 >= num2 && num1 >= num3) {
//            maxNumber = num1;
//        } else if (num2 >= num1 && num2 >= num3) {
//            maxNumber = num2;
//        } else {
//            maxNumber = num3;
//        }
//
//        if (maxNumber % 1 == 0) {
//            System.out.println("Max: " + (int) maxNumber);
//        } else {
//            System.out.println("Max: " + maxNumber);
//        }
//
//    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Mark: ");
        double mark = scanner.nextDouble();
        
        if (mark > 100 || mark < 0) {
            System.out.println("Invalid Mark");
            return;
        }
        
        String grade;
        if (mark < 32) {
            grade = "F";
        } else if (mark < 40) {
            grade = "D";
        } else if (mark < 50) {
            grade = "C";
        } else if (mark < 60) {
            grade = "B";
        } else if (mark < 70) {
            grade = "A-";
        } else if (mark < 80) {
            grade = "A";
        } else {
            grade = "A+";
        }
        
        System.out.println("Grade: " + grade);
    }

}
