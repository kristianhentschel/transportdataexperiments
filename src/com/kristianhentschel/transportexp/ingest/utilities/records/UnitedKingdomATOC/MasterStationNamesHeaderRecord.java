package com.kristianhentschel.transportexp.ingest.utilities.records.UnitedKingdomATOC;

import com.kristianhentschel.transportexp.ingest.utilities.records.FixedWidthRecord;

/**
 * Created by Kristian on 05/08/2015.
 */
public class MasterStationNamesHeaderRecord extends FixedWidthRecord {
    public MasterStationNamesHeaderRecord(String record_text) {
        super(record_text);

        setField("record_type", getValueRange(0, 1));
        setField("file_spec", getValueRange(30, 10));
        setField("spec_version", getValueRange(40, 8)); // "Version" in ATOC Spec rsps5041
        setField("date", getValueRange(48, 8));
        setField("time", getValueRange(57, 8));
        setField("file_version", getValueRange(68, 2)); // Also referred to as "Version"
    }
}
