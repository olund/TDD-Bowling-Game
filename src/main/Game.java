package main;

import org.w3c.dom.ranges.RangeException;

import java.util.ArrayList;

/**
 * Created by Nilj on 18/05/15.
 */
public class Game {

    private ArrayList<Frame> frames;
    private final int MAX_FRAMES = 11;

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

            if(strike) {
                strike = false;
                //sum += frames.get(i).getScore();
            } else if(spare) {
                spare = false;
                sum += frames.get(i).getArr(0);
            }


            if(isStrike(i)) {
                numOfStrikes++;
                strike = true;

            } else if(isSpare(i)) {
                spare = true;
                sum += 10;
            } else {
                sum += frames.get(i).getScore();
            }

            if(!strike) {
                for (int j = 0; j < numOfStrikes; j++) {
                    if (j == 0) {
                        sum += 10 + frames.get(i).getScore();
                    } else {
                        sum += (numOfStrikes * 10) + frames.get(i).getArr(0);
                    }
                }
                numOfStrikes = 0;
            } else if (numOfStrikes == 3) {
                sum += 30;
                numOfStrikes = 2;
            }
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
        } else if(frames.get(getFrameSize()-1).getScore() == 10){
            return true;
        }
        return false;
    }

    public boolean isSpare(int pos){

        if(frames.get(pos).getArr(0) == 10){
            return false;
        } else if(frames.get(pos).getScore() == 10){
            return true;
        }
        return false;
    }
}
