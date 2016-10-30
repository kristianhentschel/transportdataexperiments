package com.kristianhentschel.transportexp.ingest.uk.atoc.cif;

/**
 * Created by Kristian on 10/08/2015.
 * Following the National Rail CIF USER SPEC v29 (August 2014)
 */
public class CifChangesEnRouteRecord extends AbstractCifRecord {
    private String locationTiploc;
    private String locationSuffix;
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
    private String uicCode;
    private String rsidToc;
    private String rsidTrainNumber;
    private String rsidPortionNumber;

    public CifChangesEnRouteRecord(String record_text) {
        super(record_text);
        setLocationTiploc(takeChars(7));
        setLocationSuffix(takeChars(1));
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
        skipChars(4); // skip Traction class, not used.
        setUicCode(takeChars(5));
        setRsidToc(takeChars(2));
        setRsidTrainNumber(takeChars(4));
        setRsidPortionNumber(takeChars(2));
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

    public void setLocationTiploc(String locationTiploc) {
        this.locationTiploc = locationTiploc.trim();
    }

    public String getLocationTiploc() {
        return locationTiploc;
    }

    public void setLocationSuffix(String locationSuffix) {
        this.locationSuffix = locationSuffix.trim();
    }

    public String getLocationSuffix() {
        return locationSuffix;
    }

    public void setUicCode(String uicCode) {
        this.uicCode = uicCode.trim();
    }

    public String getUicCode() {
        return uicCode;
    }

    public void setRsidToc(String rsidToc) {
        this.rsidToc = rsidToc;
    }

    public String getRsidToc() {
        return rsidToc;
    }

    public void setRsidTrainNumber(String rsidTrainNumber) {
        this.rsidTrainNumber = rsidTrainNumber;
    }

    public String getRsidTrainNumber() {
        return rsidTrainNumber;
    }

    public void setRsidPortionNumber(String rsidPortionNumber) {
        this.rsidPortionNumber = rsidPortionNumber;
    }

    public String getRsidPortionNumber() {
        return rsidPortionNumber;
    }

    public boolean hasHeadcode() {
        return headcode.length() > 0;
    }

    public boolean hasTimingLoad() {
        return timingLoad.length() > 0;
    }

    public boolean hasOpChars() {
        return opChars.length() > 0;
    }

    public boolean hasCateringCode() {
        return cateringCode.length() > 0;
    }

    public boolean hasServiceBranding() {
        return serviceBranding.length() > 0;
    }

    public boolean hasUicCode() {
        return uicCode.length() > 0;
    }
}
