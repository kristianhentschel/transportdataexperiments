package com.kristianhentschel.transportexp.timetable.utilities;

/**
 * Created by Kristian on 13/08/2015.
 *
 * An immutable time-of-day. Represents station-clock time in an unspecified timezone, assumed to be that of the local
 * to the operating company or the specific stop, but this class is not aware of any time zone calculations and simply
 * records what would be printed in a timetable.
 */
public class TimetableTimeOfDay implements Comparable {
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

    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public int compareTo(Object o) {
        if(o == null || !(o instanceof TimetableTimeOfDay))
            return 0;
        TimetableTimeOfDay other = (TimetableTimeOfDay)o;

        int t1 = hour * 60 * 60 + minute * 60 + second;
        int t2 = other.getHour() * 60 * 60 + other.getMinute() * 60 + other.getSecond();

        if (t1 > t2)
            return 1;

        if (t1 < t2)
            return -1;

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TimetableTimeOfDay))
            return false;

        TimetableTimeOfDay other = (TimetableTimeOfDay)o;

        if (hour != other.getHour())
            return false;

        if (minute != other.getMinute())
            return false;

        if (second != other.getSecond())
            return false;

        return true;
    }
}
