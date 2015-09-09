package com.kristianhentschel.transportexp.timetable.utilities;

/**
 * Created by Kristian on 13/08/2015.
 *
 * An immutable time-of-day. Represents station-clock time in an unspecified timezone, assumed to be that of the local
 * to the operating company or the specific stop, but this class is not aware of any time zone calculations and simply
 * records what would be printed in a timetable.
 */
public class TimetableTimeOfDay {
    private final int hour;
    private final int minute;
    private final int second;

    public static TimetableTimeOfDay fromStringHHMM (String s) {
        int h = Integer.parseInt(s.substring(0, 2));
        int m = Integer.parseInt(s.substring(2, 4));
        return new TimetableTimeOfDay(h, m);
    }
    public TimetableTimeOfDay(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        this.second = 0;
    }

    public TimetableTimeOfDay(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }
}
