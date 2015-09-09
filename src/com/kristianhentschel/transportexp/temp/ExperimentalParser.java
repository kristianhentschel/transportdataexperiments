package com.kristianhentschel.transportexp.temp;

import com.kristianhentschel.transportexp.ingest.records.uk.atoc.msn.MasterStationNamesStationRecord;
import com.kristianhentschel.transportexp.timetable.TimetableSystem;
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

        // parse master station names file
        Map<String, String> tiplocToStationCode = parseMasterStationNames(ts, file_msn);

        // TODO parse other files using this base data

        // parse fixed links file

        // To see if it works, print stations from timetable system
        Iterator<TimetableStop> it = ts.getStopsIterator();
        while(it.hasNext()) {
            TimetableStop stop = it.next();
            System.out.println(stop.getName());
        }
    }

    /**
     * Parses the Master Station Names file containing mappings between tiploc codes and station names, locations, etc.
     * @param ts the timetable system of reference, wherein the stops are created.
     * @param f A File object describing the msn file path.
     * @return A map from tiploc codes to three letter station codes. Useful to determine if a tiploc is a real station.
     */
    private static Map<String, String> parseMasterStationNames(TimetableSystem ts, File f) {
        Map<String, String> tiplocToCode = new HashMap<String, String>();

        Scanner sc;

        try {
            sc = new Scanner(f);

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
                parseStop(ts, tiplocToCode, r);
            }
        } catch(FileNotFoundException e) {
            System.out.println("Error opening MSN file: " + e.getMessage());
        }

        return tiplocToCode;
    }

    private static void parseStop(TimetableSystem ts, Map<String, String> tiplocToCode, MasterStationNamesStationRecord r) {
        // Set tiploc to three-letter-code mapping
        tiplocToCode.put(r.getTiploc(), r.getCode());

        if (r.isSubsidiary()) {
            // Ignore data other than tiploc-code mapping for subsidiaries!
            return;
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
