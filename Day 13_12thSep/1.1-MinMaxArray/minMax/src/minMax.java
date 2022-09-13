import java.util.*;

public class minMax {


    public int findMax(int[] a) {
        int max = Arrays.stream(a).max().getAsInt();
        System.out.println("The max value is: "+max);
        return max;
    }

    public int findMin(int[] a) {
        int min = Arrays.stream(a).min().getAsInt();
        System.out.println("The min value is: " + min);
        return min;
    }

    public static void main(String[] args) {
//        Scanner s = new Scanner(System.in);

//        System.out.println("Enter the array size: ");
//        int size = s.nextInt();
//
//        int [] array = new int[size];
//        System.out.println("Enter the "+size+" elements: ");
//        for (int i=0; i< array.length;i++){
//            array[i] = s.nextInt();
//        }

        int [] array = new int[5];
        array[0] = 35;
        array[1] = 21;
        array[2] = 56;
        array[3] = 12;
        array[4] = 6;

        minMax minMaxObj = new minMax();

        minMaxObj.findMax(array);
        minMaxObj.findMin(array);

    }
}
