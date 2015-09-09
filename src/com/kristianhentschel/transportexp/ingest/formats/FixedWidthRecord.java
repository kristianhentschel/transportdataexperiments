package com.kristianhentschel.transportexp.ingest.formats;

/**
 * Created by Kristian on 05/08/2015.
 */
public abstract class FixedWidthRecord extends TextualRecord {
    private int current_index;

    public FixedWidthRecord(String record_text) {
        super(record_text);
        current_index = 0;
    }

    protected String getValueRange(int start, int length) {
        return getRecordText().substring(start, start + length);
    }

    /**
     * Resets the internal pointer, thus returning to read from the beginning of the record
     * again.
     */
    protected void resetChars() {
        current_index = 0;
    }

    /**
     * Reads the given number of characters and advances an internal pointer to the
     * first location after the data of interest.
     * @param length The number of characters to read from the string.
     * @return the value of the field
     */
    protected String takeChars(int length) {
        assert length > 0;
        String field = getValueRange(current_index, length);
        current_index += length;
        return field;
    }

    /**
     * Skips a number of irrelevant characters by simply advancing the internal pointer.
     */
    protected void skipChars(int length) {
        assert length > 0;
        current_index += length;
    }
}
