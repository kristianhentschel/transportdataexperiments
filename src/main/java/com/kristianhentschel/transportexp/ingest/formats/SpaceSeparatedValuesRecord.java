package com.kristianhentschel.transportexp.ingest.formats;

/**
 * Created by Kristian on 06/08/2015.
 */
public abstract class SpaceSeparatedValuesRecord extends TextualRecord{
    private int index;
    private final String[] words;

    public SpaceSeparatedValuesRecord(String record_text) {
        super(record_text);
        words = getRecordText().split("\\s+"); // \s+ matches any number of consecutive white-space charactes.
        index = 0;
    }

    protected String getNextWord() {
        if (index >= words.length)
            index = 0;
        return words[index++];
    }

    protected String getNthWord(int n) {
        return words[n];
    }


}
