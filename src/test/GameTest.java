package test;

import main.Frame;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import main.Game;
import org.w3c.dom.ranges.RangeException;

public class GameTest {

    private Game game;

    @Before
    public void setup() {
        game = new Game();
    }

    @Test
    public void getFrameSize_Standard_ExpectedTenFrames() {
        assertEquals(0, game.getFrameSize());
    }

    @Test
    public void insertFrame_Default_ExpectedFrameToBeAdded() {
        boolean result = game.insertFrame(new Frame(1, 2));

        assertEquals(true, result);
        assertEquals(1, game.getFrameSize());

    }

    @Test
    public void insertFrame_MoreThanTenFrames_ExpectedException() {

        Throwable e = null;

        try {
            for (int i = 0; i < 11; i++) {
                game.insertFrame(new Frame(1,2));
            }
        } catch (Throwable ex) {
            e = ex;
        }
        assertTrue(e instanceof RangeException);
    }

    @Test
    public void getGameScore_Default_ExpectedTheSumOfAllFrames() {

        game.insertFrame(new Frame(1,5)); // 6
        game.insertFrame(new Frame(2,3)); // 5
        game.insertFrame(new Frame(5,5)); // 10

        int result = game.getGameScore();

        assertEquals(21, result);
    }

    @Test
    public void getGameScore_Default2_ExpectedTheSumOfAllFrames() {
        game.insertFrame(new Frame(0,5));
        int result = game.getGameScore();
        assertEquals(5, result);
    }


}