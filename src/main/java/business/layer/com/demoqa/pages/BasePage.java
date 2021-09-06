package business.layer.com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.ConfigFileReader;

import java.time.Duration;

public abstract class BasePage {
    protected Actions actions;
    protected WebDriver driver;
    protected ConfigFileReader configFileReader = new ConfigFileReader();
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, 5);
        this.actions = new Actions(driver);
    }

    protected void focusOnElement(WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }
    protected void waiterClickable(WebElement webElement){
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }
    protected void waiterAlert (String someText) throws InterruptedException {
        try {
//        wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(driver.switchTo().alert().getText().contains(someText));
        driver.switchTo().alert().accept();
        } catch (Exception e) {
        }
    }

}
