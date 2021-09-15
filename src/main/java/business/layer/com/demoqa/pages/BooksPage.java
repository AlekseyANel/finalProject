package business.layer.com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BooksPage extends BasePage {
    String pageUrl = configFileReader.getPropertyFromFile("urlDemoQA") +"/books";
//configFileReader.getUrlDemoQA()
    @FindBy(xpath = "//div[contains(text(),'Book Store')]")
    WebElement heading;

    @FindBy(xpath = "//a[contains(text(),'Understanding ECMAScript 6')]")
    WebElement zakasBook;

    @FindBy(xpath = "//a[contains(text(),'Eloquent JavaScript, Second Edition')]")
    WebElement haverbekeBook;

    public BooksPage(WebDriver driver) {
        super(driver);
        driver.get(pageUrl);

    }

    public void zakasBook() {//наводим на нужный элемент и кликаем
        super.focusOnElement(zakasBook);
        zakasBook.click();
//        return new BookPage(driver);
    }

    public void haverbekeBook() {//наводим на нужный элемент и кликаем
        super.focusOnElement(haverbekeBook);
        haverbekeBook.click();
    }
public boolean isPageOpened(){
        return heading.getText().contains("Book Store");
    }
}
