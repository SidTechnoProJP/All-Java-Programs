import java.util.*;

public class secondLargest {

    public static void secLarge(int[] a, int array_length){
        if(array_length<2){
            System.out.println("There should be at least 2 elements in the array");
        }

        Arrays.sort(a);
        for(int i=array_length-1; i>=0; i--){
            if(a[i] != a[array_length - 1]){
                System.out.println("The second largest element is: "+a[i]);
                return;
            }
        }
        System.out.println("There is no second largest element");
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter the array size: ");
        int size = s.nextInt();


        int[] array = new int[size];
        System.out.println("Enter the " + size + " elements: ");
        for (int i = 0; i < array.length; i++) {
            array[i] = s.nextInt();
        }

        secLarge(array, array.length);

    }
}
