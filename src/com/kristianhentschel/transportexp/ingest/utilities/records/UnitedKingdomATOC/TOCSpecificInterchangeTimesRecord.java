package com.kristianhentschel.transportexp.ingest.utilities.records.UnitedKingdomATOC;

import com.kristianhentschel.transportexp.ingest.utilities.records.CommaSeparatedValuesRecord;

/**
 * Created by Kristian on 05/08/2015.
 */
public class TOCSpecificInterchangeTimesRecord extends CommaSeparatedValuesRecord {
    public TOCSpecificInterchangeTimesRecord(String record_text) {
        super(record_text);

        // The first four fields are mandatory
        if (getRawValuesLength() < 4) {
            // TODO: throw an error
        }
        setField("station_code", getRawValue(0));
        setField("arriving_train_toc", getRawValue(1));
        setField("departing_train_toc", getRawValue(2));
        setField("minimum_interchange_time", getRawValue(3));

        // Comments are optional
        if (getRawValuesLength() > 4) {
            setField("comments", getRawValue(4));
        } else {
            setField("comments", "");
        }
    }
}
