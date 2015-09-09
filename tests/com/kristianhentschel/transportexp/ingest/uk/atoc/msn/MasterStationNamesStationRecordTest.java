package com.kristianhentschel.transportexp.ingest.uk.atoc.msn;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kristian on 05/08/2015.
 */
public class MasterStationNamesStationRecordTest {

    @Test
    public void testStationRecord() throws Exception {
        String input = "A    HYNDLAND                      1HYNDLNDHYN   HYN12553 66676 3             530 ";
        MasterStationNamesStationRecord dut = new MasterStationNamesStationRecord(input);

        assertEquals("HYNDLAND", dut.getStationName());
        assertEquals(MasterStationNamesStationRecord.CATE.SMALL_INTERCHANGE, dut.getInterchangeStatus());
        assertEquals("HYNDLND", dut.getTiploc());
        assertEquals("HYN", dut.getSubsidiaryCode());
        assertEquals("HYN", dut.getCode());
        assertEquals(12553, dut.getEasting());
        assertEquals(false, dut.isEstimated());
        assertEquals(66676, dut.getNorthing());
        assertEquals(3, dut.getChangeTime());
        assertEquals(false, dut.isSubsidiary());
    }

    @Test
    public void testStationRecordLongFields() throws Exception {
        String input = "A____0123456789012345678901234567890TIPLOC0S3A___3AL00000E0000015";
        MasterStationNamesStationRecord dut = new MasterStationNamesStationRecord(input);

        assertEquals("012345678901234567890123456789", dut.getStationName());
        assertEquals(MasterStationNamesStationRecord.CATE.NO_INTERCHANGE, dut.getInterchangeStatus());
        assertEquals("TIPLOC0", dut.getTiploc());
        assertEquals("S3A", dut.getSubsidiaryCode());
        assertEquals("3AL", dut.getCode());
        assertEquals(0, dut.getEasting());
        assertEquals(true, dut.isEstimated());
        assertEquals(0, dut.getNorthing());
        assertEquals(15, dut.getChangeTime());
        assertEquals(true, dut.isSubsidiary());
    }

    @Test
    public void testShortTiploc() throws Exception {
        String input = "A    BISHOPBRIGGS                  0BSHB   BBG   BBG12610 66701 5             530";
        MasterStationNamesStationRecord dut = new MasterStationNamesStationRecord(input);

        assertEquals("BSHB", dut.getTiploc());
    }
}