package com.kristianhentschel.transportexp.timetable.records;

import com.kristianhentschel.transportexp.timetable.utilities.TimetableLocation;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableTimeOfDay;

/**
 * Created by Kristian on 08/09/2015.
 *
 * A stop, or calling point, in a timetable schedule's list of stops. It is, basically,
 * a glorified triple of the stop (station) and the arrival and departure times.
 */
public class TimetableServiceStop {
    private TimetableService service;
    private TimetableStop stop;
    private TimetableTimeOfDay arrives;
    private TimetableTimeOfDay departs;

    public TimetableServiceStop(TimetableService service, TimetableStop stop) {
        this.service = service;
        this.stop = stop;
        this.arrives = null;
        this.departs = null;
    }

    public void setArrives(TimetableTimeOfDay arrives) {
        this.arrives = arrives;
    }

    public TimetableTimeOfDay getArrives() {
        return arrives;
    }

    public void setDeparts(TimetableTimeOfDay departs) {
        this.departs = departs;
    }

    public TimetableTimeOfDay getDeparts() {
        return departs;
    }

    public TimetableStop getStop() {
        return stop;
    }
}
