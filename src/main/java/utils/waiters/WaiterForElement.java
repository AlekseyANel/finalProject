package utils.waiters;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WaiterForElement {

        private WebDriver driver;
        private WebDriverWait wait;

    public WaiterForElement(WebDriver driver){
            PageFactory.initElements(driver, this);
            this.driver = driver;
        }
        public void waiterByXLocator (String xpathLocator){
            WebDriverWait waitForOne = new WebDriverWait(driver, 10);
            waitForOne.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.valueOf(xpathLocator))));
        }
    public void waiterAlert (String someText) throws InterruptedException {
            wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.alertIsPresent());
            Assert.assertTrue(driver.switchTo().alert().getText().contains(someText));
            driver.switchTo().alert().accept();

    }
    }

