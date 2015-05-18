package main;

/**
 * Created by Nilj on 18/05/15.
 */
public class Frame {

    private int[] pins;

    public Frame() {

        this.pins = new int[2];
        this.pins[0] = -1;
        this.pins[1] = -1;

    }

    public Frame(int val1, int val2) {

        this.pins = new int[2];
        this.pins[0] = val1;
        this.pins[1] = val2;

    }

    public int getArr(int pos) {
        return this.pins[pos];
    }
}
