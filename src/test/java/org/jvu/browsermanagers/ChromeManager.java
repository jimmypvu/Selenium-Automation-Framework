package org.jvu.browsermanagers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.jvu.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeManager {
    private ChromeManager (){}

    public static WebDriver getChromeDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions co = getChromeOptions();

        return new ChromeDriver(co);
    }

    public static ChromeOptions getChromeOptions(){
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--remote-allow-origins=*");
        if(ConfigReader.getConfig("headless") == "true"){
            co.addArguments("--window-size=1920,1080");
            co.addArguments("--start-maximized");
            co.addArguments("--headless");
        }

        return co;
    }
}
