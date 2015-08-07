package com.kristianhentschel.transportexp.ingest.records.uk.atoc.cif;

import com.kristianhentschel.transportexp.ingest.records.formats.FixedWidthRecord;

/**
 * Created by Kristian on 06/08/2015.
 * Following the National Rail CIF USER SPEC v29 (August 2014)
 */
public class CifIntermediateLocationRecord extends AbstractCifRecord {
    private String locationTiploc;
    private String locationSuffix;
    private String scheduledArrival;
    private String scheduledDeparture;
    private String scheduledPass;
    private String publicArrival;
    private String publicDeparture;
    private String platform;
    private String line;
    private String activity;
    private String engineeringAllowance;
    private String pathingAllowance;
    private String performanceAllowance;
    private String path;

    public CifIntermediateLocationRecord(String record_text) {
        super(record_text);

        // Location is described as a single 8-character field in the spec
        // I separate them here to more easily correlate tiplocs with locations.
        setLocationTiploc(takeChars(7));
        setLocationSuffix(takeChars(1));
        setScheduledArrival(takeChars(5));
        setScheduledDeparture(takeChars(5));
        setScheduledPass(takeChars(5));
        setPublicArrival(takeChars(4));
        setPublicDeparture(takeChars(4));
        setPlatform(takeChars(3));
        setLine(takeChars(3));
        setPath(takeChars(3));
        setActivity(takeChars(12));
        setEngineeringAllowance(takeChars(2));
        setPathingAllowance(takeChars(2));
        setPerformanceAllowance(takeChars(2));
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

    public String getScheduledDeparture() {
        return scheduledDeparture;
    }

    private void setScheduledDeparture(String scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture.trim();
    }

    public String getScheduledPass() {
        return scheduledPass;
    }

    private void setScheduledPass(String scheduledPass) {
        this.scheduledPass = scheduledPass.trim();
    }

    public String getPublicArrival() {
        return publicArrival;
    }

    private void setPublicArrival(String publicArrival) {
        this.publicArrival = publicArrival.trim();
    }

    public String getPublicDeparture() {
        return publicDeparture;
    }

    private void setPublicDeparture(String publicDeparture) {
        this.publicDeparture = publicDeparture.trim();
    }

    public String getPlatform() {
        return platform;
    }

    private void setPlatform(String platform) {
        this.platform = platform.trim();
    }

    public String getLine() {
        return line;
    }

    private void setLine(String line) {
        this.line = line.trim();
    }

    public String getActivity() {
        return activity;
    }

    private void setActivity(String activity) {
        this.activity = activity.trim();
    }

    public String getEngineeringAllowance() {
        return engineeringAllowance;
    }

    private void setEngineeringAllowance(String engineeringAllowance) {
        this.engineeringAllowance = engineeringAllowance.trim();
    }

    public String getPathingAllowance() {
        return pathingAllowance;
    }

    private void setPathingAllowance(String pathingAllowance) {
        this.pathingAllowance = pathingAllowance.trim();
    }

    public String getPerformanceAllowance() {
        return performanceAllowance;
    }

    private void setPerformanceAllowance(String performanceAllowance) {
        this.performanceAllowance = performanceAllowance.trim();
    }

    public boolean hasScheduledArrival() {
        return scheduledArrival.length() > 0;
    }

    public boolean hasScheduledDeparture() {
        return scheduledDeparture.length() > 0;
    }

    public boolean hasScheduledPass() {
        return scheduledPass.length() > 0;
    }

    public boolean hasPlatform() {
        return platform.length() > 0;
    }

    public boolean hasLine() {
        return line.length() > 0;
    }

    public boolean hasActivity() {
        return activity.length() > 0;
    }

    public boolean hasEngineeringAllowance() {
        return engineeringAllowance.length() > 0;
    }

    public boolean hasPathingAllowance() {
        return pathingAllowance.length() > 0;
    }

    public boolean hasPerformanceAllowance() {
        return performanceAllowance.length() > 0;
    }

    public String getPath() {
        return path;
    }

    private void setPath(String path) {
        this.path = path.trim();
    }

    public boolean hasPath() {
        return path.length() > 0;
    }
}
