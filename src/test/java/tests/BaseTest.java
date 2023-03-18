package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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
    @Parameters({"browser"})
    public void launchBrowser(String browser) throws MalformedURLException{
//        WebDriver threadDriver = pickBrowser(System.getProperty("browser"));
        WebDriver threadDriver = pickBrowser(browser);
        threadLocal.set(threadDriver);
        manageBrowser();
        getDriver().get(URL);
    }

    @AfterMethod
    public void tearDown(){
        getDriver().quit();
        threadLocal.remove();
    }

    public WebDriver getDriver(){
        return threadLocal.get();
    }

    public void manageBrowser(){
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
    }

    public WebDriver pickBrowser(String browser) throws MalformedURLException {
        WebDriver driver;
        DesiredCapabilities caps = new DesiredCapabilities();
        //java -jar selenium-server-4.8.0.jar standalone
        String gridURL = "http://192.168.1.160:4444";

        switch(browser){
            case("firefox"):
                return driver = new FirefoxDriver();
            case("MicrosoftEdge"):
                return driver = new EdgeDriver();
            case("grid-chrome"):
                caps.setCapability("browser", "chrome");
                return driver = new RemoteWebDriver(new URL(gridURL), caps);
            case("grid-firefox"):
                caps.setCapability("browser", "firefox");
                return driver = new RemoteWebDriver(new URL(gridURL), caps);
            case("grid-edge"):
                caps.setCapability("browser", "MicrosoftEdge");
                return driver = new RemoteWebDriver(new URL(gridURL), caps);
            case("cloud"):
                return lambdaTest();
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                return driver = new ChromeDriver(options);
        }
    }

    public WebDriver lambdaTest() throws MalformedURLException {
        String hubURL = "https://jimmypvu:vygtZr8rY1Zejji0D6x0n7sWm2VOwb3uchbBYRbpDcKJ4v5SQu@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "safari");
        caps.setCapability("browserVersion", "16.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("user", "jimmypvu");
        ltOptions.put("accessKey", "vygtZr8rY1Zejji0D6x0n7sWm2VOwb3uchbBYRbpDcKJ4v5SQu");
        ltOptions.put("build", "Selenium 4");   //build name
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
