
import handset.Android;
import handset.IPhone;
import handset.Mobile;

/**
 *
 * @author Shabab-1281539
 */
public class InheritanceAndPolymorphism {

    public static void main(String[] args) {

        Android android = new Android();
        android.setBrandName("Xiaomi");
        android.setModelName("Mi 11X");
        android.setHasCamera(true);
        android.setSupportsMultimedia(true);
        android.setHasCamera(true);
        System.out.println(android.toString());

        IPhone iPhone = new IPhone();
        iPhone.setBrandName("Apple");
        iPhone.setModelName("X");
        iPhone.setHasCamera(true);
        iPhone.setSupportsMultimedia(true);
        iPhone.setHasCamera(true);
        System.out.println(iPhone.toString());
        
        Mobile mobile = new Mobile();
        mobile.setBrandName("Nokia");
        mobile.setModelName("3300");
        mobile.setHasCamera(false);
        mobile.setSupportsMultimedia(false);
        System.out.println(mobile.toString());

        Mobile mobilePolyMorphism = new Android();
        mobilePolyMorphism.setBrandName("Xiaomi");
        mobilePolyMorphism.setModelName("Mi 11X");
        mobilePolyMorphism.setHasCamera(true);
        mobilePolyMorphism.setSupportsMultimedia(true);
        mobilePolyMorphism.setHasCamera(true);
        System.out.println(mobilePolyMorphism.toString());
    }

}
