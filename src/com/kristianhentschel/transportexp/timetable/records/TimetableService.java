package com.kristianhentschel.transportexp.timetable.records;

import com.kristianhentschel.transportexp.timetable.utilities.TimetableDate;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableDaysOfWeek;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableTimeOfDay;

import java.util.ArrayList;
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

    // stops with arrival and/or departure times, in service order.
    private List <TimetableServiceStop> schedule;

    private TimetableDaysOfWeek daysOfWeek;

    public TimetableService() {
        schedule = new ArrayList<TimetableServiceStop>();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimetableDate getStartDate() {
        return startDate;
    }

    public void setStartDate(TimetableDate startDate) {
        this.startDate = startDate;
    }

    public TimetableDate getEndDate() {
        return endDate;
    }

    public void setEndDate(TimetableDate endDate) {
        this.endDate = endDate;
    }

    public TimetableDaysOfWeek getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(TimetableDaysOfWeek daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
}
