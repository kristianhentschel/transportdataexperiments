package com.kristianhentschel.transportexp.timetable.utilities;

import org.junit.Test;

import java.util.TimeZone;

import static org.junit.Assert.*;

/**
 * Created by Kristian on 07/09/2015.
 */
public class TimetableLocationTest {

    @Test
    public void testDefaults() {
        TimetableLocation dut = new TimetableLocation(TimeZone.getTimeZone("GMT"));

        assertEquals(true, dut.isEstimated());
        assertEquals(0.0, dut.getLat(), 0.01);
        assertEquals(0.0, dut.getLon(), 0.01);
        assertEquals("GMT", dut.getTimeZoneID());
        assertEquals("", dut.getStreetAddress());
    }
}