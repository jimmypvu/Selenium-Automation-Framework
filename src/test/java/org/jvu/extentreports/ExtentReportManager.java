package org.jvu.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.jvu.tests.BaseTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportManager extends BaseTest {
    public static ExtentReports report = new ExtentReports();

    public synchronized static ExtentReports createExtentReport(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yy hh-mm-ss-a");
        LocalDateTime time = LocalDateTime.now();
        String timestamp = dtf.format(time);

//        ExtentSparkReporter spark = new ExtentSparkReporter("./reports/" + "Extent Report " + timestamp + ".html");
        ExtentSparkReporter spark = new ExtentSparkReporter("./reports/ExtentReport.html");
        spark.config().setDocumentTitle("Extent Reports - Automation Report");
        spark.config().setReportName("Automation Report");
        spark.config().setTheme(Theme.DARK);
        spark.config().thumbnailForBase64(true);
        report.attachReporter(spark);
        report.setSystemInfo("Project", "automationteststore.com");
        report.setSystemInfo("Reporter", "jimmy");
        return report;
    }
}
