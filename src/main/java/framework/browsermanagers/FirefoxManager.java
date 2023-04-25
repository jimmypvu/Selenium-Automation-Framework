package framework.browsermanagers;

import io.github.bonigarcia.wdm.WebDriverManager;
import framework.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxManager {
    private FirefoxManager (){}

    public static WebDriver getFirefoxDriver(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions fo = getFirefoxOptions();

        return new FirefoxDriver(fo);
    }

    public static FirefoxOptions getFirefoxOptions(){
        FirefoxOptions fo = new FirefoxOptions();
        if(ConfigReader.getConfig("headless").equals("true")){
            fo.addArguments("-width=1920");
            fo.addArguments("-height=1080");
            fo.addArguments("--headless");
        }

        return fo;
    }
}
