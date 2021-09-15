package business.layer.com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigFileReader;

public class RegPage extends BasePage {//не работает из-за рекаптчи
    ConfigFileReader configFileReader =  new ConfigFileReader();
    String loginPageUrl = configFileReader.getPropertyFromFile("urlDemoQA") +"/register";
//        Waiter waiter;  configFileReader.getUrlDemoQA()
    @FindBy(xpath = "//div[contains(text(),'Register')]")
    WebElement heading;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    WebElement firstNameInputField;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement lastNameInputField;

    @FindBy(xpath = "//input[@placeholder='UserName']")
    WebElement userNameInputField;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement passwordInputField;

    @FindBy(xpath ="//*[@id='g-recaptcha']/div/div/iframe")
    WebElement recaptcha;
    @FindBy(xpath ="//div[@class='recaptcha-checkbox-border']")
    WebElement recaptchaInFrame;

    @FindBy(xpath = "//button[contains(text(),'Register')]")
    WebElement regButton;

    public RegPage(WebDriver driver) {
        super(driver);
        System.out.println("Чекнуть путь к reg странице: " +loginPageUrl);
        driver.get(loginPageUrl);

    }

    public void registration (String firstName,
                              String lastName,
                              String userName,
                              String password) throws InterruptedException {
        firstNameInputField.sendKeys(firstName);
        lastNameInputField.sendKeys(lastName);
        userNameInputField.sendKeys(userName);
        passwordInputField.sendKeys(password);
        driver.switchTo().frame(recaptcha);
        recaptchaInFrame.click();
        driver.switchTo().defaultContent();
        regButton.click();
    }

    public boolean isRegPageOpened(){
        return heading.getText().contains("Register");
    }
}
