package transportation;

/**
 *
 * @author Shabab-1281539
 */
public class Truck extends Vehicle {

    private int weight;

    public Truck() {
    }

    @Override
    public double getSalePrice() {
        double price = super.getRegularPrice();
        if (weight > 2000) {
            price = price - (price * 0.1); //10% discount
        }
        return price;
    }

    public Truck(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
