package com.kristianhentschel.transportexp.timetable.utilities;

/**
 * Created by Kristian on 13/08/2015.
 */
public class TimetableDuration {
    private long time; // the total duration in seconds.

    public TimetableDuration() {
        time = 0;
    }


    public void addHours(long hours) {
        time = time + hours * 60 * 60;
    }

    public void addMinutes(long minutes) {
        time = time + minutes * 60;
    }

    public void addSeconds(long seconds) {
        time = time + seconds;
    }

    public long getTime() {
        return time;
    }

    public long getUTCTimeAfter(long startTimeUTC) {
        return startTimeUTC + time;
    }
}
