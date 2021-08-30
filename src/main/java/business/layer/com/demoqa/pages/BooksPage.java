package business.layer.com.demoqa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigFileReader;

public class BooksPage {
    WebDriver driver;
    ConfigFileReader configFileReader =  new ConfigFileReader();
    String pageUrl = configFileReader.getUrlDemoQA() +"/books";

    @FindBy(xpath = "//div[contains(text(),'Book Store')]")
    WebElement heading;

    @FindBy(xpath = "//a[contains(text(),'Understanding ECMAScript 6')]")
    WebElement zakasBook;

    @FindBy(xpath = "//a[contains(text(),'Eloquent JavaScript, Second Edition')]")
    WebElement haverbekeBook;

    public BooksPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        driver.get(pageUrl);

    }

    public void zakazBook() {//наводим на нужный элемент и кликаем
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", zakasBook);
        zakasBook.click();
    }

    public void haverbekeBook() {//наводим на нужный элемент и кликаем
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", haverbekeBook);
        haverbekeBook.click();
    }

    public boolean isPageOpened(){
        return heading.getText().contains("Book Store");
    }
}
