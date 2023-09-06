package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties prop;
    private final String propertyFilePath = "./src/test/resources/config.properties";

    public ConfigReader() {
        FileInputStream fis;
        try {
            fis = new FileInputStream(propertyFilePath);
            prop = new Properties();
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("config.properties not found at " + propertyFilePath);
        }
    }
    public String getUrl(){
        String baseURI = prop.getProperty("url");
        return baseURI.trim();
    }
    public String getBrowserName(){
        String driver = prop.getProperty("browser");
        return driver.trim();
    }

    public String getUsername(){
        String driver = prop.getProperty("username");
        return driver.trim();
    }
    public String getPassword(){
        String driver = prop.getProperty("password");
        return driver.trim();
    }
}
