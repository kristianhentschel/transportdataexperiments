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

            // Special Commands
            if (request.equals("q")) {
                System.out.println("Bye.");
                System.exit(0);
            }

            if (request.equals("ps")) {
                printServices = !printServices;
                System.out.println("printing services " + (printServices ? "enabled" : "disabled"));
                continue;
            }

            if (request.equals("pfl")) {
                printFixedLinks = !printFixedLinks;
                System.out.println("printing fixed links " + (printFixedLinks ? "enabled" : "disabled"));
                continue;
            }

            // Information requests (station or train id)
            if (ts.hasStop(request))
                printStopInfo(ts.getStop(request), printFixedLinks, printServices);
            else if (ts.hasService(request))
                printServiceInfo(ts.getService(request));
            else
                System.out.println("No such command, stop, or service. " + request);
        }

    }

    private static void printServiceInfo(TimetableService service) {
        Iterator<TimetableServiceStop> scheduleIterator = service.getScheduleIterator();

        System.out.printf("%s: %s service running %s from %s to %s\n", service.getName(), service.getOperator(), service.getDaysOfWeek(), service.getStartDate(), service.getEndDate());
        while (scheduleIterator.hasNext()) {
            TimetableServiceStop s = scheduleIterator.next();

            System.out.printf("ARR %s DEP %s \t %s\n", s.getArrives(), s.getDeparts(), s.getStop().getName());
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
            Iterator<TimetableServiceStop> it = stop.getServiceStopsIterator();
            while (it.hasNext()) {
                TimetableServiceStop serviceStop = it.next();
                TimetableService service = serviceStop.getService();
                Iterator<TimetableServiceStop> scheduleIterator = service.getScheduleIterator();
                TimetableServiceStop first = service.getServiceStop(0);
                TimetableServiceStop current = serviceStop;
                TimetableServiceStop last = service.getServiceStop(service.getNumServiceStops() - 1);

                TimetableTimeOfDay time = current.getDeparts() != null ? current.getDeparts() : current.getArrives();
                System.out.printf("%s: %s service from %s to %s at %s\n", service.getName(), service.getOperator(), first.getStop().getName(), last.getStop().getName(), time);
            }
        }
    }
}
