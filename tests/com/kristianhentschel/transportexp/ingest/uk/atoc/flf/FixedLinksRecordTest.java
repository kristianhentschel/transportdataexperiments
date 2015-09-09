package com.kristianhentschel.transportexp.ingest.uk.atoc.flf;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kristian on 06/08/2015.
 */
public class FixedLinksRecordTest {
    @Test
    public void testAllFields(){
        String input = "ADDITIONAL LINK: METRO BETWEEN ALT AND BUR IN 63 MINUTES";
        FixedLinksRecord dut = new FixedLinksRecord(input);

        assertEquals(FixedLinksRecord.MODE.METRO, dut.getMode());
        assertEquals("ALT", dut.getOrigin());
        assertEquals("BUR", dut.getDestination());
        assertEquals(63, dut.getTime());
    }

    @Test
    public void testSuppressedZeroes(){
        String input = "ADDITIONAL LINK: WALK BETWEEN ABR AND ACY IN 3 MINUTES";
        FixedLinksRecord dut = new FixedLinksRecord(input);

        assertEquals(3, dut.getTime());
    }

}