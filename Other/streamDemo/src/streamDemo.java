import java.util.*;
import java.util.stream.*;

public class streamDemo {
    public static void main(String[] args) {
        List<Integer> number = Arrays.asList(1,2,3,4,5,6);

        number.stream().forEach(x-> System.out.print(x));
        System.out.println();

        number.stream().forEach(x-> System.out.println(x%2==0));

        List<String> names = Arrays.asList("Aaa","Bbb","Ccc","Abc", "Bcd", "Cde", "Def");
        System.out.println();

        List<String> result = names.stream().filter(s -> s.startsWith("A")).collect(Collectors.toList());
        List<String> result1 = names.stream().filter(s -> s.startsWith("B")).collect(Collectors.toList());
        List<String> result2 = names.stream().filter(s -> s.startsWith("C")).collect(Collectors.toList());
        List<String> result3 = names.stream().filter(s -> s.startsWith("D")).collect(Collectors.toList());

        System.out.println(result);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}
