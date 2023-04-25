package framework;

import framework.browsermanagers.ChromeManager;
import framework.browsermanagers.EdgeManager;
import framework.browsermanagers.FirefoxManager;
import framework.browsermanagers.LambdaTestManager;
import framework.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected static String url;
    private static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    @BeforeMethod(groups = {"setup"})
//    @Parameters({"browser"})
    public void launchBrowser(/*String browser, */ITestContext context, Method method) throws MalformedURLException {
        url = ConfigReader.getConfig("url");

        WebDriver threadDriver = System.getProperty("browser").equals("") ?
                                                setDriver(ConfigReader.getConfig("browser"), method)
                                                : setDriver(System.getProperty("browser"), method);

        threadLocal.set(threadDriver);

        context.setAttribute("WebDriver", getDriver());
        context.setAttribute("ThreadID", Thread.currentThread().getId());

        manageBrowser();
        getDriver().get(url);
    }

    @AfterMethod(groups = {"setup"})
    public void tearDown(){
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
            case("lt-cloud"):
                return LambdaTestManager.lambdaTest(method);
            default:
                return ChromeManager.getChromeDriver();
        }
    }

}
