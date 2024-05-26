
import vehicle.Car;
import vehicle.Motorcycle;
import vehicle.Vehicle;



/**
 *
 * @author Shabab-1281539
 */
public class Question2 {
    
    public static void main(String[] args) {
        
        Car car = new Car();
        car.start();
        car.stop();
        
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.start();
        motorcycle.stop();
        
        Vehicle carVehicle = new Car();
        carVehicle.start();
        carVehicle.stop();
        
        Vehicle motorVehicle = new Motorcycle();
        motorVehicle.start();
        motorVehicle.stop();
        
    }
    
}
