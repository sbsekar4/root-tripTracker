package com.root.driverTracking.model;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    public Long driverID;
    public String name;
    public List<Trip> trips;

    public Driver(String name){
        this.name =name;
        this.driverID = (long) Math.random();
        this.trips =new ArrayList<>();
    }

    public Long getDriverID() {
        return driverID;
    }

    public void setDriverID(Long driverID) {
        this.driverID = driverID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> tripSummaryList) {
        this.trips = tripSummaryList;
    }

    public Double getAverageSpeed(){
        double speed=0;
        for(Trip trip:trips){
            speed = trip.getSpeed() + speed;
        }
        return speed/trips.size();
    }



}
