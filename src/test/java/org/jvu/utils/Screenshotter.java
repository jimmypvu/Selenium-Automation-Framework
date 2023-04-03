package org.jvu.utils;

import org.jvu.tests.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Screenshotter extends BaseTest {
    private static final String FILEPATH = "../QA-Automation-Framework/screenshots/";

    public static void takeScreenshot(String methodName) {
        File screenshotFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yy hh-mm-ss-a SSS");
        LocalDateTime time = LocalDateTime.now();
        String timestamp = dtf.format(time);

        try {
            FileHandler.copy(screenshotFile, new File(FILEPATH + methodName + " " + timestamp + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
