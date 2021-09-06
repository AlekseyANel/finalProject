package business.layer.com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ButtonsPage extends BasePage{
    String pageUrl = configFileReader.getUrlDemoQA() +"/buttons";

    @FindBy(xpath = "//div[contains(text(),'Buttons')]")
    WebElement heading;

    @FindBy(xpath = "//button[@id='doubleClickBtn'][contains(text(),'Double Click Me')]")
    WebElement doubleClickBtn;
    @FindBy(xpath = "//*[@id='doubleClickMessage']")
    WebElement responseDoubleClickBtn;

    @FindBy(xpath = "//button[@id='rightClickBtn'][contains(text(),'Right Click Me')]")
    WebElement rightClickBtn;
    @FindBy(xpath = "//*[@id='rightClickMessage']")
    WebElement responseRightClickBtn;

    @FindBy(xpath = "//button[text()='Click Me']")
    WebElement dynamicClickMe;
    @FindBy(xpath = "//*[@id='dynamicClickMessage'][text()='You have done a dynamic click']")
    WebElement responseClickMe;


    public ButtonsPage(WebDriver driver) {
        super(driver);
        System.out.println("Чекнуть путь к buttons странице: " + pageUrl);
        driver.get(pageUrl);
    }

    public void doubleClickBtn()  {
        super.waiterClickable(doubleClickBtn);
        actions.doubleClick(doubleClickBtn).build().perform();
        Assert.assertEquals(responseDoubleClickBtn.getText(),"You have done a double click", "Not!!");
    }
    public void rightClickBtn()  {
        super.waiterClickable(rightClickBtn);
        actions.contextClick(rightClickBtn).perform();
        Assert.assertEquals(responseRightClickBtn.getText(),"You have done a right click", "Not!!");
    }
    public void clickMe()  {
        super.waiterClickable(dynamicClickMe);
        dynamicClickMe.click();
        Assert.assertEquals(responseClickMe.getText(),"You have done a dynamic click", "Not!!");
    }

    public boolean isPageOpened(){
        return heading.getText().contains("Buttons");
    }
}
