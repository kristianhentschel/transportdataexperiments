package com.kristianhentschel.transportexp.applications;

import com.kristianhentschel.transportexp.ingest.uk.atoc.AtocParser;
import com.kristianhentschel.transportexp.timetable.TimetableSystem;
import com.kristianhentschel.transportexp.timetable.records.TimetableFixedLink;
import com.kristianhentschel.transportexp.timetable.records.TimetableService;
import com.kristianhentschel.transportexp.timetable.records.TimetableStop;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by Kristian on 09/09/2015.
 */
public class AtocPlayground1 {
    static TimetableSystem ts;
    public static void main(String args[]) {

        // get the data;
        System.out.println("Loading ATOC data.");
        AtocParser p = new AtocParser(args[0]);
        ts = p.getTimetableSystem();
        System.out.println("Timetable System initialized.");

        // Now, do something with this data
        Scanner inputScanner = new Scanner(System.in);

        while(inputScanner.hasNext()) {
            String request = inputScanner.next();
            if (request.equals("q")) {
                System.out.println("Bye.");
                System.exit(0);
            }

            if (ts.hasStop(request))
                printStopInfo(ts.getStop(request));
            else
                System.out.println("No such stop, " + request);
        }

    }

    public static void printStopInfo(TimetableStop stop) {
        System.out.println(stop.getNumStoppingServices() + " services stop at " + stop.getName());

        // Fixed Links
        Iterator<TimetableFixedLink> fixedLinkIterator = stop.getFixedLinkIterator();
        while(fixedLinkIterator.hasNext()) {
            TimetableFixedLink link = fixedLinkIterator.next();
            System.out.println( link.getMode() + " to " + link.getDestination().getName() + " in " + link.getDuration());
        }

        // Services
        Iterator<TimetableService> serviceIterator = stop.getServiceIterator();
    }
}
