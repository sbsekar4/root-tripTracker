package com.root.driverSummary.model;

import com.root.driverSummary.helper.TripDataHelper;

import java.time.Duration;
import java.time.LocalTime;

public class Trip {
    public Long driverID; /*Driver ID can used to backtrack drivers from trip */
    private final Long tripID; /*Trip ID is not used in this app, created for trip reference */
    private final LocalTime startTime;
    private final LocalTime endTime;
    public final Double miles;

    public Trip(LocalTime startTime, LocalTime endTime, Double miles) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.miles = miles;
        this.tripID = TripDataHelper.createTripID();/*Kept it real simple since its not used effectively */
    }

    public Double calculateDuration() {
        return (double) Duration.between(startTime, endTime).toMinutes();
    }
}
