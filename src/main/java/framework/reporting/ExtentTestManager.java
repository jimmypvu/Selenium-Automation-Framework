package framework.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    private ExtentTestManager (){}
    private static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    private static ExtentReports report = ExtentReportManager.createExtentReport();


    public static synchronized ExtentTest getTest(){
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest startTest(String testName, String desc, ITestResult result){
        String testClass = result.getTestClass().getName().replaceAll("jpvu.tests.functional.", "").replaceAll("jpvu.tests.ui.", "");

        ExtentTest test = report.createTest(testClass + " - " + testName, desc);
        extentTestMap.put((int) Thread.currentThread().getId(), test);

        return test;
    }
}
