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

    private ArrayList<TimetableServiceStop> serviceStops;

    private List<TimetableFixedLink> fixedLinks;

    private boolean serviceStopsIsSorted;

    public TimetableStop() {
        serviceStops = new ArrayList<TimetableServiceStop>();
        fixedLinks = new ArrayList<TimetableFixedLink>();
        serviceStopsIsSorted = true;
    }

    public void addFixedLink(TimetableFixedLink link) {
        fixedLinks.add(link);
    }

    /**
     * @return an iterator over all timetable service stops in order
     */
    public Iterator<TimetableServiceStop> getServiceStopsIterator() {
        if(!serviceStopsIsSorted) {
            serviceStops.sort(null);
            serviceStopsIsSorted = true;
        }
        return serviceStops.iterator();
    }

    public Iterator<TimetableFixedLink> getFixedLinkIterator() {
        return fixedLinks.iterator();
    }

    public Calendar getCalendar(){
        if (location != null) 
          return Calendar.getInstance(TimeZone.getTimeZone(location.getTimeZoneID()));

        return Calendar.getInstance(TimeZone.getTimeZone("GMT"));
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
        return serviceStops.size();
    }

    public boolean equals(Object other) {
        if (!(other instanceof TimetableStop)) {
            return false;
        } else {
            return this.localStopId == ((TimetableStop) other).localStopId
                    && this.dataSource == ((TimetableStop) other).dataSource;
        }
    }

    public void addServiceStop(TimetableServiceStop serviceStop) {
        serviceStops.add(serviceStop);
        serviceStopsIsSorted = false;
    }
}
