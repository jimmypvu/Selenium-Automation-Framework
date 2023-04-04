package org.jvu.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class CucumberHooks {
    protected static final String URL = "https://automationteststore.com/";
    protected static WebDriver driver = null;
    protected WebDriverWait wait = null;

//    @Before(order = 1)
//    public void setContext(ITestContext context){
//        context.setAttribute("WebDriver", getDriver());
//        context.setAttribute("ThreadID", Thread.currentThread().getId());
//    }

    @Before(order = 0)
    public void setupBrowser(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--remote-allow-origins=*");
                co.addArguments("--headless");
        driver = new ChromeDriver(co);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Before(order = 1)
    public void launchBrowser(){
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
