package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class BaseTest {
    protected static final String URL = "https://automationteststore.com/";
    private static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    @BeforeMethod
//    @Parameters({"browser"})
<<<<<<< Updated upstream:src/test/java/tests/BaseTest.java
    public void launchBrowser(/*String browser,*/ ITestContext context) throws MalformedURLException{
=======
    public void launchBrowser(/*String browser*/) throws MalformedURLException{
>>>>>>> Stashed changes:src/test/java/org/jvu/tests/BaseTest.java
        WebDriver threadDriver = setDriver(System.getProperty("browser"));
//        WebDriver threadDriver = setDriver(browser);
        threadLocal.set(threadDriver);

        context.setAttribute("WebDriver", getDriver());
        context.setAttribute("ThreadID", Thread.currentThread().getId());

        manageBrowser();
        getDriver().get(URL);
    }

    @AfterMethod
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

    public WebDriver setDriver(String browser) throws MalformedURLException {
        WebDriver driver;
        DesiredCapabilities caps = new DesiredCapabilities();
        //java -jar selenium-server-4.8.0.jar standalone
        String gridURL = "http://192.168.0.163:4444";

        switch(browser){
            case("firefox"):
                FirefoxOptions fo = new FirefoxOptions();
                fo.addArguments("-width=1920");
                fo.addArguments("-height=1080");
                fo.addArguments("--headless");
                return driver = new FirefoxDriver(fo);
            case("edge"):
                EdgeOptions eo = new EdgeOptions();
                eo.addArguments("--headless=new");
                eo.addArguments("--window-size=1920,1080");
                return driver = new EdgeDriver(eo);
            case("grid-chrome"):
                caps.setCapability("browser", "chrome");
                return driver = new RemoteWebDriver(new URL(gridURL), caps);
            case("grid-firefox"):
                caps.setCapability("browser", "firefox");
                return driver = new RemoteWebDriver(new URL(gridURL), caps);
            case("grid-edge"):
                caps.setCapability("browser", "MicrosoftEdge");
                return driver = new RemoteWebDriver(new URL(gridURL), caps);
            case("lt-cloud"):
                return lambdaTest();
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions co = new ChromeOptions();
                co.addArguments("--remote-allow-origins=*");
                co.addArguments("--window-size=1920,1080");
                co.addArguments("--start-maximized");
                co.addArguments("--headless");
                return driver = new ChromeDriver(co);
        }
    }

    public WebDriver lambdaTest() throws MalformedURLException {
        String hubURL = "https://jimmyphuvu:vLyH3mPwllnH1jK7WzIZivcOoyQig2omkXsDZs0x4HnBP3IgVs@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "Safari");
        caps.setCapability("browserVersion", "16.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", System.getenv("LT_USERNAME"));
        ltOptions.put("accessKey", System.getenv("LT_ACCESS_KEY"));
        ltOptions.put("build", "Safari Tests");   //build name
        ltOptions.put("platformName", "MacOS Ventura");
        ltOptions.put("name", this.getClass().getName());   //test name
        ltOptions.put("video", true);   //enable video recording
        ltOptions.put("network", true); //enable network logging
        ltOptions.put("seCdp", true);   //chrome performance logs
        ltOptions.put("console", "true");   //enable console debugging
        ltOptions.put("selenium_version", "4.7.0");
        ltOptions.put("plugin", "java-testNG");
        caps.setCapability("LT:Options", ltOptions);

        return new RemoteWebDriver(new URL(hubURL), caps);
    }

}
