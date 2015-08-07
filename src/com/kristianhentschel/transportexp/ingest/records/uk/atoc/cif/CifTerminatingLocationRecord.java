package com.kristianhentschel.transportexp.ingest.records.uk.atoc.cif;

import com.kristianhentschel.transportexp.ingest.records.formats.FixedWidthRecord;

/**
 * Created by Kristian on 06/08/2015.
 * Following the National Rail CIF USER SPEC v29 (August 2014)
 */
public class CifTerminatingLocationRecord extends AbstractCifRecord {
    private String locationTiploc;
    private String locationSuffix;
    private String scheduledArrival;
    private String publicArrival;
    private String platform;
    private String path;
    private String activity;

    public CifTerminatingLocationRecord(String record_text) {
        super(record_text);

        setLocationTiploc(takeChars(7));
        setLocationSuffix(takeChars(1));
        setScheduledArrival(takeChars(5));
        setPublicArrival(takeChars(4));
        setPlatform(takeChars(3));
        setPath(takeChars(3));
        setActivity(takeChars(12));
    }

    public String getLocationTiploc() {
        return locationTiploc;
    }

    private void setLocationTiploc(String locationTiploc) {
        this.locationTiploc = locationTiploc.trim();
    }

    public String getLocationSuffix() {
        return locationSuffix;
    }

    private void setLocationSuffix(String locationSuffix) {
        this.locationSuffix = locationSuffix.trim();
    }

    public String getScheduledArrival() {
        return scheduledArrival;
    }

    private void setScheduledArrival(String scheduledArrival) {
        this.scheduledArrival = scheduledArrival.trim();
    }

    public String getPublicArrival() {
        return publicArrival;
    }

    private void setPublicArrival(String publicArrival) {
        this.publicArrival = publicArrival.trim();
    }

    public String getPlatform() {
        return platform;
    }

    private void setPlatform(String platform) {
        this.platform = platform.trim();
    }

    public String getPath() {
        return path;
    }

    private void setPath(String path) {
        this.path = path.trim();
    }

    public boolean hasPlatform() {
        return platform.length() > 0;
    }

    public boolean hasPath() {
        return path.length() > 0;
    }

    public String getActivity() {
        return activity;
    }

    private void setActivity(String activity) {
        this.activity = activity.trim();
    }
}
