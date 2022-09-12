import java.util.HashSet;
import java.util.Set;

public class HashSetDemo {
    public static void main(String[] args) {
        Set<String> hash_set = new HashSet<String>();

        hash_set.add("Luke");
        hash_set.add("I");
        hash_set.add("am");
        hash_set.add("your");
        hash_set.add("Father");

        System.out.println(hash_set);
    }
}
