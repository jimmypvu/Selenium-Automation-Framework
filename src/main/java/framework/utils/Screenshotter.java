package framework.utils;

import framework.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Screenshotter {
    private static final String FILEPATH = "../QA-Automation-Framework/screenshots/";

    public static void screenshotAndSave(String methodName) {
        File screenshotFile = ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.FILE);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yy hh-mm-ss-a SSS");
        LocalDateTime time = LocalDateTime.now();
        String timestamp = dtf.format(time);

        try {
            FileHandler.copy(screenshotFile, new File(FILEPATH + methodName + " " + timestamp + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String screenshotAsBase64(){

        return ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.BASE64);
    }
}
