package com.kristianhentschel.transportexp.ingest.utilities.records.uk.atoc;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kristian on 06/08/2015.
 */
public class AdditionalFixedLinksRecordTest {
    @Test
    public void testRequiredFields() {
        String input = "M=METRO,O=ALT,D=BUR,T=63,S=0600,E=2300,P=4,R=1111110";
        AdditionalFixedLinksRecord dut = new AdditionalFixedLinksRecord(input);

        assertEquals("METRO",   dut.getField("mode"));
        assertEquals("ALT",     dut.getField("origin"));
        assertEquals("BUR",     dut.getField("destination"));
        assertEquals("63",      dut.getField("time"));
        assertEquals("0600",    dut.getField("start_time"));
        assertEquals("2300",    dut.getField("end_time"));
        assertEquals("4",       dut.getField("priority"));
        assertEquals("",        dut.getField("start_date"));
        assertEquals("",        dut.getField("end_date"));
        assertEquals("1111110", dut.getField("days_of_week"));
    }

    @Test
    public void testAllFields() {
        String input = "M=METRO,O=MAN,D=MCV,T=8,S=0001,E=2359,P=5,F=07/01/2009,U=28/02/2009";
        AdditionalFixedLinksRecord dut = new AdditionalFixedLinksRecord(input);

        assertEquals("METRO",   dut.getField("mode"));
        assertEquals("MAN",     dut.getField("origin"));
        assertEquals("MCV",     dut.getField("destination"));
        assertEquals("8",       dut.getField("time"));
        assertEquals("0001",    dut.getField("start_time"));
        assertEquals("2359",    dut.getField("end_time"));
        assertEquals("5",       dut.getField("priority"));
        assertEquals("07/01/2009", dut.getField("start_date"));
        assertEquals("28/02/2009", dut.getField("end_date"));
        assertEquals("",        dut.getField("days_of_week"));
    }
}