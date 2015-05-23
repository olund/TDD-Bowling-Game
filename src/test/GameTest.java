package test;

import main.Frame;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import main.Game;
import org.w3c.dom.ranges.RangeException;

/**
 * Created by Olund on 17/05/15.
 */

public class GameTest {

    private Game game;

    @Before
    public void setup() {
        this.game = new Game();
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

    @Test(expected = RangeException.class)
    public void insertFrame_MoreThanTenFrames_ExpectedException() {
        for (int i = 0; i < 11; i++) {
            game.insertFrame(new Frame(1,2));
        }
    }

    @Test
    public void isStrike_GivenStrike_ExpectedTrue(){
        game.insertFrame(new Frame(10, 0));
        boolean result = game.isStrike();
        assertEquals(result, true);
    }

    @Test
    public void isStrike_NonStrike_ExpectedFalse(){
        game.insertFrame(new Frame(5, 5));
        boolean result = game.isStrike();
        assertFalse(result);
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
    public void getGameScore_OneStrike_ShortRun() {
        game.insertFrame(new Frame(10,0));
        game.insertFrame(new Frame(5, 2));

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

    @Test
    public void getGameScore_MultipleSpares_CalculatedSum() {
        game.insertFrame(new Frame(8,2));
        game.insertFrame(new Frame(5,5));
        game.insertFrame(new Frame(7,2));
        game.insertFrame(new Frame(3,6));
        game.insertFrame(new Frame(4,4));
        game.insertFrame(new Frame(5,3));
        game.insertFrame(new Frame(3,3));
        game.insertFrame(new Frame(4,5));
        game.insertFrame(new Frame(8,1));
        game.insertFrame(new Frame(2,6));

        int score = game.getGameScore();
        assertEquals(98, score);
        assertEquals(10, game.getFrameSize());
    }

    @Test
    public void getGameScore_ThreeSpares_CalculatedSum() {
        game.insertFrame(new Frame(8,2));
        game.insertFrame(new Frame(5,5));
        game.insertFrame(new Frame(7,3));
        game.insertFrame(new Frame(3, 6));

        int score = game.getGameScore();
        assertEquals(54, score);
    }

    @Test
    public void getGameScore_LastFrameSpare_CalculatedSum() {
        game.insertFrame(new Frame(8,2));
        game.insertFrame(new Frame(5,5));
        game.insertFrame(new Frame(3,6));
        game.insertFrame(new Frame(7,3));

        int score = game.getGameScore();
        assertEquals(47, score);
    }

    @Test
    public void getGameScore_LastFrameSpare2_CalculatedSum() {
        game.insertFrame(new Frame(1,5));
        game.insertFrame(new Frame(3,6));
        game.insertFrame(new Frame(7,2));
        game.insertFrame(new Frame(3,6));
        game.insertFrame(new Frame(4,4));
        game.insertFrame(new Frame(5,3));
        game.insertFrame(new Frame(3,3));
        game.insertFrame(new Frame(4,5));
        game.insertFrame(new Frame(8, 1));
        // Last frame with bonus throw (7).
        game.insertFrame(new Frame(2, 8, 7));

        int score = game.getGameScore();
        assertEquals(90, score);
    }

    @Test
    public void getGameScore_LastFrameStrike_CalculateSum() {
        game.insertFrame(new Frame(1,5));
        game.insertFrame(new Frame(3,6));
        game.insertFrame(new Frame(7,2));
        game.insertFrame(new Frame(3,6));
        game.insertFrame(new Frame(4,4));
        game.insertFrame(new Frame(5,3));
        game.insertFrame(new Frame(3,3));
        game.insertFrame(new Frame(4,5));
        game.insertFrame(new Frame(8, 1));

        // Last frame with a strike and bonus throw of 9.
        game.insertFrame(new Frame(10, 7, 2));

        int score = game.getGameScore();
        assertEquals(92, score);

    }

    @Test
    public void getGameScore_SecondToLastFrameIsStrikeAndBonusThrowIsStrike_CalculateSum() {
        game.insertFrame(new Frame(1,5));
        game.insertFrame(new Frame(3,6));
        game.insertFrame(new Frame(7,2));
        game.insertFrame(new Frame(3,6));
        game.insertFrame(new Frame(4,4));
        game.insertFrame(new Frame(5,3));
        game.insertFrame(new Frame(3,3));
        game.insertFrame(new Frame(4,5));
        game.insertFrame(new Frame(10, 0));

        // Last frame with a strike and bonus throw of 9.
        game.insertFrame(new Frame(10, 7, 2));

        int score = game.getGameScore();
        assertEquals(110, score);

    }

    @Test
    public void getGameScore_PerfectScore_ExpectedASumOf300() {

        for (int i = 0; i < 9; i++) {
            game.insertFrame(new Frame(10, 0));
        }

        game.insertFrame(new Frame(10,10,10));

        int score = game.getGameScore();
        assertEquals(300, score);
    }

    @Test
    public void getGameScore_RealGame_Stuff() {
        game.insertFrame(new Frame(6,3));
        game.insertFrame(new Frame(7,1));
        game.insertFrame(new Frame(8,2));
        game.insertFrame(new Frame(7,2));
        game.insertFrame(new Frame(10,0));
        game.insertFrame(new Frame(6,2));
        game.insertFrame(new Frame(7,3));
        game.insertFrame(new Frame(10,0));
        game.insertFrame(new Frame(8,0));
        game.insertFrame(new Frame(7,3, 10));

        int score = game.getGameScore();
        assertEquals(135, score);
    }
}