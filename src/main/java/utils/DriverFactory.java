package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class DriverFactory {
    public static WebDriver driver;
/*    // Создаем cookie
    private static final Cookie COOKIE = new Cookie("_ga",
            "GA1.2.2053011903.1630574276", ".demoqa.com",
            "/", new Date(2023-09-04));//
                        configFileReader.getDriverPath();*/
    public static ConfigFileReader configFileReader = new ConfigFileReader();

    static String driverPath = configFileReader.getPropertyFromFile("driverPath");



    public static WebDriver getWebDriver() {
        System.setProperty("webDriver.chrome.driver", driverPath);
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
/*        // Добавляем cookie в браузер
        webDriver.manage().addCookie(COOKIE);*/
        return driver;
    }

    public static void closeAllDriver() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}

