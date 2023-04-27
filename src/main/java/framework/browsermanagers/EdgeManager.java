package framework.browsermanagers;

import io.github.bonigarcia.wdm.WebDriverManager;
import framework.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeManager {
    private EdgeManager (){}

    public static WebDriver getEdgeDriver(){
        EdgeOptions eo = getEdgeOptions();
        WebDriverManager.edgedriver().setup();

        return new EdgeDriver(eo);
    }

    public static EdgeOptions getEdgeOptions(){
        EdgeOptions eo = new EdgeOptions();
        if(ConfigReader.getConfig("headless").equals("true")){
            eo.addArguments("--headless=new");
            eo.addArguments("--window-size=1920,1080");
        }

        return eo;
    }
}
