package com.kristianhentschel.transportexp.ingest.utilities.records.uk.atoc;

import com.kristianhentschel.transportexp.ingest.utilities.records.SpaceSeparatedValuesRecord;

/**
 * Created by Kristian on 06/08/2015.
 */
public class FixedLinksRecord extends SpaceSeparatedValuesRecord {

    // TODO extract a more general ATOC fixed link mode enum (also in AdditionalFixedLinksRecord)
    public enum MODE {BUS, TUBE, WALK, FERRY, METRO, TRANSFER} ;

    private MODE mode;
    private String origin;
    private String destination;
    private int time;

    public FixedLinksRecord(String record_text) {
        super(record_text);

        getNextWord(); // ADDITIONAL
        getNextWord(); // LINK:
        setMode(getNextWord());
        getNextWord(); // BETWEEN
        setOrigin(getNextWord());
        getNextWord(); // AND
        setDestination(getNextWord());
        getNextWord(); // IN
        setTime(getNextWord());
        getNextWord(); // MINUTES
    }

    public void setMode(String mode_str) {
        switch (mode_str) {
            case "BUS":
                this.mode = MODE.BUS;
                break;
            case "TUBE":
                this.mode = MODE.TUBE;
                break;
            case "WALK":
                this.mode = MODE.WALK;
                break;
            case "FERRY":
                this.mode = MODE.FERRY;
                break;
            case "METRO":
                this.mode = MODE.METRO;
                break;
            case "TRANSFER":
                this.mode = MODE.TRANSFER;
                break;
            default:
                this.mode = MODE.TRANSFER;
        }
    }

    private void setOrigin(String origin) {
        this.origin = origin;
    }

    private void setDestination(String destination) {
        this.destination = destination;
    }

    private void setTime(String time_str) {
        this.time = Integer.parseInt(time_str);
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getTime() {
        return time;
    }

    public MODE getMode() {
        return mode;
    }

}
