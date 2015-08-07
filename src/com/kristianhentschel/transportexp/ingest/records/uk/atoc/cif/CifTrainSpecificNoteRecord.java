package com.kristianhentschel.transportexp.ingest.records.uk.atoc.cif;

import com.kristianhentschel.transportexp.ingest.records.formats.FixedWidthRecord;

/**
 * Created by Kristian on 06/08/2015.
 * Following the National Rail CIF USER SPEC v29 (August 2014)
 */
public class CifTrainSpecificNoteRecord extends FixedWidthRecord {
    public CifTrainSpecificNoteRecord(String record_text) {
        super(record_text);
    }
}
