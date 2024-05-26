
package vehicle;

/**
 *
 * @author Shabab-1281539
 */
public class Motorcycle extends Vehicle{
    @Override
    public void start() {
        System.out.println("Motorcycle Engine Started");
    }

    @Override
    public void stop() {
        System.out.println("Motorcycle Engine Stopped");
    }
}
