package com.root.service;

import com.root.vo.FinalSummary;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrintDrivingSummary {

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
