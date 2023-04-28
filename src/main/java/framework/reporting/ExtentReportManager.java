package framework.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import framework.BaseTest;
import framework.utils.ConfigReader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportManager extends BaseTest {
    private ExtentReportManager (){}
    public static ExtentReports report;


    public synchronized static ExtentReports createExtentReport(){
//        for timestamped reports
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
//        String timestamp = LocalDateTime.now().format(dtf);
//        ExtentSparkReporter spark = new ExtentSparkReporter("./reports/" + "Test Report " + timestamp + ".html");

        ExtentSparkReporter spark = new ExtentSparkReporter("./reports/Test Report.html");
        spark.config().setDocumentTitle("Extent Report - Automation Tests Report");
        spark.config().setReportName("Test Execution Report");
        spark.config().setTheme(Theme.DARK);
        spark.config().setEncoding("UTF-8");
        spark.config().thumbnailForBase64(true);

        report = new ExtentReports();
        report.attachReporter(spark);
        report.setSystemInfo("Project", ConfigReader.getConfig("url"));
        report.setSystemInfo("Reporter", System.getProperty("user.name"));

        return report;
    }
}
