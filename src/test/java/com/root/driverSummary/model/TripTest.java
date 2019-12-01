package com.root.driverSummary.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

public class TripTest {

    private Trip driverTrip = null;

    @Before
    public void processInputFile() {
        String fileData = "Trip Lauren 12:01 13:16 42.7";
        String[] driveInfo = fileData.split("\\s+");
        driverTrip = new Trip(LocalTime.parse(driveInfo[2]), LocalTime.parse(driveInfo[3]), Double.parseDouble(driveInfo[4]));
    }

    @Test
    public void calculateDuration() {
        Assert.assertEquals("Total duration of the trip ", 75, driverTrip.calculateDuration().longValue());
    }
}
