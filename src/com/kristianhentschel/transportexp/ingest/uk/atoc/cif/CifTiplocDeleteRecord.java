package com.kristianhentschel.transportexp.ingest.uk.atoc.cif;

import com.kristianhentschel.transportexp.ingest.formats.FixedWidthRecord;

/**
 * Created by Kristian on 06/08/2015.
 * Following the National Rail CIF USER SPEC v29 (August 2014)
 */
public class CifTiplocDeleteRecord extends FixedWidthRecord {
    public CifTiplocDeleteRecord(String record_text) {
        super(record_text);
    }
}
