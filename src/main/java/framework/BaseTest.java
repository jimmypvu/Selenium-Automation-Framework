package framework;

import framework.browsermanagers.*;
import framework.utils.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    private static final String BASE_URL = ConfigReader.getConfig("url");
    private static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();


    @BeforeMethod(groups = {"web setup"})
    @Parameters({"browsers"})
    public void setupAndLaunchBrowser(@Optional String browsers, ITestContext context, Method method) throws MalformedURLException {
        WebDriver threadDriver;

        //can set browser & suite via gradle script with flag or config file, or TestNG params if adding flag "all"
        if(System.getProperty("browser").equals("all")){
            threadDriver = setDriver(browsers, method);
        }else{
            if(System.getProperty("browser").equals("")){
                System.setProperty("browser", ConfigReader.getConfig("browser"));
            }
            threadDriver = setDriver(System.getProperty("browser"), method);
        }

        threadLocal.set(threadDriver);

        context.setAttribute("WebDriver", getDriver());
        context.setAttribute("ThreadID", Thread.currentThread().getId());

        manageBrowser();

        getDriver().get(BASE_URL);
    }

    @AfterMethod(groups = {"web setup"})
    public void teardown(ITestResult result){

        if(System.getProperty("browser").equals("sauce")){
            ((JavascriptExecutor) getDriver()).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));

            if(!result.isSuccess()){
                ((JavascriptExecutor) getDriver()).executeScript("sauce:context=" + result.getThrowable());
            }
        }

        //if running on lambdatest cloud
//        if(System.getProperty("browser").equals("lambda")){
//            ((JavascriptExecutor) getDriver()).executeScript("lambda-status=" + ((result.isSuccess()) ? "passed" : "failed"));
//        }

        getDriver().quit();
        threadLocal.remove();
    }

    public static WebDriver getDriver(){
        return threadLocal.get();
    }

    public void manageBrowser(){
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
    }

    public WebDriver setDriver(String browser, Method method) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        //java -jar selenium-server-4.8.0.jar standalone
        String gridURL = "http://192.168.0.163:4444";

        switch(browser){
            case("firefox"):
                return FirefoxManager.getFirefoxDriver();
            case("edge"):
                return EdgeManager.getEdgeDriver();
            case("grid-chrome"):
                ChromeOptions co = ChromeManager.getChromeOptions();
                caps.setCapability(ChromeOptions.CAPABILITY, co);
                return new RemoteWebDriver(new URL(gridURL), caps);
            case("grid-firefox"):
                FirefoxOptions fo = FirefoxManager.getFirefoxOptions();
                caps.setCapability(FirefoxOptions.FIREFOX_OPTIONS, fo);
                return new RemoteWebDriver(new URL(gridURL), caps);
            case("grid-edge"):
                EdgeOptions eo = EdgeManager.getEdgeOptions();
                caps.setCapability(EdgeOptions.CAPABILITY, eo);
                return new RemoteWebDriver(new URL(gridURL), caps);
            case("lambda"):
                return LambdaTestManager.lambdaTest(method);
            case("sauce"):
                return SauceLabsManager.sauceLabs(method);
            default:
                return ChromeManager.getChromeDriver();
        }
    }

}
