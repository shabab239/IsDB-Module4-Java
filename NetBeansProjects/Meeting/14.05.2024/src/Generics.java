
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import printer.Animal;
import printer.DoublePrinter;
import printer.Printer;

/**
 *
 * @author shaba
 */
public class Generics {

    public static void main(String[] args) {
        ArrayList<Integer> myList = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            int count = 0;
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    count++;
                }
            }
            if (count == 2) {
                myList.add(i);
            }
        }

//        Iterator<Integer> myIterator = myList.iterator();
//        while(myIterator.hasNext()){
//            System.out.println(myIterator.next());
//        }
        Map<Integer, String> studentRollAndName = new HashMap<>();
        studentRollAndName.put(1, "Shabab");
        studentRollAndName.put(4, "Luban");
        studentRollAndName.put(3, "Labib");
        studentRollAndName.put(2, "Samin");

        for (Integer roll : studentRollAndName.keySet()) {
            String name = studentRollAndName.get(roll);
            System.out.println(name);
        }

    }

}
