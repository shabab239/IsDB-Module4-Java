package list;

import handset.Mobile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Shabab-1281539
 */
public class Lists {

    public static void main(String[] args) {
        Mobile mobile = new Mobile();
        mobile.setBrandName("YOLO");
        

        List<Mobile> mobileList = new ArrayList<>();
        mobileList.add(mobile);
        
        System.out.println(Arrays.toString(mobileList.toArray()));
    }

}
