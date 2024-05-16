
import academy.Student;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Shabab-1281539
 */
public class Sets {

    public static void main(String[] args) {

        Set<String> stringSet = new HashSet<>(); //not sorted //default initial capacity - 16, load factor 0.75 (ranges from 0 to 1)
        stringSet.add("LMAO");
        stringSet.add("LOL");
        stringSet.add("LOL");

        System.out.println(stringSet);

        Set<String> stringLinkedSet = new LinkedHashSet<>(); //sorted //default initial capacity - 16, load factor 0.75 (ranges from 0 to 1)
        stringLinkedSet.add("LOL");
        stringLinkedSet.add("LMAO");
        stringLinkedSet.add("LOL"); //this wont be in the hashset

        System.out.println(stringLinkedSet);

        TreeSet<Student> treeSet = new TreeSet<>(new Comparator<Student>() { //TreeSet doesn’t allow even a single null element
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }
        }); //sorted set
        
        treeSet.add(new Student("Shabab"));
        treeSet.add(new Student("Adnan"));
        treeSet.add(new Student("Luban"));

        for (Student s : treeSet) {
            System.out.println(s.getName());
        }

    }

}
