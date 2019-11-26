package com.root.driverTracking.vo;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TripSummaryTest {

    private List<String> driverList = new ArrayList<>();
    List<TripSummary> tripSummaryList = new ArrayList<>();
    String driverName="Driver Suresh";
    String[] newDriver = driverName.split("\\s+");
    String tripData = "Trip Alex 12:01 13:16 42.0";
    String[] tripDataArray = tripData.split("\\s+");

    @Before
    public void init() {
        driverList.add("Dan");
        driverList.add("Alex");
        driverList.add("Austin");
        TripSummary tripSummary =new TripSummary();
        /*tripSummary.name="Dan";
        tripSummary.speed=39.0;
        tripSummary.endTime = LocalTime.parse("13:16");
        tripSummary.startTime =LocalTime.parse("12:01");
        tripSummary.miles=42.0;
        tripSummary.speed= RootUtil.calculateSpeed(tripSummary.startTime,tripSummary.endTime,tripSummary.miles);
        if (tripSummary.speed > 5 && tripSummary.speed < 100){
            tripSummaryList.add(tripSummary);
        }*/
    }

    @Test
    public void addDriver() {
        //driverList.add(TripSummary.addDriver(newDriver));
        assertEquals("Adding New driver : ",4,driverList.size());
    }

    @Test
    public void addTrip() {
        //tripSummaryList.add(TripSummary.addTrip(tripDataArray));
        assertEquals("Driver Trip Summary : ", 2,tripSummaryList.size());
    }
}
