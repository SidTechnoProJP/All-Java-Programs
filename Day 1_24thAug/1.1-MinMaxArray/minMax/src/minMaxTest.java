import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class minMaxTest {

    @Test
    void findMax() {
        int []a = new int[5];
        a[0] = 35;
        a[1] = 21;
        a[2] = 56;
        a[3] = 12;
        a[4] = 56;
        minMax maxTestObj = new minMax();
        assertEquals(56,maxTestObj.findMax(a));
    }

    @Test
    void findMin() {
        int []a = new int[5];
        a[0] = 35;
        a[1] = 21;
        a[2] = 6;
        a[3] = 12;
        a[4] = 6;
        minMax minTestObj = new minMax();
        assertEquals(6,minTestObj.findMin(a));
    }
}