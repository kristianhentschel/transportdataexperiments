package com.kristianhentschel.transportexp.ingest.utilities.records.UnitedKingdomATOC;

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

        assertEquals(dut.getField("station_code"), "LUT");
        assertEquals(dut.getField("arriving_train_toc"), "TL");
        assertEquals(dut.getField("departing_train_toc"), "TL");
        assertEquals(dut.getField("minimum_interchange_time"), "4");
        assertEquals(dut.getField("comments"), "(Luton)");
    }

    @Test
    public void testNoComments() throws Exception {
        String input = "WIM,SN,TL,5,";
        TOCSpecificInterchangeTimesRecord dut = new TOCSpecificInterchangeTimesRecord(input);

        assertEquals(dut.getField("station_code"), "WIM");
        assertEquals(dut.getField("arriving_train_toc"), "SN");
        assertEquals(dut.getField("departing_train_toc"), "TL");
        assertEquals(dut.getField("minimum_interchange_time"), "5");
        assertEquals(dut.getField("comments"), "");
    }

    @Test
    public void testNoCommentsWithLineEnd() throws Exception {
        String input = "WIM,SN,TL,5,\r\n";
        TOCSpecificInterchangeTimesRecord dut = new TOCSpecificInterchangeTimesRecord(input);

        assertEquals(dut.getField("station_code"), "WIM");
        assertEquals(dut.getField("arriving_train_toc"), "SN");
        assertEquals(dut.getField("departing_train_toc"), "TL");
        assertEquals(dut.getField("minimum_interchange_time"), "5");
        assertEquals(dut.getField("comments"), "");
    }
}