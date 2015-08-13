package com.kristianhentschel.transportexp.timetable.records;

import com.kristianhentschel.transportexp.timetable.utilities.TimetableDate;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableDuration;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableLocation;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableTimeOfDay;

import java.util.Calendar;
import java.util.List;

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
    private TimetableDuration changeTimeMinutes;

    private List<TimetableService> stoppingServices;
    private List<TimetableFixedLink> fixedLinks;

    public long getUTCTime(TimetableDate date, TimetableTimeOfDay time) {
        Calendar c = Calendar.getInstance(location.getTimeZone());
        c.set(date.getYear(), date.getMonth(), date.getDay(), time.getHour(), time.getMinute(), time.getSecond());
        return c.getTime().getTime();
    }
}
