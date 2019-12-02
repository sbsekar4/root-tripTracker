package com.root.driverSummary.helper;

import com.root.driverSummary.reader.TripFileReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TripDataHelperTest {

    private final List<String[]> driver = new ArrayList<>();
    private final List<String[]> trip = new ArrayList<>();

    @Before
    public void processInputFile() {
        String driverWithName = "Driver Dan";
        String driverWithoutName = "Driver ";
        String tripWithDriver = "Trip Lauren 07:15 07:45 17.3";
        String tripWithoutDriver = "Trip    07:15 07:45 17.3";
        driver.add(driverWithName.split("\\s+"));
        driver.add(driverWithoutName.split("\\s+"));
        trip.add(tripWithDriver.split("\\s+"));
        trip.add(tripWithoutDriver.split("\\s+"));
    }

    @Test
    public void getDriver_withName() {
        Assert.assertEquals("Driver name from file and returned by model should match", "Dan", TripDataHelper.getDriver(driver.get(0)));
    }

    @Test
    public void getDriver_withoutName() {
        Assert.assertEquals("Driver name from file and returned by model should match", null, TripDataHelper.getDriver(driver.get(1)));
    }

    @Test
    public void getDriver_tripRowDriver() {
        Assert.assertEquals("Driver name from file and returned by model should match", "Lauren", TripDataHelper.getDriver(trip.get(0)));
    }

    @Test
    public void isValidTrip() {
        Assert.assertEquals("Invalid trip in the file", true, TripDataHelper.isValidTrip(trip.get(0)));
    }

    @Test
    public void notValidTrip() {
        Assert.assertEquals("Invalid trip in the file", false, TripDataHelper.isValidTrip(trip.get(1)));
    }
}
