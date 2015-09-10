package com.kristianhentschel.transportexp.timetable.utilities;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by Kristian on 04/09/2015.
 */
public class TimetableDurationTest {

    @Test
    public void testMinutesAgainstCalendar() {
        TimetableDuration dut = new TimetableDuration();
        dut.addMinutes(10);

        Calendar c = Calendar.getInstance();
        long time_start = c.getTime().getTime();
        c.add(Calendar.MINUTE, 10);
        long time_end = c.getTimeInMillis();


        assertEquals(time_end, dut.getUTCTimeAfter(time_start));
    }

    @Test
    public void testToString() {
        TimetableDuration dut = new TimetableDuration();
        dut.addMinutes(10);
        dut.addSeconds(30);

        assertEquals(10*60*1000 + 30*1000, dut.getMilliseconds());
        assertEquals("630 seconds", dut.toString());
    }

}