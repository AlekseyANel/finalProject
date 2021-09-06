package business.layer.com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage extends BasePage {
    String loginPageUrl = configFileReader.getUrlDemoQA() +"/login";


    @FindBy(css = "#userForm > div:nth-child(1) > h5")
    WebElement heading;

    @FindBy(xpath = "//input[@id='userName']")
    WebElement userNameInputField;

    @FindBy(xpath = "//input[@id='password']")
    WebElement passwordInputField;

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    WebElement loginButton;

    @FindBy(xpath ="//button[contains(text(),'Log out')]")
    WebElement logoutButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        System.out.println("Чекнуть путь к логин странице: " +loginPageUrl);
        driver.get(loginPageUrl);
    }

    public void login(String userName, String password) {
        userNameInputField.sendKeys(userName);
        passwordInputField.sendKeys(password);
        loginButton.click();
        //ожидаем полной регистрации, чтоб появилась кнопка ЛОгаут
        super.waiterClickable(logoutButton);
        Assert.assertTrue(logoutButton.isDisplayed());
    }

    public void logout() {
        super.waiterClickable(logoutButton);
        logoutButton.click();
        Assert.assertTrue(loginButton.isDisplayed());
    }


    public boolean isPageOpened(){
        return heading.getText().contains("Login in Book Store");
    }


}
