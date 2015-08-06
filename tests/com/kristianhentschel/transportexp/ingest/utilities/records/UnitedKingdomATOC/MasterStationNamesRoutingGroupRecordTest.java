package com.kristianhentschel.transportexp.ingest.utilities.records.UnitedKingdomATOC;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kristian on 06/08/2015.
 */
public class MasterStationNamesRoutingGroupRecordTest {
    @Test
    public void testRoutingGroup (){
        String input = "V    WALTHAMSTOW CENTRAL            WHC WMW BHO                                   ";
        MasterStationNamesRoutingGroupRecord dut = new MasterStationNamesRoutingGroupRecord(input);

        assertEquals("V", dut.getField("record_type"));
        assertEquals("WALTHAMSTOW CENTRAL", dut.getField("group_name").trim());
        assertEquals("WHC", dut.getField("station_1"));
        assertEquals("WMW", dut.getField("station_2"));
        assertEquals("BHO", dut.getField("station_3"));
        assertEquals("   ", dut.getField("station_4"));
        assertEquals("   ", dut.getField("station_5"));
        assertEquals("   ", dut.getField("station_6"));
        assertEquals("   ", dut.getField("station_7"));
        assertEquals("   ", dut.getField("station_8"));
        assertEquals("   ", dut.getField("station_9"));
        assertEquals("   ", dut.getField("station_10"));

    }

}