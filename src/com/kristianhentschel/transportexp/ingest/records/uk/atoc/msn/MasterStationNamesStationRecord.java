package com.kristianhentschel.transportexp.ingest.records.uk.atoc.msn;

import com.kristianhentschel.transportexp.ingest.records.formats.FixedWidthRecord;

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
    public enum CATE {NO_INTERCHANGE, SMALL_INTERCHANGE, MEDIUM_INTERCHANGE, LARGE_INTERCHANGE, SUBSIDIARY_TIPLOC}

    private String station_name;
    private CATE interchange_status;
    private String tiploc;
    private String subsidiary_code;
    private String code;
    private int easting;
    private int northing;
    private boolean estimated;
    private int change_time;

    public String getStationName() {
        return station_name;
    }

    private void setStationName(String station_name) {
        this.station_name = station_name.trim();
    }

    public CATE getInterchangeStatus() {
        return interchange_status;
    }

    private void setInterchangeStatus(String interchange_status_str) {
        int cate_type = Integer.parseInt(interchange_status_str);

        switch (cate_type) {
            case 0:
                this.interchange_status = CATE.NO_INTERCHANGE;
                break;
            case 1:
                this.interchange_status = CATE.SMALL_INTERCHANGE;
                break;
            case 2:
                this.interchange_status = CATE.MEDIUM_INTERCHANGE;
                break;
            case 3:
                this.interchange_status = CATE.LARGE_INTERCHANGE;
                break;
            case 9:
                this.interchange_status = CATE.SUBSIDIARY_TIPLOC;
                break;
            default:
                // TODO: no other cases in spec, raise an error?
                this.interchange_status = CATE.NO_INTERCHANGE;
        }
    }

    public String getTiploc() {
        return tiploc;
    }

    private void setTiploc(String tiploc) {
        this.tiploc = tiploc.trim();
    }

    public String getSubsidiaryCode() {
        return subsidiary_code;
    }

    private void setSubsidiaryCode(String subsidiary_code) {
        this.subsidiary_code = subsidiary_code;
    }

    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public int getEasting() {
        return easting;
    }

    private void setEasting(String easting) {
        this.easting = Integer.parseInt(easting.trim());
    }

    public int getNorthing() {
        return northing;
    }

    private void setNorthing(String northing) {
        this.northing = Integer.parseInt(northing.trim());
    }

    public boolean isEstimated() {
        return estimated;
    }

    private void setEstimated(String estimated) {
        this.estimated = estimated.equals("E");
    }

    public int getChangeTime() {
        return change_time;
    }

    private void setChangeTime(String change_time_str) {
        this.change_time = Integer.parseInt(change_time_str.trim());
    }

    public MasterStationNamesStationRecord(String record_text) {
        super(record_text);

        skipChars(1); // record type "A"
        skipChars(4); // Spaces
        setStationName(takeChars(30));
        setInterchangeStatus(takeChars(1));
        setTiploc(takeChars(7));
        setSubsidiaryCode(takeChars(3));
        skipChars(3); // Spaces
        setCode(takeChars(3));
        setEasting(takeChars(5));
        setEstimated(takeChars(1));
        setNorthing(takeChars(5));
        setChangeTime(takeChars(2));

        // The remaining fields (cate footnote, subsector code) are marked as historic
        // and not maintained, and are ignored as per the specification.
    }

    public boolean isSubsidiary() {
        return this.interchange_status == CATE.SUBSIDIARY_TIPLOC;
    }
}
