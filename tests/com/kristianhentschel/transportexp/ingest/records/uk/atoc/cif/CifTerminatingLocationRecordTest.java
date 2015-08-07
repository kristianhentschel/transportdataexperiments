package com.kristianhentschel.transportexp.ingest.records.uk.atoc.cif;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kristian on 07/08/2015.
 */
public class CifTerminatingLocationRecordTest {
    @Test
    public void testWithRealData() {
        String input = "LTSTPX    1810 18102     TF                                                     ";
        CifTerminatingLocationRecord dut = new CifTerminatingLocationRecord(input);

        assertEquals("STPX", dut.getLocationTiploc());
        assertEquals("", dut.getLocationSuffix());
        assertEquals("1810", dut.getScheduledArrival());
        assertEquals("1810", dut.getPublicArrival());
        assertEquals(true, dut.hasPlatform());
        assertEquals("2", dut.getPlatform());
        assertEquals(false, dut.hasPath());
        assertEquals("", dut.getPath());
        assertEquals("TF", dut.getActivity());
    }

}