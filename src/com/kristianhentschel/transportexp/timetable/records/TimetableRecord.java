package com.kristianhentschel.transportexp.timetable.records;

/**
 * Created by Kristian on 13/08/2015.
 *
 * An abstract base class for time table records, such as stops, services, etc.
 *
 * The class currently only ensures that the legally required data attribution statements are stored with each record.
 */
public abstract class TimetableRecord {
    enum DATA_SOURCE {UNKNOWN, UK_ATOC}
    DATA_SOURCE dataSource;

    public void setDataSource(DATA_SOURCE dataSource) {
        this.dataSource = dataSource;
    }

    public DATA_SOURCE getDataSource() {
        return dataSource;
    }

    public String getDataAttribution() {
        switch (dataSource) {
            case UK_ATOC:
                return "Source: RSP";
            case UNKNOWN:
            default:
                return "Data source unknown.";
        }
    }
}
