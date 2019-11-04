package com.root.service;

import com.root.vo.FinalSummary;
import com.root.vo.TripSummary;

import java.util.ArrayList;
import java.util.List;

public class PrepareDrivingSummary {

    public static List<FinalSummary> prepareSummary(List<String> registeredDriverList, List<TripSummary> tripSummaryList) {


        List<FinalSummary> summaryList = new ArrayList<>();

        for (String driverName : registeredDriverList) {
            summaryList.add(getDriverTrips(driverName, tripSummaryList));
        }
        //This is final trip summary
        return summaryList;
    }

    static FinalSummary getDriverTrips(String driverName, List<TripSummary> tripSummaryList) {
        List<TripSummary> invalidTrips = new ArrayList<>();
        double totalMiles = 0;
        double totalDuration = 0;
        double avgSpeed = 0;
        for (TripSummary tripSummary : tripSummaryList) {
            if (driverName.equalsIgnoreCase(tripSummary.name)) {
                if (tripSummary.speed > 5 && tripSummary.speed < 100) {
                    totalMiles = totalMiles + tripSummary.miles;
                    totalDuration = totalDuration + tripSummary.duration;
                } else {
                    //Ignore trips that are less than 5 miles or greater than 100 miles
                    System.out.println("Trip ignored for driver " + tripSummary.name + " for driving " + tripSummary.speed + " mph");
                    invalidTrips.add(tripSummary);// Storing these trip in a list, if required to validate.
                }
            }
        }
        if (totalDuration > 0) {
            avgSpeed = totalMiles / totalDuration * 60;
        } else {
            avgSpeed = totalMiles * 60;
        }

        FinalSummary summary = new FinalSummary();
        summary.name = driverName;
        summary.totalMiles = (int) Math.round(totalMiles);
        summary.avgSpeed = (int) Math.round(avgSpeed);
        //This is only for displaying in the console
        if (summary.totalMiles > 0) {
            summary.summary = summary.name + ": " + summary.totalMiles + " miles @ " + summary.avgSpeed + " mph.";
        } else {
            summary.summary = summary.name + ": " + summary.totalMiles + " miles.";
        }

        return summary;
    }
}
