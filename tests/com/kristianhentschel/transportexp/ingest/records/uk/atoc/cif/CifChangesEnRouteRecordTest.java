package com.kristianhentschel.transportexp.ingest.records.uk.atoc.cif;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kristian on 07/08/2015.
 */
public class CifChangesEnRouteRecordTest {
    @Test
    public void testWithRealData() {
        String input = "CRLESTER  XX1C911291122152000 HST    110      B S C                EM129100     ";
        CifChangesEnRouteRecord dut = new CifChangesEnRouteRecord(input);

        assertEquals("LESTER", dut.getLocationTiploc());
        assertEquals("", dut.getLocationSuffix());
        assertEquals(AbstractCifRecord.CATEGORY.XX, dut.getTrainCategory());
        assertEquals("1C91", dut.getTrainIdentity());
        assertEquals(true, dut.hasHeadcode());
        assertEquals("1291", dut.getHeadcode());
        // skip course indicator
        assertEquals("22152000", dut.getTrainServiceCode());
        assertEquals(AbstractCifRecord.BUSSEC.NONE, dut.getPortionId());
        assertEquals(AbstractCifRecord.POWER_TYPE.HST, dut.getPowerType());
        assertEquals(false, dut.hasTimingLoad());
        assertEquals("", dut.getTimingLoad());
        assertEquals(110, dut.getSpeed());
        assertEquals(false, dut.hasOpChars());
        assertEquals("", dut.getOpChars());
        assertEquals(AbstractCifRecord.SEATING_CLASS.FIRST_AND_STANDARD, dut.getSeatingClass());
        assertEquals(AbstractCifRecord.SLEEPERS.NONE, dut.getSleepers());
        assertEquals(AbstractCifRecord.RESERVATIONS.POSSIBLE_FROM_ANY_STATION, dut.getReservations());
        // skip connect indicator (not used as per spec)
        assertEquals(true, dut.hasCateringCode());
        assertEquals("C", dut.getCateringCode());
        assertEquals(false, dut.hasServiceBranding());
        assertEquals("", dut.getServiceBranding());
        assertEquals(false, dut.hasUicCode());
        assertEquals("", dut.getUicCode());
        assertEquals("EM", dut.getRsidToc());
        assertEquals("1291", dut.getRsidTrainNumber());
        assertEquals("00", dut.getRsidPortionNumber());
    }

}