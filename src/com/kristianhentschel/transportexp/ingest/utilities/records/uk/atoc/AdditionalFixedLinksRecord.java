package com.kristianhentschel.transportexp.ingest.utilities.records.uk.atoc;

import com.kristianhentschel.transportexp.ingest.utilities.records.CommaSeparatedKeyValuesRecord;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Kristian on 06/08/2015.
 * Describes an Additional Fixed Links file record as specified in the
 * ATOC document RSPS5041 Version 06-00 (Section 3.2.7.2).
 */
public class AdditionalFixedLinksRecord extends CommaSeparatedKeyValuesRecord {
    public static enum MODE {BUS, TUBE, WALK, FERRY, METRO, TRAM, TAXI, TRANSFER};
    private static DateFormat running_dates_format;

    private MODE mode;
    private String origin;
    private String destination;
    private int time;
    // TODO: the time of day fields should use a class - ATOCTimeOfDay extends TimeOfDay to do time zone conversions?
    private String start_time;
    private String end_time;
    private int priority;
    private Date start_date;
    private Date end_date;
    // TODO: days of week should be some kind of bit field.
    private String days_of_week;

    public AdditionalFixedLinksRecord(String record_text) {
        super(record_text);

        // Initialise the date formats
        //running_dates_format = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        running_dates_format = new SimpleDateFormat("dd/mm/yyyy", Locale.UK);

        setMode(getRawValue("M"));
        setOrigin(getRawValue("O"));
        setDestination(getRawValue("D"));
        setTime(getRawValue("T"));
        setStartTime(getRawValue("S"));
        setEndTime(getRawValue("E"));
        setPriority(getRawValue("P"));
        setStartDate(getRawValue("F"));
        setEndDate(getRawValue("U"));
        setDaysOfWeek(getRawValue("R"));
    }

    public String getOrigin() {
        return origin;
    }

    private void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    private void setDestination(String destination) {
        this.destination = destination;
    }

    public int getTime() {
        return time;
    }

    private void setTime(String time_str) {
        this.time = Integer.parseInt(time_str);
    }

    public String getStartTime() {
        return start_time;
    }

    private void setStartTime(String start_time) {
        this.start_time = start_time;
    }

    public String getEndTime() {
        return end_time;
    }

    private void setEndTime(String end_time) {
        this.end_time = end_time;
    }

    public int getPriority() {
        return priority;
    }

    private void setPriority(String priority) {
        this.priority = Integer.parseInt(priority);
    }

    public Date getStartDate() {
        if (this.hasStartDate())
            return (Date) start_date.clone();
        else
            return null;
    }

    public boolean hasStartDate() {
        return this.start_date != null;
    }

    private void setStartDate(String start_date_str) {
        try {
            this.start_date = running_dates_format.parse(start_date_str);
        } catch (ParseException e) {
            // TODO: log parse exception. Assuming a safe default.
            this.start_date = null;
        }
    }

    public Date getEndDate() {
        if (this.hasEndDate())
            return (Date) end_date.clone();
        else
            return null;
    }

    public boolean hasEndDate() {
        return this.end_date != null;
    }

    private void setEndDate(String end_date_str) {
        try {
            this.end_date = running_dates_format.parse(end_date_str);
        } catch (ParseException e) {
            // TODO: log parse exception. Assuming a safe default.
            this.end_date = null;
        }
    }

    public String getDaysOfWeek() {
        return days_of_week;
    }

    private void setDaysOfWeek(String days_of_week) {
        this.days_of_week = days_of_week;
    }

    public MODE getMode() {
        return this.mode;
    }

    private void setMode(String mode_str) {
        switch (mode_str) {
            case "BUS":
                this.mode = MODE.BUS;
                break;
            case "TUBE":
                this.mode = MODE.TUBE;
                break;
            case "WALK":
                this.mode = MODE.WALK;
                break;
            case "FERRY":
                this.mode = MODE.FERRY;
                break;
            case "METRO":
                this.mode = MODE.METRO;
                break;
            case "TRAM":
                this.mode = MODE.TRAM;
                break;
            case "TAXI":
                this.mode = MODE.TAXI;
                break;
            case "TRANSFER":
                this.mode = MODE.TRANSFER;
                break;
            default:
                this.mode = MODE.TRANSFER; // TODO: Handle invalid mode. Assuming TRANSFER as a safe default.
        }
    }
}
