package thread;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shabab-1281539
 */
public class AlphabetPrinter implements Runnable {

    private char startingAlphabet;
    private char endingAlphabet;

    public AlphabetPrinter(char startingAlphabet, char endingAlphabet) {
        this.startingAlphabet = startingAlphabet;
        this.endingAlphabet = endingAlphabet;
    }

    @Override
    public void run() {
        for (int i = startingAlphabet; i <= endingAlphabet; i++) {
            System.out.print((char) i + " ");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(NumberPrinter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public char getStartingAlphabet() {
        return startingAlphabet;
    }

    public void setStartingAlphabet(char startingAlphabet) {
        this.startingAlphabet = startingAlphabet;
    }

    public char getEndingAlphabet() {
        return endingAlphabet;
    }

    public void setEndingAlphabet(char endingAlphabet) {
        this.endingAlphabet = endingAlphabet;
    }

}
