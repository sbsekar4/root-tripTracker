package com.root.service;

import com.root.vo.FinalSummary;
import com.root.vo.TripSummary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TripProcessor {
    private static final String file = "input.txt";

    public static void main(String[] args) {
        List<String[]> fileData = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(file))) {
            while (scanner.hasNext()) {
                fileData.add(scanner.nextLine().split("\\s+"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String[] tripData : fileData) {
            if (tripData[0].contains("Driver")) {
                TripSummary.addDriver(tripData);
            } else if (tripData[0].contains("Trip")) {
                TripSummary.addTrip(tripData);
            }
        }
        FinalSummary.printTripSummary(TripSummary.prepareDriverSummary());
    }
}
