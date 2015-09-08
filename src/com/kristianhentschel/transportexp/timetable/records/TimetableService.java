package com.kristianhentschel.transportexp.timetable.records;

import com.kristianhentschel.transportexp.timetable.utilities.TimetableDate;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableDaysOfWeek;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableTimeOfDay;

import java.util.List;

/**
 * Created by Kristian on 13/08/2015.
 *
 * A timetable service describes a regular scheduled service that runs to the contained timetable
 * on several days/dates. It can be used to create service instances specific to a given day.
 */
public class TimetableService extends TimetableRecord {
    private String operator;
    private String name;
    private TimetableDate startDate;
    private TimetableDate endDate;

    // stops with arrival and/or departure times
    private List <TimetableServiceStop> schedule;

    private TimetableDaysOfWeek daysOfWeek;
}
