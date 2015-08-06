package com.kristianhentschel.transportexp.ingest.utilities.records.uk.atoc;

import com.kristianhentschel.transportexp.ingest.utilities.records.FixedWidthRecord;

/**
 * Created by Kristian on 05/08/2015.
 *
 * Describes an "A" type station record in the .msn Master Station Names file
 * as specified in the ATOC document RSPS5041 Version 06-00 (Section 3.2.7.2).
 *
 * The Station Details record occurs once for each TIPLOC and associates a station name
 * and the station's 3-alpha codes with a TIPLOC, and also defines its location in terms
 * of Easting and Northing.
 */
public class MasterStationNamesStationRecord extends FixedWidthRecord{
    public MasterStationNamesStationRecord(String record_text) {
        super(record_text);

        setField("record_type", takeChars(1));
        skipChars(4);
        setField("station_name", takeChars(30));
        setField("cate_type", takeChars(1));
        setField("tiploc", takeChars(7));
        setField("subsidiary_3_alpha", takeChars(3));
        skipChars(3);
        setField("3_alpha", takeChars(3));
        setField("easting", takeChars(5));
        setField("estimated", takeChars(1));
        setField("northing", takeChars(5));
        setField("change_time", takeChars(2));

        // The remaining fields (cate footnote, subsector code) are marked as historic
        // and not maintained, and are ignored as per the specification.
    }
}
