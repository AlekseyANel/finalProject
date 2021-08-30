package business.layer.com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigFileReader;

public class MainPage {
    WebDriver driver;
    public static ConfigFileReader configFileReader = new ConfigFileReader();

    public MainPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
       }

    public void navigateToMainPage() {
        driver.get(configFileReader.getUrlDemoQA());
    }

}
