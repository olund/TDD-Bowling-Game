package test;

import jdk.nashorn.internal.ir.annotations.Ignore;
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

    @Test
    public void isStrike_GivenStrike_ExpectedTrue(){
        game.insertFrame(new Frame(10, 0));
        boolean result = game.isStrike();
        assertTrue(result);
    }

    @Test
    public void isStrike_NonStrike_ExpectedFalse(){
        game.insertFrame(new Frame(5, 5));
        boolean result = game.isStrike();
        assertFalse(result);
    }

    @Test
    public void getGameScore_OneStrike_ShortRun() {

        game.insertFrame(new Frame(10,0));
        game.insertFrame(new Frame(5,2));

        int score = game.getGameScore();
        assertEquals(24, score);

    }

    @Test
    public void getGameScore_OneStrike_LongerRun() {

        game.insertFrame(new Frame(1,6));
        game.insertFrame(new Frame(5,2));
        game.insertFrame(new Frame(3,1));
        game.insertFrame(new Frame(10,0));
        game.insertFrame(new Frame(2,6));
        game.insertFrame(new Frame(5,2));

        int score = game.getGameScore();
        assertEquals(51, score);
    }


    @Test
    public void getGameScore_MultipleStrikes() {

        game.insertFrame(new Frame(10,0));
        game.insertFrame(new Frame(10,0));
        game.insertFrame(new Frame(10,0));
        game.insertFrame(new Frame(7,2));

        int score = game.getGameScore();
        assertEquals(85, score);
    }

    @Test
    public void getGameScore_MultipleStrikesMultipleSpares() {

        game.insertFrame(new Frame(10,0));
        game.insertFrame(new Frame(10,0));
        game.insertFrame(new Frame(2,8));
        game.insertFrame(new Frame(10,0));
        game.insertFrame(new Frame(10,0));
        game.insertFrame(new Frame(10,0));
        game.insertFrame(new Frame(10,0));
        game.insertFrame(new Frame(7,2));

        int score = game.getGameScore();
        assertEquals(177, score);
    }

    @Test
    public void getGameScore_OneSpare_ShortRun() {

        game.insertFrame(new Frame(5,5));
        game.insertFrame(new Frame(5,1));

        int score = game.getGameScore();
        assertEquals(21, score);

    }

    @Test
    public void getGameScore_OneStrikeOneSpare_CalculatedSum(){

        game.insertFrame(new Frame(10, 0));
        game.insertFrame(new Frame(5, 5));
        game.insertFrame(new Frame(2, 3));

        int score = game.getGameScore();
        assertEquals(37, score);
    }

    @Test
    public void getGameScore_OneStrikeOneSpare_LongRun(){

        game.insertFrame(new Frame(10,0));
        game.insertFrame(new Frame(4,6));
        game.insertFrame(new Frame(7,2));
        game.insertFrame(new Frame(3,6));
        game.insertFrame(new Frame(4,4));
        game.insertFrame(new Frame(5,3));
        game.insertFrame(new Frame(3,3));
        game.insertFrame(new Frame(4,5));
        game.insertFrame(new Frame(8,1));
        game.insertFrame(new Frame(2,6));

        int score = game.getGameScore();
        assertEquals(103, score);
    }

    @Test
    public void getGameScore_MultipleStrikes_CalculatedSum(){

        game.insertFrame(new Frame(10,0));
        game.insertFrame(new Frame(10,0));
        game.insertFrame(new Frame(7,2));
        game.insertFrame(new Frame(3,6));
        game.insertFrame(new Frame(4,4));
        game.insertFrame(new Frame(5,3));
        game.insertFrame(new Frame(3,3));
        game.insertFrame(new Frame(4,5));
        game.insertFrame(new Frame(8,1));
        game.insertFrame(new Frame(2,6));

        int score = game.getGameScore();
        assertEquals(112, score);
    }
}