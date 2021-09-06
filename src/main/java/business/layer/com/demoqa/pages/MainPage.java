package business.layer.com.demoqa.pages;

import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
       }

    public void navigateToMainPage() {driver.get(configFileReader.getUrlDemoQA());
    }

}
