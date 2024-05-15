
package printer;

/**
 *
 * @author shaba
 */
public class Printer<T> {
    
    private T somethingToPrint;

    public Printer(T somethingToPrint) {
        this.somethingToPrint = somethingToPrint;
    }
    
    public void print(){
        System.out.println(somethingToPrint);
    }

    public T getSomethingToPrint() {
        return somethingToPrint;
    }

    public void setSomethingToPrint(T somethingToPrint) {
        this.somethingToPrint = somethingToPrint;
    }
    
    
    
}
