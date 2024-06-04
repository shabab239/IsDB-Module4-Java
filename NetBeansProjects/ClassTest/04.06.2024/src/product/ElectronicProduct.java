package product;

public class ElectronicProduct extends Product {
    private int warrantyPeriod;
    private final double discount = 0.15; //15%

    public ElectronicProduct(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public double getSalePrice() {
        double price = super.getSalePrice();
        if (warrantyPeriod > 1) {
            price = price - (price * discount);
        }
        return price;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
}
