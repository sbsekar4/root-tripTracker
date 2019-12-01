package com.root.driverSummary.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverTest {

    private final Map<String, Driver> driverMap = new HashMap<>();
    private final List<String[]> trip = new ArrayList<>();

    @Before
    public void processInputFile() {

        String fileData = "Trip Lauren 12:01 13:16 42.7";
        String[] driveInfo = fileData.split("\\s+");

        Driver driver = new Driver(driveInfo[1]);
        Trip driverTrip = new Trip(LocalTime.parse(driveInfo[2]), LocalTime.parse(driveInfo[3]), Double.parseDouble(driveInfo[4]));
        driver.getTrips().add(driverTrip);
        driverMap.put(driveInfo[1], driver);
        trip.add(driveInfo);
    }

    @Test
    public void getTrips() {
        Driver driver = driverMap.get("Lauren");
        Assert.assertEquals("Number of trips processed by model and listed in the file for a driver should match ", trip.size(), driver.getTrips().size());
    }

    @Test
    public void getAverageSpeed() {
        Driver driver = driverMap.get("Lauren");
        Assert.assertEquals("Average speed of the Driver's overall trips ", 34, (long) driver.getAverageSpeed());
    }

    @Test
    public void getTotalMilesForDriver() {
        Driver driver = driverMap.get("Lauren");
        Assert.assertEquals("Total miles driven by the driver ", 43, (long) driver.getTotalMilesForDriver());
    }
}
