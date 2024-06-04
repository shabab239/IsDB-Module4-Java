
import thread.AlphabetPrinter;
import thread.NumberPrinter;

/**
 *
 * @author Shabab-1281539
 */
public class Question3 {

    public static void main(String[] args) {

        Runnable numberPrinter
                = new NumberPrinter(1, 10);

        Runnable alphabetPrinter
                = new AlphabetPrinter('a', 'j');

        Thread numberThread = new Thread(numberPrinter);
        Thread alphabetThread = new Thread(alphabetPrinter);

        numberThread.start();
        alphabetThread.start();

    }

}
