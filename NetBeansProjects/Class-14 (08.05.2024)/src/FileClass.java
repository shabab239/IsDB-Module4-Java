
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Shabab-1281539
 */
public class FileClass {

    public static void main(String[] args) throws IOException {

        File file = new File("F:\\Shabab Ahmed (1281539)\\IsDB-Module4-Java\\NetBeansProjects\\Class-14 (08.05.2024)\\src\\Notes.txt");
        System.out.println("Exists: " + file.exists());
        System.out.println("Readable: " + file.canRead());
        System.out.println("Writable: " + file.canWrite());
        System.out.println("Is Dir: " + file.isDirectory());
        System.out.println("Is File: " + file.isFile());
        System.out.println("Absolute: " + file.isAbsolute());
        System.out.println("Hidden: " + file.isHidden());

        System.out.println("Abs Path: " + file.getAbsolutePath());
        System.out.println("Canonical Path: " + file.getCanonicalPath());

        System.out.println("Name: " + file.getName());
        System.out.println("Path: " + file.getPath());
        System.out.println("Parent: " + file.getParent());
        System.out.println("Last Modified: " + new Date(file.lastModified()));
        System.out.println("Length: " + file.length());

        File dir = new File("F:\\Shabab Ahmed (1281539)\\IsDB-Module4-Java\\NetBeansProjects\\Class-14 (08.05.2024)\\src");
        File[] files = dir.listFiles();
        System.out.println("Existing Files:\n");
        for (File f : files) {
            System.out.println("\t" + f.getName() + "\n");
        }

        File testFile = new File("F:\\Shabab Ahmed (1281539)\\IsDB-Module4-Java\\NetBeansProjects\\Class-14 (08.05.2024)\\src\\TestFile.txt");
        System.out.println("File Created: " + testFile.createNewFile());

        //delete();
        //mkdir();
        //mkdirs();
        PrintWriter printWriter = new PrintWriter(testFile);
        printWriter.print("Sup Mate?");
        printWriter.println("Sup Mate?");
        printWriter.print("Sup Mate?");
        printWriter.close();

        Scanner fileScanner = new Scanner(testFile);
        while (fileScanner.hasNext()) {
            System.out.println(fileScanner.nextLine());
        }

        URL url = new URL("https://www.google.com");
        Scanner urlScanner = new Scanner(url.openStream());
        
        while (urlScanner.hasNext()) {
            System.out.println(urlScanner.nextLine());
        }

    }

}
