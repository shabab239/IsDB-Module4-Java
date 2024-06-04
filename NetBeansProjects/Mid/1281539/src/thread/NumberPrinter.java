package thread;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shabab-1281539
 */
public class NumberPrinter implements Runnable {

    private int startingNumber;
    private int endingNumber;

    public NumberPrinter(int startingNumber, int endingNumber) {
        this.startingNumber = startingNumber;
        this.endingNumber = endingNumber;
    }

    @Override
    public void run() {
        for (int i = startingNumber; i <= endingNumber; i++) {
            System.out.print(i + " ");
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(NumberPrinter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int getStartingNumber() {
        return startingNumber;
    }

    public void setStartingNumber(int startingNumber) {
        this.startingNumber = startingNumber;
    }

    public int getEndingNumber() {
        return endingNumber;
    }

    public void setEndingNumber(int endingNumber) {
        this.endingNumber = endingNumber;
    }

}
