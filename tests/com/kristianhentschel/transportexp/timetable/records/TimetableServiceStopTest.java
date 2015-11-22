package com.kristianhentschel.transportexp.timetable.records;

import com.kristianhentschel.transportexp.timetable.utilities.TimetableTimeOfDay;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kristian on 22/11/2015.
 */
public class TimetableServiceStopTest {
    @Test
    public void testCompareTo() {
        TimetableStop stop = new TimetableStop();
        TimetableService service = new TimetableService();
        TimetableServiceStop ts1 = new TimetableServiceStop(service, stop);
        TimetableServiceStop ts2 = new TimetableServiceStop(service, stop);

        ts1.setArrives(new TimetableTimeOfDay(0,1));
        ts1.setDeparts(new TimetableTimeOfDay(0,1));

        ts2.setArrives(new TimetableTimeOfDay(0,2));
        ts2.setDeparts(new TimetableTimeOfDay(0,2));

        assertTrue(ts1.compareTo(ts2) < 0);
        assertTrue(ts2.compareTo(ts1) > 0);
    }

    @Test
    public void testCompareToDifferentTimeKinds() {
        TimetableStop stop = new TimetableStop();
        TimetableService service = new TimetableService();
        TimetableServiceStop ts1 = new TimetableServiceStop(service, stop);
        TimetableServiceStop ts2 = new TimetableServiceStop(service, stop);

        ts1.setArrives(new TimetableTimeOfDay(0,1));
        ts2.setDeparts(new TimetableTimeOfDay(0,1));

        assertEquals(ts1.compareTo(ts2), 0);
        assertEquals(ts2.compareTo(ts1), 0);
    }



}