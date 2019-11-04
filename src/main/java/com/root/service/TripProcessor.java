package com.root.service;

import com.root.util.RootUtil;
import com.root.vo.FinalSummary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.root.service.PrintDrivingSummary.printTripSummary;

public class TripProcessor {
    private static final String file = "input.txt";

    public static void main(String[] args) {

        List<String[]> fileData = processFile();
        List<FinalSummary> finalSummaryList = RootUtil.processTripData(fileData);
        printTripSummary(finalSummaryList);
    }

    private static List<String[]> processFile() {
        List<String[]> fileData = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(file))) {
            while (scanner.hasNext()) {
                fileData.add(scanner.nextLine().split("\\s+"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileData;
    }
}
