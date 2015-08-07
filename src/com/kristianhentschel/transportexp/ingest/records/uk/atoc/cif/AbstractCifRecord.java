package com.kristianhentschel.transportexp.ingest.records.uk.atoc.cif;

import com.kristianhentschel.transportexp.ingest.records.formats.FixedWidthRecord;

/**
 * Created by Kristian on 07/08/2015.
 */
public abstract class AbstractCifRecord extends FixedWidthRecord{
    public enum TRANSACTION_TYPE {NEW, DELETE, REVISE}

    protected TRANSACTION_TYPE parseTransactionType(String transaction_type) {
        switch (transaction_type) {
            case "N":
                return TRANSACTION_TYPE.NEW;
            case "D":
                return TRANSACTION_TYPE.DELETE;
            case "R":
                return TRANSACTION_TYPE.REVISE;
            default:
                return TRANSACTION_TYPE.NEW;
        }
    }

    public enum BHX {NONE, X, E, G}

    protected BHX parseBHX(String bhx) {
        switch (bhx) {
            case "X":
                return BHX.X;
            case "E":
                return BHX.E;
            case "G":
                return BHX.G;
            default:
                return BHX.NONE;
        }
    }

    public enum STATUS {NONE, BUS, FREIGHT, PASSENGERS_AND_PARCELS, SHIP, TRIP, STP_PASSENGER_AND_PARCELS, STP_FREIGHT, STP_TRIP, STP_SHIP, STP_BUS}

    protected STATUS parseStatus(String status) {
        switch(status) {
            case "B":
                return STATUS.BUS;
            case "F":
                return STATUS.FREIGHT;
            case "P":
                return STATUS.PASSENGERS_AND_PARCELS;
            case "S":
                return STATUS.SHIP;
            case "T":
                return STATUS.TRIP;
            case "1":
                return STATUS.STP_PASSENGER_AND_PARCELS;
            case "2":
                return STATUS.STP_FREIGHT;
            case "3":
                return STATUS.STP_TRIP;
            case "4":
                return STATUS.STP_SHIP;
            case "5":
                return STATUS.STP_BUS;
            default:
                return STATUS.NONE;
        }
    }

    public enum CATEGORY {
        // not specified, used internally if field blank/truncated
        NONE,
        // Ordinary passenger trains
        OL, OU, OO, OS, OW,
        // Express passenger trains
        XC, XD, XI, XR, XU, XX, XZ,
        // buses nad ships
        BR, BS, SS,
        // empty coaching stock trains
        EE, EL, ES,
        // Parcels and postal trains
        JJ, PM, PP, PV,
        // Departmental trains
        DD, DH, DI, DQ, DT, DY,
        // Light locomotives
        ZB, ZZ,
        // Railfright Distribution
        J2, H2, J6, J5, J3, J9, H9, H8, J8, J4,
        // Trainload Freight
        A0, E0, B0, B1, B4, B5, B6, B7,
        // Railfright Distribution (channel tunnel)
        H0, H1, H3, H4, H5, H6
    }

    protected CATEGORY parseCategory(String category) {
        try {
            return CATEGORY.valueOf(category);
        } catch (IllegalArgumentException e) {
            return CATEGORY.NONE;
        }
    }

    public enum BUSSEC {NONE, OPERATOR_SPECIFIC, PORTION_0, PORTION_1, PORTION_2, PORTION_4, PORTION_8}

    protected BUSSEC parsePortionId(String portionId) {
        switch (portionId) {
            case "Z":
                return BUSSEC.OPERATOR_SPECIFIC;
            case "0":
                return BUSSEC.PORTION_0;
            case "1":
                return BUSSEC.PORTION_1;
            case "2":
                return BUSSEC.PORTION_2;
            case "4":
                return BUSSEC.PORTION_4;
            case "8":
                return BUSSEC.PORTION_8;
            default:
                return BUSSEC.NONE;
        }
    }

    public enum POWER_TYPE {NONE, D, DEM, DMU, E, ED, EML, EMU, HST}

    protected POWER_TYPE parsePowerType(String powerType) {
        try {
            return POWER_TYPE.valueOf(powerType.trim());
        } catch (IllegalArgumentException e) {
            return POWER_TYPE.NONE;
        }
    }

    public enum SEATING_CLASS {FIRST_AND_STANDARD, STANDARD_ONLY}

    protected SEATING_CLASS parseSeatingClass(String seating_class) {
        if (seating_class.equals("S")) {
            return SEATING_CLASS.STANDARD_ONLY;
        } else {
            return SEATING_CLASS.FIRST_AND_STANDARD;
        }
    }

    public enum SLEEPERS {NONE, FIRST_AND_STANDARD, FIRST_ONLY, STANDARD_ONLY}

    protected SLEEPERS parseSleepers(String sleepers) {
        switch(sleepers) {
            case "B":
                return SLEEPERS.FIRST_AND_STANDARD;
            case "F":
                return SLEEPERS.FIRST_ONLY;
            case "S":
                return SLEEPERS.STANDARD_ONLY;
            default:
                return SLEEPERS.NONE;
        }
    }
    public enum RESERVATIONS {NONE, COMPULSORY, BICYCLES, RECOMMENDED, POSSIBLE_FROM_ANY_STATION}

    protected RESERVATIONS parseReservations (String reservations) {
        switch (reservations) {
            case "A":
                return RESERVATIONS.COMPULSORY;
            case "E":
                return RESERVATIONS.BICYCLES;
            case "R":
                return RESERVATIONS.RECOMMENDED;
            case "S":
                return RESERVATIONS.POSSIBLE_FROM_ANY_STATION;
            default:
                return RESERVATIONS.NONE;
        }
    }

    public AbstractCifRecord(String record_text) {
        super(record_text);
        skipChars(2); // skip record identity
    }

}
