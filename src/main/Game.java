package main;

import org.w3c.dom.ranges.RangeException;

import java.util.ArrayList;

/**
 * Created by Nilj on 18/05/15.
 */
public class Game {

    public enum states {
        STRIKE,
        STANDARD,
        SPARE,
        LAST_SPARE,
        LAST_STRIKE
    };

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
        boolean strike = false;
        boolean spare = false;
        int numOfStrikes = 0;

        for(int i = 0; i < frames.size(); i++) {

            if(spare) {
                spare = false;
                sum += frames.get(i).getArr(0);
            }

            if(isStrike(i) && i != MAX_FRAMES-1) {
                numOfStrikes++;
                strike = true;
            } else if(isSpare(i)) {
                spare = true;
                sum += 10;
            } else {
                sum += frames.get(i).getScore(1);
            }

            if(!strike) {
                for (int j = 0; j < numOfStrikes; j++) {
                    if (j == 0) {
                        sum += 10 + frames.get(i).getScore(-0);
                    } else {
                        sum += (numOfStrikes * 10) + frames.get(i).getArr(0);
                    }
                }
                numOfStrikes = 0;
            } else if (numOfStrikes == 3) {
                sum += 30;
                numOfStrikes = 2;
            }

            strike = false;
        }

        return sum;
    }

    public boolean isStrike(){
        return frames.get(frames.size()-1).getArr(0) == 10;
    }
    public boolean isStrike(int pos){
        return frames.get(pos).getArr(0) == 10;
    }

    public boolean isSpare(){

        if(frames.get(getFrameSize()-1).getArr(0) == 10){
            return false;
        } else if(frames.get(getFrameSize()-1).getScore(-1) == 10){
            return true;
        }
        return false;
    }

    public boolean isSpare(int pos){

        if(frames.get(pos).getArr(0) == 10){
            return false;
        } else if(frames.get(pos).getScore(1) == 10){
            return true;
        }
        return false;
    }

    private void calculateStrikeSum() {

    }
}
