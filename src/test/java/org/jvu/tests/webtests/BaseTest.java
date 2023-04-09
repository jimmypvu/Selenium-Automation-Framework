package org.jvu.tests.webtests;

import org.jvu.browsermanagers.ChromeManager;
import org.jvu.browsermanagers.EdgeManager;
import org.jvu.browsermanagers.FirefoxManager;
import org.jvu.utils.ConfigReader;
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
import java.util.HashMap;

public class BaseTest {

    protected static String url;
    private static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    @BeforeMethod(groups = {"setup"})
//    @Parameters({"browser"})
    public void launchBrowser(/*String browser, */ITestContext context, Method method) throws MalformedURLException {
        url = ConfigReader.getConfig("url");

//        WebDriver threadDriver = setDriver(System.getProperty("browser"));
        WebDriver threadDriver = setDriver(ConfigReader.getConfig("browser"), method);
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
        WebDriver driver;
        DesiredCapabilities caps = new DesiredCapabilities();
        //java -jar selenium-server-4.8.0.jar standalone
        String gridURL = "http://192.168.0.163:4444";

        switch(browser){
            case("firefox"):
                return driver = FirefoxManager.getFirefoxDriver();
            case("edge"):
                return driver = EdgeManager.getEdgeDriver();
            case("grid-chrome"):
                ChromeOptions co = ChromeManager.getChromeOptions();
                caps.setCapability(ChromeOptions.CAPABILITY, co);
                return driver = new RemoteWebDriver(new URL(gridURL), caps);
            case("grid-firefox"):
                FirefoxOptions fo = FirefoxManager.getFirefoxOptions();
                caps.setCapability(FirefoxOptions.FIREFOX_OPTIONS, fo);
                return driver = new RemoteWebDriver(new URL(gridURL), caps);
            case("grid-edge"):
                EdgeOptions eo = EdgeManager.getEdgeOptions();
                caps.setCapability(EdgeOptions.CAPABILITY, eo);
                return driver = new RemoteWebDriver(new URL(gridURL), caps);
            case("lt-cloud"):
                return driver = lambdaTest(method);
            default:
                return driver = ChromeManager.getChromeDriver();
        }
    }

    public WebDriver lambdaTest(Method method) throws MalformedURLException {
        String hubURL = "https://jimmyphuvu:vLyH3mPwllnH1jK7WzIZivcOoyQig2omkXsDZs0x4HnBP3IgVs@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "Safari");
        caps.setCapability("browserVersion", "16.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", System.getenv("LT_USERNAME"));
        ltOptions.put("accessKey", System.getenv("LT_ACCESS_KEY"));
        ltOptions.put("build", "Safari Tests");   //build name
        ltOptions.put("platformName", "MacOS Ventura");
//        ltOptions.put("name", this.getClass().getName());   //test name
        ltOptions.put("name", method.getName());   //test name
        ltOptions.put("video", true);   //enable video recording
        ltOptions.put("network", true); //enable network logging
        ltOptions.put("seCdp", true);   //selenium cdp for stability
        ltOptions.put("console", "true");   //enable console debugging
        ltOptions.put("w3c", true);
        ltOptions.put("selenium_version", "4.7.0");
        ltOptions.put("plugin", "java-testNG");
        caps.setCapability("LT:Options", ltOptions);

        return new RemoteWebDriver(new URL(hubURL), caps);
    }

}
