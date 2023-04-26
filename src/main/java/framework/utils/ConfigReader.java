package framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private ConfigReader (){}
    private static String propertiesPath = "configs/qa-env.properties";
    private static Properties config = new Properties();


    public static String getConfig(String property){
        try (InputStream ip = ConfigReader.class.getClassLoader().getResourceAsStream(propertiesPath)) {
            config.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return config.getProperty(property);
    }

}
