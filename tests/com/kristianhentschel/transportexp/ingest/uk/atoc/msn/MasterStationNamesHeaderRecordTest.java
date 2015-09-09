package com.kristianhentschel.transportexp.ingest.uk.atoc.msn;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kristian on 05/08/2015.
 */
public class MasterStationNamesHeaderRecordTest {

    @Test
    public void testHeaderRecord() throws Exception {
        String input = "A                             FILE-SPEC=05 1.00 30/07/15 09.35.22   57 ";
        MasterStationNamesHeaderRecord dut = new MasterStationNamesHeaderRecord(input);

        assertEquals("05 1.00", dut.getSpecVersion());
        assertEquals("30/07/15", dut.getDate());
        assertEquals("09.35.22", dut.getTime());
        assertEquals(57, dut.getFileVersion());
    }
}