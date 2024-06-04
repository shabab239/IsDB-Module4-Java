
import transportation.Truck;

/**
 *
 * @author Shabab-1281539
 */
public class Question2 {

    public static void main(String[] args) {

        Truck truck = new Truck();
        truck.setRegularPrice(2200);
        truck.setWeight(3000);

        System.out.println("Weight: " + truck.getWeight());
        System.out.println("Regular Price: " + truck.getRegularPrice());
        System.out.println("Sale Price: " + truck.getSalePrice());

    }

}
