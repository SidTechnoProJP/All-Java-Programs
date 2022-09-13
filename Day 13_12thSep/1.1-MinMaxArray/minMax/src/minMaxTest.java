import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class minMaxTest {

    @BeforeAll
    static void beforeAll(){
        System.out.println("Before All");
    }

    @Before
    public void beforeMax(){
        System.out.println("Before MaxMin Class");
    }
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
    @After
    public void afterMin(){
        System.out.println("After Min Max class");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("After All");
    }
}