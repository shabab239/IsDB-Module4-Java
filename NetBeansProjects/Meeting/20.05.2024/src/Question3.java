
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import student.Student;

/**
 *
 * @author shaba
 */
public class Question3 {

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\shaba\\OneDrive\\Desktop\\students.dat");

        Student student = new Student("Skip Khan", 90);
        Student student2 = new Student("Skip Khan 2", 90);
        Student student3 = new Student("Skip Khan 3", 90);

        DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
        dos.writeUTF(student.toString());
        dos.writeUTF(student2.toString());
        dos.writeUTF(student3.toString());
        dos.close();

        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        while (dis.available() > 0) {
            System.out.println(dis.readUTF());
        }
    }

}
