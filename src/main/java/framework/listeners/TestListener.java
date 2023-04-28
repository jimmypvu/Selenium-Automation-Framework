package framework.listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import framework.reporting.ExtentReportManager;
import framework.utils.Screenshotter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static framework.reporting.ExtentTestManager.getTest;
import static framework.reporting.ExtentTestManager.startTest;

public class TestListener implements ITestListener {

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
//        to save screenshot locally
//        Screenshotter.screenshotAndSave(result.getName());

        getTest().log(Status.INFO, result.getTestContext().getName() + " *** " + result.getInstanceName() + " *** " + result.getMethod().getMethodName());
        getTest().fail(result.getThrowable());

        if(!result.getTestContext().getName().contains("API")){
            String encodedImage = Screenshotter.screenshotAsBase64();
            getTest().log(Status.INFO, "Test failed at step: ", MediaEntityBuilder.createScreenCaptureFromBase64String(encodedImage).build());

            getTest().log(Status.INFO, result.getTestContext().getAttribute("WebDriver").toString());
            getTest().log(Status.INFO, "Thread ID: " + result.getTestContext().getAttribute("ThreadID"));
        }

        System.out.println(result.getName() + " test failed");
    }

    @Override
    public void onTestSuccess(ITestResult result){
        getTest().log(Status.INFO, result.getTestContext().getName() + " *** " + result.getInstanceName() + " *** " + result.getMethod().getMethodName());
        getTest().log(Status.PASS, "Test passed!");

        if(!result.getTestContext().getName().contains("API")){
            getTest().log(Status.INFO, result.getTestContext().getAttribute("WebDriver").toString());
            getTest().log(Status.INFO, "Thread ID: " + result.getTestContext().getAttribute("ThreadID"));
        }

        System.out.println(result.getName() + " test passed");
    }

    @Override
    public void onTestSkipped(ITestResult result){
        getTest().log(Status.INFO, result.getTestContext().getName() + " *** " + result.getInstanceName() + " *** " + result.getMethod().getMethodName());
        getTest().log(Status.SKIP, "Test ignored / skipped / retried");
        getTest().log(Status.INFO, "Retried: " + result.wasRetried());
        if(!result.wasRetried()){
            getTest().log(Status.INFO, result.getThrowable());
        }

        if(!result.getTestContext().getName().contains("API")){
            getTest().log(Status.INFO, result.getTestContext().getAttribute("WebDriver").toString());
            getTest().log(Status.INFO, "Thread ID: " + result.getTestContext().getAttribute("ThreadID"));
        }

        System.out.println(result.getTestContext().getName() + " test skipped");
    }

}
