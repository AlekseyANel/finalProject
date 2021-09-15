package business.layer.com.demoqa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class BookPage extends BasePage {

    @FindBy(xpath = "//label[contains(text(),'ISBN')]")
    WebElement heading;

    @FindBy(xpath = "//button[contains(text(),'Back To Book Store')]")
    WebElement back;

    @FindBy(xpath = "//button[contains(text(),'Add To Your Collection')]")
    WebElement addBook;


    public BookPage(WebDriver driver) {
        super(driver);
    }

    public void back() {
        super.focusOnElement(back);
        back.click();
    }

    public void addBook() {
        super.focusOnElement(addBook);
        addBook.click();
        super.waiterAlert("Book added to your collection.");
    }

    public boolean isPageOpened() {
        return heading.getText().contains("ISBN");
    }
}

