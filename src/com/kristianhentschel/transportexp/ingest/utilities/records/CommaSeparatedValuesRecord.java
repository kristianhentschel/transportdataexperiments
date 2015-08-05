package com.kristianhentschel.transportexp.ingest.utilities.records;

/**
 * Created by Kristian on 05/08/2015.
 */
public abstract class CommaSeparatedValuesRecord extends TextualRecord {
    private String[] raw_values;

    public CommaSeparatedValuesRecord(String record_text) {
        super(record_text);
        raw_values = record_text.trim().split(",");
    }

    protected int getRawValuesLength() {
        return raw_values.length;
    }

    protected String getRawValue(int n) {
        return raw_values[n];
    }
}
