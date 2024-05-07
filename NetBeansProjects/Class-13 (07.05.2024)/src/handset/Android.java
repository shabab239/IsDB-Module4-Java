
package handset;

/**
 *
 * @author Shabab-1281539
 */
public class Android extends Mobile {

    private boolean hasUSBTypeC;

    public Android() {
    }

    public Android(boolean hasUSBTypeC) {
        this.hasUSBTypeC = hasUSBTypeC;
    }

    public Android(boolean hasUSBTypeC, String brandName, String modelName, boolean supportsMultimedia, boolean hasCamera) {
        super(brandName, modelName, supportsMultimedia, hasCamera);
        this.hasUSBTypeC = hasUSBTypeC;
    }

    @Override
    public String toString() {
        System.out.println("Android{" + "hasUSBTypeC=" + hasUSBTypeC + "}");
        return super.toString();
    }

    public boolean hasUSBTypeC() {
        return hasUSBTypeC;
    }

    public void setHasUSBTypeC(boolean hasUSBTypeC) {
        this.hasUSBTypeC = hasUSBTypeC;
    }
    
    

}
