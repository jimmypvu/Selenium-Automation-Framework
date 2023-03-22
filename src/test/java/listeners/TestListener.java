package listeners;

import com.aventstack.extentreports.Status;
import extentreports.ExtentReportManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;
import utils.Screenshotter;

import static extentreports.ExtentTestManager.getTest;
import static extentreports.ExtentTestManager.startTest;

public class TestListener extends BaseTest implements ITestListener {
    @Override
    public void onStart(ITestContext context){
        System.out.println(context.getName() + " tests starting!");
    }

    @Override
    public void onTestStart(ITestResult result){
        startTest(result.getName(), result.getMethod().getDescription());
    }

    @Override
    public void onTestFailure(ITestResult result){
        String methodName = result.getName();
        Screenshotter.takeScreenshot(methodName);

        getTest().log(Status.FAIL,"Test failed.");

        System.out.println(result.getName() + " test failed");
    }

    @Override
    public void onTestSuccess(ITestResult result){
        getTest().log(Status.PASS, "Test passed!");

        System.out.println(result.getName() + " test passed");
    }

    @Override
    public void onTestSkipped(ITestResult result){
        getTest().log(Status.SKIP, "Test ignored/skipped");
    }

    @Override
    public void onFinish(ITestContext context){
        System.out.println(context.getName() + " finished!");
        ExtentReportManager.report.flush();
    }

}
