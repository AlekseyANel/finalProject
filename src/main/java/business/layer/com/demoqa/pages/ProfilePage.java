package business.layer.com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.ConfigFileReader;

import java.util.List;

public class ProfilePage {
    WebDriver driver;
//    WaiterForElement waiterForElement;
    WebDriverWait wait;
    ConfigFileReader configFileReader =  new ConfigFileReader();
    String pageUrl = configFileReader.getUrlDemoQA() +"/profile";

    @FindBy(xpath = "//div[contains(text(),'Profile')]")
    WebElement heading;
    @FindBy(xpath = "//a[contains(text(),'Understanding ECMAScript 6')]")
    WebElement zakasBook;
    @FindBy(xpath = "//a[contains(text(),'Eloquent JavaScript, Second Edition')]")
    WebElement haverbekeBook;
    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/div[1]/div[2]/div[2]/div/div[5]/div/span")
    WebElement secondBookDel;
    @FindBy(xpath = "//*[@id='delete-record-undefined']")
        List<WebElement> collectionSize;

    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        driver.get(pageUrl);
    }


    public int getCollectionSize() {
        return collectionSize.size();           // driver.findElements(By.xpath(collectionSize.toString())).size();
    }
    public boolean getZakasBook() {
        return zakasBook.isEnabled();
    }
    public boolean getHaverbekeBook() {
        return haverbekeBook.isEnabled();
    }
    public void secondBookDel()  {
        secondBookDel.click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[@id='closeSmallModal-ok']"))).click();
//        Assert.assertTrue(driver.switchTo().alert().getText().contains("Do you want to delete this book?"));
        try {
            wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.alertIsPresent());
            Assert.assertTrue(driver.switchTo().alert().getText().contains("Book deleted."));
            driver.switchTo().alert().accept();
//            waiterForElement.waiterAlert("Book deleted.");
        } catch (Exception e) {
            //exception handling
        }
    }

    public boolean isPageOpened(){
        return heading.getText().contains("Profile");
    }

}