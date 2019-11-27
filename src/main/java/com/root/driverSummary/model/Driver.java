package com.root.driverSummary.model;

import com.root.driverSummary.helper.TripDataHelper;
import com.root.driverSummary.util.RootUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Driver implements Comparator<Driver> {
    public Long driverID; /*Driver ID is not used in this app, created to map the Trip details using Driver ID */
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
        return Math.round(RootUtil.averageSpeed((double) getTotalDurationForDriver(), (double) getTotalMilesForDriver()));
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


    @Override
    public int compare(Driver o1, Driver o2) {
        return o1.getTotalMilesForDriver().compareTo(o2.getTotalMilesForDriver());
    }
}
