package com.kristianhentschel.transportexp.timetable.utilities;

import java.util.TimeZone;

/**
 * Created by Kristian on 13/08/2015.
 */
public class TimetableLocation {
    private double lat;
    private double lon;
    private boolean estimated;
    private String streetAddress;
    private TimeZone timeZone;

    public TimeZone getTimeZone() {
        return (TimeZone)timeZone.clone();
    }
}
