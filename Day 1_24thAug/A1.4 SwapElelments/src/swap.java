import java.util.*;

public class swap {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the values of x & y: ");
        int x = s.nextInt();
        int y = s.nextInt();
        System.out.println("The current values of x & y are "+x+" & "+y);

        int temp;
        temp = x;
        x = y;
        y = temp;

        System.out.println("The swapped values of x & y are "+x+" & "+y);

    }
}
