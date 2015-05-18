package test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;
import main.Frame;
import org.w3c.dom.ranges.RangeException;

public class FrameTest {

    private Frame f;
    private int val1 = 5;
    private int val2 = 1;

    @Before
    public void setup() {
        f = new Frame(val1,val2);
    }

    @Test
    public void test_constructor() {

        assertEquals(val1, f.getArr(0));
        assertEquals(val2, f.getArr(1));

    }

    //Test for FrameScore
    @Test
    public void constructor_GivenInvalidPositiveRange_ExpectedException() {

        Throwable e = null;
        Frame f;
        try {
            f = new Frame(50,50);
        }catch(Throwable ex) {
            e = ex;
        }

        assertTrue(e instanceof RangeException);
    }

    //Test for FrameScore
    @Test
    public void constructor_GivenInvalidNegativeRange_ExpectedException() {

        Throwable e = null;
        Frame f;
        try {
            f = new Frame(-50,-25);
        }catch(Throwable ex) {
            e = ex;
        }

        assertTrue(e instanceof RangeException);
    }

    //Test for FrameScore
    @Test
    public void constructor_GivenBadValuesValidSum_ExpectedException() {

        Throwable e = null;
        Frame f;
        try {
            f = new Frame(-50,50);
        }catch(Throwable ex) {
            e = ex;
        }

        assertTrue(e instanceof RangeException);
    }

    @Test
    public void getScore_GivenSumOfVal1AndVal2_ExpectedSum() {

        int result = f.getScore();
        assertEquals(6, result);

    }
}