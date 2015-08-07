package com.kristianhentschel.transportexp.ingest.records.uk.atoc.cif;

import com.kristianhentschel.transportexp.ingest.records.formats.FixedWidthRecord;

/**
 * Created by Kristian on 06/08/2015.
 */
public class CifTiplocInsertRecord extends FixedWidthRecord {
    public CifTiplocInsertRecord(String record_text) {
        super(record_text);
    }
}
