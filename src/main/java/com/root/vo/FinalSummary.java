package com.root.vo;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FinalSummary {
    public String name;
    public int totalMiles;
    public int avgSpeed;
    public String summary;

    public static void printTripSummary(List<FinalSummary> summaryList) {

        summaryList.sort(Comparator.comparingInt(o -> o.totalMiles));
        System.out.println("/******** Driving Summary *********/");
        Collections.reverse(summaryList);
        for (FinalSummary summary : summaryList) {
            System.out.println(summary.summary);
        }
        System.out.println("/******** End *********/");
    }

}
