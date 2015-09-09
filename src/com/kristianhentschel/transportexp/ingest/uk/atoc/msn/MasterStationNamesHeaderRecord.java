package com.kristianhentschel.transportexp.ingest.uk.atoc.msn;

import com.kristianhentschel.transportexp.ingest.formats.FixedWidthRecord;

/**
 * Created by Kristian on 05/08/2015.
 *
 * Describes the single header record, with type "A", from the .msn Master Station Names file
 * as specified in the ATOC document RSPS5041 Version 06-00 (Section 3.2.7.2).
 *
 */
public class MasterStationNamesHeaderRecord extends FixedWidthRecord {
    private int file_version;
    private String date;
    private String time;
    private String spec_version;

    public MasterStationNamesHeaderRecord(String record_text) {
        super(record_text);


        skipChars(1);                   // Record Type
        skipChars(29);                  // Spaces
        skipChars(10);                  // "FILE-SPEC="
        setSpecVersion(takeChars(8));   // Just Version in ATOC Spec
        setDate(takeChars(8));
        skipChars(1);                   // Space
        setTime(takeChars(8));
        skipChars(3);                   // Spaces
        setFileVersion(takeChars(2));   // Also referred to as Version
    }

    private void setSpecVersion(String spec_version) {
        this.spec_version = spec_version.trim();
    }

    private void setDate(String date) {
        // TODO maybe treat as date?
        this.date = date;
    }

    private void setTime(String time) {
        // TODO maybe group with date?
        this.time = time;
    }

    private void setFileVersion(String file_version) {
        this.file_version = Integer.parseInt(file_version.trim());
    }

    public String getSpecVersion() {
        return spec_version;
    }

    public int getFileVersion() {
        return file_version;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
