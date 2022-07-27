package com.kristianhentschel.transportexp.timetable.utilities;

import java.util.*;

/**
 * Created by Kristian on 13/08/2015.
 *
 * Represents a single, immutable, day as printed in a time table. Times are assumed to be local to the stops affected,
 * but this class is not aware of any time zones.
 * Days and months are 1-based (with month values from 1 to 12, and days from 1 to 31).
 */
public class TimetableDate implements Comparable{
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

    public String toString() {
        return String.format("%02d/%02d/%04d", getDay(), getMonth(), getYear());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof TimetableDate))
            return false;

        TimetableDate other = (TimetableDate) o;
        return year == other.year
                && month == other.month
                && day == other.day;
    }

    @Override
    public int compareTo(Object o) {
        if (o == null)
            throw new NullPointerException();
        if (!(o instanceof TimetableDate))
            throw new IllegalArgumentException();

        TimetableDate other = (TimetableDate)o;

        if (year != other.year)
            return year - other.year;

        if (month != other.month)
            return month - other.month;

        return day - other.day;
    }

    /**
     * Checks whether this date is within the range of the given start and end date (inclusive)
     * @param start
     * @param end
     * @return
     */
    public boolean inRange(TimetableDate start, TimetableDate end) {
        if (this.compareTo(start) < 0)
            return false;
        if (this.compareTo(end) > 0)
            return false;
        return true;
    }

    public int getDayOfWeek(Calendar calendar) {
      Date date = new Date(this.getYear(), this.getMonth(), this.getDay());

      calendar.setFirstDayOfWeek(Calendar.MONDAY);
      calendar.setTime(date);
      return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }
}
