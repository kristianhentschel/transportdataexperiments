package com.kristianhentschel.transportexp.ingest.records.uk.atoc.cif;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kristian on 07/08/2015.
 */
public class CifOriginLocationRecordTest {
    @Test
    public void testWithRealData() {
        String input = "LOLEEDS   1434 143411 D      TB                                                 ";
        CifOriginLocationRecord dut = new CifOriginLocationRecord(input);

        assertEquals("LEEDS", dut.getLocationTiploc());
        assertEquals("", dut.getLocationSuffix());
        assertEquals("1434", dut.getScheduledDeparture());
        assertEquals("1434", dut.getPublicDeparture());
        assertEquals(true, dut.hasPlatform());
        assertEquals("11", dut.getPlatform());
        assertEquals(true, dut.hasLine());
        assertEquals("D", dut.getLine());
        assertEquals(false, dut.hasEngineeringAllowance());
        assertEquals("", dut.getEngineeringAllowance());
        assertEquals(false, dut.hasPathingAllowance());
        assertEquals("", dut.getPathingAllowance());
        assertEquals("TB", dut.getActivity()); // TODO may have multiple activities?
        assertEquals(false, dut.hasPerformanceAllowance());
        assertEquals("", dut.getPerformanceAllowance());

    }

}