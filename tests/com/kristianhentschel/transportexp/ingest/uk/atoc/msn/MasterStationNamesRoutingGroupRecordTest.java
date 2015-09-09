package com.kristianhentschel.transportexp.ingest.uk.atoc.msn;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kristian on 06/08/2015.
 */
public class MasterStationNamesRoutingGroupRecordTest {
    @Test
    public void testRoutingGroup(){
        String input = "V    WALTHAMSTOW CENTRAL            WHC WMW BHO                                   ";
        MasterStationNamesRoutingGroupRecord dut = new MasterStationNamesRoutingGroupRecord(input);

        assertEquals("WALTHAMSTOW CENTRAL", dut.getGroupName());
        assertEquals("WHC", dut.getStation(0));
        assertEquals("WMW", dut.getStation(1));
        assertEquals("BHO", dut.getStation(2));
        assertEquals(3, dut.getNumStations());

        // TODO: Test expected exception on higher station numbers.
    }

}