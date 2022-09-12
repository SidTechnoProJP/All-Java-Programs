package p1;
import java.util.Arrays;
public class ArrayOps {

    public static int getSmallestElement(int[] a, int size){
        Arrays.sort(a);
        return a[0];
    }

    public static int[] getScaleArray(int [] a, int sizeFactor) {
        for (int i = 0; i < a.length; i++) {
            a[i] = a[i] * sizeFactor;
        }
        return a;
    }
}

