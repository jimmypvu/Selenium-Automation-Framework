package jpvu.stepdefinitions.runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CucumberHooks {
    protected static final String URL = "https://automationteststore.com/";
    protected static WebDriver driver;
    protected WebDriverWait wait;

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

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

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
