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
    private final List<String> inputTime = new ArrayList<>();

    @Before
    public void processInputFile() {
        final String fileName = "input.txt";
        List<String[]> fileData = TripFileReader.readTripData(fileName);
        for (String[] driveInfo : fileData) {
            if (driveInfo[0].contains("Driver")) {
                driver.add(driveInfo);
            } else if (driveInfo[0].contains("Trip") && driveInfo.length > 3) {
                trip.add(driveInfo);
                inputTime.add(driveInfo[2]);
                inputTime.add(driveInfo[3]);
            }
        }
    }

    @Test
    public void getDriver() {
        for (String[] driver : driver) {
            Assert.assertEquals("Driver name from file and returned by model should match", driver[1], TripDataHelper.getDriver(driver));
        }
    }

    @Test
    public void isValidTrip() {
        for (String[] trip : trip) {
            Assert.assertEquals("Invalid trip in the file", true, TripDataHelper.isValidTrip(trip));
        }
    }

    @Test
    public void getLocalTime() {
        for (String localTime : inputTime) {
            Assert.assertEquals("Time from file and LocalTime should be same", (LocalTime.parse(localTime)), TripDataHelper.getLocalTime(localTime));
        }
    }
}
