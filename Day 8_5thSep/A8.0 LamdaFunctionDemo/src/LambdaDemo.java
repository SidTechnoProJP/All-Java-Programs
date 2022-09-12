import java.util.ArrayList;

public class LambdaDemo {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        System.out.println("The numbers are: ");
        numbers.forEach((n) -> System.out.print(n+"\t"));
    }
}


