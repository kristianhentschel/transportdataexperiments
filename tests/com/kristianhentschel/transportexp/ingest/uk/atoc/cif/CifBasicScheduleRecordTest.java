package com.kristianhentschel.transportexp.ingest.uk.atoc.cif;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kristian on 07/08/2015.
 */
public class CifBasicScheduleRecordTest {
    @Test
    public void testWithRealData() {
        String input = "BSNC140061505171512060000001 BBS0B00    125527005                              P";
        CifBasicScheduleRecord dut = new CifBasicScheduleRecord(input);

        assertEquals(AbstractCifRecord.TRANSACTION_TYPE.NEW, dut.getTransactionType());
        assertEquals("C14006", dut.getTrainUid());
        assertEquals("150517", dut.getDateRunsFrom());
        assertEquals("151206", dut.getDateRunsTo());
        assertEquals("0000001", dut.getDaysRun());

        //assertEquals(false, dut.hasBankHolidayRunning());
        assertEquals(AbstractCifRecord.BHX.NONE, dut.getBankHolidayRunning());
        assertEquals(AbstractCifRecord.STATUS.BUS, dut.getTrainStatus());
        assertEquals(AbstractCifRecord.CATEGORY.BS, dut.getTrainCategory());
        assertEquals("0B00", dut.getTrainIdentity());
        //assertEquals(false, dut.hasHeadcode());
        assertEquals("", dut.getHeadcode());
        // skip course indicator
        assertEquals("25527005", dut.getTrainServiceCode());
//        assertEquals(false, dut.hasPortionId());
        assertEquals(AbstractCifRecord.BUSSEC.NONE, dut.getPortionId());
        assertEquals(AbstractCifRecord.POWER_TYPE.NONE, dut.getPowerType());
//        assertEquals(false, dut.hasTimingLoad());
        assertEquals("", dut.getTimingLoad()); // TODO could be a class, as it is a combination of enum and a number
        assertEquals(0, dut.getSpeed());
//        assertEquals(false, dut.hasOpChars());
        assertEquals("", dut.getOpChars());
//        assertEquals(false, dut.hasSeatingClass());
        assertEquals(AbstractCifRecord.SEATING_CLASS.FIRST_AND_STANDARD, dut.getSeatingClass());
//        assertEquals(false, dut.hasSleepers());
        assertEquals(AbstractCifRecord.SLEEPERS.NONE, dut.getSleepers());
//        assertEquals(false, dut.hasReservations());
        assertEquals(AbstractCifRecord.RESERVATIONS.NONE, dut.getReservations());
        // skip connect indicator (not used as per spec)
//        assertEquals(false, dut.hasCateringCode());
        assertEquals("", dut.getCateringCode());
//        assertEquals(false, dut.hasServiceBranding());
        assertEquals("", dut.getServiceBranding());
        //skip stp indicator???
    }
}