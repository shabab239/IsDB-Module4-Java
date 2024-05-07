
package handset;

/**
 *
 * @author Shabab-1281539
 */
public class Mobile {
    
    private String brandName;
    private String modelName;
    private boolean supportsMultimedia;
    private boolean hasCamera;

    public Mobile() {
    }
    
    public Mobile(String brandName, String modelName, boolean supportsMultimedia, boolean hasCamera) {
        this.brandName = brandName;
        this.modelName = modelName;
        this.supportsMultimedia = supportsMultimedia;
        this.hasCamera = hasCamera;
    }
    
//    public void printDetails(){
//        this.toString();
//    }

    @Override
    public String toString() {
        return "Mobile{" + "brandName=" + brandName + ", modelName=" + modelName + ", supportsMultimedia=" + supportsMultimedia + ", hasCamera=" + hasCamera + '}';
    }
    
    

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public boolean isSupportsMultimedia() {
        return supportsMultimedia;
    }

    public void setSupportsMultimedia(boolean supportsMultimedia) {
        this.supportsMultimedia = supportsMultimedia;
    }

    public boolean isHasCamera() {
        return hasCamera;
    }

    public void setHasCamera(boolean hasCamera) {
        this.hasCamera = hasCamera;
    }
    
    
    
    
    
    
    
    
}
