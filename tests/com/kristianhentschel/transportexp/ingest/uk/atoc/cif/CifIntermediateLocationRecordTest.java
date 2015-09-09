package com.kristianhentschel.transportexp.ingest.uk.atoc.cif;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kristian on 07/08/2015.
 */
public class CifIntermediateLocationRecordTest {
    @Test
    public void testWithRealData() {
        String input = "LILEEDSWJ           1435 00000000   D                                           ";
        CifIntermediateLocationRecord dut = new CifIntermediateLocationRecord(input);

        assertEquals("LEEDSWJ", dut.getLocationTiploc());
        assertEquals("", dut.getLocationSuffix());
        assertEquals(false, dut.hasScheduledArrival());
        assertEquals("", dut.getScheduledArrival());
        assertEquals(false, dut.hasScheduledDeparture());
        assertEquals("", dut.getScheduledDeparture());
        assertEquals(true, dut.hasScheduledPass());
        assertEquals("1435", dut.getScheduledPass());

        assertEquals("0000", dut.getPublicArrival());
        assertEquals("0000", dut.getPublicDeparture());

        assertEquals(false, dut.hasPlatform());
        assertEquals("", dut.getPlatform());
        assertEquals(true, dut.hasLine());
        assertEquals("D", dut.getLine());
        assertEquals(false, dut.hasPath());
        assertEquals("", dut.getPath());
        assertEquals(false, dut.hasActivity());
        assertEquals("", dut.getActivity());

        assertEquals(false, dut.hasEngineeringAllowance());
        assertEquals("", dut.getEngineeringAllowance());
        assertEquals(false, dut.hasPathingAllowance());
        assertEquals("", dut.getPathingAllowance());
        assertEquals(false, dut.hasPerformanceAllowance());
        assertEquals("", dut.getPerformanceAllowance());
    }

}