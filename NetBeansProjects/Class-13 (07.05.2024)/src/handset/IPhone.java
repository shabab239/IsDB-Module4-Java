
package handset;

/**
 *
 * @author Shabab-1281539
 */
public class IPhone extends Mobile{
    
    private boolean hasUSBTypeC;
    
    public IPhone() {
    }

    public IPhone(boolean storeType) {
        this.hasUSBTypeC = storeType;
    }

    public IPhone(boolean hasUSBTypeC, String brandName, String modelName, boolean supportsMultimedia, boolean hasCamera) {
        super(brandName, modelName, supportsMultimedia, hasCamera);
        this.hasUSBTypeC = hasUSBTypeC;
    }

    @Override
    public String toString() {
        System.out.println("IPhone{" + "hasUSBTypeC=" + hasUSBTypeC + "}");
        return super.toString();
    }

    public boolean hasUSBTypeC() {
        return hasUSBTypeC;
    }

    public void setHasUSBTypeC(boolean hasUSBTypeC) {
        this.hasUSBTypeC = hasUSBTypeC;
    }
    
    
}
