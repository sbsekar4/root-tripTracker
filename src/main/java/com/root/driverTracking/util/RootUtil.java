package com.root.driverTracking.util;

import java.time.Duration;
import java.time.LocalTime;

public class RootUtil {

    public static double calculateSpeed(LocalTime startTime, LocalTime endTime, Double miles) {
        return averageSpeed(calculateDuration(startTime, endTime), miles);
    }

    public static double calculateDuration(LocalTime startTime, LocalTime endTime) {
        return Duration.between(startTime, endTime).toMinutes();
    }

    public static double averageSpeed(Double totalDuration, Double totalMiles) {
        if (totalDuration > 0) {
            return totalMiles / totalDuration * 60;
        } else {
            return totalMiles * 60;
        }
    }

}
