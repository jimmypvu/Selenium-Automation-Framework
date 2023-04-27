package framework.browsermanagers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SauceLabsManager {
    private SauceLabsManager(){};
    private static final String SAUCE_HUB = "https://" + System.getenv("SL_USERNAME") + ":"
            + System.getenv("SL_ACCESS_KEY") + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";


    public static WebDriver sauceLabs(Method method) throws MalformedURLException {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 11");
        browserOptions.setBrowserVersion("latest");
        browserOptions.setCapability("unhandledPromptBehavior", "dismiss and notify");

        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("build", "Selenium");
        sauceOptions.put("name", method.getName());
//        enable for performance logging, har files and console logs (chrome and firefox only), turn off if not needed (slows runtime down ~3x)
//        sauceOptions.put("extendedDebugging", true);
//        sauceOptions.put("capturePerformance", true);

        browserOptions.setCapability("sauce:options", sauceOptions);

        return new RemoteWebDriver(new URL(SAUCE_HUB), browserOptions);
    }
}
