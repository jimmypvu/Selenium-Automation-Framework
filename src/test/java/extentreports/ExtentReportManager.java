package extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportManager {
    public static ExtentReports report = new ExtentReports();

    public synchronized static ExtentReports createExtentReports(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yy hh-mm-ss-a");
        LocalDateTime time = LocalDateTime.now();
        String timestamp = dtf.format(time);

        ExtentSparkReporter reporter = new ExtentSparkReporter("./reports/" + "Extent Report " + timestamp + ".html");
        reporter.config().setReportName("Extent Report");
        report.attachReporter(reporter);
        report.setSystemInfo("Project", "automationteststore.com");
        report.setSystemInfo("Reporter", "jimmy");
        return report;
    }
}
