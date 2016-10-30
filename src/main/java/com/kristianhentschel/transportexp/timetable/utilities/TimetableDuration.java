package com.kristianhentschel.transportexp.timetable.utilities;

/**
 * Created by Kristian on 13/08/2015.
 *
 * A duration at seconds granularity, to be used for fixed link times and any such durations advertised in minutes,
 * hours, or seconds rather than as a timetable or service frequency.
 */
public class TimetableDuration {
    private long milliseconds; // the total duration in seconds.

    public TimetableDuration() {
        milliseconds = 0;
    }

    public TimetableDuration(long time) {
        this.milliseconds = time;
    }

    public void addHours(long hours) {
        milliseconds = milliseconds + hours * 60 * 60 * 1000;
    }

    public void addMinutes(long minutes) {
        milliseconds = milliseconds + minutes * 60 * 1000;
    }

    public void addSeconds(long seconds) {
        milliseconds = milliseconds + seconds * 1000;
    }

    public long getMilliseconds() {
        return milliseconds;
    }

    /**
     *
     * @param startTimeUTC
     * @return UTC milliseconds in _milliseconds_ after the duration has passed
     */
    public long getUTCTimeAfter(long startTimeUTC) {
        return startTimeUTC + milliseconds;
    }

    public String toString() {
        return Long.toString(milliseconds/1000) + " seconds";
    }
}
