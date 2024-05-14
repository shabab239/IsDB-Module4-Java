
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Shabab-1281539
 */
public class BinaryInputOutput {

    public static void main(String[] args) {

        try {
            File file = new File("C:\\Users\\Admin\\Desktop\\TEST.txt");
            if (!file.exists()) {
                file.createNewFile();
                System.out.println(file.getName() + " File Created");
            }

            FileWriter writer = new FileWriter(file, true);
            writer.append("Hello World!");
            writer.append("\n");
            writer.append("Sup Mate?");
            writer.close();
            System.out.println("Contents Written to File");

            File copyFile = new File("C:\\Users\\Admin\\Desktop\\TEST-Copy.txt");
            if (!copyFile.exists()) {
                copyFile.createNewFile();
                System.out.println(copyFile.getName() + " File Created for Copying");
            }
            BufferedReader inputReader = new BufferedReader(new FileReader(file));
            BufferedWriter outputWriter = new BufferedWriter(new FileWriter(copyFile, true));
            
            String line;
            while ((line = inputReader.readLine()) != null) {
                outputWriter.append(line);
                outputWriter.append("\n");
            }
            outputWriter.close();
            inputReader.close();
            System.out.println("Contents Copied to New File");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
