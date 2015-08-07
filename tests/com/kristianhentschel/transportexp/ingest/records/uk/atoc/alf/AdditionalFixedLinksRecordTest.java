package com.kristianhentschel.transportexp.ingest.records.uk.atoc.alf;

import com.kristianhentschel.transportexp.ingest.records.uk.atoc.alf.AdditionalFixedLinksRecord;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kristian on 06/08/2015.
 */
public class AdditionalFixedLinksRecordTest {
    @Test
    public void testAllRequiredFields() throws Exception {
        String input = "M=METRO,O=ALT,D=BUR,T=63,S=0600,E=2300,P=4,R=1111110";
        AdditionalFixedLinksRecord dut = new AdditionalFixedLinksRecord(input);

        assertEquals(AdditionalFixedLinksRecord.MODE.METRO, dut.getMode());

        assertEquals("ALT",     dut.getOrigin());
        assertEquals("BUR",     dut.getDestination());

        assertEquals(63,        dut.getTime());

        assertEquals("0600",    dut.getStartTime());
        assertEquals("2300",    dut.getEndTime());

        assertEquals(4,         dut.getPriority());
        assertEquals(null,      dut.getStartDate());
        assertEquals(null,      dut.getEndDate());
        assertEquals("1111110", dut.getDaysOfWeek());
    }

    @Test
    public void testGetMode() throws Exception {
        String input = "M=WALK,O=MAN,D=MCV,T=8,S=0001,E=2359,P=5,F=07/01/2009,U=28/02/2009";
        AdditionalFixedLinksRecord dut = new AdditionalFixedLinksRecord(input);

        assertEquals(AdditionalFixedLinksRecord.MODE.WALK, dut.getMode());
    }

    @Test
    public void testGetOrigin() throws Exception {
        String input = "M=WALK,O=MAN,D=MCV,T=8,S=0001,E=2359,P=5,F=07/01/2009,U=28/02/2009";
        AdditionalFixedLinksRecord dut = new AdditionalFixedLinksRecord(input);

        assertEquals("MAN", dut.getOrigin());
    }

    @Test
    public void testGetDestination() throws Exception {
        String input = "M=WALK,O=MAN,D=MCV,T=8,S=0001,E=2359,P=5,F=07/01/2009,U=28/02/2009";
        AdditionalFixedLinksRecord dut = new AdditionalFixedLinksRecord(input);

        assertEquals("MCV", dut.getDestination());
    }

    @Test
    public void testGetTime() throws Exception {
        String input = "M=WALK,O=MAN,D=MCV,T=8,S=0001,E=2359,P=5,F=07/01/2009,U=28/02/2009";
        AdditionalFixedLinksRecord dut = new AdditionalFixedLinksRecord(input);

        assertEquals(8, dut.getTime());
    }

    @Test
    public void testGetStartTime() throws Exception {
        String input = "M=WALK,O=MAN,D=MCV,T=8,S=0001,E=2359,P=5,F=07/01/2009,U=28/02/2009";
        AdditionalFixedLinksRecord dut = new AdditionalFixedLinksRecord(input);

        assertEquals("0001", dut.getStartTime());
    }

    @Test
    public void testGetEndTime() throws Exception {
        String input = "M=WALK,O=MAN,D=MCV,T=8,S=0001,E=2359,P=5,F=07/01/2009,U=28/02/2009";
        AdditionalFixedLinksRecord dut = new AdditionalFixedLinksRecord(input);

        assertEquals("2359", dut.getEndTime());
    }

    @Test
    public void testGetPriority() throws Exception {
        String input = "M=WALK,O=MAN,D=MCV,T=8,S=0001,E=2359,P=5,F=07/01/2009,U=28/02/2009";
        AdditionalFixedLinksRecord dut = new AdditionalFixedLinksRecord(input);

        assertEquals(5, dut.getPriority());
    }

    @Test
    public void testGetStartDate() throws Exception {
        // TODO
    }

    @Test
    public void testHasStartDate() throws Exception {
        AdditionalFixedLinksRecord dut;

        // All date fields set
        String input1 = "M=WALK,O=MAN,D=MCV,T=8,S=0001,E=2359,P=5,F=07/01/2009,U=28/02/2009";
        dut = new AdditionalFixedLinksRecord(input1);
        assertEquals(true, dut.hasStartDate());

        // Start and end date order swapped
        String input2 = "M=WALK,O=MAN,D=MCV,T=8,S=0001,E=2359,P=5,U=07/01/2009,F=28/02/2009";
        dut = new AdditionalFixedLinksRecord(input2);
        assertEquals(true, dut.hasStartDate());

        // no start date
        String input3 = "M=WALK,O=MAN,D=MCV,T=8,S=0001,E=2359,P=5,U=28/02/2009";
        dut = new AdditionalFixedLinksRecord(input3);
        assertEquals(false, dut.hasStartDate());
    }

    @Test
    public void testGetEndDate() throws Exception {
        // TODO
    }

    @Test
    public void testHasEndDate() throws Exception {
        // TODO
    }

    @Test
    public void testGetDaysOfWeek() throws Exception {
        // TODO
    }
}