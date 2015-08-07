package com.kristianhentschel.transportexp.ingest.records.uk.atoc.cif;

import com.kristianhentschel.transportexp.ingest.records.formats.FixedWidthRecord;

/**
 * Created by Kristian on 06/08/2015.
 * Following the National Rail CIF USER SPEC v29 (August 2014)
 */
public class CifOriginLocationRecord extends AbstractCifRecord {
    private String locationTiploc;
    private String locationSuffix;
    private String scheduledDeparture;
    private String publicDeparture;
    private String platform;
    private String line;
    private String engineeringAllowance;
    private String pathingAllowance;
    private String activity;
    private String performanceAllowance;

    public CifOriginLocationRecord(String record_text) {
        super(record_text);

        // Location is described as a single 8-character field in the spec
        // I separate them here to more easily correlate tiplocs with locations.
        setLocationTiploc(takeChars(7));
        setLocationSuffix(takeChars(1));
        setScheduledDeparture(takeChars(5));
        setPublicDeparture(takeChars(4));
        setPlatform(takeChars(3));
        setLine(takeChars(3));
        setEngineeringAllowance(takeChars(2));
        setPathingAllowance(takeChars(2));
        setActivity(takeChars(12));
        setPerformanceAllowance(takeChars(2));
    }

    public String getLocationTiploc() {
        return locationTiploc;
    }

    public void setLocationTiploc(String locationTiploc) {
        this.locationTiploc = locationTiploc.trim();
    }

    public String getLocationSuffix() {
        return locationSuffix;
    }

    public void setLocationSuffix(String locationSuffix) {
        this.locationSuffix = locationSuffix.trim();
    }

    public String getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(String scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture.trim();
    }

    public String getPublicDeparture() {
        return publicDeparture;
    }

    public void setPublicDeparture(String publicDeparture) {
        this.publicDeparture = publicDeparture.trim();
    }

    public boolean hasPlatform() {
        return platform.length() > 0;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform.trim();
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line.trim();
    }

    public String getEngineeringAllowance() {
        return engineeringAllowance;
    }

    public void setEngineeringAllowance(String engineeringAllowance) {
        this.engineeringAllowance = engineeringAllowance.trim();
    }

    public String getPathingAllowance() {
        return pathingAllowance;
    }

    public void setPathingAllowance(String pathingAllowance) {
        this.pathingAllowance = pathingAllowance.trim();
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        // TODO this is actually a 6 x 2 field, listing multiple activities.
        this.activity = activity.trim();
    }

    public String getPerformanceAllowance() {
        return performanceAllowance;
    }

    public boolean hasPerformanceAllowance() {
        return performanceAllowance.length() > 0;
    }

    public boolean hasPathingAllowance() {
        return pathingAllowance.length() > 0;
    }

    public boolean hasEngineeringAllowance() {
        return engineeringAllowance.length() > 0;
    }

    public boolean hasLine() {
        return line.length() > 0;
    }

    public void setPerformanceAllowance(String performanceAllowance) {
        this.performanceAllowance = performanceAllowance.trim();
    }
}
