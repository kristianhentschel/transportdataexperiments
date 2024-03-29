package com.kristianhentschel.transportexp.timetable;

import com.kristianhentschel.transportexp.timetable.records.TimetableRecord;
import com.kristianhentschel.transportexp.timetable.records.TimetableService;
import com.kristianhentschel.transportexp.timetable.records.TimetableStop;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Kristian on 09/09/2015.
 * The timetable system describes the timetables within a single frame of reference, usually equivalent to a data source.
 * It holds the system-local unique identifiers for services and/or stops, for example, as well as all timetables
 * within a single system. For example, there could be a system for all UK rail services, with a few European connections
 * that might also be listed in the French rail system data.
 *
 * @TODO: Interchanges/duplicate services between systems will have to be defined.
 */

public class TimetableSystem {
    private TimetableRecord.DATA_SOURCE dataSource;
    private String name;
    private Map<String, TimetableStop> stops;
    private Map<String, TimetableService> services;

    public TimetableSystem(String name, TimetableRecord.DATA_SOURCE dataSource) {
        this.stops = new HashMap<String, TimetableStop>();
        this.services = new HashMap<String, TimetableService>();
        this.name = name;
        this.dataSource = dataSource;
    }

    /**
     * creates or looks up a stop with the given id.
     * @param stopId A unique identifier string for this stop. E.g. the station code or timetable unique id.
     * @return the stop associated with the given ID.
     */
    public TimetableStop getStop(String stopId) {
        // If there already is a stop with this (unique) id, just return it.
        if(hasStop(stopId))
            return stops.get(stopId);

        // If it does not already exist, initialise the stop first.
        TimetableStop stop = new TimetableStop();
        stop.setDataSource(dataSource);
        stops.put(stopId, stop);
        return stop;
    }

    public boolean hasStop(String stopId) {
        return stops.containsKey(stopId);
    }

    /**
     * @return an iterator over all stops, in unspecified order
     */
    public Iterator<TimetableStop> getStopsIterator() {
        return stops.values().iterator();
    }

    public void addService(TimetableService s) {
        services.put(s.getUniqueId(), s);
    }

    public boolean hasService(String serviceName) {
        return services.containsKey(serviceName);
    }

    public TimetableService getService(String serviceName) {
        return services.get(serviceName);
    }
}
