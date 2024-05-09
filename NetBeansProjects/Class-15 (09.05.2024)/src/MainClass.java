
import animal.Dog;
import java.util.Scanner;
import product.ElectronicProduct;
import transportation.Truck;

/**
 *
 * @author Shabab-1281539
 */
public class MainClass {

    public static void main(String[] args) {

        Truck truck = new Truck();
        truck.setWeight(2100);
        truck.setRegularPrice(100);

        System.out.println(truck.getSalesPrice());

        
        ElectronicProduct product = new ElectronicProduct();
        product.setWarrantyPeriod(2);
        product.setRegularPrice(100);

        System.out.println(product.getSalesPrice());
        
        
        Dog dog = new Dog();
        dog.makeSound();
//        Scanner scanner = new Scanner(System.in);
//        double sum = 0.0;
//        while (true) {
//            System.out.println("Enter Number: ");
//            double number = scanner.nextInt();
//            if (number < 0) {
//                break;
//            } else {
//                sum += number;
//            }
//        }
//        System.out.println("Sum: " + sum);

    }

}
