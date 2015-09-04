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
        days.clear();
    }

    /**
     * Sets the running flag for the given day
     * @param d index for the day of the week, from 0 - Monday to 6 - Sunday.
     * @param running boolean flag, true if the service runs this day.
     */
    public void setDay(int d, boolean running) {
        days.set(d, running);
    }

    public boolean getDay(int d) {
        return days.get(d);
    }

    /**
     * Parses a formatted bit string to set the day flags.
     * @param s the string, made up of 7 '1' and '0' characters, the first representing the state for Monday;
     *          e.g. "1111100" for a service that only runs on week-days.
     */
    public void parseString(String s) {
        for(int i = 0; i < 7; i++) {
            days.set(i, (s.charAt(i) == '1'));
        }
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
