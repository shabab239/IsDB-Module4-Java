
package academy;

/**
 *
 * @author Shabab-1281539
 */
public class Student<E> {
    
    private E id;

    public Student(E id) {
        this.id = id;
    }

    public E getId() {
        return id;
    }

    public void setId(E id) {
        this.id = id;
    }
    
    
    
}
