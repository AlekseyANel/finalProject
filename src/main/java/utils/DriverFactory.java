package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class DriverFactory {
    public static   WebDriver driver;
    public  static ConfigFileReader configFileReaderUI = new ConfigFileReader();
    static String driverPath = configFileReaderUI.getDriverPath();


        //webDriver = new ChromeDriver(options);

    public static WebDriver getWebDriver() {
        System.setProperty("webDriver.chrome.driver", driverPath);
        return driver = new ChromeDriver();
    }

    public static void closeAllDriver() {
        if (driver != null) {
//            driver.close();
            driver.quit();
        }
    }
}

