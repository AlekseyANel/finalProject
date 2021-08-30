package business.layer.com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigFileReader;

public class LoginPage {
    WebDriver driver;
        ConfigFileReader configFileReader =  new ConfigFileReader();
    String loginPageUrl = configFileReader.getUrlDemoQA() +"/login";
        //String loginPageUrl = "https://demoqa.com/login";
//        Waiter waiter;
    @FindBy(css = "#userForm > div:nth-child(1) > h5")
    WebElement heading;

    @FindBy(xpath = "//input[@id='userName']")
    WebElement userNameInputField;

    @FindBy(xpath = "//input[@id='password']")
    WebElement passwordInputField;

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    WebElement loginButton;

    @FindBy(xpath ="//button[contains(text(),'Log out')]")                      //"//div[contains(text(),'Profile')]"
    static WebElement logoutButton;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        System.out.println("Чекнуть путь к логин странице: " +loginPageUrl);
        driver.get(loginPageUrl);

    }

    public void login(String userName, String password) throws InterruptedException {
        heading.click();
        userNameInputField.sendKeys(userName);
        passwordInputField.sendKeys(password);
        loginButton.click();
        //Assert.assertTrue(logoutButton.isDisplayed());
    }

    public boolean isLoginPageOpened(){
        return heading.getText().contains("Login in Book Store");
    }
}
