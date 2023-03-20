package utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import tests.BaseTest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotTaker extends BaseTest {
    private static final String FILEPATH = "./src/test/resources/screenshots/";

    public static void takeScreenshot(String methodName){
        File screenshotFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy-MM-dd HH-mm-ss-SSS");
        LocalDateTime time = LocalDateTime.now();
        String timestamp = dtf.format(time);

        try{
            FileHandler.copy(screenshotFile, new File(FILEPATH + methodName + " " + timestamp + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
