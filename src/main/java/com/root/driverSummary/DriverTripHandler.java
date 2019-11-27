package com.root.driverSummary;

import com.root.driverSummary.manager.DriverTripManager;

class DriverTripHandler {
    public static void main(String[] args) {
        DriverTripManager driverManager = new DriverTripManager("input.txt");
        driverManager.loadDriverTripInfo();
        driverManager.printAllDriverTrips();
    }
}
