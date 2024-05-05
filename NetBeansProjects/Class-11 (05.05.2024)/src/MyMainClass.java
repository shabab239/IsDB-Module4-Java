
import geometry.Circle;
import math.PrimeNumber;

/**
 *
 * @author Shabab-1281539
 */
public class MyMainClass {

    public static void main(String[] args) {

//        Circle circle = new Circle();
//        circle.setRadius(3.5);
//        
//        System.out.println(circle.getArea());
//        System.out.println(circle.getPerimeter());
//        
//        
//        Circle circle2 = new Circle(3.5);
//        
//        System.out.println(circle2.getArea());
//        System.out.println(circle2.getPerimeter());
        int number = 17;
        PrimeNumber primeNumber = new PrimeNumber(number);
        System.out.println(primeNumber.checkPrimeNumber());

    }

}
