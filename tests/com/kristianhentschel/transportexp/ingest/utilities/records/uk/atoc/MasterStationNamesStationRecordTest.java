package com.kristianhentschel.transportexp.ingest.utilities.records.uk.atoc;

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

        assertEquals("A", dut.getField("record_type"));
        assertEquals("HYNDLAND", dut.getField("station_name").trim());
        assertEquals("1", dut.getField("cate_type"));
        assertEquals("HYNDLND", dut.getField("tiploc"));
        assertEquals("HYN", dut.getField("subsidiary_3_alpha"));
        assertEquals("HYN", dut.getField("3_alpha"));
        assertEquals("12553", dut.getField("easting"));
        assertEquals(" ", dut.getField("estimated"));
        assertEquals("66676", dut.getField("northing"));
        assertEquals("3", dut.getField("change_time").trim());
    }

    public void testStationRecordLongFields() throws Exception {
        String input = "A___012345678901234567890134567890TIPLOC0S3A___3AL00000E0000015";
        MasterStationNamesStationRecord dut = new MasterStationNamesStationRecord(input);

        assertEquals("A", dut.getField("record_type"));
        assertEquals("012345678901234567890123456789", dut.getField("station_name").trim());
        assertEquals("0", dut.getField("cate_type"));
        assertEquals("TIPLOC0", dut.getField("tiploc"));
        assertEquals("S3A", dut.getField("subsidiary_3_alpha"));
        assertEquals("3AL", dut.getField("3_alpha"));
        assertEquals("00000", dut.getField("easting"));
        assertEquals("E", dut.getField("estimated"));
        assertEquals("00000", dut.getField("northing"));
        assertEquals("15", dut.getField("change_time").trim());
    }
}