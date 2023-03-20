package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;
import utility.ScreenshotTaker;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result){
        String methodName = result.getMethod().getMethodName().trim();
        ScreenshotTaker.takeScreenshot(methodName);

        System.out.println("boo failed :[");
    }

    @Override
    public void onTestSuccess(ITestResult result){
        System.out.println("yay passed!");
    }

}
