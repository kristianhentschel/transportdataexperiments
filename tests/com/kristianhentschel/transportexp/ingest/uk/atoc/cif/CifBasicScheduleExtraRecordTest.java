package com.kristianhentschel.transportexp.ingest.uk.atoc.cif;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kristian on 07/08/2015.
 */
public class CifBasicScheduleExtraRecordTest {
    @Test
    public void testWithRealData() {
        String input ="BX         GWYGW010300                                                    ";
        CifBasicScheduleExtraRecord dut = new CifBasicScheduleExtraRecord(input);

        assertEquals(false, dut.hasUicCode());
        assertEquals("", dut.getUicCode());
        assertEquals("GW", dut.getAtocCode());
        assertEquals(true, dut.getAts());
        assertEquals("GW", dut.getRsidToc());
        assertEquals("0103", dut.getRsidTrainNumber());
        assertEquals("00", dut.getRsidPortionNumber());

    }

}