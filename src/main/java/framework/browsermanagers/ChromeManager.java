package framework.browsermanagers;

import io.github.bonigarcia.wdm.WebDriverManager;
import framework.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeManager {
    private ChromeManager (){}


    public static WebDriver getChromeDriver(){
        ChromeOptions co = getChromeOptions();
        WebDriverManager.chromedriver().setup();

        return new ChromeDriver(co);
    }

    public static ChromeOptions getChromeOptions(){
        ChromeOptions co = new ChromeOptions();
//        co.addArguments("--remote-allow-origins=*");
        if(ConfigReader.getConfig("headless").equals("true")){
            co.addArguments("--window-size=1920,1080");
            co.addArguments("--start-maximized");
            co.addArguments("--headless");
        }

        return co;
    }
}
