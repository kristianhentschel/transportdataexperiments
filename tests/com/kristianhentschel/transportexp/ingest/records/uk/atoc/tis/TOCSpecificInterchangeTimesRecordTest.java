package com.kristianhentschel.transportexp.ingest.records.uk.atoc.tis;

import com.kristianhentschel.transportexp.ingest.records.uk.atoc.tis.TOCSpecificInterchangeTimesRecord;
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

        assertEquals("LUT", dut.getStationCode());
        assertEquals("TL", dut.getArrivingTrainToc());
        assertEquals("TL", dut.getDepartingTrainToc());
        assertEquals(4, dut.getMinimumInterchangeTime());
        assertEquals("(Luton)", dut.getComments());
        assertEquals(true, dut.hasComments());
    }

    @Test
    public void testNoComments() throws Exception {
        String input = "WIM,SN,TL,5,";
        TOCSpecificInterchangeTimesRecord dut = new TOCSpecificInterchangeTimesRecord(input);

        assertEquals("WIM", dut.getStationCode());
        assertEquals("SN", dut.getArrivingTrainToc());
        assertEquals("TL", dut.getDepartingTrainToc());
        assertEquals(5, dut.getMinimumInterchangeTime());
        assertEquals("", dut.getComments());
        assertEquals(false, dut.hasComments());
    }

    @Test
    public void testNoCommentsWithLineEnd() throws Exception {
        String input = "WIM,SN,TL,5,\r\n";
        TOCSpecificInterchangeTimesRecord dut = new TOCSpecificInterchangeTimesRecord(input);

        assertEquals("WIM", dut.getStationCode());
        assertEquals("SN", dut.getArrivingTrainToc());
        assertEquals("TL", dut.getDepartingTrainToc());
        assertEquals(5, dut.getMinimumInterchangeTime());
        assertEquals("", dut.getComments());
        assertEquals(false, dut.hasComments());
    }
}