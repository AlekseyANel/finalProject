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

public class ProfilePage extends BasePage{
    String pageUrl = configFileReader.getPropertyFromFile("urlDemoQA") +"/profile";
    //configFileReader.getUrlDemoQA()

    @FindBy(xpath = "//div[contains(text(),'Profile')]")
    WebElement heading;
    @FindBy(xpath = "//a[contains(text(),'Understanding ECMAScript 6')]")
    WebElement zakasBook;
    @FindBy(xpath = "//a[contains(text(),'Eloquent JavaScript, Second Edition')]")
    WebElement haverbekeBook;
    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/div[1]/div[2]/div[2]/div/div[5]/div/span")
    WebElement secondBookDel;
    @FindBy(xpath = "//div[@class='text-right button di']/button[contains(text(),'Delete All Books')]")
    WebElement allBooksDel;
    @FindBy(xpath = "//button[contains(text(),'Delete Account')]")
    WebElement accountDel;


    @FindBy(xpath = "//*[@id='closeSmallModal-ok']")
    WebElement confirmDel;
    @FindBy(xpath = "//*[@id='delete-record-undefined']")
    List<WebElement> collectionSize;

    public ProfilePage(WebDriver driver) {
        super(driver);
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
    public void secondBookDel() throws InterruptedException {
        secondBookDel.click();
        super.waiterClickable(confirmDel);
        confirmDel.click();
        super.waiterAlert("Book deleted.");
    }
    public void delAllBooks() throws InterruptedException {
        focusOnElement(allBooksDel);
        allBooksDel.click();
        super.waiterClickable(confirmDel);
        confirmDel.click();
        super.waiterAlert("All Books deleted.");
    }
    public void delAccount() throws InterruptedException {
        focusOnElement(accountDel);
        accountDel.click();
        super.waiterClickable(confirmDel);
        confirmDel.click();
        super.waiterAlert("User Deleted.");
    }


    public boolean isPageOpened(){
        return heading.getText().contains("Profile");
    }

}