package business.layer.com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.testng.Assert;


import java.util.ArrayList;
import java.util.List;

public class LinksPage extends BasePage{
    String pageUrl = configFileReader.getUrlDemoQA() +"/links";

    @FindBy(xpath = "//div[contains(text(),'Links')]")
    WebElement heading;
    @FindBy(xpath = "//a[@id='simpleLink'][contains(text(),'Home')]")
    WebElement home;

    @FindBy(xpath = "//a[contains(text(),'Created')]")
    WebElement created;
    @FindBy(xpath = "//p[@id='linkResponse']/b[contains(text(),'201')]")
    WebElement createdResponse;

    @FindBy(xpath = "//a[contains(text(),'No Content')]")
    WebElement noContent;
    @FindBy(xpath = "//p[@id='linkResponse']/b[contains(text(),'No Content')]")
    WebElement noContentResponse;

    @FindBy(xpath = "//a[contains(text(),'Not Found')]")
    WebElement notFound;
    @FindBy(xpath = "//p[@id='linkResponse']/b[contains(text(),'Not Found')]")
    WebElement notFoundResponse;

    public LinksPage(WebDriver driver) {
        super(driver);
        System.out.println("Чекнуть путь к links странице: " + pageUrl);
        driver.get(pageUrl);
    }

    public void homeNewTab()  {
        home.click();
        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));//switch to new tab 1
//check is it correct page opened or not (check page's url and title)
        Assert.assertTrue(driver.getCurrentUrl().contains("https://demoqa.com/"), "Not Right url");
        Assert.assertTrue(driver.getTitle().equalsIgnoreCase("ToolsQA"), "Not Right title");
//then close tab and get back
        driver.close();
        driver.switchTo().window(browserTabs.get(0));
    }

    public void created()  {
        super.waiterClickable(created);
        created.click();
        Assert.assertTrue(createdResponse.isDisplayed(), "Link hasn't responded with status 201 and status text Created");
    }
    public void noContent()  {
        super.waiterClickable(noContent);
        noContent.click();
        Assert.assertTrue(noContentResponse.isDisplayed(), "Link hasn't responded with status 204 and status text No Content");
    }
    public void notFound()  {
        super.focusOnElement(notFound);
        notFound.click();
        Assert.assertTrue(notFoundResponse.isEnabled(), "Link hasn't responded with status 404 and status text Not Found");
    }



    public boolean isPageOpened(){
        return heading.getText().contains("Links");
    }
}
