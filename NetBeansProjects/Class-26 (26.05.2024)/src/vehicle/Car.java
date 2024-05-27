package vehicle;

/**
 *
 * @author Shabab-1281539
 */
public class Car extends Vehicle {

    @Override
    public void start() {
        System.out.println("Car Engine Started");
    }

    @Override
    public void stop() {
        System.out.println("Car Engine Stopped");
    }

}
