package com.kristianhentschel.transportexp.ingest.utilities.records.uk.atoc;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kristian on 05/08/2015.
 */
public class TOCSpecificInterchangeTimesRecordTest {

    @Test
    public void testAllFields() throws Exception {
        String input = "LUT,TL,TL,4,(Luton)";
        TOCSpecificInterchangeTimesRecord dut = new TOCSpecificInterchangeTimesRecord(input);

        assertEquals("LUT", dut.getField("station_code"));
        assertEquals("TL", dut.getField("arriving_train_toc"));
        assertEquals("TL", dut.getField("departing_train_toc"));
        assertEquals("4", dut.getField("minimum_interchange_time"));
        assertEquals("(Luton)", dut.getField("comments"));
    }

    @Test
    public void testNoComments() throws Exception {
        String input = "WIM,SN,TL,5,";
        TOCSpecificInterchangeTimesRecord dut = new TOCSpecificInterchangeTimesRecord(input);

        assertEquals("WIM", dut.getField("station_code"));
        assertEquals("SN", dut.getField("arriving_train_toc"));
        assertEquals("TL", dut.getField("departing_train_toc"));
        assertEquals("5", dut.getField("minimum_interchange_time"));
        assertEquals("", dut.getField("comments"));
    }

    @Test
    public void testNoCommentsWithLineEnd() throws Exception {
        String input = "WIM,SN,TL,5,\r\n";
        TOCSpecificInterchangeTimesRecord dut = new TOCSpecificInterchangeTimesRecord(input);

        assertEquals("WIM", dut.getField("station_code"));
        assertEquals("SN", dut.getField("arriving_train_toc"));
        assertEquals("TL", dut.getField("departing_train_toc"));
        assertEquals("5", dut.getField("minimum_interchange_time"));
        assertEquals("", dut.getField("comments"));
    }
}