package test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;
import main.Frame;
import org.w3c.dom.ranges.RangeException;

/**
 * Created by Olund on 17/05/15.
 */
public class FrameTest {

    private Frame f;

    @Before
    public void setup() {
        this.f = new Frame(5, 1);
    }

    @Test
    public void test_constructor() {
        assertEquals(5, f.getArr(0));
        assertEquals(1, f.getArr(1));
    }

    @Test(expected = RangeException.class)
    public void constructor_GivenInvalidPositiveRange_ExpectedException() {
        Frame f = new Frame(50, 50);
    }

    @Test(expected = RangeException.class)
    public void constructor_GivenInvalidNegativeRange_ExpectedException() {
        Frame f = new Frame(-50, -25);
    }

    @Test(expected = RangeException.class)
    public void constructor_GivenBadValuesValidSum_ExpectedException() {
        Frame f = new Frame(-50, 50);
    }

    @Test
    public void getScore_GivenSumOfVal1AndVal2_ExpectedSum() {
        int result = f.getScore(-1);
        assertEquals(6, result);
    }
}