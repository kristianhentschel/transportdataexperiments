package com.kristianhentschel.transportexp.ingest.uk.atoc.cif;

/**
 * Created by Kristian on 06/08/2015.
 * Following the National Rail CIF USER SPEC v29 (August 2014)
 */
public class CifBasicScheduleExtraRecord extends AbstractCifRecord {
    private String uicCode;
    private String atocCode;
    private String rsidToc;
    private String rsidTrainNumber;
    private String rsidPortionNumber;
    private boolean ats;

    public CifBasicScheduleExtraRecord(String record_text) {
        super(record_text);

        skipChars(4); // Traction class, not used
        setUicCode(takeChars(5));
        setAtocCode(takeChars(2));
        setAts(takeChars(1));

        // RSID field is populated in data received from ATOC, not national rail cif
        setRsidToc(takeChars(2));
        setRsidTrainNumber(takeChars(4));
        setRsidPortionNumber(takeChars(2));
        skipChars(1); // Data source (only for data received from TOCs/NRS?)

    }

    public String getUicCode() {
        return uicCode;
    }

    private void setUicCode(String uicCode) {
        this.uicCode = uicCode.trim();
    }

    public String getAtocCode() {
        return atocCode;
    }

    private void setAtocCode(String atocCode) {
        this.atocCode = atocCode.trim();
    }

    public boolean getAts() {
        return ats;
    }

    private void setAts(String atsCode) {
        switch(atsCode) {
            case "Y":
                this.ats = true;
                break;
            case "N":
                this.ats = false;
                break;
            default:
                this.ats = false;
        }
    }

    public String getRsidToc() {
        return rsidToc;
    }

    private void setRsidToc(String rsidToc) {
        this.rsidToc = rsidToc.trim();
    }

    public String getRsidTrainNumber() {
        return rsidTrainNumber;
    }

    private void setRsidTrainNumber(String rsidTrainNumber) {
        this.rsidTrainNumber = rsidTrainNumber.trim();
    }

    public String getRsidPortionNumber() {
        return rsidPortionNumber;
    }

    private void setRsidPortionNumber(String rsidPortionNumber) {
        this.rsidPortionNumber = rsidPortionNumber.trim();
    }

    public boolean hasUicCode() {
        return this.uicCode.length() > 0;
    }
}
