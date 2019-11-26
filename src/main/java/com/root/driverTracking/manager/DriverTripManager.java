package com.root.driverTracking.manager;

import com.root.driverTracking.model.Driver;
import com.root.driverTracking.service.TripProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DriverTripManager {
    private final String fileName;
    private List<Driver> driverList = new ArrayList<>();
    private final TripProcessor tripProcessor = new TripProcessor();

    public DriverTripManager(String fileName) {
        this.fileName = fileName;
    }

    public void loadDriverTripInfo() {
        driverList = tripProcessor.processFileData(fileName);
    }

    public void printAllDriverTrips() {
        driverList.sort(Comparator.comparingInt(o -> Math.toIntExact(o.getTotalMilesForDriver())));
        System.out.println("/******** Driving Summary *********/");
        Collections.reverse(driverList);
        for (Driver driver : driverList) {
            Long totalMilesForDriver = driver.getTotalMilesForDriver();
            if (totalMilesForDriver > 0) {
                System.out.println(driver.name + " : " + totalMilesForDriver + " miles @ " + driver.getAverageSpeed() + " mph.");
            } else {
                System.out.println(driver.name + " : " + totalMilesForDriver + " miles");
            }
        }
        System.out.println("/******** End *********/");
    }
}
