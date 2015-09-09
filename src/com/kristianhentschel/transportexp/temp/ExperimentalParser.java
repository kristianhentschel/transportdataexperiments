package com.kristianhentschel.transportexp.temp;

import com.kristianhentschel.transportexp.ingest.records.uk.atoc.alf.AdditionalFixedLinksRecord;
import com.kristianhentschel.transportexp.ingest.records.uk.atoc.cif.*;
import com.kristianhentschel.transportexp.ingest.records.uk.atoc.flf.FixedLinksRecord;
import com.kristianhentschel.transportexp.ingest.records.uk.atoc.msn.MasterStationNamesStationRecord;
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
 * A playground for developing parser methods for the ATOC files.
 */
public class ExperimentalParser {
    private static TimetableSystem ts;
    private static Map<String, String> tiplocToCode;
    private static Map<String, String> subsidiaryToPrimary;

    public static void main(String argv[]) {
        if(argv.length < 1) {
            System.err.println("Need first parameter atoc-base-dir");
            System.exit(1);
        }

        // Determine paths
        String parsedir = argv[0];
        System.out.println(parsedir);

        File base_dir = new File(parsedir);
        String base_name = base_dir.getName();
        String data_seq = base_name.substring(base_name.length()-3, base_name.length());
        File file_msn = new File(base_dir, "ttisf"+data_seq+".msn");
        File file_flf = new File(base_dir, "ttisf"+data_seq+".flf");
        File file_alf = new File(base_dir, "ttisf"+data_seq+".alf");
        File file_mca = new File(base_dir, "ttisf"+data_seq+".mca");

        // initialise the system and alias maps
        ts = new TimetableSystem("ATOC", TimetableRecord.DATA_SOURCE.UK_ATOC);
        subsidiaryToPrimary = new HashMap<String, String>();
        tiplocToCode = new HashMap<String, String>();

        Runtime R = Runtime.getRuntime();

        // parse individual files in the correct order
        System.out.println("Before loading any files: Heap Size: "+(R.totalMemory() - R.freeMemory()));

        parseMasterStationNames(file_msn);
        parseFixedLinks(file_flf);
        parseAdditionalFixedLinks(file_alf);

        System.out.println("Before loading timetable: Heap Size: "+(R.totalMemory() - R.freeMemory()));
        parseTimetableFile(file_mca);

        System.out.println("After loading timetable: Heap Size: "+(R.totalMemory() - R.freeMemory()));
        System.gc();
        System.out.println("After forced GC: Heap Size: "+(R.totalMemory() - R.freeMemory()));

        // To see if it works, print stations from timetable system

        Iterator<TimetableStop> it = ts.getStopsIterator();
        while(it.hasNext()) {
            TimetableStop stop = it.next();
            System.out.print(stop.getName());
            System.out.print(" (" + stop.getNumStoppingServices() + " services) ");

//            Iterator<TimetableFixedLink> fl_it = stop.getFixedLinkIterator();
//            if(fl_it.hasNext())
//                System.out.print("\t\tFixed links: ");
//            while(fl_it.hasNext()) {
//                TimetableFixedLink fl = fl_it.next();
//                System.out.print(fl.getMode());
//                System.out.print(" to ");
//                if(fl.getOrigin()
//                        .getLocalStopId()
//                        .equals(stop.getLocalStopId())) {
//                    System.out.print(fl.getDestination().getName());
//                } else {
//                    System.out.print(fl.getOrigin().getName());
//                }
//                System.out.print(" ");
//            }

            System.out.println();
        }
    }

    private static void parseTimetableFile(File file_mca) {
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

                        // This is the first record for a new train. The service object has already been created,
                        // so we can just fill in the basic info here.

                        // TODO: set start and end dates once implemented in record class.
                        // s.setStartDate(bs.getDateRunsFrom());
                        // s.setEndDate(bs.getDateRunsTo());
                        s.setName(bs.getTrainIdentity());

                        TimetableDaysOfWeek dow = new TimetableDaysOfWeek();
                        dow.parseString(bs.getDaysRun());
                        s.setDaysOfWeek(dow);
                        break;
                    case "BX":
                        CifBasicScheduleExtraRecord bx = new CifBasicScheduleExtraRecord(line);
                        s.setOperator(bx.getAtocCode());
                        // The extra record does not include any other information relevant to us at this stage.
                        break;
                    case "LO":
                        CifOriginLocationRecord lo = new CifOriginLocationRecord(line);

                        // We only care about tiplocs associated with a station (public stops).
                        if (tiplocToCode.containsKey(lo.getLocationTiploc())) {
                            TimetableStop stop = getStop(tiplocToCode.get(lo.getLocationTiploc()));
                            s.addStop(stop,
                                    null,
                                    TimetableTimeOfDay.fromStringHHMM(lo.getPublicDeparture()));
                            stop.addService(s);
                        }
                        break;
                    case "LI":
                        CifIntermediateLocationRecord li = new CifIntermediateLocationRecord(line);
                        if (tiplocToCode.containsKey(li.getLocationTiploc())) {
                            TimetableStop stop = getStop(tiplocToCode.get(li.getLocationTiploc()));
                            s.addStop(stop,
                                    TimetableTimeOfDay.fromStringHHMM(li.getPublicArrival()),
                                    TimetableTimeOfDay.fromStringHHMM(li.getPublicDeparture()));
                            stop.addService(s);
                        }
                        break;
                    case "LT":
                        CifTerminatingLocationRecord lt = new CifTerminatingLocationRecord(line);
                        if (tiplocToCode.containsKey(lt.getLocationTiploc())) {
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

    private static void parseAdditionalFixedLinks(File file_alf) {
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

    private static void parseAdditionalFixedLinksRecord(AdditionalFixedLinksRecord r) {
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

        // TODO: Set start/end times of day (implement in TimetableFixedLink class)
        // fl.setStartTime(new TimetableTimeOfDay(Integer.parseInt(r.getStartTime().substring(0,2))));
        // fl.setEndTime(new TimetableTimeOfDay(Integer.parseInt(r.getEndTime().substring(2,4))));

        // The additional fixed links appear to be uni-directional (duplicates exist in source file.)
        origin.addFixedLink(fl);
    }

    private static void parseFixedLinks(File file_flf) {
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

    private static TimetableStop getStop(String stopId) {
        if (subsidiaryToPrimary.containsKey(stopId))
            return ts.getStop(subsidiaryToPrimary.get(stopId));

        return ts.getStop(stopId);
    }

    /**
     * Parses a single fixed links record, adding the resulting fixed link object to both the origin and destination
     * stop in the timetable system of reference.
     * @param r record
     */
    private static void parseFixedLinksRecord(FixedLinksRecord r) {

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
    private static void parseMasterStationNames(File file_msn) {
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

    private static void parseStop(MasterStationNamesStationRecord r) {
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

    private static void convertLocation(TimetableLocation location, int easting, int northing) {
        // TODO implement UK national grid (see national rail/atoc docs) to lat/lon conversion here.
        location.setLat(northing);
        location.setLon(easting);
        location.setEstimated(true);
    }
}
