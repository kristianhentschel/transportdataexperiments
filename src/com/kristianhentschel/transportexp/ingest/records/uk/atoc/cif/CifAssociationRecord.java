package com.kristianhentschel.transportexp.ingest.records.uk.atoc.cif;

import com.kristianhentschel.transportexp.ingest.records.uk.atoc.msn.MasterStationNamesStationRecord;

/**
 * Created by Kristian on 06/08/2015.
 * Following the National Rail CIF USER SPEC v29 (August 2014)
 *
 * If transaction type 'D' or STP indicator 'C' is used, not all data fields are populated!
 */
public class CifAssociationRecord extends AbstractCifRecord {
    public enum CATEGORY {JOIN, SPLIT, NEXT}
    public enum DATE_IND {STANDARD, NEXT_MIDNIGHT, PREVIOUS_MIDNIGHT}
    public enum TYPE {PASSENGER_USE, OPERATING_USE}

    private TRANSACTION_TYPE transaction_type;
    private String main_train_uid;
    private String associated_train_uid;
    private String start_date;
    private String end_date;
    private String days;
    private CATEGORY category;
    private DATE_IND date_ind;
    private String location;
    private String base_loc_suffix;
    private String assoc_loc_suffix;
    private TYPE type;

    public CifAssociationRecord(String record_text) {
        super(record_text);

        setTransactionType(takeChars(1));
        setMainTrainUid(takeChars(6));
        setAssociatedTrainUid(takeChars(6));
        setStartDate(takeChars(6));
        setEndDate(takeChars(6));
        setDays(takeChars(7));
        setCategory(takeChars(2));
        setDateInd(takeChars(1));
        setLocation(takeChars(7));
        setBaseLocSuffix(takeChars(1));
        setAssocLocSuffix(takeChars(1));
        skipChars(1); // Reserved field, diagram type "T"
        setType(takeChars(1));
        skipChars(31); // Spare
        // TODO: Ignoring overlay-only field STP Indicator (C/N/O/P)
        //should be: setSTPIndicator(takeChars(1));
    }

    public TRANSACTION_TYPE getTransactionType() {
        return transaction_type;
    }

    public String getMainTrainUid() {
        return main_train_uid;
    }

    public String getAssociatedTrainUid() {
        return associated_train_uid;
    }

    public String getStartDate() {
        return start_date;
    }

    public String getEndDate() {
        return end_date;
    }

    public String getDays() {
        return days;
    }

    public CATEGORY getCategory() {
        return category;
    }

    public DATE_IND getDateInd() {
        return date_ind;
    }

    public String getLocation() {
        return location;
    }

    public String getBaseLocSuffix() {
        return base_loc_suffix;
    }

    public String getAssocLocSuffix() {
        return assoc_loc_suffix;
    }

    public TYPE getType() {
        return type;
    }

    public void setTransactionType(String transaction_type) {
        this.transaction_type = parseTransactionType(transaction_type);
    }

    public void setMainTrainUid(String main_train_uid) {
        this.main_train_uid = main_train_uid;
    }

    public void setAssociatedTrainUid(String associated_train_uid) {
        this.associated_train_uid = associated_train_uid;
    }

    public void setStartDate(String start_date) {
        this.start_date = start_date;
    }

    public void setEndDate(String end_date) {
        this.end_date = end_date;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public void setCategory(String category) {
        switch (category) {
            case "JJ":
                this.category = CATEGORY.JOIN;
                break;
            case "VV":
                this.category = CATEGORY.SPLIT;
                break;
            case "NP":
                this.category = CATEGORY.NEXT;
                break;
            default:
                this.category = CATEGORY.NEXT; // TODO should be a safe default or error condition
        }
    }

    public void setDateInd(String date_ind) {
        switch (date_ind) {
            case "S":
                this.date_ind = DATE_IND.STANDARD;
                break;
            case "N":
                this.date_ind = DATE_IND.NEXT_MIDNIGHT;
                break;
            case "P":
                this.date_ind = DATE_IND.PREVIOUS_MIDNIGHT;
                break;
            default:
                this.date_ind = DATE_IND.STANDARD; // TODO should be an error?
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setBaseLocSuffix(String base_loc_suffix) {
        this.base_loc_suffix = base_loc_suffix;
    }

    public void setAssocLocSuffix(String assoc_loc_suffix) {
        this.assoc_loc_suffix = assoc_loc_suffix;
    }

    public void setType(String type) {
        switch (type) {
            case "P":
                this.type = TYPE.PASSENGER_USE;
                break;
            case "O":
                this.type = TYPE.OPERATING_USE;
                break;
            default:
                this.type = TYPE.OPERATING_USE; // TODO should be an error
        }
    }
}
