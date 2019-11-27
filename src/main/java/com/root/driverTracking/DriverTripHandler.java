package com.root.driverTracking;

import com.root.driverTracking.manager.DriverTripManager;

class DriverTripHandler {
    public static void main(String[] args) {
        DriverTripManager driverManager = new DriverTripManager("input.txt");
        driverManager.loadDriverTripInfo();
        driverManager.printAllDriverTrips();
    }
}
