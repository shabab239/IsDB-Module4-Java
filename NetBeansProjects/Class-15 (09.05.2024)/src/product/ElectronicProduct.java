package product;

public class ElectronicProduct extends Product {

    private int warrantyPeriod;

    @Override
    public double getSalesPrice() {
        double regularPrice = super.getRegularPrice();
        if (warrantyPeriod > 1) {
            return regularPrice - regularPrice * 0.15;
        }
        return regularPrice;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

}
