package jpvu.stepdefinitions.runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CucumberHooks {
    protected static final String URL = "https://automationteststore.com/";
    protected static WebDriver driver;


    @Before
    public void launchBrowser(){
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--remote-allow-origins=*");
        co.addArguments("--headless");
        co.addArguments("--window-size=1920,1080");
        co.addArguments("--start-maximized");
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver(co);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.get(URL);
    }

    public static WebDriver getDriver(){
        return driver;
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
