package com.kristianhentschel.transportexp.timetable.utilities;

import java.util.BitSet;

/**
 * Created by Kristian on 13/08/2015.
 */
public class TimetableDaysOfWeek {
    // the bit set stores flags for each day, with Monday being the first day of the week at bit index 0.
    private BitSet days;

    public TimetableDaysOfWeek() {
        days = new BitSet(7);
    }

    public boolean runsMondays() {
        return days.get(0);
    }

    public boolean runsTuesdays() {
        return days.get(1);
    }

    public boolean runsWednesdays() {
        return days.get(2);
    }

    public boolean runsThursdays() {
        return days.get(3);
    }

    public boolean runsFridays() {
        return days.get(4);
    }

    public boolean runsSaturdays() {
        return days.get(5);
    }

    public boolean runsSundays() {
        return days.get(6);
    }

}
