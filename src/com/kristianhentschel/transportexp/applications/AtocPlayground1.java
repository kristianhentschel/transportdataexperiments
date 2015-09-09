package com.kristianhentschel.transportexp.applications;

import com.kristianhentschel.transportexp.ingest.uk.atoc.AtocParser;
import com.kristianhentschel.transportexp.timetable.TimetableSystem;
import com.kristianhentschel.transportexp.timetable.records.TimetableFixedLink;
import com.kristianhentschel.transportexp.timetable.records.TimetableService;
import com.kristianhentschel.transportexp.timetable.records.TimetableServiceStop;
import com.kristianhentschel.transportexp.timetable.records.TimetableStop;
import com.kristianhentschel.transportexp.timetable.utilities.TimetableTimeOfDay;

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

        boolean printServices = true;
        boolean printFixedLinks = false;

        while(inputScanner.hasNext()) {
            String request = inputScanner.next();
            if (request.equals("q")) {
                System.out.println("Bye.");
                System.exit(0);
            }

            if (ts.hasStop(request))
                printStopInfo(ts.getStop(request), printFixedLinks, printServices);
            else
                System.out.println("No such stop, " + request);
        }

    }

    public static void printStopInfo(TimetableStop stop, boolean printLinks, boolean printServices) {
        System.out.println(stop.getNumStoppingServices() + " services stop at " + stop.getName());

        // Fixed Links
        if (printLinks) {
            Iterator<TimetableFixedLink> fixedLinkIterator = stop.getFixedLinkIterator();
            while (fixedLinkIterator.hasNext()) {
                TimetableFixedLink link = fixedLinkIterator.next();
                System.out.println(link.getMode() + " to " + link.getDestination().getName() + " in " + link.getDuration());
            }
        }

        // Services
        if(printServices) {
            Iterator<TimetableService> serviceIterator = stop.getServiceIterator();
            while (serviceIterator.hasNext()) {
                TimetableService service = serviceIterator.next();
                Iterator<TimetableServiceStop> scheduleIterator = service.getScheduleIterator();
                TimetableServiceStop first = null;
                TimetableServiceStop current = null;
                TimetableServiceStop last = null;
                while(scheduleIterator.hasNext()) {
                    TimetableServiceStop serviceStop = scheduleIterator.next();

                    if(first == null) {
                        first = serviceStop;
                    }

                    if(serviceStop.getStop().equals(stop)) {
                        current = serviceStop;
                    }

                    last = serviceStop;
                }

                TimetableTimeOfDay time = current.getDeparts() != null ? current.getDeparts() : current.getArrives();
                System.out.printf("%s: %s service from %s to %s at %s\n", service.getName(), service.getOperator(), first.getStop().getName(), last.getStop().getName(), time);
            }
        }
    }
}
