package com.kristianhentschel.transportexp.ingest.utilities.records.uk.atoc;

import com.kristianhentschel.transportexp.ingest.utilities.records.CommaSeparatedValuesRecord;

/**
 * Created by Kristian on 05/08/2015.
 */
public class TOCSpecificInterchangeTimesRecord extends CommaSeparatedValuesRecord {

    private String station_code;
    private String arriving_train_toc;
    private String departing_train_toc;
    private String comments;
    private int minimum_interchange_time;


    public TOCSpecificInterchangeTimesRecord(String record_text) {
        super(record_text);

        // The first four fields are mandatory
        if (getRawValuesLength() < 4) {
            // TODO: throw a more descriptive error if the mandatory fields are unfilled.
            throw new IndexOutOfBoundsException();
        }

        setStationCode(getRawValue(0));
        setArrivingTrainToc(getRawValue(1));
        setDepartingTrainToc(getRawValue(2));
        setMinimumInterchangeTime(getRawValue(3));

        if(getRawValuesLength() > 4) {
            setComments(getRawValue(4));
        } else {
            setComments("");
        }

    }

    private void setComments(String comments) {
        this.comments = comments.trim();
    }

    public String getComments() {
        return this.comments;
    }

    public boolean hasComments() {
        return this.comments.length() > 0;
    }

    private void setStationCode(String station_code) {
        this.station_code = station_code;
    }

    public String getStationCode() {
        return this.station_code;
    }

    private void setArrivingTrainToc(String arriving_train_toc) {
        this.arriving_train_toc = arriving_train_toc;
    }

    public String getArrivingTrainToc() {
        return this.arriving_train_toc;
    }

    private void setDepartingTrainToc(String departing_train_toc) {
        this.departing_train_toc = departing_train_toc;
    }

    public String getDepartingTrainToc() {
        return this.departing_train_toc;
    }

    private void setMinimumInterchangeTime(String minimum_interchange_time_str) {
        this.minimum_interchange_time = Integer.parseInt(minimum_interchange_time_str.trim());
    }

    public int getMinimumInterchangeTime() {
        return this.minimum_interchange_time;
    }

}
