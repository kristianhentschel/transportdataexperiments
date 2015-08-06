package com.kristianhentschel.transportexp.ingest.utilities.records.UnitedKingdomATOC;

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

        assertEquals("METRO",   dut.getField("mode"));
        assertEquals("ALT",     dut.getField("origin"));
        assertEquals("BUR",     dut.getField("destination"));
        assertEquals("63",      dut.getField("time"));
    }

    @Test
    public void testSuppressedZeroes(){
        String input = "ADDITIONAL LINK: WALK BETWEEN ABR AND ACY IN 3 MINUTES";
        FixedLinksRecord dut = new FixedLinksRecord(input);

        assertEquals("WALK",    dut.getField("mode"));
        assertEquals("ABR",     dut.getField("origin"));
        assertEquals("ACY",     dut.getField("destination"));
        assertEquals("3",       dut.getField("time"));
    }

}