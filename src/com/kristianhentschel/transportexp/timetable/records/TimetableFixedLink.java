package com.kristianhentschel.transportexp.timetable.records;

import com.kristianhentschel.transportexp.timetable.utilities.TimetableDate;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableDaysOfWeek;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableDuration;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableTimeOfDay;

/**
 * Created by Kristian on 13/08/2015.
 *
 * A fixed link is a transfer between two stops that takes a fixed amount of time. It could be a walk,
 * transfer bus, ferry, metro, tram, etc.
 *
 * It mainly has a duration, but may also be running only on certain days of the week, or certain times of the day.
 * Some services might also have a frequency.
 */
public class TimetableFixedLink extends TimetableRecord {
    private String mode;
    private String operator;
    private String name;
    private TimetableDuration duration;
    private TimetableStop origin;
    private TimetableStop destination;
    private TimetableDuration frequency;
    private TimetableDaysOfWeek daysOfWeek;
    private TimetableDate startDate;
    private TimetableDate endDate;
    private TimetableTimeOfDay startTime;
    private TimetableTimeOfDay endTime;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
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

    public TimetableDuration getDuration() {
        return duration;
    }

    public void setDuration(TimetableDuration duration) {
        this.duration = duration;
    }

    public TimetableStop getOrigin() {
        return origin;
    }

    public void setOrigin(TimetableStop origin) {
        this.origin = origin;
    }

    public TimetableStop getDestination() {
        return destination;
    }

    public void setDestination(TimetableStop destination) {
        this.destination = destination;
    }

    public TimetableDuration getFrequency() {
        return frequency;
    }

    public void setFrequency(TimetableDuration frequency) {
        this.frequency = frequency;
    }

    public TimetableDaysOfWeek getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(TimetableDaysOfWeek daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
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

    public void setStartTime(TimetableTimeOfDay startTime) {
        this.startTime = startTime;
    }

    public TimetableTimeOfDay getStartTime() {
        return startTime;
    }

    public void setEndTime(TimetableTimeOfDay endTime) {
        this.endTime = endTime;
    }

    public TimetableTimeOfDay getEndTime() {
        return endTime;
    }
}
