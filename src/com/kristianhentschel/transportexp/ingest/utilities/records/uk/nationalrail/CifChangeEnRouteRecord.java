package com.kristianhentschel.transportexp.ingest.utilities.records.uk.nationalrail;

import com.kristianhentschel.transportexp.ingest.utilities.records.FixedWidthRecord;

/**
 * Created by Kristian on 06/08/2015.
 * Following the National Rail CIF USER SPEC v29 (August 2014)
 */
public class CifChangeEnRouteRecord extends FixedWidthRecord {
    public CifChangeEnRouteRecord(String record_text) {
        super(record_text);
    }
}
