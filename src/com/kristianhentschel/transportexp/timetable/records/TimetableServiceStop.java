package com.kristianhentschel.transportexp.timetable.records;

import com.kristianhentschel.transportexp.timetable.utilities.TimetableTimeOfDay;

/**
 * Created by Kristian on 08/09/2015.
 *
 * A stop, or calling point, in a timetable schedule's list of stops. It is, basically,
 * a glorified triple of the stop (station) and the arrival and departure times.
 */
public class TimetableServiceStop implements Comparable {
    private TimetableService service;
    private TimetableStop stop;
    private TimetableTimeOfDay arrives;
    private TimetableTimeOfDay departs;
    private int itineraryIndex;

    public TimetableServiceStop(TimetableService service, TimetableStop stop) {
        this.service = service;
        this.stop = stop;
        this.arrives = null;
        this.departs = null;
    }

    public void setItineraryIndex(int itineraryIndex) {
        this.itineraryIndex = itineraryIndex;
    }

    public int getItineraryIndex() {
        return this.itineraryIndex;
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

    public TimetableService getService() {
        return service;
    }

    @Override
    /**
     * The comparison is based on the time of day for scheduled arrival or departure times.
     */
    public int compareTo(Object o) {
        if (!(o instanceof TimetableServiceStop))
            return 0;

        TimetableServiceStop other = (TimetableServiceStop)o;

        // if both have a departure time, use that.
        if (getDeparts() != null && other.getDeparts() != null)
            return getDeparts().compareTo(other.getDeparts());

        // if not, and if both have an arrival time, use that instead.
        if (getArrives() != null && other.getArrives() != null)
            return getArrives().compareTo(other.getArrives());

        // finally, if they don't have the same kind of arrival/departure time, assume they are equal.
        // TODO: this is not quite logical. If they both have exactly one kind of time set, those
        // should be compared.
        return 0;
    }
}
