package com.kristianhentschel.transportexp.ingest.records.formats;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kristian on 06/08/2015.
 */
public abstract class CommaSeparatedKeyValuesRecord extends CommaSeparatedValuesRecord {
    private final Map<String, String> raw_values;

    public CommaSeparatedKeyValuesRecord(String record_text) {
        super(record_text);
        raw_values = new HashMap<String, String>(getRawValuesLength());

        for (int i = 0; i < getRawValuesLength(); i++) {
            String s[] = getRawValue(i).split("=");
            raw_values.put(s[0], s[1]);
        }
    }

    /**
     * get the value associated with the given key, or the empty String if the key does not exist.
     * @param key the key from the original record's key=value pair.
     * @return the value, or the empty string if it was not set.
     */
    protected String getRawValue(String key) {
        return raw_values.getOrDefault(key, "");
    }
}
