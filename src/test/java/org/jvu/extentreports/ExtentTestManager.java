package org.jvu.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    private static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    private static ExtentReports report = ExtentReportManager.createExtentReport();

    public static synchronized ExtentTest getTest(){
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest startTest(String testName, String desc){
        ExtentTest test = report.createTest(testName, desc);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }
}
