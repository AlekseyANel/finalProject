package business.layer.com.demoqa.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import utils.waiters.WaiterForElement;

public class BookPage {
    WebDriver driver;
    WebDriverWait wait;
    //WaiterForElement waiterForElement;

    @FindBy(xpath = "//label[contains(text(),'ISBN')]")
    WebElement heading;

    @FindBy(xpath = "//button[contains(text(),'Back To Book Store')]")
    WebElement back;

    @FindBy(xpath = "//button[contains(text(),'Add To Your Collection')]")
    WebElement addBook;


    public BookPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void back() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", back);
        back.click();

    }

    public void addBook() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addBook);
        addBook.click();
        try {
            wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.alertIsPresent());
            Assert.assertTrue(driver.switchTo().alert().getText().contains("Book added to your collection."));
            driver.switchTo().alert().accept();
//            waiterForElement.waiterAlert("Book added to your collection.");
        } catch (Exception e) {
            //exception handling
        }
    }

    public boolean isPageOpened() {
        return heading.getText().contains("ISBN");
    }

}