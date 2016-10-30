package com.kristianhentschel.transportexp.ingest.uk.atoc.cif;

/**
 * Created by Kristian on 06/08/2015.
 * Following the National Rail CIF USER SPEC v29 (August 2014)
 */
public class CifBasicScheduleRecord extends AbstractCifRecord {
    private AbstractCifRecord.TRANSACTION_TYPE transactionType;
    private String trainUid;
    private String dateRunsFrom;
    private String dateRunsTo;
    private String daysRun;
    private AbstractCifRecord.BHX bankHolidayRunning;
    private AbstractCifRecord.STATUS trainStatus;
    private AbstractCifRecord.CATEGORY trainCategory;
    private String trainIdentity;
    private String headcode;
    private String trainServiceCode;
    private AbstractCifRecord.BUSSEC portionId;
    private AbstractCifRecord.POWER_TYPE powerType;
    private String timingLoad;
    private int speed;
    private String opChars;
    private AbstractCifRecord.SEATING_CLASS seatingClass;
    private AbstractCifRecord.SLEEPERS sleepers;
    private AbstractCifRecord.RESERVATIONS reservations;
    private String cateringCode;
    private String serviceBranding;

    public CifBasicScheduleRecord(String record_text) {
        super(record_text);

        setTransactionType(takeChars(1));
        setTrainUid(takeChars(6));
        setDateRunsFrom(takeChars(6));
        setDateRunsTo(takeChars(6));
        setDaysRun(takeChars(7));
        setBankHolidayRunning(takeChars(1));
        setTrainStatus(takeChars(1));
        setTrainCategory(takeChars(2));
        setTrainIdentity(takeChars(4));
        setHeadcode(takeChars(4));
        skipChars(1); // skip non-data field course-indicator according to spec
        setTrainServiceCode(takeChars(8));
        setPortionId(takeChars(1));
        setPowerType(takeChars(3));
        setTimingLoad(takeChars(4));
        setSpeed(takeChars(3));
        setOpChars(takeChars(6));
        setSeatingClass(takeChars(1));
        setSleepers(takeChars(1));
        setReservations(takeChars(1));
        skipChars(1); // skip unused field connection indicator
        setCateringCode(takeChars(4));
        setServiceBranding(takeChars(4));
        skipChars(1);
        // TODO: skipping STP overlay indicator: setStpIndicator(takeChars(1));
    }

    public AbstractCifRecord.TRANSACTION_TYPE getTransactionType() {
        return transactionType;
    }

    private void setTransactionType(String transactionType) {
        this.transactionType = parseTransactionType(transactionType);
    }

    public String getTrainUid() {
        return trainUid;
    }

    private void setTrainUid(String trainUid) {
        this.trainUid = trainUid;
    }

    public String getDateRunsFrom() {
        return dateRunsFrom;
    }

    private void setDateRunsFrom(String dateRunsFrom) {
        this.dateRunsFrom = dateRunsFrom;
    }

    public String getDateRunsTo() {
        return dateRunsTo;
    }

    private void setDateRunsTo(String dateRunsTo) {
        this.dateRunsTo = dateRunsTo;
    }

    public String getDaysRun() {
        return daysRun;
    }

    private void setDaysRun(String daysRun) {
        this.daysRun = daysRun;
    }

    public AbstractCifRecord.BHX getBankHolidayRunning() {
        return bankHolidayRunning;
    }

    private void setBankHolidayRunning(String bankHolidayRunning) {
        this.bankHolidayRunning = parseBHX(bankHolidayRunning);
    }

    public AbstractCifRecord.STATUS getTrainStatus() {
        return trainStatus;
    }

    private void setTrainStatus(String trainStatus) {
        this.trainStatus = parseStatus(trainStatus);
    }

    public AbstractCifRecord.CATEGORY getTrainCategory() {
        return trainCategory;
    }

    private void setTrainCategory(String trainCategory) {
        this.trainCategory = parseCategory(trainCategory);
    }

    public String getTrainIdentity() {
        return trainIdentity;
    }

    private void setTrainIdentity(String trainIdentity) {
        this.trainIdentity = trainIdentity;
    }

    public String getHeadcode() {
        return headcode;
    }

    private void setHeadcode(String headcode) {
        this.headcode = headcode.trim();
    }

    public String getTrainServiceCode() {
        return trainServiceCode;
    }

    private void setTrainServiceCode(String trainServiceCode) {
        this.trainServiceCode = trainServiceCode.trim();
    }

    public AbstractCifRecord.BUSSEC getPortionId() {
        return portionId;
    }

    private void setPortionId(String portionId) {
        this.portionId = parsePortionId(portionId);
    }

    public AbstractCifRecord.POWER_TYPE getPowerType() {
        return powerType;
    }

    private void setPowerType(String powerType) {
        this.powerType = parsePowerType(powerType);
    }

    public String getTimingLoad() {
        return timingLoad;
    }

    private void setTimingLoad(String timingLoad) {
        this.timingLoad = timingLoad.trim();
    }

    public int getSpeed() {
        return speed;
    }

    private void setSpeed(String speed) {
        speed = speed.trim();
        if(speed.length() == 0)
            this.speed = 0;
        else
            this.speed = Integer.parseInt(speed);
    }

    public String getOpChars() {
        return opChars;
    }

    private void setOpChars(String opChars) {
        this.opChars = opChars.trim();
    }

    public AbstractCifRecord.SEATING_CLASS getSeatingClass() {
        return seatingClass;
    }

    private void setSeatingClass(String seatingClass) {
        this.seatingClass = parseSeatingClass(seatingClass);
    }

    public AbstractCifRecord.SLEEPERS getSleepers() {
        return sleepers;
    }

    public AbstractCifRecord.RESERVATIONS getReservations() {
        return reservations;
    }

    private void setReservations(String reservations) {
        this.reservations = parseReservations(reservations);
    }

    public String getCateringCode() {
        return cateringCode;
    }

    private void setCateringCode(String cateringCode) {
        this.cateringCode = cateringCode.trim();
    }

    public String getServiceBranding() {
        return serviceBranding;
    }

    private void setServiceBranding(String serviceBranding) {
        this.serviceBranding = serviceBranding.trim();
    }

    private void setSleepers(String sleepers) {
        this.sleepers = parseSleepers(sleepers);
    }
}
