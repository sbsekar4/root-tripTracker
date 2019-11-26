package com.root.driverTracking.model;

import java.time.Duration;
import java.time.LocalTime;

public class Trip {
    public Long driverID;
    public Long tripID;
    public LocalTime startTime;
    public LocalTime endTime;
    public Double miles;

    public Trip(LocalTime startTime,LocalTime endTime,Double miles){
        this.startTime = startTime;
        this.endTime = endTime;
        this.miles = miles;
        this.tripID = (long) Math.random();
    }

    public Double getSpeed(){
        double totalDuration = calculateDuration();
        if (totalDuration > 0) {
            return miles / totalDuration * 60;
        } else {
            return miles * 60;
        }
    }

    public Double calculateDuration(){
        return Double.valueOf(Duration.between(startTime, endTime).toMinutes());
    }
}
