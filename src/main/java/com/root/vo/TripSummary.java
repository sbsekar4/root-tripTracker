package com.root.vo;

import com.root.util.RootUtil;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TripSummary {
    public String name;
    public LocalTime startTime;
    public LocalTime endTime;
    public double miles;
    public double speed;
    public long duration;
    static List<String> driverList = new ArrayList<>();
    static List<TripSummary> tripSummaryList = new ArrayList<>();

    public static String addDriver(String[] tripData) {

        if (tripData.length == 2 && tripData[1] != null) {
            driverList.add(tripData[1]);
        } else {
            //No driver info found
            System.out.println("/***** Validation Required *****/");
            System.out.println("File validation required: Driver name not found");
        }
        return tripData[1];
    }

    public static TripSummary addTrip(String[] tripData) {
        TripSummary tripSummary = new TripSummary();
        if (tripData.length == 5 && LocalTime.parse(tripData[3]).isAfter(LocalTime.parse(tripData[2]))) {
            tripSummary.name = tripData[1];
            tripSummary.startTime = LocalTime.parse(tripData[2]);
            tripSummary.endTime = LocalTime.parse(tripData[3]);
            tripSummary.miles = Double.parseDouble(tripData[4]);
            tripSummary.duration = RootUtil.calculateDuration(tripSummary.startTime, tripSummary.endTime);
            tripSummary.speed = RootUtil.calculateSpeed(tripSummary.startTime, tripSummary.endTime, tripSummary.miles);
            if (tripSummary.speed > 5 && tripSummary.speed < 100) {
                tripSummaryList.add(tripSummary);
            } else {
                //Ignore trips that are less than 5 miles or greater than 100 miles
                System.out.println("Trip ignored for driver " + tripData[1] + " for not driving in valid speed limit");
            }
        } else {
            //Ignore this trip as its gone past 24 hours or end time is less than start time
            System.out.println("/***** Validation Required *****/");
            System.out.println("File validation required : Invalid trip data found");
        }
        return tripSummary;
    }

    public static List<FinalSummary> prepareDriverSummary() {
        List<FinalSummary> finalSummaryList = new ArrayList<>();
        for (String driverName : driverList) {
            double totalMiles = 0;
            double totalDuration = 0;
            for (TripSummary tripSummary : tripSummaryList) {
                if (driverName.equalsIgnoreCase(tripSummary.name)) {
                    totalMiles = totalMiles + tripSummary.miles;
                    totalDuration = totalDuration + tripSummary.duration;
                }
            }
            FinalSummary summary = new FinalSummary();
            summary.name = driverName;
            summary.totalMiles = (int) Math.round(totalMiles);
            summary.avgSpeed = (int) Math.round(RootUtil.averageSpeed(totalDuration,totalMiles));
            //This is only for displaying in the console
            if (summary.totalMiles > 0) {
                summary.summary = summary.name + ": " + summary.totalMiles + " miles @ " + summary.avgSpeed + " mph.";
            } else {
                summary.summary = summary.name + ": " + summary.totalMiles + " miles.";
            }
            finalSummaryList.add(summary);
        }
        return finalSummaryList;
    }

}
