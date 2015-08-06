package com.kristianhentschel.transportexp.ingest.utilities.records.uk.atoc;

import com.kristianhentschel.transportexp.ingest.utilities.records.CommaSeparatedKeyValuesRecord;

/**
 * Created by Kristian on 06/08/2015.
 */
public class AdditionalFixedLinksRecord extends CommaSeparatedKeyValuesRecord {
    public AdditionalFixedLinksRecord(String record_text) {
        super(record_text);

        setField("mode", getRawValue("M"));
        setField("origin", getRawValue("O"));
        setField("destination", getRawValue("D"));
        setField("time", getRawValue("T"));
        setField("start_time", getRawValue("S"));
        setField("end_time", getRawValue("E"));
        setField("priority", getRawValue("P"));
        setField("start_date", getRawValue("F"));
        setField("end_date", getRawValue("U"));
        setField("days_of_week", getRawValue("R"));
    }
}
