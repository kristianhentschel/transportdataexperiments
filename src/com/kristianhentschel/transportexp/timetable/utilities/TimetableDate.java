package com.kristianhentschel.transportexp.timetable.utilities;

/**
 * Created by Kristian on 13/08/2015.
 *
 * Represents a single, immutable, day as printed in a time table. Times are assumed to be local to the stops affected,
 * but this class is not aware of any time zones.
 * Days and months are 1-based (with month values from 1 to 12, and days from 1 to 31).
 */
public class TimetableDate {
    private final int year;
    private final int month;
    private final int day;

    public TimetableDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
