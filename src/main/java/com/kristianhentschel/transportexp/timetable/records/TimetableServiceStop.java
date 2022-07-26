package com.kristianhentschel.transportexp.timetable.records;

import com.kristianhentschel.transportexp.timetable.utilities.TimetableTimeOfDay;

abstract class TimetableServiceStopComparable {}

/**
 * Created by Kristian on 08/09/2015.
 *
 * A stop, or calling point, in a timetable schedule's list of stops. It is, basically,
 * a glorified triple of the stop (station) and the arrival and departure times.
 */
public class TimetableServiceStop extends TimetableServiceStopComparable implements Comparable<TimetableServiceStopComparable> {
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
    public int compareTo(TimetableServiceStopComparable o) {
        if (!(o instanceof TimetableServiceStop))
            return 0;

        TimetableServiceStop other = (TimetableServiceStop)o;

        // if both have a departure time, use that.
        if (getDeparts() != null && other.getDeparts() != null)
            return getDeparts().compareTo(other.getDeparts());

        // if not, and if both have an arrival time, use that instead.
        if (getArrives() != null && other.getArrives() != null)
            return getArrives().compareTo(other.getArrives());

        // If they both only have exactly one kind of time set compare those.
        if (getDeparts() != null && other.getArrives() != null)
          return getDeparts().compareTo(other.getArrives());
        if (getArrives() != null && other.getDeparts() != null)
          return getArrives().compareTo(other.getDeparts());

        // finally, if at least one of them doesn't have either tiem, assume they are equal.
        return 0;
    }
}
