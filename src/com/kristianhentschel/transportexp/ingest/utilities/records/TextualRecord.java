package com.kristianhentschel.transportexp.ingest.utilities.records;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kristian on 05/08/2015.
 *
 * The base class for the parsed records from files using an ASCII format.
 * Its children shall be instantiated with a line from the file,
 * parse the fields as appropriate, and make it available by a name from the file format documentation.
 */
public abstract class TextualRecord {
    private final String record_text;
    private final Map<String, String> fields;

    public TextualRecord(String record_text) {
        this.record_text = record_text;
        this.fields = new HashMap<String, String>();
    }

    @Deprecated
    public String getField(String name) {
        // TODO: handle "requested field may not be set".
        return this.fields.get(name);
    }

    @Deprecated
    protected void setField(String key, String value) {
        fields.put(key, value);
    }

    protected String getRecordText() {
        return record_text;
    }
}
