package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import main.Frame;

public class FrameTest {

    private Frame f;

    @Before
    public void setup() {
        f = new Frame();
    }

    @Test
    public void test_default_constructor() {

        assertEquals(f.getArr(0), -1);
        assertEquals(f.getArr(1), -1);

    }

    @Test
    public void test_constructor() {

        Frame f2 = new Frame(5,1);

        assertEquals(f2.getArr(0),5);
        assertEquals(f2.getArr(1),1);

    }
}