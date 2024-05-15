
import java.util.ArrayList;
import java.util.LinkedList;


/**
 *
 * @author Shabab-1281539
 */
public class Lists {

    public static void main(String[] args) {

        LinkedList<String> linkedList = new LinkedList<>(); //Better when random access is not a priority
        linkedList.add("Data1");
        linkedList.add("Data2");
        linkedList.add("Data3");
        linkedList.remove(); //Removes first element
        
        System.out.println(linkedList);

        ArrayList<String> arrayList = new ArrayList<>(linkedList);//Better when adding and removing elements to and from large data is a priority
        
        System.out.println(arrayList.contains("Data1"));
       
        
    }

}
