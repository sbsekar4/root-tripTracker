package com.root.util;

import com.root.vo.Driver;
import com.root.vo.FinalSummary;
import com.root.vo.TripSummary;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.root.service.PrepareDrivingSummary.prepareSummary;

public class RootUtil {

    private static double calculateSpeed(LocalTime startTime, LocalTime endTime, Double miles){

        long duration = Duration.between(startTime, endTime).toMinutes();
        double speed;
        if(duration>0)
            speed =  (miles / duration) * 60 ;
        else
            speed = miles * 60;

        return speed;
    }

    private static long calculateDuration(LocalTime startTime, LocalTime endTime){

        return Duration.between(startTime, endTime).toMinutes();
    }

    public static List<FinalSummary> processTripData(List<String[]> tripList){

        List<Driver> driverList = new ArrayList<>();
        List<TripSummary> tripSummaryList = new ArrayList<>();
        List<String> registeredDriverList = new ArrayList<>();
        List<String> tripDriverList = new ArrayList<>();

        int lineNumber=0;
        for(String[] tripData : tripList){
            lineNumber++;
            if (tripData[0].contains("Driver")) {
                Driver driver = new Driver();
                if (tripData.length == 2 && tripData[1] != null) {
                    driver.name = tripData[1];
                    registeredDriverList.add(driver.name);
                    driverList.add(driver);
                } else {
                    //No driver info found
                    System.out.println("/***** Validation Required *****/");
                    System.out.println("File validation required: Driver name not found on line number: " + lineNumber);
                }
            } else if (tripData[0].contains("Trip")) {
                TripSummary tripSummary = new TripSummary();
                if (tripData.length == 5 && LocalTime.parse(tripData[3]).isAfter(LocalTime.parse(tripData[2]))) {
                    tripSummary.name = tripData[1];
                    tripDriverList.add(tripSummary.name);
                    tripSummary.startTime = LocalTime.parse(tripData[2]);
                    tripSummary.endTime = LocalTime.parse(tripData[3]);
                    tripSummary.miles = Double.parseDouble(tripData[4]);
                    tripSummary.speed = calculateSpeed(tripSummary.startTime,tripSummary.endTime,tripSummary.miles);
                    tripSummary.duration = calculateDuration(tripSummary.startTime,tripSummary.endTime);
                    tripSummaryList.add(tripSummary);
                } else {
                    //Ignore this trip as its gone past 24 hours or end time is less than start time
                    System.out.println("/***** Validation Required *****/");
                    System.out.println("File validation required : Invalid trip data on line number: " + lineNumber);
                }
            }
        }
        List<String> validDriverList= getRegisteredDrivers(registeredDriverList,tripDriverList);

        return prepareSummary(validDriverList,tripSummaryList);
    }

    private static List<String> getRegisteredDrivers(List<String> registeredDriverList,List<String> tripDriverList){
        for(String driverName : tripDriverList){
            if(!registeredDriverList.contains(driverName)){
                //Driver not registered in the system
                System.out.println("/***** Validation Required *****/");
                System.out.println("File validation required: Trip data found for unregistered driver: "+driverName);
                registeredDriverList.remove(driverName);
            }
        }
        return registeredDriverList;
    }
}
