package com.kristianhentschel.transportexp.timetable.utilities;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kristian on 04/09/2015.
 */
public class TimetableDaysOfWeekTest {
    private String daysToString(TimetableDaysOfWeek d) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 7; i++) {
            sb.append(d.getDay(i) ? '1' : '0');
        }

        return sb.toString();
    }

    @Test
    public void testSetOneHotSunday() {
        TimetableDaysOfWeek dut = new TimetableDaysOfWeek();

        dut.setDay(6, true);

        // tests "runs___days()"
        assertEquals(false, dut.runsMondays());
        assertEquals(false, dut.runsTuesdays());
        assertEquals(false, dut.runsWednesdays());
        assertEquals(false, dut.runsThursdays());
        assertEquals(false, dut.runsFridays());
        assertEquals(false, dut.runsSaturdays());
        assertEquals(true, dut.runsSundays());

        // tests "getDay()"
        assertEquals("0000001", daysToString(dut));
    }

    @Test
    public void testSetMultiple() {
        TimetableDaysOfWeek dut = new TimetableDaysOfWeek();

        dut.setDay(1, true);
        dut.setDay(3, true);
        dut.setDay(5, true);
        assertEquals(false, dut.runsMondays());
        assertEquals(true, dut.runsTuesdays());
        assertEquals(false, dut.runsWednesdays());
        assertEquals(true, dut.runsThursdays());
        assertEquals(false, dut.runsFridays());
        assertEquals(true, dut.runsSaturdays());
        assertEquals(false, dut.runsSundays());
        assertEquals("0101010", daysToString(dut));
    }

    @Test
    public void testClearDays() {
        TimetableDaysOfWeek dut = new TimetableDaysOfWeek();

        dut.setDay(2, true);
        dut.setDay(4, true);
        assertEquals(false, dut.runsMondays());
        assertEquals(false, dut.runsTuesdays());
        assertEquals(true, dut.runsWednesdays());
        assertEquals(false, dut.runsThursdays());
        assertEquals(true, dut.runsFridays());
        assertEquals(false, dut.runsSaturdays());
        assertEquals(false, dut.runsSundays());
        assertEquals("0010100", daysToString(dut));


        dut.setDay(3, true);
        dut.setDay(2, false);
        assertEquals(false, dut.runsMondays());
        assertEquals(false, dut.runsTuesdays());
        assertEquals(false, dut.runsWednesdays());
        assertEquals(true, dut.runsThursdays());
        assertEquals(true, dut.runsFridays());
        assertEquals(false, dut.runsSaturdays());
        assertEquals(false, dut.runsSundays());
        assertEquals("0001100", daysToString(dut));
    }

    @Test
    public void testParseString() {
        TimetableDaysOfWeek dut = new TimetableDaysOfWeek();

        String s = "1111100";
        dut.parseString(s);
        assertEquals(s, daysToString(dut));

        s = "0000011";
        dut.parseString(s);
        assertEquals(s, daysToString(dut));
    }

    @Test
    public void testToString() {
        TimetableDaysOfWeek dut = new TimetableDaysOfWeek();
        String s = "1111100";
        dut.parseString(s);
        assertEquals(s, daysToString(dut));
        assertEquals(s, dut.toString());
    }
}