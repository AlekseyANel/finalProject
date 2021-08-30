package business.layer.com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.ConfigFileReader;

import java.util.ArrayList;
import java.util.List;

public class LinksPage {
    WebDriver driver;
//    WaiterForElement waiter = new WaiterForElement(driver);//пробовал вейтер
    ConfigFileReader configFileReader =  new ConfigFileReader();
    String pageUrl = configFileReader.getUrlDemoQA() +"/links";

    @FindBy(xpath = "//div[contains(text(),'Links')]")
    WebElement heading;

    @FindBy(xpath = "//*[@id='simpleLink']")
    WebElement newTab;

    @FindBy(xpath = "//*[@id='created']")
    WebElement created;


    public LinksPage(WebDriver driver) {
        this.driver = driver;
        System.out.println("Чекнуть путь к буук странице: " +pageUrl);
        driver.get(pageUrl);
        PageFactory.initElements(driver, this);
    }

    public void newTab()  {
        newTab.click();
        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));//switch to new tab 1
//check is it correct page opened or not (check page's title)
        Assert.assertTrue(driver.getTitle().equalsIgnoreCase("ToolsQA"));
//then close tab and get back
        driver.close();
        driver.switchTo().window(browserTabs.get(0));
    }
    public void created()  {
        created.click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='linkResponse']/b[contains(text(),'201')]")).isEnabled());
    }

    public boolean isPageOpened(){
        return heading.getText().contains("Links");

    }
}
