package com.kristianhentschel.transportexp.ingest.records.uk.atoc.cif;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kristian on 07/08/2015.
 */
public class CifAssociationRecordTest {
    @Test
    public void testAllFields() {
        String input = "AAN1234566543217001017001010000000JJS123456701TP                               P";
        CifAssociationRecord dut = new CifAssociationRecord(input);

        assertEquals(AbstractCifRecord.TRANSACTION_TYPE.NEW, dut.getTransactionType());
        assertEquals("123456", dut.getMainTrainUid());
        assertEquals("654321", dut.getAssociatedTrainUid());
        assertEquals("700101", dut.getStartDate());
        assertEquals("700101", dut.getEndDate());
        assertEquals("0000000", dut.getDays());
        assertEquals(CifAssociationRecord.CATEGORY.JOIN, dut.getCategory());
        assertEquals(CifAssociationRecord.DATE_IND.STANDARD, dut.getDateInd());
        assertEquals("1234567", dut.getLocation());
        assertEquals("0", dut.getBaseLocSuffix());
        assertEquals("1", dut.getAssocLocSuffix());
        assertEquals(CifAssociationRecord.TYPE.PASSENGER_USE, dut.getType());
        // TODO: STP indicator?
    }

}