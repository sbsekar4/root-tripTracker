package com.root.driverTracking.manager;

import com.root.driverTracking.model.Driver;
import com.root.driverTracking.service.TripProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DriverTripManager {
    String fileName;
    List<Driver> driverList = new ArrayList<>();
    TripProcessor tripProcessor =new TripProcessor();

    public DriverTripManager(String fileName) {
        this.fileName = fileName;
    }

    public void loadDriverTripInfo() {
        driverList = tripProcessor.processFileData(fileName);
    }

    public void printAllDriverTrips() {
        //driverList.sort(Comparator.comparingInt(o -> o.totalMiles));
        System.out.println("/******** Driving Summary *********/");
        Collections.reverse(driverList);
        for (Driver driver : driverList) {
            System.out.println(driver.name);
        }
        System.out.println("/******** End *********/");
    }
}
