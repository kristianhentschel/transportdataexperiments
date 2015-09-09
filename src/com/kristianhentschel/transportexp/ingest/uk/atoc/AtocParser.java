package com.kristianhentschel.transportexp.ingest.uk.atoc;

import com.kristianhentschel.transportexp.ingest.uk.atoc.alf.AdditionalFixedLinksRecord;
import com.kristianhentschel.transportexp.ingest.uk.atoc.cif.*;
import com.kristianhentschel.transportexp.ingest.uk.atoc.flf.FixedLinksRecord;
import com.kristianhentschel.transportexp.ingest.uk.atoc.msn.MasterStationNamesStationRecord;
import com.kristianhentschel.transportexp.timetable.TimetableSystem;
import com.kristianhentschel.transportexp.timetable.records.TimetableFixedLink;
import com.kristianhentschel.transportexp.timetable.records.TimetableRecord;
import com.kristianhentschel.transportexp.timetable.records.TimetableService;
import com.kristianhentschel.transportexp.timetable.records.TimetableStop;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableDaysOfWeek;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableDuration;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableLocation;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableTimeOfDay;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Kristian on 09/09/2015.
 *
 * A prototype for a more generic parser - this specific implementation parses a folder of ATOC/RSP files.
 */
public class AtocParser {
    private TimetableSystem ts;
    private Map<String, String> tiplocToCode;
    private Map<String, String> subsidiaryToPrimary;
    private String parse_dir;
    private boolean parsed;

    /**
     * Construct a new parser for a set of ATOC files.
     *
     * @param parse_dir path to the base directory, which must be unzipped but named according to ATOC convention,
     *                  that is, it should be of the form ttifXXX where XXX is a 3-digit sequence number also used
     *                  in the contained files.
     */
    public AtocParser(String parse_dir) {
        this.parse_dir = parse_dir;

        ts = new TimetableSystem("ATOC", TimetableRecord.DATA_SOURCE.UK_ATOC);
        subsidiaryToPrimary = new HashMap<String, String>();
        tiplocToCode = new HashMap<String, String>();

        this.parsed = false;
    }

    /**
     * Parses the relevant files contained in the parse_dir, once, and populates the timetable system.
     * After the first parse, if this method is called again, the same object will be returned, and the parse
     * will not be repeated. Note that the timetable system may contain mutable objects that could be modified outside
     * of this class.
     *
     * @return A timetable system based on this set of files.
     */
    public TimetableSystem getTimetableSystem() {
        // if we've already done the expensive parsing, just return the timetable system we created.
        if (parsed) {
            return ts;
        }

        // Otherwise, prepare the file paths and start parsing away!
        File base_dir = new File(parse_dir);
        String base_name = base_dir.getName();
        String data_seq = base_name.substring(base_name.length()-3, base_name.length());
        File file_msn = new File(base_dir, "ttisf"+data_seq+".msn");
        File file_flf = new File(base_dir, "ttisf"+data_seq+".flf");
        File file_alf = new File(base_dir, "ttisf"+data_seq+".alf");
        File file_mca = new File(base_dir, "ttisf"+data_seq+".mca");

        Runtime R = Runtime.getRuntime();

        // parse individual files in the correct order
        parseMasterStationNames(file_msn);
        parseFixedLinks(file_flf);
        parseAdditionalFixedLinks(file_alf);
        parseTimetableFile(file_mca);

        return ts;
    }

    private void parseTimetableFile(File file_mca) {
        Scanner sc;

        try {
            sc = new Scanner(file_mca);

            // This parser needs some state as the order of records (BS -> BX -> LO, CR, LI, LT) is significant
            // This state is a service. So we always keep a service object around that we can add to. It is replaced
            // with a new blank service object when the last (LT) record for the service has been parsed.
            TimetableService s = new TimetableService();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String recordType = line.substring(0,2);
                switch(recordType) {
                    case "HD":
                        // Skip Header.
                        break;
                    case "TI":
                    case "TA":
                    case "TD":
                        // Skip Tiploc insert/amend/delete
                        break;
                    case "AA":
                        // Skip Associations
                        break;
                    case "BS":
                        CifBasicScheduleRecord bs = new CifBasicScheduleRecord(line);

                        // ignore this record if it is for a service deletion
                        if (bs.getTransactionType() == AbstractCifRecord.TRANSACTION_TYPE.DELETE)
                            break;

                        // TODO: deal with UPDATE transactions if present?

                        // TODO: set start and end dates once implemented in record class.
                        // s.setStartDate(bs.getDateRunsFrom());
                        // s.setEndDate(bs.getDateRunsTo());
                        s.setName(bs.getTrainUid());

                        TimetableDaysOfWeek dow = new TimetableDaysOfWeek();
                        dow.parseString(bs.getDaysRun());
                        s.setDaysOfWeek(dow);

                        ts.addService(s);
                        break;
                    case "BX":
                        CifBasicScheduleExtraRecord bx = new CifBasicScheduleExtraRecord(line);
                        s.setOperator(bx.getAtocCode());
                        // The extra record does not include any other information relevant to us at this stage.
                        break;
                    case "LO":
                        CifOriginLocationRecord lo = new CifOriginLocationRecord(line);

                        // We only care about tiplocs associated with a station (public stops).
                        if (tiplocToCode.containsKey(lo.getLocationTiploc()) && lo.isPublicStop()) {
                            TimetableStop stop = getStop(tiplocToCode.get(lo.getLocationTiploc()));
                            s.addStop(stop,
                                    null,
                                    TimetableTimeOfDay.fromStringHHMM(lo.getPublicDeparture()));
                            stop.addService(s);
                        }
                        break;
                    case "LI":
                        CifIntermediateLocationRecord li = new CifIntermediateLocationRecord(line);
                        if (tiplocToCode.containsKey(li.getLocationTiploc()) && li.isPublicStop()) {
                            TimetableStop stop = getStop(tiplocToCode.get(li.getLocationTiploc()));
                            s.addStop(stop,
                                    TimetableTimeOfDay.fromStringHHMM(li.getPublicArrival()),
                                    TimetableTimeOfDay.fromStringHHMM(li.getPublicDeparture()));
                            stop.addService(s);
                        }
                        break;
                    case "LT":
                        CifTerminatingLocationRecord lt = new CifTerminatingLocationRecord(line);
                        if (tiplocToCode.containsKey(lt.getLocationTiploc()) && lt.isPublic()) {
                            TimetableStop stop = getStop(tiplocToCode.get(lt.getLocationTiploc()));
                            s.addStop(stop,
                                    TimetableTimeOfDay.fromStringHHMM(lt.getPublicArrival()),
                                    null);
                            stop.addService(s);
                        }
                        // The Terminating location is the last record specific to this service.
                        // We create a new one to guarantee it is there for the next service (BS record).
                        s = new TimetableService();
                        break;
                    case "TN":
                        // Skip train-specific note
                        break;
                    case "LN":
                        // Skip location-specific note
                        break;
                    case "CR":
                        // Skip change-on-route
                        break;
                    case "ZZ":
                        // Skip trailer record
                        break;
                    default:
                        System.err.println("Undefined CIF record type "+recordType);
                }
            }

        } catch(FileNotFoundException e) {
            System.out.println("Error opening MCA file: " + e.getMessage());
        }
    }

    private void parseAdditionalFixedLinks(File file_alf) {
        Scanner sc;

        try {
            sc = new Scanner(file_alf);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                AdditionalFixedLinksRecord r = new AdditionalFixedLinksRecord(line);
                parseAdditionalFixedLinksRecord(r);
            }

        } catch(FileNotFoundException e) {
            System.out.println("Error opening ALF file: " + e.getMessage());
        }
    }

    private void parseAdditionalFixedLinksRecord(AdditionalFixedLinksRecord r) {
        TimetableStop origin = getStop(r.getOrigin());
        TimetableStop destination = getStop(r.getDestination());
        TimetableFixedLink fl = new TimetableFixedLink();

        fl.setOrigin(origin);
        fl.setDestination(destination);

        fl.setMode(r.getMode().toString());

        TimetableDuration duration = new TimetableDuration();
        duration.addMinutes(r.getTime());
        fl.setDuration(duration);

        TimetableDaysOfWeek dow = new TimetableDaysOfWeek();
        dow.parseString(r.getDaysOfWeek());
        fl.setDaysOfWeek(dow);

        // TODO: Set start/end dates (implement a better interface in record class first!)
        // if(r.hasEndDate()) { }
        // if(r.hasStartDate()) { }

        fl.setStartTime(TimetableTimeOfDay.fromStringHHMM(r.getStartTime()));
        fl.setEndTime(TimetableTimeOfDay.fromStringHHMM(r.getEndTime()));

        // The additional fixed links appear to be uni-directional (duplicates exist in source file.)
        // so we only add it once in the specified direction.
        origin.addFixedLink(fl);
    }

    private void parseFixedLinks(File file_flf) {
        Scanner sc;

        try {
            sc = new Scanner(file_flf);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.startsWith("END"))
                    break;

                FixedLinksRecord r = new FixedLinksRecord(line);
                parseFixedLinksRecord(r);
            }

        } catch(FileNotFoundException e) {
            System.out.println("Error opening FLF file: " + e.getMessage());
        }
    }

    private TimetableStop getStop(String stopId) {
        if (subsidiaryToPrimary.containsKey(stopId))
            return ts.getStop(subsidiaryToPrimary.get(stopId));

        return ts.getStop(stopId);
    }

    /**
     * Parses a single fixed links record, adding the resulting fixed link object to both the origin and destination
     * stop in the timetable system of reference.
     * @param r record
     */
    private void parseFixedLinksRecord(FixedLinksRecord r) {

        TimetableStop origin = getStop(r.getOrigin());
        TimetableStop destination = getStop(r.getDestination());
        TimetableFixedLink fl = new TimetableFixedLink();

        fl.setOrigin(origin);
        fl.setDestination(destination);

        fl.setMode(r.getMode().toString());

        TimetableDuration duration = new TimetableDuration();
        duration.addMinutes(r.getTime());
        fl.setDuration(duration);

        // TODO: bi-directional link represented by a uni-directional class added to both... might want separate instances
        // or make links bi-directional by default?
        origin.addFixedLink(fl);
        destination.addFixedLink(fl);
    }

    /**
     * Parses the Master Station Names file containing mappings between tiploc codes and station names, locations, etc.
     * @param file_msn A File object describing the msn file path.
     */
    private void parseMasterStationNames(File file_msn) {
        Scanner sc;

        try {
            sc = new Scanner(file_msn);

            // ignore header record (also starts with A)
            sc.nextLine();

            // read station name records
            while(sc.hasNextLine()){
                String line = sc.nextLine();

                // break when seeing the first non-station name record (e.g. routing group, irrelevant here).
                if (!line.startsWith("A")) {
                    break;
                }

                MasterStationNamesStationRecord r = new MasterStationNamesStationRecord(line);
                parseStop(r);
            }
        } catch(FileNotFoundException e) {
            System.out.println("Error opening MSN file: " + e.getMessage());
        }
    }

    private void parseStop(MasterStationNamesStationRecord r) {
        // Set tiploc to three-letter-code mapping
        tiplocToCode.put(r.getTiploc(), r.getCode());

        if (r.isSubsidiary()) {
            // Ignore data other than tiploc-code mapping for subsidiaries!
            //System.out.println("Ignoring subsidiary with codes: "+r.getCode()+" " +r.getSubsidiaryCode());
            return;
        } else if (!r.getSubsidiaryCode().equals(r.getCode())) {
            // for stations with multiple 3-alpha codes, keep track of the equivalences
            //System.out.println("different codes but not a subsidiary. principal: "+r.getCode()+" subsid: " +r.getSubsidiaryCode());
            subsidiaryToPrimary.put(r.getSubsidiaryCode(), r.getCode());
        }

        // Create stop object in timetable system
        TimetableStop stop = ts.getStop(r.getCode());

        // Convert Location and choose Timezone
        // TODO: Using GMT non-discriminatively may be incorrect even for ATOC data (continental connections)
        TimetableLocation location = new TimetableLocation(TimeZone.getTimeZone("GMT"));
        convertLocation(location, r.getEasting(), r.getNorthing());

        // Station three-letter code and full name
        stop.setLocalStopId(r.getCode());
        stop.setName(r.getStationName());

        // Change time
        TimetableDuration changeTime = new TimetableDuration();
        changeTime.addMinutes(r.getChangeTime());
        stop.setChangeTime(changeTime);
    }

    private void convertLocation(TimetableLocation location, int easting, int northing) {
        // TODO implement UK national grid (see national rail/atoc docs) to lat/lon conversion here.
        location.setLat(northing);
        location.setLon(easting);
        location.setEstimated(true);
    }
}
