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

    @Test
    public void testInRange() {

        TimetableDate start = new TimetableDate(2015, 12, 30);
        TimetableDate end = new TimetableDate(2016, 01, 01);

        assertEquals(false, new TimetableDate(2015, 12, 29).inRange(start, end));
        assertEquals(true,  new TimetableDate(2015, 12, 30).inRange(start, end));
        assertEquals(true,  new TimetableDate(2015, 12, 31).inRange(start, end));
        assertEquals(true,  new TimetableDate(2016, 01, 01).inRange(start, end));
        assertEquals(false, new TimetableDate(2016, 01, 02).inRange(start, end));
    }

}