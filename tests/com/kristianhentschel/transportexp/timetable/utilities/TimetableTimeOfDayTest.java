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

    @Test
    public void testToString() {
        TimetableTimeOfDay dut = new TimetableTimeOfDay(6,15,3);
        assertEquals("06:15:03", dut.toString());
    }

    @Test
    public void testCompareToSimple() {
        TimetableTimeOfDay t1 = new TimetableTimeOfDay(0,1);
        TimetableTimeOfDay t2 = new TimetableTimeOfDay(0,2);

        assertTrue(t1.compareTo(t2) < 0);
        assertTrue(t2.compareTo(t1) > 0);
    }

    @Test
    public void testCompareToEquals() {
        TimetableTimeOfDay t1 = new TimetableTimeOfDay(0,0);
        TimetableTimeOfDay t2 = new TimetableTimeOfDay(0,0);

        assertTrue(t1.compareTo(t2) == 0);
        assertTrue(t1.equals(t2));
    }

    @Test
    public void testCompareToTransitive() {
        TimetableTimeOfDay t1 = new TimetableTimeOfDay(0,1);
        TimetableTimeOfDay t2 = new TimetableTimeOfDay(0,2);
        TimetableTimeOfDay t3 = new TimetableTimeOfDay(0,3);

        assertTrue(t1.compareTo(t2) < 0);
        assertTrue(t2.compareTo(t3) < 0);

        assertTrue(t1.compareTo(t3) < 0);
    }
}