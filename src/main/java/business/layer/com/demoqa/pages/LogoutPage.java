package business.layer.com.demoqa.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage {
/*    WebDriver driver;
    ConfigFileReaderUI configFileReaderUI =  new ConfigFileReaderUI();
    String loginPageUrl = configFileReaderUI.getUrl() +"/login";*/


    @FindBy(xpath ="//button[contains(text(),'Log out')]")
    static WebElement logoutButton;

    public static void logout() {
        logoutButton.click();
    }

}
/*

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        System.out.println("Чекнуть путь к логин странице: " +loginPageUrl);
        driver.get(loginPageUrl);
        PageFactory.initElements(driver, this);
    }

        @FindBy(xpath ="//button[contains(text(),'Log out')]")     //"//div[contains(text(),'Profile')]"
*/
