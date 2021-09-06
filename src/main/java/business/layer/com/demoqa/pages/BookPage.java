package business.layer.com.demoqa.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookPage extends BasePage {
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
        super(driver);
    }

    public void back() {
        super.focusOnElement(back);
        back.click();
    }

    public void addBook() throws InterruptedException {
        super.focusOnElement(addBook);
        addBook.click();
        super.waiterAlert("Book added to your collection.");
    }

    public boolean isPageOpened() {
        return heading.getText().contains("ISBN");
    }
}


/*
        try {
                wait = new WebDriverWait(driver, 3);
                wait.until(ExpectedConditions.alertIsPresent());
                Assert.assertTrue(driver.switchTo().alert().getText().contains("Book added to your collection."));
                driver.switchTo().alert().accept();
//            waiterForElement.waiterAlert("Book added to your collection.");
                } catch (Exception e) {
                //exception handling
                }*/
