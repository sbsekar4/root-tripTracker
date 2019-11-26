package com.root.driverTracking.util;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class RootUtilTest {
    private LocalTime endTime;
    private LocalTime startTime;
    private double miles;
    private double duration;

    @Before
    public void init() {
        endTime = LocalTime.parse("13:16");
        startTime = LocalTime.parse("12:01");
        miles =42.0;
        duration =10.0;
    }

    @Test
    public void calculateSpeed() {
        long speed =(long) RootUtil.calculateSpeed(startTime,endTime, miles);
        assertEquals("Driving Speed in mph : ",33,speed);
    }

    @Test
    public void calculateDuration() {
        long duration =(long) RootUtil.calculateDuration(startTime,endTime);
        assertEquals("Driving duration in miles : ",75,duration);
    }

    @Test
    public void averageSpeed() {
        long avgSpeed=(long) RootUtil.averageSpeed(duration,miles);
        assertEquals("Average speed for the trip in mph : ",252,avgSpeed);
    }
}
