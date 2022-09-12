import java.util.*;

public class duplicateValues {

    public static void duplicate(int[] a){
        for (int i = 0; i<a.length-1;i++){
            for(int j = i+1; j<a.length; j++){
                if((a[i] == a[j]) && (i != j)){
                    System.out.println("The Duplicate values are: "+a[j]);
                }
            }
        }
       // System.out.println("There are no Duplicates!");
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
        duplicate(array);

    }
}
