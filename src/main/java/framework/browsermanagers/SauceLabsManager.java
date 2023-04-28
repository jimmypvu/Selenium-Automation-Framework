package framework.browsermanagers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SauceLabsManager {
    private SauceLabsManager(){};
    private static final String SAUCE_HUB = "https://" + System.getenv("SAUCE_USERNAME") + ":"
            + System.getenv("SAUCE_ACCESS_KEY") + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";


    public static WebDriver sauceLabs(Method method) throws MalformedURLException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = LocalDateTime.now().format(dtf);

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 11");
        browserOptions.setBrowserVersion("latest");
        browserOptions.setCapability("unhandledPromptBehavior", "dismiss and notify");

        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("build", "Selenium Tests: " + timestamp);
        sauceOptions.put("name", method.getName());
        sauceOptions.put("extendedDebugging", true);  //network logs har files and console logs
//        sauceOptions.put("capturePerformance", true); //sauce performance logging, not working at the moment

        browserOptions.setCapability("sauce:options", sauceOptions);

        return new RemoteWebDriver(new URL(SAUCE_HUB), browserOptions);
    }


}
