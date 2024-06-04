
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author Shabab-1281539
 */
public class Question1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter input to write: ");
        String input = scanner.nextLine();

        File file = new File("TEST.txt");

        try {
            FileWriter writer = new FileWriter(file, true);
            writer.append(input);
            writer.close();
            System.out.println("Writing completed.");
        } catch (Exception e) {
            System.out.println("Could not write to file");
        }
        
        System.out.println("\nReading the file...");
        try {
            FileReader reader = new FileReader(file);
            int character;
            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
            System.out.println("");
            reader.close();
        } catch (Exception e) {
            System.out.println("Could not write to file");
        }

    }

}
