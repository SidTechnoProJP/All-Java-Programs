import java.util.Arrays;
import java.util.Scanner;
//import static p1.*;
import static p1.ArrayOps.getScaleArray;
import static p1.ArrayOps.getSmallestElement;

public class Driver {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("The size of array: ");
        int n = sc.nextInt();
        int[] array = new int[n];

        System.out.println("Enter the elements: ");
        for (int i=0; i< array.length; i++){
            array[i] = sc.nextInt();
        }

        System.out.println("The smallest element is: "
                +getSmallestElement(array, n));

        System.out.println("\nEnter the Scale factor: ");
        int scaleFactor = sc.nextInt();

        System.out.println("The array after scaling is: "+
                Arrays.toString(getScaleArray(array, scaleFactor)));
    }
}

/*
OUTPUT:

The size of array:
5
Enter the elements:
56
42
1
89
12
The smallest element is: 1

Enter the Scale factor:
2
The array after scaling is: [2, 24, 84, 112, 178]

Process finished with exit code 0

* */