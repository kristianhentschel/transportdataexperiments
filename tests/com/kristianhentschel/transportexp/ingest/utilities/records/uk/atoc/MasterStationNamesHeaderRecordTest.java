package com.kristianhentschel.transportexp.ingest.utilities.records.uk.atoc;

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

        assertEquals("A", dut.getField("record_type"));
        assertEquals("FILE-SPEC=", dut.getField("file_spec"));
        assertEquals("05 1.00 ", dut.getField("spec_version"));
        assertEquals("30/07/15", dut.getField("date"));
        assertEquals("09.35.22", dut.getField("time"));
        assertEquals("57", dut.getField("file_version"));
    }
}