package com.kristianhentschel.transportexp.timetable.utilities;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kristian on 07/09/2015.
 */
public class TimetableDateTest {
    @Test
    public void testLegalDates() {
        TimetableDate dut = new TimetableDate(1970, 01, 01);
        assertEquals(1970, dut.getYear());
        assertEquals(1, dut.getMonth());
        assertEquals(1, dut.getDay());
    }

    @Test
    public void testToString() {
        TimetableDate dut = new TimetableDate(2008, 02, 29);
        assertEquals("29/02/2008", dut.toString());
    }
}