

import transportation.Car;
import transportation.Motorcycle;
import transportation.Vehicle;

/**
 *
 * @author shaba
 */
public class Question2 {

    public static void main(String[] args) {

        Car car = new Car();
        car.start();
        car.stop();
        
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.start();
        motorcycle.stop();
        
        
        //Polymorphism
        Vehicle carVehicle = new Car();
        carVehicle.start();
        carVehicle.stop();
        
        Vehicle motorVehicle = new Motorcycle();
        motorVehicle.start();
        motorVehicle.stop();

    }

}
