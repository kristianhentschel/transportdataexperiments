package com.kristianhentschel.transportexp.ingest.utilities.records.uk.atoc;

import com.kristianhentschel.transportexp.ingest.utilities.records.FixedWidthRecord;

/**
 * Created by Kristian on 05/08/2015.
 *
 * Describes an "V" type routing group record in the .msn Master Station Names file
 * as specified in the ATOC document RSPS5041 Version 06-00 (Section 3.2.7.8).
 *
 */
public class MasterStationNamesRoutingGroupRecord extends FixedWidthRecord{
    public MasterStationNamesRoutingGroupRecord(String record_text) {
        super(record_text);

        setField("record_type", takeChars(1));
        skipChars(4);
        setField("group_name", takeChars(30));
        skipChars(1);
        setField("station_1", takeChars(3));
        skipChars(1);
        setField("station_2", takeChars(3));
        skipChars(1);
        setField("station_3", takeChars(3));
        skipChars(1);
        setField("station_4", takeChars(3));
        skipChars(1);
        setField("station_5", takeChars(3));
        skipChars(1);
        setField("station_6", takeChars(3));
        skipChars(1);
        setField("station_7", takeChars(3));
        skipChars(1);
        setField("station_8", takeChars(3));
        skipChars(1);
        setField("station_9", takeChars(3));
        skipChars(1);
        setField("station_10", takeChars(3));
    }
}
