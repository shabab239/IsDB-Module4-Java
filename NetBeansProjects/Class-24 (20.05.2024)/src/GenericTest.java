
import academy.Teacher;
import generic.MyFileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shabab-1281539
 */
public class GenericTest {

    public static void main(String[] args) {
//        try {
//            MyFileWriter writer = new MyFileWriter();
//            writer.setFilePath("C:\\Users\\Admin\\Desktop\\Test.txt");
//            writer.setToWrite("Hi!\n");
//            writer.write();
//            writer.setToWrite(123);
//            writer.write();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(new Teacher(1L, "Skip Khan", "lamo@mail.com", "OMG"));
        teacherList.add(new Teacher(2L, "Skip Khan 2", "lamo@mail.com", "OMG"));
        teacherList.add(new Teacher(3L, "Skip Khan 3", "lamo@mail.com", "OMG"));

        teacherList.forEach(teacher -> {
            System.out.println(teacher.getName());
        });
    }

}
