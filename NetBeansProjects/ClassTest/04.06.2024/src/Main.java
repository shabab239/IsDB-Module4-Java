import product.ElectronicProduct;
import product.Product;

/**
 *
 * @author Shabab-1281539
 */
public class Main {

    public static void main(String[] args) {

        Product product = new ElectronicProduct(2);
        System.out.println(product.getSalePrice());

    }
    
}
