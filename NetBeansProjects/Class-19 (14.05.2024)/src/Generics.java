
import academy.Student;
import java.util.ArrayList;

/**
 *
 * @author Shabab-1281539
 */
public class Generics {

    public static void main(String[] args) {

        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student(10));
        list.add(new Student("E20"));

        for (Student s : list) {
            System.out.println(s.getId());
        }

    }

}
