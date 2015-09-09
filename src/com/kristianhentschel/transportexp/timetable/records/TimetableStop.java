package com.kristianhentschel.transportexp.timetable.records;

import com.kristianhentschel.transportexp.timetable.utilities.TimetableDate;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableDuration;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableLocation;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableTimeOfDay;

import java.util.*;

/**
 * Created by Kristian on 13/08/2015.
 *
 * A stop (train station, bus stop, ferry port, ...) on the transport network is a location at which timetabled services
 * may stop. As a stop must have a geographical location, it is also associated with a time zone to be used when
 * interpreting local arrival and departure times.
 */
public class TimetableStop extends TimetableRecord {
    private String name;
    private String localStopId;
    private TimetableLocation location;
    private TimetableDuration changeTime;

    private List<TimetableService> stoppingServices;
    private List<TimetableFixedLink> fixedLinks;

    private Calendar calendar;

    public TimetableStop() {
        stoppingServices = new ArrayList<TimetableService>();
        fixedLinks = new ArrayList<TimetableFixedLink>();
    }

    public void addService(TimetableService service) {
        stoppingServices.add(service);
    }

    public void addFixedLink(TimetableFixedLink link) {
        fixedLinks.add(link);
    }

    public Iterator<TimetableService> getServiceIterator() {
        return stoppingServices.iterator();
    }

    public Iterator<TimetableFixedLink> getFixedLinkIterator() {
        return fixedLinks.iterator();
    }

    private Calendar getCalendar(){
        if (calendar != null)
            return calendar;
        else
            return calendar = Calendar.getInstance(TimeZone.getTimeZone(location.getTimeZoneID())); // TODO requires initialised location!
    }

    public long getUTCTime(TimetableDate date, TimetableTimeOfDay time) {
        Calendar c = getCalendar();
        c.set(date.getYear(), date.getMonth(), date.getDay(), time.getHour(), time.getMinute(), time.getSecond());
        return c.getTimeInMillis();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalStopId() {
        return localStopId;
    }

    public void setLocalStopId(String localStopId) {
        this.localStopId = localStopId;
    }

    public TimetableLocation getLocation() {
        return location;
    }

    public void setLocation(TimetableLocation location) {
        this.location = location;
    }

    public TimetableDuration getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(TimetableDuration changeTime) {
        this.changeTime = changeTime;
    }

    public int getNumStoppingServices() {
        return stoppingServices.size();
    }

    public boolean equals(Object other) {
        if (!(other instanceof TimetableStop)) {
            return false;
        } else {
            return this.localStopId == ((TimetableStop) other).localStopId
                    && this.dataSource == ((TimetableStop) other).dataSource;
        }
    }
}
