package transportation;

/**
 *
 * @author Shabab-1281539
 */
public class Truck extends Vehicle {

    private int weight;

    public Truck() {
    }

    public Truck(int weight) {
        this.weight = weight;
    }

    public Truck(int weight, int speed, double regularPrice, String color) {
        super(speed, regularPrice, color);
        this.weight = weight;
    }

    @Override
    public double getSalesPrice() {
        double regularPrice = super.getRegularPrice();
        if (this.weight > 2000) {
            return regularPrice - (regularPrice * 0.1);
        }
        return regularPrice;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
