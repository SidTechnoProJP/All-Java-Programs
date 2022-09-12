import java.util.Arrays;
import java.util.Scanner;

public class sortArray {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter the array size: ");
        int size = s.nextInt();

        int[] array = new int[size];
        System.out.println("Enter the " + size + " elements: ");
        for (int i = 0; i < array.length; i++) {
            array[i] = s.nextInt();
        }
        Arrays.sort(array);
        System.out.println("The sorted array is = " + Arrays.toString(array));
    }
}
