package com.kristianhentschel.transportexp.timetable.records;

import com.kristianhentschel.transportexp.timetable.utilities.TimetableDate;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableDaysOfWeek;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableDuration;

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
}
