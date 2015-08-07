package com.kristianhentschel.transportexp.ingest;

import java.io.File;

/**
 * Created by Kristian on 07/08/2015.
 *
 * An abstract base class defining the public interface for all the different data source parsers.
 */
public abstract class AbstractTransportSourceParser {
    private String source_file_name;
    private boolean is_parsed;

    public AbstractTransportSourceParser(String source_file_name) {
        this.source_file_name = source_file_name;
        this.is_parsed = false;
    }

    public boolean doParse() {
        File f = new File(this.source_file_name);
        if(f.canRead())
            this.is_parsed = parseFile(f);
        return this.is_parsed;
    }

    // must be implemented by all children
    protected abstract boolean parseFile(File f);
}
