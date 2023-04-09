package org.jvu.browsermanagers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class LambdaTestManager {
    private LambdaTestManager (){}

    public static WebDriver lambdaTest(Method method) throws MalformedURLException {
        String hubURL = "https://jimmyphuvu:vLyH3mPwllnH1jK7WzIZivcOoyQig2omkXsDZs0x4HnBP3IgVs@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "Safari");
        caps.setCapability("browserVersion", "16.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", System.getenv("LT_USERNAME"));
        ltOptions.put("accessKey", System.getenv("LT_ACCESS_KEY"));
        ltOptions.put("build", "MacOS Ventura / Safari 16");   //build name
        ltOptions.put("platformName", "MacOS Ventura");
//        ltOptions.put("name", this.getClass().getName());   //test name
        ltOptions.put("name", method.getName());   //test name
        ltOptions.put("video", true);   //enable video recording
        ltOptions.put("network", true); //enable network logging
        ltOptions.put("seCdp", true);   //selenium cdp for stability
        ltOptions.put("console", "true");   //enable console debugging
        ltOptions.put("w3c", true);
        ltOptions.put("selenium_version", "4.7.0");
        ltOptions.put("plugin", "java-testNG");
        caps.setCapability("LT:Options", ltOptions);

        return new RemoteWebDriver(new URL(hubURL), caps);
    }
}
