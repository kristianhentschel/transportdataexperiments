package com.kristianhentschel.transportexp.ingest.utilities.records.uk.atoc;

import com.kristianhentschel.transportexp.ingest.utilities.records.SpaceSeparatedValuesRecord;

/**
 * Created by Kristian on 06/08/2015.
 */
public class FixedLinksRecord extends SpaceSeparatedValuesRecord {
    public FixedLinksRecord(String record_text) {
        super(record_text);

        getNextWord(); // ADDITIONAL
        getNextWord(); // LINK:
        setField("mode", getNextWord());
        getNextWord(); // BETWEEN
        setField("origin", getNextWord());
        getNextWord(); // AND
        setField("destination", getNextWord());
        getNextWord(); // IN
        setField("time", getNextWord());
        getNextWord(); // MINUTES
    }
}
