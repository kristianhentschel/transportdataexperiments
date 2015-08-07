package com.kristianhentschel.transportexp.ingest.records.uk.atoc.cif;

import com.kristianhentschel.transportexp.ingest.records.formats.FixedWidthRecord;

/**
 * Created by Kristian on 07/08/2015.
 */
public abstract class AbstractCifRecord extends FixedWidthRecord{
    public enum TRANSACTION_TYPE {NEW, DELETE, REVISE};

    protected TRANSACTION_TYPE parseTransactionType(String transaction_type) {
        switch (transaction_type) {
            case "N":
                return TRANSACTION_TYPE.NEW;
            case "D":
                return TRANSACTION_TYPE.DELETE;
            case "R":
                return TRANSACTION_TYPE.REVISE;
            default:
                return TRANSACTION_TYPE.NEW;
        }
    }

    public AbstractCifRecord(String record_text) {
        super(record_text);
        skipChars(2); // skip record identity
    }
}
