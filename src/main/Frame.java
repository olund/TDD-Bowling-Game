package main;

import org.w3c.dom.ranges.RangeException;

/**
 * Created by Nilj on 18/05/15.
 */
public class Frame {

    private int[] pins;

    public Frame(int val1, int val2) {


        if (val1 < 0 || val2 < 0) {
            throw new RangeException((short) 1, "Invalid range");
        }

        int sum = val1 + val2;

        if (sum > 10) {
            throw new RangeException((short) 1, "Invalid range");
        }
        this.pins = new int[2];
        this.pins[0] = val1;
        this.pins[1] = val2;

    }

    public int getArr(int pos) {
        return this.pins[pos];
    }

    public int getScore() {
        return this.pins[0] + this.pins[1];
    }
}
