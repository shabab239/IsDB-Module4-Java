
import academy.Student;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shabab-1281539
 */
public class Question3 {

    private static final String FILE_NAME = "students.dat";

    public static void main(String[] args) {

        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Shabab", 24));
        studentList.add(new Student("Samin", 15));
        studentList.add(new Student("Labib", 20));

        writeStudentsToFile(studentList);
        
        readStudentsFromFile();

    }

    public static void writeStudentsToFile(List<Student> studentList) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            for (Student student : studentList) {
                oos.writeObject(student);
            }
            oos.close();
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void readStudentsFromFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
            while (true) {
                try {
                    Student student = (Student) ois.readObject();
                    System.out.println(student);
                } catch (EOFException e) {
                    break;
                }
            }
            ois.close();
        } catch (Exception e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

}
