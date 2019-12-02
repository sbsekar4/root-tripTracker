package com.root.driverSummary.model;

import com.root.driverSummary.helper.TripDataHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This is the Model for Driver, Stores all the Driver and Trip details
 */
public class Driver {
    //Driver ID is not used in this project effectively
    //created for enhancement purpose to map the Trip details using Driver ID
    private final Long driverID;
    public final String name;
    private final List<Trip> trips;

    public Driver(String name) {
        this.name = name;
        this.driverID = TripDataHelper.createDriverID();/*Kept it real simple since its not used effectively */
        this.trips = new ArrayList<>();
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public Long getAverageSpeed() {
        return Math.round(TripDataHelper.averageSpeed((double) getTotalDurationForDriver(), (double) getTotalMilesForDriver()));
    }

    public Long getTotalMilesForDriver() {
        double miles = 0;
        for (Trip trip : trips) {
            miles = trip.miles + miles;
        }
        return Math.round(miles);
    }

    private Long getTotalDurationForDriver() {
        double duration = 0;
        for (Trip trip : trips) {
            duration = trip.calculateDuration() + duration;
        }
        return Math.round(duration);
    }

    public static final Comparator<Driver> MILES_COMPARATOR = Comparator.comparingLong(Driver::getTotalMilesForDriver);
    public static final Comparator<Driver> Speed_COMPARATOR = Comparator.comparingLong(Driver::getAverageSpeed);

}
