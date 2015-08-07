package com.kristianhentschel.transportexp.ingest.records.uk.atoc.cif;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kristian on 07/08/2015.
 */
public class CifHeaderRecordTest {

    @Test
    public void testAllFields() {
        String input = "HDTPS.UCFCATE.PD1507313107152213DFTTISS       FA310715140516 ";
        CifHeaderRecord dut = new CifHeaderRecord(input);

        // TODO: CifHeaderRecord is not implemented as it is not really relevant to our application.
    }
}