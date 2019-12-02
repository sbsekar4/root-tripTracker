package com.root.driverSummary.manager;

import com.root.driverSummary.model.Driver;
import com.root.driverSummary.service.TripProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class calls the processFileData() method with filename as the parameter.
 * The actual file is located in the classpath of this project.
 */
public class DriverTripManager {
    private final String fileName;
    private List<Driver> driverList = new ArrayList<>();
    private final TripProcessor tripProcessor = new TripProcessor();

    public DriverTripManager(String fileName) {
        this.fileName = fileName;
    }

    /**
     * This method calls the TripProcessor class to process the input file and create the model objects
     */
    public void loadDriverTripInfo() {
        driverList = tripProcessor.processFileData(fileName);
    }

    /**
     * This method uses the driverList populated and returned by the TripProcessor and model objects
     * and prints the results in the console
     */
    public void printAllDriverTrips() {
        Collections.sort(driverList, Driver.MILES_COMPARATOR);
        //Collections.sort(driverList,Driver.Speed_COMPARATOR); If required in future, Sort by speed
        System.out.println("/******** Printing Driving Summary *********/");
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
