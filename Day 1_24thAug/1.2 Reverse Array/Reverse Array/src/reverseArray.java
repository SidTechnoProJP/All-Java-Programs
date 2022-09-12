import java.util.*;

public class reverseArray {

    public static void reverse(Integer a[])
    {
        Collections.reverse(Arrays.asList(a));
        System.out.println("The reversed array is = " +Arrays.asList(a));
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter the array size: ");
        int size = s.nextInt();

        Integer[] array = new Integer[size];
        System.out.println("Enter the " + size + " elements: ");
        for (int i = 0; i < array.length; i++) {
            array[i] = s.nextInt();
        }
        reverse(array);
    }
}
