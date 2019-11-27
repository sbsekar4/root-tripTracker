package com.root.driverTracking.service;

import com.root.driverTracking.helper.TripDataHelper;
import com.root.driverTracking.model.Driver;
import com.root.driverTracking.model.Trip;
import com.root.driverTracking.reader.TripFileReader;

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
