package com.root.driverSummary;

import com.root.driverSummary.manager.DriverTripManager;

/**
 * Main class for this project.
 * Calls two functions loadDriverTripInfo() and printAllDriverTrips(),
 * to process the input file and print the results in console.
 */
class DriverTripHandler {
    public static void main(String[] args) {
        DriverTripManager driverManager = new DriverTripManager("input.txt");
        driverManager.loadDriverTripInfo();
        driverManager.printAllDriverTrips();
    }
}
