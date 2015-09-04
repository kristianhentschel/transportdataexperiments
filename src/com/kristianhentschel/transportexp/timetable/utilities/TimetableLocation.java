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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public boolean isEstimated() {
        return estimated;
    }

    public void setEstimated(boolean estimated) {
        this.estimated = estimated;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    private TimeZone getTimeZone() {
        return timeZone;
    }

    public String getTimeZoneID() {
        return timeZone.getID();
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }
}
