package org.framework.listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.framework.reporting.ExtentReportManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.framework.BaseTest;

import static org.framework.reporting.ExtentTestManager.getTest;
import static org.framework.reporting.ExtentTestManager.startTest;

public class TestListener extends BaseTest implements ITestListener {
    @Override
    public void onStart(ITestContext context){
        System.out.println(context.getName() + " tests starting!");
    }

    @Override
    public void onTestStart(ITestResult result){
        startTest(result.getName(), result.getMethod().getDescription(), result);
    }

    @Override
    public void onFinish(ITestContext context){
        System.out.println(context.getName() + " tests finished!");
        ExtentReportManager.report.flush();
    }

    @Override
    public void onTestFailure(ITestResult result){
//        enable if you need screenshots saved to local machine
//        String methodName = result.getName();
//        Screenshotter.takeScreenshot(methodName);
        getTest().log(Status.INFO, result.getTestContext().getName() + " --- " + result.getInstanceName() + " --- " + result.getMethod().getMethodName());
        getTest().fail(result.getThrowable());

        if(!result.getTestContext().getName().toString().contains("API")){
            String base64 = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
            getTest().log(Status.INFO, "Test failed at: ", MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());

            getTest().log(Status.INFO, result.getTestContext().getAttribute("WebDriver").toString());
            getTest().log(Status.INFO, "Thread ID: " + result.getTestContext().getAttribute("ThreadID"));
        }

        System.out.println(result.getName() + " test failed");
    }

    @Override
    public void onTestSuccess(ITestResult result){
        getTest().log(Status.INFO, result.getTestContext().getName() + " --- " + result.getInstanceName() + " --- " + result.getMethod().getMethodName());
        getTest().log(Status.PASS, "Test passed!");
        getTest().log(Status.INFO, result.getThrowable());

        if(!result.getTestContext().getName().toString().contains("API")){
            getTest().log(Status.INFO, result.getTestContext().getAttribute("WebDriver").toString());
            getTest().log(Status.INFO, "Thread ID: " + result.getTestContext().getAttribute("ThreadID"));
        }

        System.out.println(result.getName() + " test passed");
    }

    @Override
    public void onTestSkipped(ITestResult result){
        System.out.println(result.getTestContext().getName() + " tests skipped");
        getTest().log(Status.INFO, result.getTestContext().getName() + " --- " + result.getInstanceName() + " --- " + result.getMethod().getMethodName());
        getTest().log(Status.SKIP, "Test ignored / skipped / retried");
        getTest().log(Status.INFO, "Retried: " + result.wasRetried());
        if(!result.wasRetried()){
            getTest().log(Status.INFO, result.getThrowable());
        }

        if(!result.getTestContext().getName().toString().contains("API")){
            getTest().log(Status.INFO, result.getTestContext().getAttribute("WebDriver").toString());
            getTest().log(Status.INFO, "Thread ID: " + result.getTestContext().getAttribute("ThreadID"));
        }
    }

}
