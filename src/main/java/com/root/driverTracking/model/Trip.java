package com.root.driverTracking.model;

import java.time.Duration;
import java.time.LocalTime;

public class Trip {
    public Long driverID;
    private final Long tripID;
    private final LocalTime startTime;
    private final LocalTime endTime;
    public final Double miles;

    public Trip(LocalTime startTime, LocalTime endTime, Double miles) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.miles = miles;
        this.tripID = (long) Math.random();
    }

    public Double calculateDuration() {
        return (double) Duration.between(startTime, endTime).toMinutes();
    }
}
