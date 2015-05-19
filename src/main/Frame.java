package main;

import org.w3c.dom.ranges.RangeException;

import java.util.ArrayList;

/**
 * Created by Nilj on 18/05/15.
 */
public class Frame {

    private ArrayList<Integer> pins;

    public Frame(int val1, int val2) {

        if (val1 < 0 || val2 < 0) {
            throw new RangeException((short) 1, "Invalid range");
        }

        int sum = val1 + val2;

        if (sum > 10) {
            throw new RangeException((short) 1, "Invalid range");
        }

        this.pins = new ArrayList<>(2);
        this.pins.add(0, val1);
        this.pins.add(1, val2);
    }

    public Frame(int val1, int val2, int val3) {
        if (val1 < 0 || val2 < 0) {
            throw new RangeException((short) 1, "Invalid range");
        }

        int sum = val1 + val2;

        if (sum != 10) {
            //throw new Exception("not");
        }

        this.pins = new ArrayList<>(2);
        this.pins.add(0, val1);
        this.pins.add(1, val2);
        this.pins.add(2, val3);

    }

    public int getArr(int pos) {
        return this.pins.get(pos);
    }

    public int getScore(int state) {

        int sum = this.pins.get(0) + this.pins.get(1);

        if (this.pins.size() > 2 && state == 1) {
            sum += this.pins.get(2);
        }

        return sum;
    }
}
