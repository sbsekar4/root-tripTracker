package com.root.driverSummary.service;

import com.root.driverSummary.helper.TripDataHelper;
import com.root.driverSummary.model.Driver;
import com.root.driverSummary.model.Trip;
import com.root.driverSummary.reader.TripFileReader;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripProcessor {

    public List<Driver> processFileData(String fileName) {

        List<String[]> rawData = TripFileReader.readTripData(fileName);
        Map<String, Driver> driverMap = new HashMap<>();

        for (String[] tripData : rawData) {
            LocalTime tripStartTime = null;
            LocalTime tripEndTime = null;
            Double tripMiles = null;

            String driverName = TripDataHelper.getDriver(tripData);
            if (driverName != null) {
                if (TripDataHelper.isValidTrip(tripData)) {
                    tripStartTime = TripDataHelper.getLocalTime(tripData[2]);
                    tripEndTime = TripDataHelper.getLocalTime(tripData[3]);
                    tripMiles = TripDataHelper.getMiles(tripData[4]);
                }
                Driver driver = driverMap.containsKey(driverName) ? driverMap.get(driverName) : new Driver(driverName);
                if (tripStartTime != null && tripEndTime != null && tripMiles != null) {
                    Trip driverTrip = new Trip(tripStartTime, tripEndTime, tripMiles);
                    driver.getTrips().add(driverTrip);
                }
                driverMap.put(driverName, driver);
            } else {
                System.out.println("File validation required : Invalid data found");
            }
        }
        return new ArrayList<>(driverMap.values());
    }
}
