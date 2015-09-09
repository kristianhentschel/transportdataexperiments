package com.kristianhentschel.transportexp.ingest.uk.atoc.msn;

import com.kristianhentschel.transportexp.ingest.formats.FixedWidthRecord;

/**
 * Created by Kristian on 05/08/2015.
 *
 * Describes an "V" type routing group record in the .msn Master Station Names file
 * as specified in the ATOC document RSPS5041 Version 06-00 (Section 3.2.7.8).
 *
 */
public class MasterStationNamesRoutingGroupRecord extends FixedWidthRecord {
    private String[] stations;
    private int num_stations;
    private String group_name;

    public MasterStationNamesRoutingGroupRecord(String record_text) {
        super(record_text);

        this.stations = new String[10];

        skipChars(1);
        skipChars(4);
        setGroupName(takeChars(30));
        skipChars(1);
        addStation(takeChars(3));
        skipChars(1);
        addStation(takeChars(3));
        skipChars(1);
        addStation(takeChars(3));
        skipChars(1);
        addStation(takeChars(3));
        skipChars(1);
        addStation(takeChars(3));
        skipChars(1);
        addStation(takeChars(3));
        skipChars(1);
        addStation(takeChars(3));
        skipChars(1);
        addStation(takeChars(3));
        skipChars(1);
        addStation(takeChars(3));
        skipChars(1);
        addStation(takeChars(3));
    }

    private void setGroupName(String group_name) {
        this.group_name = group_name.trim();
    }

    public String getGroupName() {
        return this.group_name;
    }

    private void addStation(String code) {
        code = code.trim();
        if (code.length() > 0) {
            this.stations[this.num_stations++] = code;
        }
    }

    public int getNumStations() {
        return this.num_stations;
    }

    public String getStation(int n) {
        if (n < num_stations) {
            return this.stations[n];
        } else {
            // TODO throw array index out of bounds exception?
            throw new ArrayIndexOutOfBoundsException("Routing group does not have this many stations.");
        }
    }
}
