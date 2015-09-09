package com.kristianhentschel.transportexp.ingest.formats;

/**
 * Created by Kristian on 05/08/2015.
 *
 * The base class for the parsed records from files using an ASCII format.
 * Its children shall be instantiated with a line from the file,
 * parse the fields as appropriate, and make it available by a name from the file format documentation.
 */
public abstract class TextualRecord {
    private final String record_text;

    public TextualRecord(String record_text) {
        this.record_text = record_text;
    }

    protected String getRecordText() {
        return record_text;
    }
}
