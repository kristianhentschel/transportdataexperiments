package com.kristianhentschel.transportexp.ingest.utilities.records;

/**
 * Created by Kristian on 05/08/2015.
 */
public abstract class FixedWidthRecord extends TextualRecord {
    public FixedWidthRecord(String record_text) {
        super(record_text);
    }

    protected String getValueRange(int start, int length) {
        return getRecordText().substring(start, start + length);
    }
}
