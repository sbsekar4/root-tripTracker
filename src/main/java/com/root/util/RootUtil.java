package com.root.util;

import java.time.Duration;
import java.time.LocalTime;

public class RootUtil {

    public static double calculateSpeed(LocalTime startTime, LocalTime endTime, Double miles){

        long duration = Duration.between(startTime, endTime).toMinutes();
        double speed;
        if(duration>0)
            speed =  (miles / duration) * 60 ;
        else
            speed = miles * 60;

        return speed;
    }

    public static long calculateDuration(LocalTime startTime, LocalTime endTime){

        return Duration.between(startTime, endTime).toMinutes();
    }

    public static double averageSpeed(Double totalDuration,Double totalMiles){
        if (totalDuration > 0) {
            return totalMiles / totalDuration * 60;
        } else {
            return totalMiles * 60;
        }
    }

}
