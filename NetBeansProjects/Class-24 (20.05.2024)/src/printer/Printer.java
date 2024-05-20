package printer;

import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Shabab-1281539
 */
public class Printer implements Runnable {

    private String toPrint;

    public Printer(String toPrint) {
        this.toPrint = toPrint;
    }

    @Override
    public void run() {
        System.out.println(toPrint);
    }

    public String getToPrint() {
        return toPrint;
    }

    public void setToPrint(String toPrint) {
        this.toPrint = toPrint;
    }

}
