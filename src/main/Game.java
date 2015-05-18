package main;

import org.w3c.dom.ranges.RangeException;

import java.util.ArrayList;

/**
 * Created by Nilj on 18/05/15.
 */
public class Game {

    private ArrayList<Frame> frames;
    private final int MAX_FRAMES = 10;

    public Game() {
        frames = new ArrayList<>(10);
    }

    public int getFrameSize() {
        return frames.size();
    }

    public boolean insertFrame(Frame f) {
        boolean result = frames.add(f);

        if (frames.size() > MAX_FRAMES) {
            throw new RangeException((short) 1, "Invalid range");
        }

        return result;
    }

    public int getGameScore() {

        int sum = 0;

        for (Frame f : frames) {
            sum += f.getScore();
        }

        return sum;
    }




}
