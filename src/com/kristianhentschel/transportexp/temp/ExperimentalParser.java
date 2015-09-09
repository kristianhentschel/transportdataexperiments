package com.kristianhentschel.transportexp.temp;

import com.kristianhentschel.transportexp.ingest.records.uk.atoc.flf.FixedLinksRecord;
import com.kristianhentschel.transportexp.ingest.records.uk.atoc.msn.MasterStationNamesStationRecord;
import com.kristianhentschel.transportexp.timetable.TimetableSystem;
import com.kristianhentschel.transportexp.timetable.records.TimetableFixedLink;
import com.kristianhentschel.transportexp.timetable.records.TimetableRecord;
import com.kristianhentschel.transportexp.timetable.records.TimetableStop;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableDuration;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableLocation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Kristian on 09/09/2015.
 *
 * A playground for developing parser methods for the ATOC files.
 */
public class ExperimentalParser {
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
        File file_tis = new File(base_dir, "ttisf"+data_seq+".tis");
        File file_msn = new File(base_dir, "ttisf"+data_seq+".msn");
        File file_mca = new File(base_dir, "ttisf"+data_seq+".mca");
        File file_flf = new File(base_dir, "ttisf"+data_seq+".flf");
        File file_alf = new File(base_dir, "ttisf"+data_seq+".alf");

        // initialise the system
        TimetableSystem ts = new TimetableSystem("ATOC", TimetableRecord.DATA_SOURCE.UK_ATOC);

        // alternative station codes still used in these files but not relevant to timetable system
        Map<String,String> subsidiaryToPrimary = new HashMap<String, String>();

        // parse master station names file
        Map<String, String> tiplocToStationCode = parseMasterStationNames(ts, file_msn, subsidiaryToPrimary);

        // TODO parse other files using this base data

        // parse fixed links file
        parseFixedLinks(ts, subsidiaryToPrimary, file_flf);

        //parseAdditionalFixedLinks(ts, file_alf);

        // To see if it works, print stations from timetable system

        Iterator<TimetableStop> it = ts.getStopsIterator();
        while(it.hasNext()) {
            TimetableStop stop = it.next();
            System.out.print(stop.getName());

            Iterator<TimetableFixedLink> fl_it = stop.getFixedLinkIterator();
            if(fl_it.hasNext())
                System.out.print("\t\tFixed links: ");
            while(fl_it.hasNext()) {
                TimetableFixedLink fl = fl_it.next();
                System.out.print(fl.getMode());
                System.out.print(" to ");
                if(fl.getOrigin()
                        .getLocalStopId()
                        .equals(stop.getLocalStopId())) {
                    System.out.print(fl.getDestination().getName());
                } else {
                    System.out.print(fl.getOrigin().getName());
                }
                System.out.print(" ");
            }

            System.out.println();
        }
    }

    private static void parseFixedLinks(TimetableSystem ts, Map<String, String> subsidiaryToPrimary, File file_flf) {
        Scanner sc;

        try {
            sc = new Scanner(file_flf);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.startsWith("END"))
                    break;

                FixedLinksRecord r = new FixedLinksRecord(line);
                parseFixedLinksRecord(ts, subsidiaryToPrimary, r);
            }

        } catch(FileNotFoundException e) {
            System.out.println("Error opening FLF file: " + e.getMessage());
        }
    }

    private static TimetableStop getStop(TimetableSystem ts, Map<String, String> subsidiaryToPrimary, String stopId) {
        if (subsidiaryToPrimary.containsKey(stopId))
            return ts.getStop(subsidiaryToPrimary.get(stopId));

        return ts.getStop(stopId);
    }

    /**
     * Parses a single fixed links record, adding the resulting fixed link object to both the origin and destination
     * stop in the timetable system of reference.
     * @param ts timetable system
     * @param r record
     */
    private static void parseFixedLinksRecord(TimetableSystem ts, Map<String, String> subsidiaryToPrimary, FixedLinksRecord r) {

        TimetableStop origin = getStop(ts, subsidiaryToPrimary, r.getOrigin());
        TimetableStop destination = getStop(ts, subsidiaryToPrimary, r.getDestination());
        TimetableFixedLink fl = new TimetableFixedLink();

        fl.setOrigin(origin);
        fl.setDestination(destination);

        fl.setMode(r.getMode().toString());

        TimetableDuration duration = new TimetableDuration();
        duration.addMinutes(r.getTime());
        fl.setDuration(duration);

        // TODO: bi-directional link represented by a uni-directional class added to both...???
        origin.addFixedLink(fl);
        destination.addFixedLink(fl);
    }

    /**
     * Parses the Master Station Names file containing mappings between tiploc codes and station names, locations, etc.
     * @param ts the timetable system of reference, wherein the stops are created.
     * @param file_msn A File object describing the msn file path.
     * @param subsidiaryToPrimary
     * @return A map from tiploc codes to three letter station codes. Useful to determine if a tiploc is a real station.
     */
    private static Map<String, String> parseMasterStationNames(TimetableSystem ts, File file_msn, Map<String, String> subsidiaryToPrimary) {
        Map<String, String> tiplocToCode = new HashMap<String, String>();

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
                parseStop(ts, tiplocToCode, subsidiaryToPrimary, r);
            }
        } catch(FileNotFoundException e) {
            System.out.println("Error opening MSN file: " + e.getMessage());
        }

        return tiplocToCode;
    }

    private static void parseStop(TimetableSystem ts, Map<String, String> tiplocToCode, Map<String, String> subsidiaryToPrimary, MasterStationNamesStationRecord r) {
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
