
import printer.Printer;


/**
 *
 * @author Shabab-1281539
 */
public class Multithreading {


    public static void main(String[] args) {
        
        
        Printer printer = new Printer("LMAO");
        Printer printer2 = new Printer("LOL");
        Printer printer3 = new Printer("LMFAO");
       
        Thread t1 = new Thread(printer);
        Thread t2 = new Thread(printer2);
        Thread t3 = new Thread(printer3);
        
        t1.start();
        t2.start();
        t3.start();
        
        
    }
    
}
