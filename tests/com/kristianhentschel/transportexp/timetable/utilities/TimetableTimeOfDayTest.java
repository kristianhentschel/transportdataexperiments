package com.kristianhentschel.transportexp.timetable.utilities;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kristian on 07/09/2015.
 */
public class TimetableTimeOfDayTest {

    @Test
    public void testConstructor() {
        TimetableTimeOfDay dut = new TimetableTimeOfDay(9,4,30);
        assertEquals(9, dut.getHour());
        assertEquals(4, dut.getMinute());
        assertEquals(30, dut.getSecond());
    }

    @Test
    public void testConstructorPM() {
        TimetableTimeOfDay dut = new TimetableTimeOfDay(19,59,0);
        assertEquals(19, dut.getHour());
        assertEquals(59, dut.getMinute());
        assertEquals(0, dut.getSecond());
    }

    @Test
    public void testConstructorNoSecond() {
        TimetableTimeOfDay dut = new TimetableTimeOfDay(0,2);
        assertEquals(0, dut.getHour());
        assertEquals(2, dut.getMinute());
        assertEquals(0, dut.getSecond());
    }
}